package com.example.FirstCELL.repository;

import com.example.FirstCELL.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {

    Information findByName(String name);
}