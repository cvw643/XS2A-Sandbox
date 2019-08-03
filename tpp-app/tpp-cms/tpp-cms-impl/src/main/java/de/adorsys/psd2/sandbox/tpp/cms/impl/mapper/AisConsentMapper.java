package de.adorsys.psd2.sandbox.tpp.cms.impl.mapper;

import de.adorsys.psd2.consent.api.AccountInfo;
import de.adorsys.psd2.consent.api.ais.AisAccountAccessInfo;
import de.adorsys.psd2.consent.api.ais.CreateAisConsentRequest;
import de.adorsys.psd2.sandbox.tpp.cms.api.domain.AccountAccessInfo;
import de.adorsys.psd2.sandbox.tpp.cms.api.domain.AisConsent;
import de.adorsys.psd2.sandbox.tpp.cms.api.domain.PsuInfo;
import de.adorsys.psd2.sandbox.tpp.cms.api.domain.UserAccountInfo;
import de.adorsys.psd2.xs2a.core.profile.AccountReferenceType;
import de.adorsys.psd2.xs2a.core.psu.PsuIdData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AisConsentMapper {
    @Mapping(target = "psuData", source = "psuInfo")
    CreateAisConsentRequest toCmsAisConsentRequest(AisConsent aisConsent);

    AisAccountAccessInfo toAisAccountAccessInfo(AccountAccessInfo info);

    default AccountInfo toAccountInfo(UserAccountInfo userAccountInfo) {
        return AccountInfo.builder()
                   .resourceId(userAccountInfo.getResourceId())
                   .aspspAccountId(userAccountInfo.getAspspAccountId())
                   .accountIdentifier(userAccountInfo.getAccountIdentifier())
                   .accountReferenceType(AccountReferenceType.valueOf(userAccountInfo.getAccountType().name()))
                   .currency(userAccountInfo.getCurrency())
                   .build();
    }

    default PsuIdData toPsuIdData(PsuInfo psuInfo){
        return new PsuIdData(psuInfo.getPsuId(),psuInfo.getPsuIdType(),psuInfo.getPsuCorporateId(),psuInfo.getPsuCorporateIdType());
    }
}
