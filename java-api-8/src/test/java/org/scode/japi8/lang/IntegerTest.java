package org.scode.japi8.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wyh
 * @date 2019/3/16 1:25 PM
 */
public class IntegerTest {
    @Test
    public void testInteger() {
        String a = "abcd";
        int b = 16;
        Assert.assertEquals(java.lang.Integer.parseInt(a + "", b), Integer.parseInt(a + "", b));
    }
}
