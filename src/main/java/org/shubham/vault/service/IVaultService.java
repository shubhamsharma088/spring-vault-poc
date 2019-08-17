package org.shubham.vault.service;

import org.shubham.vault.model.Credentials;

import java.util.List;

public interface IVaultService {

    void storeCredentialData(Credentials credentials, String path);

    List<Credentials> readCredentials(String path);

}
