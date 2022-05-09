import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String args[]) throws JMSException {
        
        // create connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // create connection and start it
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // create session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create topic 
        Topic topic = session.createTopic("Practical exam");

        // create producer
        MessageProducer producer = session.createProducer(topic);

        // create and send message
        TextMessage message = session.createTextMessage();
        message.setText("Hello");

        producer.send(message);

        System.out.println("Sent message " + message.getText() + " ");

        // close connection
        connection.close();
    }
}