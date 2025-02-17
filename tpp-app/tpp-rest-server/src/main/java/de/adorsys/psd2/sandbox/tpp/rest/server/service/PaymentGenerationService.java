package de.adorsys.psd2.sandbox.tpp.rest.server.service;

import de.adorsys.ledgers.middleware.api.domain.account.AccountReferenceTO;
import de.adorsys.ledgers.middleware.api.domain.general.AddressTO;
import de.adorsys.ledgers.middleware.api.domain.payment.*;
import de.adorsys.psd2.sandbox.tpp.rest.server.model.AccountBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static de.adorsys.ledgers.middleware.api.domain.payment.PaymentTypeTO.BULK;
import static de.adorsys.ledgers.middleware.api.domain.payment.PaymentTypeTO.SINGLE;

@Service
@RequiredArgsConstructor
public class PaymentGenerationService {
    private static final String TEST_CREDITOR_IBAN = "DE68370400440000000000";
    private Random random = new Random();

    public Map<PaymentTypeTO, Object> generatePayments(AccountBalance balance, String branch) {
        EnumMap<PaymentTypeTO, Object> map = new EnumMap<>(PaymentTypeTO.class);
        map.put(SINGLE, generateSinglePayment(balance, branch));
        map.put(BULK, generateBulkPayment(balance, branch));
        return map;
    }

    private BulkPaymentTO generateBulkPayment(AccountBalance balance, String branch) {
        return new BulkPaymentTO(
            null,
            false,
            generateReference(balance.getIban(), balance.getCurrency()),
            LocalDate.now(),
            TransactionStatusTO.RCVD,
            Arrays.asList(generateSinglePayment(balance, branch), generateSinglePayment(balance, branch)),
            PaymentProductTO.INSTANT_SEPA
        );
    }

    private SinglePaymentTO generateSinglePayment(AccountBalance balance, String branch) {
        String endToEndId = generateEndToEndId(branch);
        return new SinglePaymentTO(
            null,
            endToEndId,
            generateReference(balance.getIban(), balance.getCurrency()),
            generateAmount(balance),
            generateReference(TEST_CREDITOR_IBAN, balance.getCurrency()),
            "adorsys GmbH & CO KG",
            "adorsys GmbH & CO KG",
            getTestCreditorAddress(),
            null,
            TransactionStatusTO.RCVD,
            PaymentProductTO.INSTANT_SEPA,
            LocalDate.now(),
            null
        );
    }

    private AmountTO generateAmount(AccountBalance balance) {
        AmountTO amount = new AmountTO();
        amount.setCurrency(balance.getCurrency());
        int balanceAmount = balance.getAmount().intValue();
        int maxAmount = balanceAmount * 100 / 3;
        int rand = random.nextInt(maxAmount - 1) + 1;
        amount.setAmount(BigDecimal.valueOf(rand / 100d));
        return amount;
    }

    private AddressTO getTestCreditorAddress() {
        return new AddressTO("Fürther Str.", "246a", "Nürnberg", "90429", "Germany");
    }

    private AccountReferenceTO generateReference(String iban, Currency currency) {
        AccountReferenceTO reference = new AccountReferenceTO();
        reference.setIban(iban);
        reference.setCurrency(currency);
        return reference;
    }

    private String generateEndToEndId(String branchId) {
        return String.join("_", branchId, String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) + random.nextInt(9), String.valueOf(ThreadLocalRandom.current().nextLong(10000, 99999)));
    }
}
