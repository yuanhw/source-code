package org.scode.sioc.annotation;

import java.lang.annotation.*;

/**
 * @author wyh
 * @date 2019/3/17 2:27 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface RestController {
    String value() default "";
}
