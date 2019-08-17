package org.shubham.vault.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCredentialsRequest {
    private String path;
    private Credentials credentials;
}
