package de.adorsys.psd2.sandbox.tpp.rest.server.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(Xs2aCmsConfig.class)
public @interface EnableXs2aCms {
}
