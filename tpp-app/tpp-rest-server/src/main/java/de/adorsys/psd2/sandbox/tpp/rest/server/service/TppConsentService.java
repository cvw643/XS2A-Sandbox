package de.adorsys.psd2.sandbox.tpp.rest.server.service;

import de.adorsys.psd2.consent.service.AisConsentServiceInternal;
import de.adorsys.psd2.sandbox.tpp.rest.api.domain.AisConsent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TppConsentService {
    private final AisConsentServiceInternal aisConsentServiceInternal;

    public void generateConsents(List<AisConsent> consents) {
        log.info("Not implemented yet");
    }
}
