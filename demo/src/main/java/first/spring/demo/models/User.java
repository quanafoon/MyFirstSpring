package first.spring.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    public User(){
        
    }
    public User(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() { return id; }
    public String getFirstname() { return firstname;}
    public String getLastname() { return lastname;}
}
