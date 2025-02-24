package de.adorsys.ledgers.oba.rest.api.resource.oba;

import de.adorsys.ledgers.oba.rest.api.domain.ObaAisConsent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Api(value = ObaConsentApi.BASE_PATH, tags = "Online Banking Consents")
public interface ObaConsentApi {
    String BASE_PATH = "/api/v1/consents";

    /**
     * @param userLogin login of current user
     * @return List of valid AIS Consents for user
     */
    @GetMapping(path = "/{userLogin}")
    @ApiOperation(value = "Get List of valid AIS Consents", authorizations = @Authorization(value = "apiKey"))
    ResponseEntity<List<ObaAisConsent>> consents(@PathVariable("userLogin") String userLogin);

    /**
     * @param consentId identifier of consent
     */
    @PutMapping(path = "/{consentId}")
    @ApiOperation(value = "Revoke consent by ID", authorizations = @Authorization(value = "apiKey"))
    ResponseEntity<Boolean> revokeConsent(@PathVariable String consentId);

    /**
     * @param consentId       decrypted consent id
     * @param authorizationId authorization id
     * @param tan             TAN for single operation
     * @return 200 OK if operation was successful, or an error with msg on the failure reason
     */
    @GetMapping(path = "/confirm/{userLogin}/{consentId}/{authorizationId}/{tan}")
    @ApiOperation(value = "Confirm AIS Consent for Decoupled Approach")
    ResponseEntity<Void> confirm(@PathVariable("userLogin") String userLogin,
                                 @PathVariable("consentId") String consentId,
                                 @PathVariable("authorizationId") String authorizationId,
                                 @PathVariable("tan") String tan);
}
