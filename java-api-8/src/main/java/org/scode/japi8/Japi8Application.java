package org.scode.japi8;

import lombok.extern.slf4j.Slf4j;
import org.scode.japi8.lang.Integer;


/**
 * @author wyh
 * @date 2019/3/16 11:34 AM
 */
@Slf4j
public class Japi8Application {
    public static void main(String[] args) {
        log.info("comm begin...");
        test1();
    }

    private static void test1() {
        System.out.println(Integer.toHexString(Integer.MAX_VALUE));
    }
}
