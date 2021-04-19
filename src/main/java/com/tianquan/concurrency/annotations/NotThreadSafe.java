package com.tianquan.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add [NotThreadSafe] class annotation
 */
//for NOT threadsafe class, add NotThreadsafe class to identify
@Target(ElementType.TYPE)
//define scope of the annotation
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {
    String value() default "";
}
