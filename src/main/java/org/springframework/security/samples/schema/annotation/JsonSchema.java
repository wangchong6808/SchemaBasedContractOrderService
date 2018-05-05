package org.springframework.security.samples.schema.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME) @Target(METHOD)
public @interface JsonSchema {

    String ns() default "/json/schema";

    String requestSchema() default "";

    String responseSchema() default "";

}
