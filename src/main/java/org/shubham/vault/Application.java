package org.shubham.vault;

import org.shubham.vault.model.AddCredentialsRequest;
import org.shubham.vault.model.Credentials;
import org.shubham.vault.service.IVaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private IVaultService vaultService;

    @PostMapping("/")
    public ResponseEntity addCredentials(@RequestBody AddCredentialsRequest credentialsRequest) {

        vaultService.storeCredentialData(credentialsRequest.getCredentials(), credentialsRequest.getPath());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/path/{id}")
    public List<Credentials> fetchCredentials(@PathVariable("id") String path) {
        return vaultService.readCredentials(path);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
