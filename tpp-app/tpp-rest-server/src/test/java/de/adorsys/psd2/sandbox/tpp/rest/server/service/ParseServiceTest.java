package de.adorsys.psd2.sandbox.tpp.rest.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import de.adorsys.psd2.sandbox.tpp.cms.api.domain.AisConsent;
import de.adorsys.psd2.sandbox.tpp.rest.server.model.DataPayload;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseServiceTest {
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private ParseService parseService = new ParseService(resourceLoader);

    @Test
    public void getDataFromFile_Consents() throws IOException {
        Resource resource = resourceLoader.getResource("consents_template.yml");
        MultipartFile multipartFile = new MockMultipartFile("file", resource.getFile().getName(), "text/plain", resource.getInputStream());

        Optional<List<AisConsent>> data = parseService.getDataFromFile(multipartFile, new TypeReference<List<AisConsent>>() {
        });
        assertThat(data.isPresent()).isTrue();
        data.get().forEach(this::assertNoNullFields);
    }

    @Test
    public void getDataFromFile_DataPayload() throws IOException {
        Resource resource = resourceLoader.getResource("data_payload_template.yml");
        MultipartFile multipartFile = new MockMultipartFile("file", resource.getFile().getName(), "text/plain", resource.getInputStream());

        Optional<DataPayload> data = parseService.getDataFromFile(multipartFile, new TypeReference<DataPayload>() {
        });
        assertThat(data.isPresent()).isTrue();
        validateDataPayload(data.get());
    }

    private void validateDataPayload(DataPayload dataPayload) {
        assertThat(dataPayload.getBranch() == null).isTrue();
        assertThat(dataPayload.getGeneratedIbans().size() == 0).isTrue();
        assertThat(dataPayload.getUsers().size() == 1).isTrue();
        assertThat(dataPayload.getAccounts().size() == 1).isTrue();
        assertThat(dataPayload.getBalancesList().size() == 1).isTrue();
        assertThat(dataPayload.getPayments().size() == 1).isTrue();
    }

    private void assertNoNullFields(AisConsent consent) {
        assertThat(consent).hasNoNullFieldsOrProperties();
        assertThat(consent.getPsuInfo()).hasNoNullFieldsOrProperties();
        assertThat(consent.getTppInfo()).hasNoNullFieldsOrProperties();
        consent.getAccess().getAccounts().forEach(a -> assertThat(a).hasNoNullFieldsOrProperties());
        consent.getAccess().getBalances().forEach(a -> assertThat(a).hasNoNullFieldsOrProperties());
        consent.getAccess().getTransactions().forEach(a -> assertThat(a).hasNoNullFieldsOrProperties());
    }
}
