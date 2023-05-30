package com.pensatocode.example.filters;

import com.pensatocode.example.api.Country;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CountryAllowed {
    Country[] values() default {};
}
