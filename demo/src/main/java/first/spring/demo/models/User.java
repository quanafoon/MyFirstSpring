package first.spring.demo.models;

import jakarta.persistence.*;
import java.util.Random;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Long identifier;

    public User(){
        
    }
    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() { return id; }
    public String getFirstname() { return firstname;}
    public String getLastname() { return lastname;}
    private Long getIdentifier() { return identifier;}

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
