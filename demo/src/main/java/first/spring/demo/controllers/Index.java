package first.spring.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import first.spring.demo.models.User;
import first.spring.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class Index {
 
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String start(Model model){
        User user = new User("Quan", "Afoon");
        userRepository.save(user);                      // Saves the user to the database
        model.addAttribute("user", user); // Sends the data to view
        return "index";
    }

    @GetMapping("/error")
    public String error(){
        return "error.html";
    }
    
}