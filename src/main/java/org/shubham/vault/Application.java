package org.shubham.vault;

import org.shubham.vault.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private VaultTemplate vaultTemplate;

    @RequestMapping("/")
    public String create() {
        Credentials credentials = new Credentials("username", "password");
        vaultTemplate.write("secret/myapp", credentials);
        return "Hello World";
    }

    @RequestMapping("/creds")
    public Credentials read() {
        VaultResponseSupport<Credentials> response = vaultTemplate
                .read("secret/myapp", Credentials.class);
        return response.getData();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
