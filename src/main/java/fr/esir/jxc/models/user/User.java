package fr.esir.jxc.models.user;

import lombok.Value;

import java.util.List;

@Value
public class User {

    private String id;
    private String username;
    private String password;
    private String email;
    private String photoUrl;
    private String bio;
    private Address address;
    private List<String> friendsId;
    private List<Article> articles;
    private List<Article> sharedArticles;
}



