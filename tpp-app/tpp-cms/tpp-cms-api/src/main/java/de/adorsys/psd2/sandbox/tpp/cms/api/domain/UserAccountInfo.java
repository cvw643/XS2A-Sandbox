package de.adorsys.psd2.sandbox.tpp.cms.api.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccountInfo {
    private String resourceId;
    private String aspspAccountId;
    private String accountIdentifier;
    private String currency;
    private UserAccountReferenceType accountType;
}
