package org.example.a05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

public class A05 {

    private static final Logger log = LoggerFactory.getLogger(A05.class);

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

//        context.registerBean("beanPostProcessor", ConfigurationClassPostProcessor.class);
        context.registerBean("beanPostProcessor", AtBeanPostProcessor.class);
        context.refresh();

        System.out.println("---");
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println("---");
        context.close();
    }

}
