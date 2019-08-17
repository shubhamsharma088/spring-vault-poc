package org.shubham.vault.config;

import org.shubham.vault.service.IVaultService;
import org.shubham.vault.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;

@Configuration
public class VaultConfig {

    @Autowired
    private VaultOperations vaultOperations;


    @Bean
    public VaultKeyValueOperations getVaultKeyValueOperations() {
        return vaultOperations.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.versioned());
    }

    @Bean
    public IVaultService getVaultService() {
        return new VaultService();
    }
}
