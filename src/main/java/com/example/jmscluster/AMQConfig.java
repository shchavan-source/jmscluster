package com.example.jmscluster;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class AMQConfig {

    @Bean("jmsListenerContainerFactory")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws Exception {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("10-30");
        factory.setTaskExecutor(taskExecutor());
        factory.setPubSubDomain(false);
        return factory;
    }

    private ActiveMQConnectionFactory connectionFactory() {
        String url = "(tcp://127.0.0.1:61616,tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?useTopologyForLoadBalancing=false";
        String username = "admin";
        String passwd = "admin";

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url, username, passwd);
        return connectionFactory;
    }

    @Bean
    TaskExecutor taskExecutor() throws Exception {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(30);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setThreadNamePrefix("GW_IPE2-ID-AMQ-Listener-");
        taskExecutor.setThreadGroupName("GW_IPE2-ID-AMQ-Listener-Group");
        return taskExecutor;
    }

}
