# Read Me First
The following was discovered as part of building this project:

# Getting Started

## Vault
- [Installation](https://learn.hashicorp.com/tutorials/vault/getting-started-install?in=vault/getting-started)
- [Start Server](https://learn.hashicorp.com/tutorials/vault/getting-started-dev-server?in=vault/getting-started)

## Run in test environment
- Configure test variables in [bootstrap.yml](./src/test/resources/bootstrap.yml)
  - token principally, for default the server will be running in http://localhost:8200
- run command: ```mvn test```

## Run in non-test environments
- Configure the ENV_VARIABLES 
  - VAULT_TOKEN
  - VAULT_HOST
  - VAULT_PORT
  - VAULT_SCHEME
- run command: ```mvn spring-boot:run``` 
    
