/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.Client;
import dal.Compte;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.Utilitaire;
import session.ArticleFacade;
import session.CategorieFacade;
import session.ClientFacade;
import session.CompteFacade;

/**
 *
 * @author Epulapp
 */
public class UserServlet extends HttpServlet {

    private String erreurR = "";

    private final ClientFacade clientF = new ClientFacade();
    
    private final CategorieFacade categorieF = new CategorieFacade();
    
    private final ArticleFacade articleF = new ArticleFacade();
    
    private final CompteFacade compteF = new CompteFacade();

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
        erreurR = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("login.cpt")) {
                vueReponse = login(request);
            } else if (demande.equalsIgnoreCase("connecter.cpt")) {
                vueReponse = connecter(request);
            } else if (demande.equalsIgnoreCase("deconnecter.cpt")) {
                vueReponse = deconnecter(request);
            } else if (demande.equalsIgnoreCase("creerCompte.cpt") || demande.equalsIgnoreCase("voirCompte.cpt")) {
                vueReponse = createAccount(request);
            } else if (demande.equalsIgnoreCase("validerCompte.cpt")) {
                vueReponse = addCustomer(request);
            }

        } catch (Exception e) {
            erreurR = Utilitaire.getExceptionCause(e);
        } finally {
            request.setAttribute("erreurR", erreurR);
            request.setAttribute("pageR", vueReponse);
            // Par défaut la page à afficher est index.jsp
            // sauf s'il faut rediriger vers une fonction
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".cpt")) {
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

    private String login(HttpServletRequest request) throws Exception {
        String vueReponse;
        try {
            vueReponse = "/login.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Vérifie que l'utilisateur a saisi le bon login et mot de passe
     *
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String connecter(HttpServletRequest request) throws Exception {
        String login, pwd;
        String vueReponse = "/index.jsp";
        erreurR = "";
        try {
            login = request.getParameter("txtLogin");
            pwd = request.getParameter("txtPwd");
            Client client = clientF.lireLogin(login);
            if (client != null) {
                if (client.getPwdClient().equals(pwd)) {
                    request.setAttribute("articleR", articleF.getLastArticle());
                    vueReponse = "/accueil.jsp";
                    HttpSession session = request.getSession(true);
                    session.setAttribute("clientId", client.getIdClient());
                    request.setAttribute("clientR", client);
                    request.setAttribute("categorieR", client.getCategorie());
                } else {
                    erreurR = "Login ou mot de passe inconnus !";
                    vueReponse = "/login.jsp";
                }
            }
        } catch (Exception e) {
            erreurR = e.getMessage();
            vueReponse = "/login.jsp";
        } finally {
            return (vueReponse);
        }
    }

    private String deconnecter(HttpServletRequest request) throws Exception {
        String vueReponse;
        erreurR = "";
        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("clientId", null);
            vueReponse = "/login.jsp";
            return (vueReponse);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private String createAccount(HttpServletRequest request) throws Exception{
        try {
            request.setAttribute("listeCategoriesR", categorieF.getCategories());
            request.setAttribute("titre", "Création du compte");
            Integer idClient = (Integer) request.getSession().getAttribute("clientId");
            if (idClient != null) {
                request.setAttribute("clientR", clientF.getClientById(idClient));
            }            
            return "/client.jsp";
        } catch (Exception e) {
            throw e;
        }
    }
    
    private String addCustomer(HttpServletRequest request) throws Exception{
        try {
            Integer idClient = (Integer) request.getSession().getAttribute("clientId");
            Client client = null;
            if (idClient != null) {
                client = new Client(idClient);
            } else {
                client = clientF.getClientLastId();
            }
            client.setIdentiteClient(request.getParameter("txtIdentite"));
            System.out.println(request.getParameter("txtAdresse"));
            client.setAdresseClient(request.getParameter("txtAdresse"));
            client.setLoginClient(request.getParameter("txtLogin"));
            client.setPwdClient(request.getParameter("txtPwd"));
            System.out.println(request.getParameter("txtPwd"));
            client.setCredits(Integer.parseInt(request.getParameter("txtCredits")));
            System.out.println(request.getParameter("cbCategories"));
            client.setCategorie(categorieF.getCategoryById(Integer.parseInt(request.getParameter("cbCategories"))));
            
            if (idClient != null) {
                clientF.editAccount(client);
                compteF.editSolde(client.getCredits(), client.getIdClient());
            } else {
                clientF.createAccount(client);
                Compte compte = new Compte(client.getIdClient());
                compte.setSolde(client.getCredits());
                compteF.creatéAccount(compte);
                request.getSession().setAttribute("clientId", client.getIdClient());
            }
            
            request.setAttribute("articleR", articleF.getLastArticle());
            return "/accueil.jsp";
        } catch (Exception e) {
            request.setAttribute("listeCategoriesR", categorieF.getCategories());
            request.setAttribute("titre", "Création du compte");
            erreurR = e.getMessage();
            return "/client.jsp";
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
