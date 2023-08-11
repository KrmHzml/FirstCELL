package com.example.FirstCELL.service;



import com.example.FirstCELL.model.Information;
import com.example.FirstCELL.model.Subscriber;
import com.example.FirstCELL.repository.InformationRepository;
import com.example.FirstCELL.repository.SubscriberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.FirstCELL.dto.SubscriberDto;

import java.util.Arrays;


@Service
public class SubscriberService {

    private SubscriberRepository subscriberRepository;
    private InformationRepository informationRepository;
    private PasswordEncoder passwordEncoder;

    public SubscriberService(SubscriberRepository subscriberRepository,
                       InformationRepository informationRepository,
                       PasswordEncoder passwordEncoder) {
        this.subscriberRepository = subscriberRepository;
        this.informationRepository = informationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveSubscriber(SubscriberDto subscriberDto) {
        Subscriber subscriber = new Subscriber();
        subscriber.setName(subscriberDto.getName());
        subscriber.setTelephone(subscriberDto.getTelephone());
        subscriber.setEmail(subscriberDto.getEmail());
        subscriber.setPassword(passwordEncoder.encode(subscriberDto.getPassword()));
        subscriber.setAmountData(subscriberDto.getAmountData());
        subscriber.setAmountSms(subscriberDto.getAmountSms());
        subscriber.setAmountVoice(subscriberDto.getAmountVoice());

        Information information = informationRepository.findByName("i2iSystem");
        if(information == null){
            information = addNewInformation();
        }
        subscriber.setInformations(Arrays.asList(information));
        subscriberRepository.save(subscriber);
    }

    public Subscriber findSubscriberByEmail(String email) {
        return subscriberRepository.findByEmail(email);
    }

    private Information addNewInformation(){
        Information information = new Information();
        information.setName("i2iSystem");
        return informationRepository.save(information);
    }

    public void save(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

}