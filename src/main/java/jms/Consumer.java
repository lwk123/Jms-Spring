package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 2017/8/8.
 */
public class Consumer {
    private static final String url="tcp://192.168.30.42:61616";
    private static final String queueName="queue-test";

    public static void main(String[] args) throws JMSException {
        //创建连接工厂connectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //创建连接connection
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();;
        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Destination destination = session.createQueue(queueName);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //创建监听者
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                //接收消息
                TextMessage textMessage  =(TextMessage) message;
                try {
                    System.out.println("接收消息 ：" +textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //关闭连接
        //connection.close();

    }
}
