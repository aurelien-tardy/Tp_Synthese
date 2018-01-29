/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producteur;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author Epulapp
 */
public class Emetteur {
    @Resource(mappedName = "FabriqueArticles")
    private static ConnectionFactory fabriqueConnexionJMS;

    @Resource(mappedName = "jms/Articles")
    private static Queue articles;

    private static Connection connection = null;
    private static Session session = null;

    private static MessageProducer producteur = null;

    private static MapMessage mapMessage = null;
    
    public static void main(String[] args) {
        try {
            connection = fabriqueConnexionJMS.createConnection();
            session = connection.createSession(false, 0);
            producteur = session.createProducer(articles);
            mapMessage = session.createMapMessage();

            System.out.println("Entrer l'id de l'etudiant (Vide pour arrêter) : ");
            String id = Saisie.lectureChaine();
            while (id.compareTo("") > 0) {
                mapMessage.setInt("id", Integer.parseInt(id));
                System.out.println("Entrer le prénom (Vide pour arrêter) : ");
                String prenom = Saisie.lectureChaine();
                mapMessage.setString("prenom", prenom);
                System.out.println("Entrer le nom : ");
                String nom = Saisie.lectureChaine();
                mapMessage.setString("nom", nom);
                System.out.println("Entrer le numéro de département");
                int dep = Integer.parseInt(Saisie.lectureChaine());
                mapMessage.setInt("dep", dep);
                producteur.send(mapMessage);
                System.out.println("Entrer l'id de l'etudiant (Vide pour arrêter) : ");
                id = Saisie.lectureChaine();
                
            }

        } catch (JMSException e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {

                }
            }
        }

    }
}
