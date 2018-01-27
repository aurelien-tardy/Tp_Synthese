/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Achete;
import dal.Article;
import dal.Client;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.AcheteFacade;
import session.ArticleFacade;
import session.ClientFacade;
import session.CompteFacade;
import session.DomaineFacade;
import session.UtilsFacade;

/**
 *
 * @author Epulapp
 */
public class CommandeServlet extends HttpServlet {

    private String erreur = "";

    private DomaineFacade domaineFacade = new DomaineFacade();

    private ArticleFacade articleFacade = new ArticleFacade();

    private AcheteFacade acheteFacade = new AcheteFacade();

    private CompteFacade compteFacade = new CompteFacade();

    private ClientFacade clientFacade = new ClientFacade();

    private UtilsFacade utilsFacade = new UtilsFacade();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String demande;
        // Si aucune demande n'est satisfaite, c'est la vue index.jsp
        // qui sera affichée
        String vueReponse = "/index.jsp";
        erreur = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("voirPanier.cde")) {
                vueReponse = "/panier.jsp";
            } else if (demande.equalsIgnoreCase("ajoutPanier.cde")) {
                vueReponse = ajoutPanier(request);
            } else if (demande.equalsIgnoreCase("supprimerPanier.cde")) {
                vueReponse = supprimerPanier(request);
            } else if (demande.equalsIgnoreCase("listeAchats.cde")) {
                vueReponse = mesArticles(request);
            } else if (demande.equalsIgnoreCase("confirmationPaiement.cde")) {
                vueReponse = confirmationPaiement(request);
            } else if (demande.equalsIgnoreCase("keyConfirmation.cde")) {
                vueReponse = confirmationKey(request);
            }

        } catch (Exception e) {
            erreur = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            // Par défaut la page à afficher est index.jsp
            // sauf s'il faut rediriger vers une fonction
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".na")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }
    }

    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

    private String ajoutPanier(HttpServletRequest request) throws Exception {
        String vueReponse;
        try {
            HttpSession session = request.getSession();
            List<Article> panier = null;
            if (session.getAttribute("panier") == null) {
                panier = new ArrayList<>();
            } else {
                panier = (ArrayList<Article>) session.getAttribute("panier");
            }

            Article article = articleFacade.getArticleById(request.getParameter("id_article"));
            if (panier.size() > 0) {
                boolean add = true;
                for (Article art : panier) {
                    if (article.equals(art)) {
                        add = false;
                    }
                }

                if (add) {
                    panier.add(article);
                    vueReponse = "/panier.jsp";
                } else {
                    erreur = "L'article est déjà présent dans le panier";
                    vueReponse = "/listeArticles.jsp";
                    request.setAttribute("id_domaineR", article.getIdArticle());
                    request.setAttribute("lArticlesR", articleFacade.getArticlesByField(Integer.toString(article.getIdArticle())));
                    request.setAttribute("lDomainesR", domaineFacade.getFields());
                }
            } else {
                panier.add(article);
                vueReponse = "/panier.jsp";
            }

            session.setAttribute("panier", panier);
            session.setAttribute("montantTotalR", montantTotal(panier));

            return vueReponse;
        } catch (Exception e) {
            throw e;
        }
    }

    private String validerPanier(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("panier") != null && session.getAttribute("clientId") != null) {
            try {
                Integer idClient = (int) session.getAttribute("clientId");
                List<Article> panier = (ArrayList<Article>) session.getAttribute("panier");
                if ((Integer) session.getAttribute("montantTotalR") <= compteFacade.getSoldeById(idClient)) {
                    for (Article article : panier) {
                        Achete achat = new Achete(idClient, article.getIdArticle());
                        acheteFacade.validerPanier(achat);
                    }
                    Client client = clientFacade.getClientById(idClient);
                    compteFacade.editSolde(client.getCredits() - (Integer) session.getAttribute("montantTotalR"), idClient);

                    client.setCredits(client.getCredits() - (Integer) session.getAttribute("montantTotalR"));
                    clientFacade.editAccount(client);
                    session.removeAttribute("panier");
                    session.removeAttribute("montantTotalR");

                    List<Achete> lAchete = acheteFacade.getListAcheteByIdClient((Integer) request.getSession().getAttribute("clientId"));
                    request.setAttribute("lAchetesR", lAchete);

                    return "/listeAchats.jsp";
                } else {
                    erreur = "Solde insuffisant";
                    return "/panier.jsp";
                }

            } catch (Exception e) {
                throw e;
            }
        } else {
            erreur = "Veuillez vous connecter pour valider votre panier";
            return "/login.jsp";
        }
    }

    private String supprimerPanier(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<Article> panier = (ArrayList<Article>) session.getAttribute("panier");
        Article article = articleFacade.getArticleById(request.getParameter("id_article"));
        int i = 0;
        for (Article art : panier) {
            if (article.equals(art)) {
                panier.remove(i);
                break;
            } else {
                i++;
            }
        }

        if (panier.size() > 0) {
            session.setAttribute("panier", panier);
        } else {
            session.removeAttribute("panier");
            List<Achete> lAchete = acheteFacade.getListAcheteByIdClient((Integer) request.getSession().getAttribute("clientId"));
            request.setAttribute("lAchetesR", lAchete);
            return "/listeAchats.jsp";
        }

        session.setAttribute("montantTotalR", montantTotal(panier));

        return "/panier.jsp";
    }

    private String mesArticles(HttpServletRequest request) throws Exception {
        List<Achete> lAchete = acheteFacade.getListAcheteByIdClient((Integer) request.getSession().getAttribute("clientId"));
        request.setAttribute("lAchetesR", lAchete);
        return "/listeAchats.jsp";
    }

    private int montantTotal(List<Article> panier) {
        int total = 0;
        for (Article art : panier) {
            total += art.getPrix().intValue();
        }
        return total;
    }

    /**
     *
     * @param request
     * @return String
     */
    private String confirmationPaiement(HttpServletRequest request) {
        try {
            String email = (String) request.getParameter("adresseEmail");
            String key = utilsFacade.getKey(email);
            if (key == null) {
                throw new Exception("Erreur lors de la génération de la clé");
            }
            request.getSession().setAttribute("trueKey", key);
            request.getSession().setAttribute("essai", 3);
        } catch (Exception ex) {
            Logger.getLogger(CommandeServlet.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getMessage().contains("Erreur lors de la génération de la clé")) {
                erreur = ex.getMessage();
            }
            return "/panier.jsp";
        }
        return "confirmationKey.jsp";
    }

    /**
     *
     * @param request
     * @return String
     */
    private String confirmationKey(HttpServletRequest request) throws Exception {
        String key = (String) request.getSession().getAttribute("trueKey");
        String userKey = (String) request.getParameter("key");
        if (getEncryptedKey(userKey).equals(key)) {
            request.getSession().removeAttribute("essai");
            return validerPanier(request);
        } else if ((Integer) request.getSession().getAttribute("essai") == 0) {
            erreur = "Trop d'essais invalide !";
            request.getSession().removeAttribute("essai");
            return "/panier.jsp";
        } else {
            request.getSession().setAttribute("essai", (Integer) request.getSession().getAttribute("essai") - 1);
            request.setAttribute("trueKey", key);
            erreur = "Code de confirmation incorrect";
            return "confirmationKey.jsp";
        }

    }

    /**
     * Renvoie un hash de la chaine en MD5
     *
     * @param key
     * @return String
     */
    private String getEncryptedKey(String key) {
        String encryptedKey = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(key.getBytes());
            String encryptedString = new String(messageDigest.digest());
            encryptedKey = encryptedString;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ex.getStackTrace().toString());
        } finally {
            return encryptedKey;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
