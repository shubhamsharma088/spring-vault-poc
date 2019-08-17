package org.shubham.vault.service;

import lombok.extern.slf4j.Slf4j;
import org.shubham.vault.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.support.VaultResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class VaultService implements IVaultService {

    @Autowired
    private VaultKeyValueOperations keyValueOperations;

    private List<Credentials> credentials;

    @Override
    public void storeCredentialData(Credentials credentials, String path) {
        keyValueOperations.put(path, credentials);
    }

    @Override
    public List<Credentials> readCredentials(String path) {
        credentials = new ArrayList<>();
        try {
            VaultResponse vaultResponse = keyValueOperations.get(path);
            vaultResponse.getData().entrySet().forEach(this::populateCredentialResponse);

        } catch (Exception ex) {
            log.error("Could not find credentials at path: {}", path, ex);
        }
        return credentials;
    }

    private void populateCredentialResponse(Map.Entry<String, Object> entry) {
        credentials.add(new Credentials(entry.getKey(), entry.getValue().toString()));
    }

}
