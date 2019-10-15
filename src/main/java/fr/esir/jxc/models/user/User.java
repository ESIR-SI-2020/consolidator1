package fr.esir.jxc.models.user;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName="user", type="user")
public class User {

    private String id;
    private String username;
    private String password;
    private String email;
    private String photoUrl;
    private String bio;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Address address;
    private List<String> friendsId;

    public UserPublicRepresentation toPublicRepresentation() {
        return new UserPublicRepresentation(id, username, email, photoUrl, bio, address, friendsId);
    }

    @Value
    public static class UserPublicRepresentation {
        String id;
        String username;
        String email;
        String photoUrl;
        String bio;
        Address address;
        List<String> friendsId;

        public UserPublicRepresentation(String id, String username, String email, String photoUrl, String bio, Address address, List<String> friendsId) {
        }
    }

}
