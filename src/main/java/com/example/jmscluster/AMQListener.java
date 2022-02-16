package com.example.jmscluster;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AMQListener {

    @JmsListener(id="acctPostReq1", destination = "TEST", containerFactory = "jmsListenerContainerFactory")
    public void acctPostReqListener(String req){
        System.out.println(req);
    }

    @JmsListener(id="test1", destination = "TEST", containerFactory = "jmsListenerContainerFactory")
    public void test1Listener(String req){
        System.out.println(req);
    }

    @JmsListener(id="test2", destination = "TEST", containerFactory = "jmsListenerContainerFactory")
    public void test2Listener(String req){
        System.out.println(req);
    }
}
