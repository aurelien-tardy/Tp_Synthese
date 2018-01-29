/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producteur;

import dal.Auteur;
import java.util.Date;
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
    
    public void publish(String title, Date date, Auteur auteur) {
        try {
            connection = fabriqueConnexionJMS.createConnection();
            session = connection.createSession(false, 0);
            producteur = session.createProducer(articles);
            mapMessage = session.createMapMessage();

            mapMessage.setString("titreArticle", title);
            mapMessage.setString("date", date.toString());
            mapMessage.setInt("idAuteur", auteur.getIdAuteur());
            mapMessage.setString("identiteAuteur", auteur.getIdentiteAuteur());
            producteur.send(mapMessage);
            

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
