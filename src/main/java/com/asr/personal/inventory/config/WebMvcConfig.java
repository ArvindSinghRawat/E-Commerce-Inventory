package com.asr.personal.inventory.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Primary
@Configuration
@ConditionalOnClass(EnableWebFlux.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class WebMvcConfig extends WebFluxConfigurationSupport {

  @Override
  protected void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {

    configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
  }
}