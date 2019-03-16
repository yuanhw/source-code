package org.scode.japi8.lang;

/**
 * @author wyh
 * @date 2019/3/16 11:50 AM
 */
public class Integer extends Number implements Comparable<Integer> {

    public final static int MAX_VALUE = 0x7fffffff;

    public final static int MIN_VALUE = 0x80000000;

    private static final long serialVersionUID = 6320425042037608746L;

    private final int value;

    public Integer(int value) {
        this.value = value;
    }

    public Integer(String value) {
        this.value = parseInt(value, 10);

    }

    public static int parseInt(String value, int radix) throws NumberFormatException{
        if (value == null || "".equals(value)) {
            throw new NumberFormatException("input " + value);
        }

        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new NumberFormatException("input " + value);
        }

        boolean isNegative = false;
        int index = 0;
        int len = value.length();
        int limit = -MAX_VALUE;
        char firstChar = value.charAt(0);

        if (firstChar < '0') {
            if (firstChar == '-') {
                isNegative = true;
                limit = MIN_VALUE;
            } else if (firstChar != '+') {
                throw new NumberFormatException("input " + value);
            }

            if (len == 1) {
                throw new NumberFormatException("input " + value);
            }
            index++;
        }

        int result = 0;
        while (index < len) {
            int digit = Character.digit(value.charAt(index++), radix);
            if (digit < 0) {
                throw new NumberFormatException("input " + value);
            }

            if (result < limit / radix) {
                throw new NumberFormatException("too great");
            }
            result *= radix;

            if (result < limit + digit) {
                throw new NumberFormatException("too great");
            }
            result -= digit;
        }

        return isNegative ? result : -result;
    }

    @Override
    public byte byteValue() {
        return (byte) this.value;
    }

    @Override
    public short shortValue() {
        return (short) this.value;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public int compareTo(Integer o) {
        return this.value - o.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Integer) {
            return this.value == ((Integer) o).intValue();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    public static Integer valueOf(int value) {
        if (value >= IntegerCache.low && value <= IntegerCache.high) {
            return IntegerCache.caches[value - IntegerCache.low];
        } else {
            return new Integer(value);
        }
    }

    private static class IntegerCache {

        static int low = -128;
        static int high = 127;
        static Integer[] caches;

        static {
            int size = high - low + 1;
            caches = new Integer[size];
            int k = low;
            for (int i = 0; i < caches.length; i++) {
                caches[i] = new Integer(k++);
            }
        }

    }

    public static String toBinaryString(int i) {
        return toUnsignedString0(i, 1);
    }

    private static String toUnsignedString0(int value, int shift) {
        int zeroLeft = getZeroLeft(value);
        int zero = 32 - zeroLeft;
        int size = (zero + (shift - 1)) / shift;
        char[] chars = new char[size];
        int len = size;
        int ra = (1 << shift) - 1;
        do {
            chars[--len] = getCharFormInt(value & ra);
            value >>>= shift;
        } while (len > 0 && value > 0);
        return new String(chars);
    }

    static char[] d0 = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    private static char getCharFormInt(int v2) {
        return d0[v2];
    }

    private static int getZeroLeft(int value) {
        if (value == 0) {
            return 32;
        }
        int count = 1;
        if (value >>> 16 == 0) {
            value <<= 16;
            count += 16;
        }
        if (value >>> 24 == 0) {
            value <<= 8;
            count += 8;
        }
        if (value >>> 28 == 0) {
            value <<= 4;
            count += 4;
        }
        if (value >>> 30 == 0) {
            value <<= 2;
            count += 2;
        }

        count -= value >>> 31;
        return count;
    }

    public static String toHexString(int i) {
        return toUnsignedString0(i, 4);
    }

    @Override
    public String toString() {
        return "Integer{" +
                "value=" + value +
                '}';
    }
}
