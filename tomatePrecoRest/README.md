# 🍅 Serviço de Preço do Tomate

API REST para cálculo de preços de tomate com sistema de descontos baseado na quantidade de caixas solicitadas.

## 📋 Visão Geral

Este é um serviço web desenvolvido em **Spring Boot** que calcula o preço final de caixas de tomate aplicando descontos progressivos conforme a quantidade solicitada. O sistema implementa uma arquitetura em camadas com separação clara de responsabilidades.

## 🏗️ Arquitetura do Sistema

O projeto segue o padrão **MVC (Model-View-Controller)** adaptado para APIs REST:

```
📁 src/main/java/com/tomate/
├── 🚀 TomateAplicacao.java          # Classe principal (Spring Boot)
├── 🎮 controlador/
│   └── TomatoController.java        # Controlador REST (API endpoints)
├── ⚙️ service/
│   └── TomatePrecoService.java      # Lógica de negócio (cálculos)
└── 📦 dto/
    └── PrecoResposta.java           # Objeto de resposta (JSON)
```

## 🔧 Tecnologias Utilizadas

- **Java 17+** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework para aplicações Java
- **Spring Web MVC** - Para criação de APIs REST
- **Maven** - Gerenciador de dependências
- **SLF4J + Logback** - Sistema de logging
- **Tomcat** - Servidor web embutido

## 📊 Regras de Negócio

### Tabela de Descontos
| Quantidade de Caixas | Desconto Aplicado |
|---------------------|-------------------|
| 1-9                 | 0% (sem desconto) |
| 10-19               | 5% de desconto    |
| 20-29               | 11% de desconto   |
| 30+                 | 22% de desconto   |

### Fórmula de Cálculo
```
Preço Base por Caixa = R$ 50,00
Preço Total Base = Quantidade × R$ 50,00
Multiplicador de Desconto = 1 - (Percentual de Desconto ÷ 100)
Preço Final = Preço Total Base × Multiplicador de Desconto
```

## Detalhamento das Classes

###  **TomateAplicacao.java** - Classe Principal
**Localização:** `src/main/java/com/tomate/TomateAplicacao.java`

**Responsabilidades:**
- **Ponto de entrada** da aplicação Spring Boot
- **Configuração automática** do Spring (`@SpringBootApplication`)
- **Inicialização** do servidor web Tomcat
- **Mensagem de boas-vindas** após inicialização

**Funcionalidades:**
```java
@SpringBootApplication  // Habilita autoconfiguração do Spring
public class TomateAplicacao {
    public static void main(String[] args) {
        SpringApplication.run(TomateAplicacao.class, args);
    }
    
    @Bean  // Executa após inicialização
    public ApplicationRunner welcomeMessage(Environment env) {
        // Exibe mensagem com endpoint e exemplo de uso
    }
}
```

**Output no console:**
```
 Serviço de Preço do Tomate iniciado com sucesso!
 API REST está no ar e pronta para receber requisições.
 Endpoint principal: http://localhost:8080/api/tomate/preco
 Exemplo de uso: http://localhost:8080/api/tomate/preco?quantidade=15
```

---

###  **TomatoController.java** - Controlador REST
**Localização:** `src/main/java/com/tomate/controlador/TomatoController.java`

**Responsabilidades:**
- **Receber requisições HTTP** GET
- **Validar parâmetros** de entrada
- **Chamar o serviço** de negócio
- **Retornar respostas** JSON apropriadas
- **Tratar erros** e exceções

**Endpoint:**
```
GET /api/tomate/preco?quantidade={numero}
```

**Funcionalidades:**
```java
@RestController
@RequestMapping("/api/tomate")
public class TomatoController {
    
    @GetMapping("/preco")
    public ResponseEntity<Object> getTomatoPrice(@RequestParam int quantidade) {
        try {
            // Chama o serviço e retorna sucesso (200 OK)
            PrecoResposta response = tomatePrecoService.CalcularPreco(quantidade);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Retorna erro de validação (400 Bad Request)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
```

**Respostas:**
- **Sucesso (200):** JSON com dados do cálculo
- **Erro (400):** Mensagem de erro em texto

---

### **TomatePrecoService.java** - Lógica de Negócio
**Localização:** `src/main/java/com/tomate/service/TomatePrecoService.java`

**Responsabilidades:**
- **Implementar regras de negócio** (cálculos de preço)
- **Aplicar tabela de descontos**
- **Validar dados** de entrada
- **Realizar cálculos** matemáticos precisos
- **Criar objeto de resposta**

**Constantes:**
```java
private static final BigDecimal BASE_PRICE_PER_BOX = new BigDecimal("50.00");
```

**Métodos principais:**
```java
@Service
public class TomatePrecoService {
    
    // Método público - calcula preço com desconto
    public PrecoResposta CalcularPreco(int quantidade) {
        // 1. Valida quantidade
        // 2. Calcula desconto
        // 3. Aplica fórmula
        // 4. Retorna resultado
    }
    
    // Método privado - determina percentual de desconto
    private BigDecimal getDiscountPercentage(int quantidade) {
        // Implementa tabela de descontos
    }
}
```

**Validações:**
- Quantidade deve ser > 0
- Lança `IllegalArgumentException` para valores inválidos

**Cálculos:**
- Usa `BigDecimal` para precisão monetária
- Arredondamento para 2 casas decimais
- Fórmula: `PreçoFinal = TotalBase × (1 - Desconto/100)`

