package com.epam.lab.shop.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {

    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private String password;
    private String repeatPassword;
    private String subscription;

    public UserDTO() {
        this.firstName = null;
        this.secondName = null;
        this.email = null;
        this.phoneNumber = null;
        this.birthDate = null;
        this.password = null;
        this.repeatPassword = null;
        this.subscription = null;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserDTO userDTO = (UserDTO) obj;
        return Objects.equals(firstName, userDTO.firstName) && Objects.equals(secondName, userDTO.secondName) && Objects.equals(email, userDTO.email) && Objects.equals(phoneNumber, userDTO.phoneNumber) && Objects.equals(birthDate, userDTO.birthDate) && Objects.equals(password, userDTO.password) && Objects.equals(repeatPassword, userDTO.repeatPassword) && Objects.equals(subscription, userDTO.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, email, phoneNumber, birthDate, password, repeatPassword, subscription);
    }

    public static class Builder {

        private final UserDTO userDTO;

        public Builder() {
            this.userDTO = new UserDTO();
        }

        public Builder withFirstName(String firstName) {
            userDTO.setFirstName(firstName);
            return this;
        }

        public Builder withSecondName(String secondName) {
            userDTO.setSecondName(secondName);
            return this;
        }

        public Builder withEmail(String email) {
            userDTO.setEmail(email);
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            userDTO.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder withBirthDate(String birthDate) {
            userDTO.setBirthDate(birthDate);
            return this;
        }

        public Builder withPassword(String password) {
            userDTO.setPassword(password);
            return this;
        }

        public Builder withRepeatPassword(String repeatPass) {
            userDTO.setRepeatPassword(repeatPass);
            return this;
        }

        public Builder withSubscription(String subscription) {
            userDTO.setSubscription(subscription);
            return this;
        }

        public UserDTO build() {
            return userDTO;
        }
    }
}