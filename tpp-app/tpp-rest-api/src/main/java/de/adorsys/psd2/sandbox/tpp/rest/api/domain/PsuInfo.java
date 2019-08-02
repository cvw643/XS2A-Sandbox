package de.adorsys.psd2.sandbox.tpp.rest.api.domain;

import lombok.Data;

@Data
public class PsuInfo {
    private String psuId;
    private String psuIdType;
    private String psuCorporateId;
    private String psuCorporateIdType;
}
