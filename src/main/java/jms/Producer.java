package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 2017/8/8.
 */
public class Producer {
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
        //创建生产者
        MessageProducer producer = session.createProducer(destination);
        //发送消息
        for (int i=0;i<100;i++){
            //创建消息
            TextMessage textMessage = session.createTextMessage("test queue message"+ i);
            //发送消息
            producer.send(textMessage);
            //log
            System.out.println("发送消息 ：" + textMessage.getText());
        }
        //关闭连接
        connection.close();

    }
}
