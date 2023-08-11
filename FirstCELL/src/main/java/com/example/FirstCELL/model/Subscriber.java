package com.example.FirstCELL.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="subscribers")
public class Subscriber
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=true)
    private String telephone;

    @Column(nullable=false)
    private String password;
    @Column
    private int amountData;
    @Column
    private int amountSms;
    @Column
    private int amountVoice;
    public int getAmountData() {
        return amountData;
    }

    public void setAmountData(int amountData) {
        this.amountData = amountData;
    }

    public int getAmountSms() {
        return amountSms;
    }

    public void setAmountSms(int amountSms) {
        this.amountSms = amountSms;
    }

    public int getAmountVoice() {
        return amountVoice;
    }

    public void setAmountVoice(int amountVoice) {
        this.amountVoice = amountVoice;
    }



    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="balance",
            joinColumns={@JoinColumn(name="SUBSCRIBER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="INFORMATION_ID", referencedColumnName="ID")})
    private Collection<Information> informations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Information> getInformations() {
        return informations;
    }

    public void setInformations(Collection<Information> informations) {
        this.informations = informations;
    }

}