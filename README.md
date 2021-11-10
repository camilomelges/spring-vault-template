# Spring vault template

## Vault

## Run in test environment with [TESTCONTAINERS!!](https://www.testcontainers.org/)
- run command: ```mvn test``` just it.

## Run in non-test environments

- [Install vault](https://learn.hashicorp.com/tutorials/vault/getting-started-install?in=vault/getting-started)
- [Start Server](https://learn.hashicorp.com/tutorials/vault/getting-started-dev-server?in=vault/getting-started)
- Configure the ENV_VARIABLES
    - export VAULT_TOKEN="..."
    - export VAULT_HOST="..."
    - export VAULT_PORT="..."
    - export VAULT_SCHEME="..."
- run command: ```mvn spring-boot:run``` 
    
