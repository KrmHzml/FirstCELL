package com.example.FirstCELL.controller;


import com.example.FirstCELL.dto.SubscriberDto;
import com.example.FirstCELL.model.Subscriber;
import com.example.FirstCELL.service.SubscriberService;
import jakarta.validation.Valid;


import java.security.Principal;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    private SubscriberService subscriberService;

    public MainController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/")
    public String home(Principal principal, Model model){
        Subscriber subscriber = subscriberService.findSubscriberByEmail(principal.getName());
        model.addAttribute("subscriber", subscriber);
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        SubscriberDto subscriber = new SubscriberDto();
        model.addAttribute("subscriber", subscriber);
        return "register";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("subscriber") SubscriberDto subscriberDto,
                               BindingResult result,
                               Model model){
        Subscriber existingSubscriber= subscriberService.findSubscriberByEmail(subscriberDto.getEmail());

        if(existingSubscriber != null && existingSubscriber.getEmail() != null && !existingSubscriber.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("subscriber", subscriberDto);
            return "/register";
        }

        subscriberService.saveSubscriber(subscriberDto);
        return "redirect:/register?success";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/makeCall")
    public String makeCall(Principal principal, Model model) {
        Subscriber subscriber = subscriberService.findSubscriberByEmail(principal.getName());
        Random rn = new Random();
        int a=(rn.nextInt(4));
        int b=(rn.nextInt(11));
        int c=(rn.nextInt(11));
        subscriber.setAmountVoice(subscriber.getAmountVoice()-a);
        subscriber.setAmountSms(subscriber.getAmountSms()-b);
        subscriber.setAmountData(subscriber.getAmountData()-c);


        subscriberService.save(subscriber);
        model.addAttribute("subscriber", subscriber);
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        model.addAttribute("c",c);

        return "index";
    }
}