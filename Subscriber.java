import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String []args) {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = new session.createTopic("Practical Exam");

        MessageConsumer consumer = session.createConsumer(topic);

        MessageListener listener = new MessageListener() {
            
            public void onMessage() {
                try {
                    
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received Message '" + textMessage.getText() + "'");
                    }
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        consumer.setMessageListener(listener);  
        try {
            System.in.read();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        connection.close();
    }

}