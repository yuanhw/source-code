package org.scode.client;


import org.scode.sioc.context.AnnotationConfigApplicationContext;
import org.scode.sioc.context.ApplicationContext;

/**
 * @author wyh
 * @date 2019/3/17 12:38 PM
 */
public class ClientApplication {
    public static void main(String[] args) {
        String basePackage = "org.scode.client";
        ApplicationContext context = new AnnotationConfigApplicationContext(basePackage);
        System.out.println(context.getBean("userController"));
        System.out.println(context.getBean("orderController"));
        System.out.println(context.getBean("otherController"));

    }
}
