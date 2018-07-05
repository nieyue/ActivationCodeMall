package com.nieyue.comments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

@Configuration
class MqReceiverConfigureRMI {
 
    @Value("${myPugin.jmx.rmi.hostname}")
    private String rmiHost;
 
    @Value("${myPugin.jmx.rmi.port}")
    private Integer rmiPort;
 
    @Bean
    public RmiRegistryFactoryBean rmiRegistry() {
        final RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
        rmiRegistryFactoryBean.setPort(rmiPort);
        rmiRegistryFactoryBean.setAlwaysCreate(true);
        return rmiRegistryFactoryBean;
    }
 
    @Bean
    @DependsOn("rmiRegistry")
    public ConnectorServerFactoryBean connectorServerFactoryBean() throws Exception {
        final ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setObjectName("connector:name=rmi");
        connectorServerFactoryBean.setServiceUrl(String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi", rmiHost, rmiPort, rmiHost, rmiPort));
        // connectorServerFactoryBean.setServiceUrl("service:jmx:rmi:///jndi/rmi://"+rmiHost+":"+rmiPort+"/jmxrmi");
        return connectorServerFactoryBean;
    }
}