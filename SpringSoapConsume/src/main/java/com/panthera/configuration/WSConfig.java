/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panthera.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import com.panthera.service.StudentClient;

/**
 *
 * @author Administrator
 */
@Configuration
public class WSConfig extends WsConfigurerAdapter {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.panthera.wsdl");
        return marshaller;
    }

    @Bean
    public StudentClient studentClient(Jaxb2Marshaller marshaller) {
        StudentClient client = new StudentClient();
        client.setDefaultUri("http://localhost:8080/soapws/students.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
