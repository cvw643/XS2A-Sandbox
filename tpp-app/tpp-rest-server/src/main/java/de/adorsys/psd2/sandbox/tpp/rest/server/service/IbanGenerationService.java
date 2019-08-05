package de.adorsys.psd2.sandbox.tpp.rest.server.service;

import de.adorsys.ledgers.middleware.api.domain.um.AccountAccessTO;
import de.adorsys.ledgers.middleware.api.domain.um.UserTO;
import de.adorsys.ledgers.middleware.client.rest.UserMgmtRestClient;
import de.adorsys.psd2.sandbox.tpp.rest.server.exception.TppException;
import de.adorsys.psd2.sandbox.tpp.rest.server.model.DataPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static de.adorsys.psd2.sandbox.tpp.rest.server.utils.IbanGenerator.generateIban;

@Service
@RequiredArgsConstructor
public class IbanGenerationService {
    private static final String MSG_NO_IBAN_AVAILABLE = "Could not generate new IBAN, seems you used out all possible combinations";
    private static final String MSG_USER_NOT_FOUND = "User Not Found";

    @Value("${iban.generator.countryCode: DE}")
    private String COUNTRY_CODE_PREFIX;
    @Value("${iban.generator.bankCode.random: 76050101}")
    private String BANK_CODE_FOR_RANDOM;
    @Value("${iban.generator.bankCode.nisp: 76070024}")
    private String BANK_CODE_NISP;

    private final UserMgmtRestClient userMgmtRestClient;

    public String generateRandomIban() {
        UserTO user = userMgmtRestClient.getUser()
                          .getBody();
        List<AccountAccessTO> access = Optional.ofNullable(user)
                                           .map(UserTO::getAccountAccesses)
                                           .orElseThrow(() -> new TppException(MSG_USER_NOT_FOUND, 404));
        return getNextFreeIban(access, user.getBranch());
    }

    public String generateIbanForNisp(DataPayload payload, String iban, String branch) {
        if (payload.getGeneratedIbans().containsKey(iban)) {
            return payload.getGeneratedIbans().get(iban);
        }
        String generatedIban = generateIban(COUNTRY_CODE_PREFIX, branch, BANK_CODE_FOR_RANDOM, iban);
        payload.getGeneratedIbans().put(iban, generatedIban);
        return generatedIban;
    }

    private String getNextFreeIban(List<AccountAccessTO> access, String branch) {
        return IntStream.range(0, 100)
                   .mapToObj(i -> generateIban(COUNTRY_CODE_PREFIX, branch, BANK_CODE_FOR_RANDOM, String.format("%02d", i)))
                   .filter(iban -> isNotContainingIban(access, iban))
                   .findFirst()
                   .orElseThrow(() -> new TppException(MSG_NO_IBAN_AVAILABLE, 400));
    }

    private boolean isNotContainingIban(List<AccountAccessTO> access, String iban) {
        return access.stream()
                   .noneMatch(a -> a.getIban().equals(iban));
    }
}
