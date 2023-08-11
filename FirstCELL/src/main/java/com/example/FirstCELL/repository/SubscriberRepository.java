package com.example.FirstCELL.repository;

import com.example.FirstCELL.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Subscriber findByEmail(String email);

}