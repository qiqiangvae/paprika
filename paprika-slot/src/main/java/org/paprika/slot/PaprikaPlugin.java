package org.paprika.slot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author qiqiang
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PaprikaPlugin {
    String value() default "";
}