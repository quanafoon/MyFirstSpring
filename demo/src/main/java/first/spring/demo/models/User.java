package first.spring.demo.models;

import jakarta.persistence.*;
import java.util.Random;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Long identifier;

    public User(){
        
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }
    public String getUsername() { return username;}
    public String getPassword() { return password;}

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
    public Long generateIdentifier(){
        Random rand = new Random();
        Long identifier = rand.nextLong(1000000);
        setIdentifier(identifier);
        return identifier;
    }
    public boolean validate(Long identifer){
        if(identifer.equals(this.identifier)){
            return true;
        }
        return false;
    }
}
