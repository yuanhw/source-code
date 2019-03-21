package org.scode.client;


/**
 * @author wyh
 * @date 2019/3/17 12:38 PM
 */
public class ClientApplication {
    public static void main(String[] args) {
//        String basePackage = "org.scode.client";
//        ApplicationContext context = new AnnotationConfigApplicationContext(basePackage);
//        System.out.println(context.getBean("userController"));
//        System.out.println(context.getBean("orderController"));
//        System.out.println(context.getBean("otherController"));

        testOne(100);
    }

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
