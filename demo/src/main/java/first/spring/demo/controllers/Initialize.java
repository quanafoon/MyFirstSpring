package first.spring.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import first.spring.demo.models.User;
import first.spring.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class Initialize {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/init")
    public String initialize() {
        userRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        User user = new User("Quan", "Afoon");
        userRepository.save(user);
        return "db initialized";
    }    
}
