package fr.esir.jxc.models;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@SpringBootApplication
// @EnableElasticsearchRepositories(basePackages = {"fr.esir.jxc.repositories"})
@ComponentScan({"fr.esir.jxc.elasticsearch.config"})
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);
    }
/*
    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }
*/

}