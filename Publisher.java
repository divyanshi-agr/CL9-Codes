import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String []args) throws JMSException {

        //Create Connection Factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //Create New Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //Create Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        //Create Topic
        Topic topic = session.createTopic("Practical Exam");
        
        //Create Producer
        MessageProducer producer = session.createProducer(topic);
        
        //Create and Send Message 
        TextMessage message = session.createTextMessage();
        message.setText("Hello!");

        producer.send(message);

        System.out.println("Sent message '" + message.getText() + "'");

        //Close the connection
        connection.close();
        


    }

}