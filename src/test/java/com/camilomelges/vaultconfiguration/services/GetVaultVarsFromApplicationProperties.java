package com.camilomelges.vaultconfiguration.services;


import com.camilomelges.vaultconfiguration.testcontainers.HashcorpVaultContainerSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.vault.annotation.VaultPropertySource;

@SpringBootTest
@VaultPropertySource(value = "secret/testing")
public class GetVaultVarsFromApplicationProperties extends HashcorpVaultContainerSingleton {

    @Value("${testing.variable}")
    private String testVariable;

    @Autowired
    private IGetVaultVars getVaultVars;

    @Test
    public void shouldBeReturnCorrectUsername() {
        final String path = "testing";
        final String key = "variableFromVault";
        Assertions.assertEquals(getVaultVars.get(path, key), testVariable);
    }
}
