package fr.esir.jxc.models.elasticSearchConfig;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "fr.esir.jxc.repositories")
public class ElasticSearchConfig {

        @Value("${elasticsearch.host:localhost}")
        private String host;

        @Value("${elasticsearch.port:9300}")
        private int port;

        @Value("${elasticsearch.clustername}")
        private String clusterName;

        @Value("${elasticsearch.home:}")
        private String home;

        @Bean
        public Client client() {
                final Settings elasticsearchSettings = Settings.builder()
                        .put("path.home", host)
                        .put("cluster.name", clusterName).build();

                try {
                        TransportClient transportClient = new PreBuiltTransportClient(elasticsearchSettings);
                        return transportClient.addTransportAddress(
                                new InetSocketTransportAddress(InetAddress.getByName(host), port)
                        );
                } catch (UnknownHostException e) {
                        log.error(e.getMessage(), e);
                        return null;
                }
        }

        @Bean
        public ElasticsearchOperations elasticsearchTemplate() {
                return new ElasticsearchTemplate(client());
        }
}


