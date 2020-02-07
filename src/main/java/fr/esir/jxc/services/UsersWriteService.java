package fr.esir.jxc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esir.jxc.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class UsersWriteService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;

    @Value("${elasticsearch.indexName}") private String indexName;

    public UsersWriteService(
            @Autowired ElasticsearchOperations elasticsearchOperations,
            @Autowired ObjectMapper objectMapper) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.objectMapper = objectMapper;
    }

    public User save(User user) {
        final IndexQuery query = new IndexQueryBuilder()
                .withId(user.getEmail())
                .withIndexName(indexName)
                .withObject(user)
                .withType("user")
                .build();

        this.elasticsearchOperations.index(query);
        return user;
    }

    public void delete(String userEmail) {
        this.elasticsearchOperations.delete(indexName, "user", userEmail);
    }
}
