package com.tomate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class TomateApplication {

    private static final Logger logger = LoggerFactory.getLogger(TomateApplication.class);


   
    public static void main(String[] args) {
        SpringApplication.run(TomateApplication.class, args);
    }

     
    @Bean
    public ApplicationRunner welcomeMessage(Environment env) {
        return args -> {
            String port = env.getProperty("server.port", "8080"); // Pega a porta do servidor, padrão 8080.

            logger.info("----------------------------------------------------------");
            logger.info(" Serviço de Preço do Tomate iniciado com sucesso!");
            logger.info(" API REST está no ar e pronta para receber requisições.");
            logger.info("");
            logger.info(" Endpoint principal: http://localhost:{}/api/tomate/preco?quantidade=0", port);
            logger.info("----------------------------------------------------------");
        };
    }
}
