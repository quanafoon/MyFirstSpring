package first.spring.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import first.spring.demo.models.User;
import first.spring.demo.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class Index {
 
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String start(Model model){
        User user = new User();
        model.addAttribute("user", user); // Sends the data to view
        return "index";
    }

    @GetMapping("/error")
    public String error(){
        return "error.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        User found = userRepository.findByFirstname(user.getFirstname());
        if(found != null){
            Long userId = found.getId();
            Long identifier = found.generateIdentifier();
            userRepository.save(found);
            try{
                String encryptedIdentifier = Encryption.encrypt(Long.toString(identifier), "scolding");
                redirectAttributes.addFlashAttribute("message", "Hey there, " + user.getFirstname());
                return "redirect:/home/" + userId + "/" + encryptedIdentifier ;
            } catch (Exception e) {e.printStackTrace();}
        }
        redirectAttributes.addFlashAttribute("message", "User does not exist");
        System.out.println(user.getFirstname() + " was not found");
        return "redirect:/";
    }
    
    @GetMapping("/home/{id}/{identifier}")
    public String home(@PathVariable("id") Long userId, @PathVariable("identifier") String code, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && code != null){
            try{
                String decryptedIdentifier = Encryption.decrypt(code, "scolding");
                User user = userOptional.get();
                boolean valid = user.validate(Long.parseLong(decryptedIdentifier));
                if(valid){
                    model.addAttribute("user", user);
                    return "home";
                }
            } catch (Exception e){ 
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message","Suspicious Activity");
        return "redirect:/";
    }
    
}