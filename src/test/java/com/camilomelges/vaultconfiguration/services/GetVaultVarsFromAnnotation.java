package com.camilomelges.vaultconfiguration.services;


import com.camilomelges.vaultconfiguration.testcontainers.HashcorpVaultContainerSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.vault.annotation.VaultPropertySource;

@SpringBootTest
@VaultPropertySource(value = "secret/testing")
public class GetVaultVarsFromAnnotation extends HashcorpVaultContainerSingleton {

    @Value("${test.username}")
    private String username;

    @Value("${test.password}")
    private String password;

    @Test
    public void shouldBeReturnCorrectUsername() {
        Assertions.assertEquals("John Doe", username);
    }

    @Test
    public void shouldBeReturnCorrectPassword() {
        Assertions.assertEquals("FooBar", password);
    }
}
