package org.scode.japi8.lang;

import java.io.Serializable;

/**
 * @author wyh
 * @date 2019/3/16 11:47 AM
 */
public abstract class Number implements Serializable {

    private static final long serialVersionUID = -8423595837998141365L;

    public abstract byte byteValue();

    public abstract short shortValue();

    public abstract int intValue();

    public abstract long longValue();

    public abstract float floatValue();

    public abstract double doubleValue();

}
