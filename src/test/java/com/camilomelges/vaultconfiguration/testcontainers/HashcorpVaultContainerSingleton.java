package com.camilomelges.vaultconfiguration.testcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.vault.VaultContainer;

@SuppressWarnings("rawtypes")
@Testcontainers
public class HashcorpVaultContainerSingleton {

    protected static VaultContainer VAULT_CONTAINER;

    @BeforeAll
    public static void vaultContainer() {
        if (VAULT_CONTAINER == null) {
            VAULT_CONTAINER = new VaultContainer<>()
                    .withVaultToken("my-root-token")
                    .withVaultPort(8200)
                    .withSecretInVault("secret/testing", "test.username=JohnDoe","test.password=ultraSecretPassword");

            VAULT_CONTAINER.start();
        }
    }
}
