package com.camilomelges.vaultconfiguration.testcontainers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Slf4j
@Testcontainers
public class HashcorpVaultContainerSingleton {

    protected static GenericContainer VAULT_CONTAINER;

    @BeforeAll
    public static void vaultContainer() {
        if (VAULT_CONTAINER == null) {
            VAULT_CONTAINER = new GenericContainer<>("vault:latest")
                    .withEnv("VAULT_DEV_ROOT_TOKEN_ID", "test_token")
                    .withCommand("server -dev")
                    .withExposedPorts(8200);

            VAULT_CONTAINER.setPortBindings(ImmutableList.of("0.0.0.0:" + 8200 + ":" + 8200));

            VAULT_CONTAINER.start();


            final Map<String, List<String>> secretsMap = new HashMap<>() {{
                putIfAbsent("secret/testing", List.of("test.username='John Doe'", "test.password='FooBar'"));
            }};

            try {
                VAULT_CONTAINER.execInContainer(buildExecCommand(secretsMap)).getStdout().contains("Success");
            } catch (IOException | InterruptedException e) {
                log.error("Failed to add these secrets {} into Vault via exec command. Exception message: {}", secretsMap, e.getMessage());
            }
        }
    }


    private static String[] buildExecCommand(Map<String, List<String>> map) {
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((path, secrets) -> {
            stringBuilder.append(" && vault kv put " + path);
            secrets.forEach(item -> stringBuilder.append(" " + item));
        });
        return new String[]{"/bin/sh", "-c", "export VAULT_ADDR='http://127.0.0.1:8200' && export VAULT_TOKEN='test_token' && ".concat(stringBuilder.substring(4))};
    }
}
