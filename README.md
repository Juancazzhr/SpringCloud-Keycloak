# Keycloak and Spring Cloud
## Gateway
### POM
- OAuth2 Client
- Spring Security
- Gateway (WebFlux)
  
### Properties
  
  ```yaml
  server:
    port: 8081
  
  spring:
    application:
      name: gateway
  
    security:
      oauth2:
        client:
          provider:
            keycloak-provider:
              issuer-uri: http://localhost:8080/realms/academy
          registration:
            gateway:
              provider: keycloak-provider
              client-id: ${client_id}
              client-secret: ${client_secret}
              scope: openid
              authorization-grant-type: authorization_code
              redirect-uri: 'http://localhost:8081/login/oauth2/code/keycloak'
  
    cloud:
      gateway:
        default-filters:
          - TokenRelay
        routes:
          - id: ms-student
            uri: http://localhost:9090
            predicates:
              - Path=/students/**
  ```

## ms-students
### POM
- OAuth2 Client
- OAuth2 Resource Server
- Spring Security
- Spring Web

### Properties
```yaml
spring:
  application:
    name: ms-student

  security:
    oauth2:
      client:
        provider:
          keycloak-provider:
            issuer-uri: 'http://localhost:8080/realms/academy'
        registration:
          ms-student:
            provider: keycloak-provider
            client-id: ${client_id}
            client-secret: ${client_secret}
            scope:
              - openid
            authorization-grant-type: authorization_code
            redirect-uri: 'http://localhost:8081/login/oauth2/code/keycloak'
server:
  port: 9090

jwt:
  auth:
    converter:
      resource-id: ${client_id}
      principal-attribute: preferred_username

logging:
  level:
    org:
      springframework:
        security: DEBUG
```
