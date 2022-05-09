import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String args[]) {
        // create connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // create connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // create session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create topic
        Topic topic = new session.createTopic("Practical Exam");

        MessageConsumer consumer = session.createConsumer(topic);

        MessageListener listener = new MessageListener() {
            
        }
    }
}