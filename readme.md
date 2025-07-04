# Projeto de Microsserviços: Venda de Tomates

Este repositório contém o código-fonte de um sistema de microsserviços para a venda de tomates, composto por três serviços principais: um para calcular o preço do tomate, outro para calcular o frete, e um serviço de mashup que orquestra os dois para calcular o valor final de uma venda.

## Arquitetura

O sistema é composto por três microsserviços:

1.  **Serviço de Preço do Tomate**: Uma API REST que calcula o preço das caixas de tomate com base na quantidade, aplicando descontos progressivos.
2.  **Serviço de Frete**: Uma API GraphQL que calcula o custo do frete com base na distância e no tipo de veículo.
3.  **Serviço de Mashup**: Uma API REST que consome os outros dois serviços para fornecer uma cotação de venda completa, incluindo preço dos produtos, frete, impostos e lucro.

---

## 1. Serviço de Preço do Tomate

API REST para cálculo de preços de tomate com um sistema de descontos baseado na quantidade de caixas solicitadas.

### Visão Geral

Este é um serviço web desenvolvido em **Spring Boot** que calcula o preço final de caixas de tomate, aplicando descontos progressivos conforme a quantidade. A arquitetura segue o padrão de camadas, com clara separação de responsabilidades.

### Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3.2.0**
* **Spring Web MVC** para a API REST
* **Maven** como gerenciador de dependências
* **SLF4J + Logback** para logging
* **Tomcat** como servidor web embutido

### Lógica de Negócio

O preço é calculado com base em um valor fixo por caixa, com descontos aplicados a partir de certas quantidades.

* **Preço Base por Caixa**: R$ 50,00

#### Tabela de Descontos

| Quantidade de Caixas | Desconto Aplicado |
| -------------------- | ----------------- |
| 1-9                  | 0% (sem desconto) |
| 10-19                | 5% de desconto    |
| 20-29                | 11% de desconto   |
| 30+                  | 22% de desconto   |

### Estrutura do Código

* `com.tomate.TomateAplicacao`: Ponto de entrada da aplicação Spring Boot.
* `com.tomate.controlador.TomatoController`: Controlador REST que expõe o endpoint para o cálculo de preço.
* `com.tomate.service.TomatePrecoService`: Implementa a lógica de negócio, incluindo a validação da quantidade e a aplicação dos descontos.
* `com.tomate.dto.PrecoResposta`: DTO (Data Transfer Object) que estrutura a resposta JSON.

### Como Usar a API

* **Endpoint**: `GET http://localhost:8080/api/tomate/preco`
* **Parâmetro**: `quantidade` (inteiro)
* **Exemplo de Requisição**:
    ```bash
    curl "http://localhost:8080/api/tomate/preco?quantidade=15"
    ```
* **Exemplo de Resposta (JSON)**:
    ```json
    {
        "quantidadeDeCaixas": 15,
        "precoBasePorCaixa": 50.00,
        "percentualDeDesconto": 5,
        "precoFinalComDesconto": 712.50
    }
    ```

---

## 2. Serviço de Frete

API GraphQL que calcula o valor do frete com base na distância e no tipo de veículo.

### Visão Geral

Este serviço, construído com Spring for GraphQL, oferece um único endpoint para consultas de frete, aplicando uma lógica de custo variável com desconto para longas distâncias.

### Pré-requisitos

* Java 17 ou superior
* Gradle 8.14.2 ou superior

### Instalação e Execução

1.  Clone o repositório.
2.  Navegue até o diretório do projeto: `cd servico-frete`
3.  Execute o método main da classe `FreteApplication`

A aplicação estará disponível na porta `8081`.

### Lógica de Negócio

O custo do frete é composto por uma taxa fixa mais um custo variável por quilômetro, que depende do tipo de veículo. Um desconto de 20% é aplicado sobre o valor do quilômetro rodado para distâncias acima de 100km.

| Tipo de Veículo | Preço por Km | Taxa Fixa |
| --------------- | ------------ | --------- |
| Caminhão        | R$ 20,00     | R$ 200,00 |
| Carreta         | R$ 40,00     | R$ 400,00 |

### Estrutura do Código

* `com.example.demo.DemoApplication`: Ponto de entrada da aplicação.
* `com.example.demo.FreteController`: Controlador GraphQL que mapeia a query `calcularFrete`.
* `com.example.demo.FreteServico`: Contém a lógica de cálculo do frete.

### Como Usar a API

* **Endpoint**: `http://localhost:8081/graphql`
* **Query**: `calcularFrete`
    * **Argumentos**: `distancia` (Int!), `tipoVeiculo` (String!)
* **Exemplo de Requisição (GraphQL)**:
    ```graphql
    query {
      calcularFrete(distancia: 150, tipoVeiculo: "caminhao")
    }
    ```
* **Exemplo de Resposta (JSON)**:
    ```json
    {
      "data": {
        "calcularFrete": 3000
      }
    }
    ```

---

## 3. Serviço de Mashup de Venda de Tomate

API REST que orquestra os serviços de Preço e Frete para fornecer uma cotação de venda completa.

### Visão Geral

Este serviço atua como um agregador, recebendo a quantidade de tomates e a distância da entrega. Ele invoca os outros microsserviços para obter os custos e, em seguida, aplica suas próprias regras de negócio para calcular o valor final da venda.

### Tecnologias Utilizadas

* Java 17
* Spring Boot
* Maven

### Estrutura do Código

* `com.mashup.MashupApplication`: Ponto de entrada da aplicação.
* `com.mashup.controller.MashupController`: Controlador REST que expõe o endpoint de cálculo da venda.
* `com.mashup.service.MashupService`: Orquestra as chamadas aos outros serviços e aplica as regras de negócio finais.
* **DTOs**:
    * `FreteDTO`: Para desserializar a resposta do serviço de frete.
    * `PrecoTomateDTO`: Para desserializar a resposta do serviço de preço.
    * `ResultadoVendaDTO`: Para estruturar a resposta final do mashup.

### Como Usar a API

* **Endpoint**: `GET /venda/calcular`
* **Parâmetros**: `quantidade` (inteiro), `distancia` (inteiro)
* **Exemplo de Requisição**:
    ```bash
    curl "http://localhost:8082/venda/calcular?quantidade=15&distancia=150"
    ```
* **Exemplo de Resposta (JSON)**:
    ```json
    {
        "precoTomates": 712.50,
        "custoFrete": 3000.00,
        "tipoCaminhao": "caminhao",
        "lucro": 371.25,
        "impostos": 185.63,
        "valorFinal": 4269.38
    }
    ```
