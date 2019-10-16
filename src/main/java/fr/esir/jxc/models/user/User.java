package fr.esir.jxc.models.user;

import java.util.List;

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

    public User(String id, String username, String password, String email, String photoUrl, String bio, Address address, List<String> friendsId,List<Article> articles,List<Article>sharedArticles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.address = address;
        this.friendsId = friendsId;
        this.articles=articles;
        this.sharedArticles=sharedArticles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(List<String> friendsId) {
        this.friendsId = friendsId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", address=" + address +
                ", friendsId=" + friendsId +
                '}';
    }
}



