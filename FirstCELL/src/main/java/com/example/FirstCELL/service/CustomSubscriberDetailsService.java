package com.example.FirstCELL.service;

import com.example.FirstCELL.model.Information;
import com.example.FirstCELL.model.Subscriber;
import com.example.FirstCELL.repository.SubscriberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomSubscriberDetailsService implements UserDetailsService {

    private SubscriberRepository subscriberRepository;

    public CustomSubscriberDetailsService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Subscriber subscriber = subscriberRepository.findByEmail(email);

        if (subscriber != null) {
            return new org.springframework.security.core.userdetails.User(subscriber.getEmail(),
                    subscriber.getPassword(),
                    mapInformationsToAuthorities(subscriber.getInformations()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.Test");
        }
    }

    private Collection < ? extends GrantedAuthority> mapInformationsToAuthorities(Collection <Information> informations) {
        Collection < ? extends GrantedAuthority> mapInformations = informations.stream()
                .map(information -> new SimpleGrantedAuthority(information.getName()))
                .collect(Collectors.toList());
        return mapInformations;
    }
}