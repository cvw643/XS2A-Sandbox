/*
 * Copyright 2018-2018 adorsys GmbH & Co KG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * NOTE: This class is auto generated by the swagger code generator program (unset).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package de.adorsys.ledgers.xs2a.client;

import de.adorsys.ledgers.xs2a.client.util.RemoteURLs;
import de.adorsys.psd2.model.*;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.UUID;

@FeignClient(value = "fundsConfirmationApiClient", url = RemoteURLs.XS2A_URL, path = "/", configuration = FeignConfig.class)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-11T12:48:04.675377+02:00[Europe/Kiev]")
@Api(value = "v1", description = "funds confirmation API")
public interface FundsConfirmationApiClient {

    // TODO: fix this after https://git.adorsys.de/adorsys/xs2a/aspsp-xs2a/issues/648 addressed
    @ApiOperation(value = "Confirmation of Funds Request", nickname = "checkAvailabilityOfFunds", notes = "Creates a confirmation of funds request at the ASPSP. Checks whether a specific amount is available at point of time of the request on an account linked to a given tuple card issuer(TPP)/card number, or addressed by IBAN and TPP respectively", response = InlineResponse200.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = InlineResponse200.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error400NGAIS.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error401NGPIIS.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error403NGPIIS.class),
            @ApiResponse(code = 404, message = "Not found", response = Error404NGPIIS.class),
            @ApiResponse(code = 405, message = "Method Not Allowed", response = Error405NGPIIS.class),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 408, message = "Request Timeout"),
            @ApiResponse(code = 415, message = "Unsupported Media Type"),
            @ApiResponse(code = 429, message = "Too Many Requests"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/v1/funds-confirmations",
            produces = {"application/json", "application/problem+json"},
            method = RequestMethod.POST)
    ResponseEntity<FundsConfirmationResponse> _checkAvailabilityOfFunds(@ApiParam(value = "Request body for a confirmation of funds request. ", required = true) @Valid @RequestBody ConfirmationOfFunds body, @ApiParam(value = "ID of the request, unique to the call, as determined by the initiating party.", required = true) @RequestHeader(value = "X-Request-ID", required = true) UUID xRequestID, @ApiParam(value = "Is contained if and only if the \"Signature\" element is contained in the header of the request.") @RequestHeader(value = "Digest", required = false) String digest, @ApiParam(value = "A signature of the request by the TPP on application level. This might be mandated by ASPSP. ") @RequestHeader(value = "Signature", required = false) String signature, @ApiParam(value = "The certificate used for signing the request, in base64 encoding.  Must be contained if a signature is contained. ") @RequestHeader(value = "TPP-Signature-Certificate", required = false) byte[] tpPSignatureCertificate);
}

