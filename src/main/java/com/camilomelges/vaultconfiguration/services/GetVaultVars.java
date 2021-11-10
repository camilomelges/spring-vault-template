package com.camilomelges.vaultconfiguration.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@Service
public class GetVaultVars implements IGetVaultVars {

    private final VaultOperations operations;

    public GetVaultVars(final VaultOperations operations) {
        this.operations = operations;
    }

    private VaultKeyValueOperations getVaultOperations() {
        return operations.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
    }

    public void put(final String path, final String key, final String value) {
        final Map<String, Object> secrets = getAllSecrets(path);
        secrets.put(key, value);

        getVaultOperations().put(path, secrets);
    }

    public String get(final String path, final String key) {
        final VaultResponse response = getVaultOperations().get(path);
        assert response != null;
        return Objects.requireNonNull(response.getData()).get(key).toString();
    }

    public Map<String, Object> getAllSecrets(final String path) {
        final VaultResponse response = getVaultOperations().get(path);
        return response != null ? response.getData() : new HashMap<>();
    }
}
