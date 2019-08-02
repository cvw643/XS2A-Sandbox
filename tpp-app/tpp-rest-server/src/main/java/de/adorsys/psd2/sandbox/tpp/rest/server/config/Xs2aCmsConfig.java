package de.adorsys.psd2.sandbox.tpp.rest.server.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan({"de.adorsys.psd2.consent.domain", "de.adorsys.psd2.event.persist.entity"})
@EnableJpaRepositories(basePackages = {"de.adorsys.psd2.consent.repository", "de.adorsys.psd2.event.persist"})
@ComponentScan(basePackages = {"de.adorsys.psd2.consent", "de.adorsys.psd2.aspsp", "de.adorsys.psd2.event"})
@EnableTransactionManagement
public class Xs2aCmsConfig {
}
