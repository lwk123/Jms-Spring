package jmsSpring.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kevin on 2017/8/12.
 */
public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Producer.xml");
        ProducerService producerService = context.getBean(ProducerService.class);
        for (int i=0;i<100;i++){
            producerService.sendMessage("test"+i);
        }
        context.close();
    }
}

