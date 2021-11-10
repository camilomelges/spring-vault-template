package com.camilomelges.vaultconfiguration.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.annotation.VaultPropertySource;

@SpringBootTest
@VaultPropertySource(value = "secret/secret")
public class GetVaultVarsFromAnnotation {

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Test
    public void shouldBeReturnCorrectUsername() {
        Assertions.assertEquals("John Doe", user);
    }

    @Test
    public void shouldBeReturnCorrectPassword() {
        Assertions.assertEquals("FooBar", password);
    }
}
