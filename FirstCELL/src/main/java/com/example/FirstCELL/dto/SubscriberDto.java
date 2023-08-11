package com.example.FirstCELL.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberDto
{
    private Long id;

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;

    private String telephone;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    private String passwordConfirmation;

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordsEqual() {
        try {
            return password.equals(passwordConfirmation);
        } catch(NullPointerException e) {
            return false;
        }
    }

}
