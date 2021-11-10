package com.camilomelges.vaultconfiguration.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetVaultVarsTest {

    @Autowired
    private IGetVaultVars getVaultVars;

    @Test
    public void shouldBeReturnCorrectUsername() {
        final String path = "secret";
        final String value = "John Doe";
        final String key = "user";
        getVaultVars.put(path, key, value);
        Assertions.assertEquals(value, getVaultVars.get(path, key));
    }

    @Test
    public void shouldBeReturnCorrectPassword() {
        final String path = "secret";
        final String value = "FooBar";
        final String key = "password";
        getVaultVars.put(path, key, value);
        Assertions.assertEquals(value, getVaultVars.get(path, key));
    }
}
