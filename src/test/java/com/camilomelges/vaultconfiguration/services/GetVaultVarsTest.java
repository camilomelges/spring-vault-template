package com.camilomelges.vaultconfiguration.services;


import com.camilomelges.vaultconfiguration.testcontainers.HashcorpVaultContainerSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetVaultVarsTest extends HashcorpVaultContainerSingleton {

    @Autowired
    private IGetVaultVars getVaultVars;

    @Test
    public void shouldBeReturnCorrectUsername() {
        final String path = "testing";
        final String value = "John Doe";
        final String key = "test.username";
        getVaultVars.put(path, key, value);
        Assertions.assertEquals(value, getVaultVars.get(path, key));
    }

    @Test
    public void shouldBeReturnCorrectPassword() {
        final String path = "testing";
        final String value = "FooBar";
        final String key = "test.password";
        getVaultVars.put(path, key, value);
        Assertions.assertEquals(value, getVaultVars.get(path, key));
    }
}
