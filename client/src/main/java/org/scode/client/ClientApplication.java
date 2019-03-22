package org.scode.client;


import org.scode.sioc.context.AnnotationConfigApplicationContext;
import org.scode.sioc.context.ApplicationContext;

import javax.servlet.ServletContainerInitializer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ServiceLoader;

/**
 * @author wyh
 * @date 2019/3/17 12:38 PM
 */
public class ClientApplication {
    public static void main(String[] args) throws Exception {
        //doTest0();
        //doTest1();
        doTest2();
    }

    private static void doTest2() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
    }

    private static void doTest0() {
        String basePackage = "org.scode.client";
        ApplicationContext context = new AnnotationConfigApplicationContext(basePackage);
        System.out.println(context.getBean("userController"));
        System.out.println(context.getBean("orderController"));
        System.out.println(context.getBean("otherController"));
    }

    private static void doTest1() {
        ServiceLoader<ServletContainerInitializer> loader = ServiceLoader.load(ServletContainerInitializer.class);
        loader.forEach(System.out::println);
    private static void testOne(int n) {
        boolean[] data = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            data[i] = true;
        }

        int a = (int) Math.ceil(Math.sqrt(n));
        for (int i = 2; i <= a; i++) {
            for (int j = 2; j <= n / i; j++) {
                data[i * j] = false;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (data[i]) {
                System.out.println(i);
            }
        }
    }
}