---

### **PrecoResposta.java** - Objeto de Resposta
**Localização:** `src/main/java/com/tomate/dto/PrecoResposta.java`

**Responsabilidades:**
- **Estruturar dados** de resposta
- **Serializar para JSON** automaticamente
- **Encapsular** informações do cálculo

**Estrutura:**
```java
public class PrecoResposta {
    private int quantidadeDeCaixas;           // Quantidade solicitada
    private BigDecimal precoBasePorCaixa;     // R$ 50,00
    private BigDecimal percentualDeDesconto;  // % aplicado
    private BigDecimal precoFinalComDesconto; // Valor final
}
```

**JSON de resposta:**
```json
{
    "quantidadeDeCaixas": 15,
    "precoBasePorCaixa": 50.00,
    "percentualDeDesconto": 5,
    "precoFinalComDesconto": 712.50
}
```

##  Como Executar

### Pré-requisitos
- **Java 17** ou superior
- **Maven** (opcional - projeto inclui Maven Wrapper)

### Execução Rápida
```bash
# Usando Maven Wrapper (recomendado)
.\mvnw.cmd spring-boot:run

# Ou usando Maven instalado
mvn spring-boot:run
```

### Compilação
```bash
# Compilar projeto
.\mvnw.cmd clean compile

# Criar JAR executável
.\mvnw.cmd clean package
```

## Como Usar a API

### Endpoint Principal
```
GET http://localhost:8080/api/tomate/preco?quantidade={numero}
```

### Exemplos de Uso

#### 1. Consulta sem desconto (1-9 caixas)
```bash
curl "http://localhost:8080/api/tomate/preco?quantidade=5"
```
**Resposta:**
```json
{
    "quantidadeDeCaixas": 5,
    "precoBasePorCaixa": 50.00,
    "percentualDeDesconto": 0,
    "precoFinalComDesconto": 250.00
}
```

#### 2. Consulta com 5% de desconto (10-19 caixas)
```bash
curl "http://localhost:8080/api/tomate/preco?quantidade=15"
```
**Resposta:**
```json
{
    "quantidadeDeCaixas": 15,
    "precoBasePorCaixa": 50.00,
    "percentualDeDesconto": 5,
    "precoFinalComDesconto": 712.50
}
```

#### 3. Consulta com 11% de desconto (20-29 caixas)
```bash
curl "http://localhost:8080/api/tomate/preco?quantidade=25"
```
**Resposta:**
```json
{
    "quantidadeDeCaixas": 25,
    "precoBasePorCaixa": 50.00,
    "percentualDeDesconto": 11,
    "precoFinalComDesconto": 1112.50
}
```

#### 4. Consulta com 22% de desconto (30+ caixas)
```bash
curl "http://localhost:8080/api/tomate/preco?quantidade=35"
```
**Resposta:**
```json
{
    "quantidadeDeCaixas": 35,
    "precoBasePorCaixa": 50.00,
    "percentualDeDesconto": 22,
    "precoFinalComDesconto": 1365.00
}
```

#### 5. Erro de validação
```bash
curl "http://localhost:8080/api/tomate/preco?quantidade=0"
```
**Resposta:**
```
A quantidade de caixas deve ser maior que zero.
```

## Fluxo de Execução

1. **Cliente** envia requisição GET para `/api/tomate/preco?quantidade=15`
2. **TomatoController** recebe a requisição e extrai o parâmetro `quantidade`
3. **Controller** chama `tomatePrecoService.CalcularPreco(15)`
4. **TomatePrecoService** valida a quantidade e calcula o desconto (5%)
5. **Service** aplica a fórmula: `15 × 50 × (1 - 5/100) = 712.50`
6. **Service** cria objeto `PrecoResposta` com os dados
7. **Controller** retorna JSON com status 200 OK
8. **Cliente** recebe a resposta com o preço calculado

##  Desenvolvimento

### Estrutura de Pacotes
```
com.tomate
├── TomateAplicacao.java
├── controlador
│   └── TomatoController.java
├── service
│   └── TomatePrecoService.java
└── dto
    └── PrecoResposta.java
```

### Padrões Utilizados
- **Dependency Injection** - Injeção de dependências com `@Autowired`
- **Service Layer** - Separação da lógica de negócio
- **DTO Pattern** - Objetos de transferência de dados
- **RESTful API** - Endpoints REST padronizados
- **Exception Handling** - Tratamento de erros centralizado

### Boas Práticas Implementadas
- ✅ **Validação de entrada** - Verificação de parâmetros
- ✅ **Precisão monetária** - Uso de `BigDecimal`
- ✅ **Logging profissional** - SLF4J em vez de `System.out.println`
- ✅ **Separação de responsabilidades** - Cada classe tem uma função específica
- ✅ **Tratamento de erros** - Respostas HTTP apropriadas
- ✅ **Documentação** - Comentários explicativos no código

## Logs e Monitoramento

A aplicação utiliza **SLF4J + Logback** para logging:

- **Inicialização:** Informações sobre startup e configuração
- **Requisições:** Log de cada chamada à API
- **Erros:** Detalhes de exceções e problemas
- **Performance:** Tempo de resposta das operações

## Configuração

### Porta do Servidor
Por padrão, a aplicação roda na **porta 8080**. Para alterar:

Este projeto é de uso educacional e demonstração de conceitos de desenvolvimento Java com Spring Boot.
