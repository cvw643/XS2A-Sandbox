package de.adorsys.psd2.sandbox.tpp.rest.server.controller;

import de.adorsys.ledgers.middleware.api.domain.account.AccountDetailsTO;
import de.adorsys.psd2.sandbox.tpp.rest.api.resource.TppAccountsRestApi;
import de.adorsys.psd2.sandbox.tpp.rest.api.resource.TppRestApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(TppRestApi.BASE_PATH)
public class TppAccountsController implements TppAccountsRestApi {
    @Override
    public void create(String userId, AccountDetailsTO account) {

    }

    @Override
    public ResponseEntity<List<AccountDetailsTO>> getAll() {
        return null;
    }
}
