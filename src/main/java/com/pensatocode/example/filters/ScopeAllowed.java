package com.pensatocode.example.filters;

import com.pensatocode.example.api.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScopeAllowed {
    Scope[] values() default {Scope.IN};
}
