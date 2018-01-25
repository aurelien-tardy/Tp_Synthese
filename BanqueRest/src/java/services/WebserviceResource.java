/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dal.Compte;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.taglibs.standard.functions.Functions;
import outils.Utilitaire;
import session.CompteFacade;

/**
 * REST Web Service
 *
 * @author Epulapp
 */
@Path("webservice")
public class WebserviceResource {

    @EJB
    private CompteFacade compteFacade;

    /**
     * Creates a new instance of WebserviceResource
     */
    public WebserviceResource() {
    }

    @POST
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(Compte compte) throws Exception {
        Response response = null;
        try {
            compteFacade.createAccount(compte);
            response = Response.status(Response.Status.OK).entity(compte).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @GET
    @Path("getSoldeById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSoldeById(@PathParam("id") Integer id) throws Exception {
        Response response = null;
        try {
            Compte compte = compteFacade.getSoldeById(id);
            response = Response.status(Response.Status.OK).entity(compte).build();
        } catch (Exception ex) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(ex)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }

    @POST
    @Path("editSolde")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editSolde(Compte compte) throws Exception {
        try {
            compteFacade.editSolde(compte);
        } catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("sendPaiementEmail/{mail}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendPaiementEMail(@PathParam("mail") String mail) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("confirmation.net.article@gmail.com", "polytech01"));
            email.setSSLOnConnect(true);
            email.setFrom("confirmation.net.article@gmail.com");
            email.setSubject("Code de confirmation pour paiement");
            String generatedKey = generateKey();
            email.setMsg("Voici votre code de confirmation de paiement sur le site Net Article: " + generatedKey);
            email.addTo(mail);
            email.send();
            return generatedKey;
        } catch (EmailException ex) {
            Logger.getLogger(WebserviceResource.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    public String generateKey() {
        String key = Functions.substring(UUID.randomUUID().toString().replace("-", ""), 0, 8);
        return key;
    }
}
