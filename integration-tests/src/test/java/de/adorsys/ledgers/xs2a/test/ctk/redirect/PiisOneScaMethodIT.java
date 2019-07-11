package de.adorsys.ledgers.xs2a.test.ctk.redirect;

import de.adorsys.ledgers.oba.rest.api.domain.AuthorizeResponse;
import de.adorsys.ledgers.oba.rest.api.domain.PIISConsentCreateResponse;
import de.adorsys.ledgers.xs2a.client.FundsConfirmationResponse;
import de.adorsys.psd2.model.InlineResponse200;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class PiisOneScaMethodIT extends AbstractPiis {
    @Override
    protected String getPsuId() {
        return "anton.brueckner";
    }

    @Override
    protected String getIban() {
        return "DE80760700240271232400";
    }

    @Test
    public void test_cif() {
        // Login
        ResponseEntity<AuthorizeResponse> login = cifHelper.login();

        // AuthCode
        ResponseEntity<AuthorizeResponse> authCode = cifHelper.authCode(login);

        // create PIIS consent
        ResponseEntity<PIISConsentCreateResponse> createPiisConsent = cifHelper.createPiisConsent(authCode);

        // Availability of funds request
        ResponseEntity<InlineResponse200> response = cifHelper.confOfFund(authCode);

        // Assert body is not null
        Assert.assertNotNull(response.getBody());

        // Intentional not null
        Assert.assertNotNull(response.getBody().isFundsAvailable());
    }
}
