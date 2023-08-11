package com.example.FirstCELL.controller;




import com.example.FirstCELL.model.Subscriber;
import com.example.FirstCELL.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ForgotPasswordController {
    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;
    @GetMapping("/forgotpassword")
    public String showForgotPasswordPage(Model model) {
        return "forgotpassword";
    }
    @PostMapping("/sendpassword")
    public String getPassword(@RequestParam String email, Model model) {
        Subscriber subscriber = subscriberService.findSubscriberByEmail(email);

        if (subscriber != null) {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("The password you forgot");
            mailMessage.setText("Email: "+ subscriber.getEmail() +"\n"+"Password: "+ subscriber.getPassword());
            mailMessage.setFrom("hkerem600@gmail.com");
            javaMailSender.send(mailMessage);
        }

        return "login";
    }
}