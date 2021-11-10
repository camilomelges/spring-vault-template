package com.camilomelges.vaultconfiguration.services;

public interface IGetVaultVars {

    void put(final String path, final String key, final String value);
    String get(final String path, final String key);
}
