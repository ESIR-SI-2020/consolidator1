package fr.esir.jxc.services;

import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.utils.ElasticSearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersReadService {

    private final ElasticsearchOperations elasticsearchOperations;

    public UsersReadService(@Autowired ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(
                this.elasticsearchOperations.queryForObject(ElasticSearchUtils.getQuery(id), User.class)
        );
    }

}
