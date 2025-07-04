# Demo

Este é um projeto GraphQL com Spring Boot que calcula o valor do frete com base na distância e no tipo de veículo, para gerar esse projeto foi usado o site recomendado (https://start.spring.io/)
## Pré-requisito

  * Java 17 ou superior
  * Gradle 8.14.2 ou superior
  * Talvez seja necessário definir a variável JAVA_HOME caso ocorra um erro por causa dela, para conserta-lo no windows basta seguir esse tutorial: [https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html]
## Instalação

1.  Clone o repositório:
    ```bash
    git clone https://github.com/Caio2909/WEB3/tree/Servi%C3%A7o2---Frete
    ```
2.  Navegue até o diretório do projeto:
    ```bash
    cd demo
    ```
3. Navegue até o diretório executável:
   ```bash
    cd .\demo\
   ```
5.  Compile o projeto com o Gradle:
    ```bash
    ./gradlew build
    ```

## Executando a aplicação

Para executar a aplicação, use o seguinte comando:

```bash
./gradlew bootRun
```
Ou
```bash
./gradlew.bat bootRun
```
Caso Tenha sido Executado com sucesso aparecerá:
```bash
<==========---> 80% EXECUTING [8s]
> :bootRun
```
A aplicação estará disponível em `http://localhost:8081`.
(Porta alterada para 8081 em demo/src/main/resources/application.properties usando o parâmetro `server.port=8081` para poder rodar os outros serviços sem ter conflito de porta)

## Uso

A aplicação expõe um endpoint GraphQL para calcular o frete.

**Endpoint:** `http://localhost:8081/graphql`

## Endpoints

A API expõe um endpoint GraphQL para calcular o frete.

### `calcularFrete`

Calcula o valor do frete com base na distância e no tipo de veículo.

  * **Argumentos:**

      * `distancia` (Int\!): A distância em quilômetros.
      * `tipoVeiculo` (String\!): O tipo de veículo (por exemplo, "caminhao").

  * **Retorno:**

      * `Float`: O valor do frete calculado.

**Exemplo de query:**

```graphql
query {
  calcularFrete(distancia: 150, tipoVeiculo: "caminhao")
}
```

**Resposta:**

```json
{
  "data": {
    "calcularFrete": 3000
  }
}
```

## Exemplo Acima Executado no Postman:

![image](https://github.com/user-attachments/assets/6b08ed69-3e09-491b-a52c-09eebcdfa576)

