package com.github.joelws.release.tracker.boot;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String... args) {
        Application application = new Application();
        application.run();
    }

    void run() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/beans.xml");
        JAXRSServerFactoryBean sf = (JAXRSServerFactoryBean) ctx.getBean("releaseTracker");
        sf.init();
    }
}
