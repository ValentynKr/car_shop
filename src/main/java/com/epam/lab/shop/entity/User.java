package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class User implements Serializable {

    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String password;
    private boolean subscription;
    private int loginAttempts;
    private Timestamp dateOfUnblocking;
    private Role role;

    public User() {
        this.id = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        this.firstName = null;
        this.secondName = null;
        this.email = null;
        this.phoneNumber = null;
        this.birthDate = null;
        this.password = null;
        this.subscription = false;
        this.loginAttempts = 0;
        this.dateOfUnblocking = null;
        this.role = Role.USER;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Timestamp getDateOfUnblocking() {
        return dateOfUnblocking;
    }

    public void setDateOfUnblocking(Timestamp dateOfUnblocking) {
        this.dateOfUnblocking = dateOfUnblocking;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                ", subscription=" + subscription +
                ", loginAttempts=" + loginAttempts +
                ", dateOfUnblocking=" + dateOfUnblocking +
                ", role=" + role +
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
        User user = (User) obj;
        return id == user.id && subscription == user.subscription && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(birthDate, user.birthDate) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, email, phoneNumber, birthDate, password, subscription);
    }

    public static class Builder {

        private final User user;

        public Builder() {
            this.user = new User();
        }

        public Builder withId(int id) {
            user.setId(id);
            return this;
        }

        public Builder withFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public Builder withSecondName(String secondName) {
            user.setSecondName(secondName);
            return this;
        }

        public Builder withEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            user.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder withBirthDate(Date birthDate) {
            user.setBirthDate(birthDate);
            return this;
        }

        public Builder withPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder withSubscription(boolean subscription) {
            user.setSubscription(subscription);
            return this;
        }

        public Builder withLoginAttempts(int loginAttempts) {
            user.setLoginAttempts(loginAttempts);
            return this;
        }

        public Builder withDateOfUnblocking(Timestamp dateOfUnblocking) {
            user.setDateOfUnblocking(dateOfUnblocking);
            return this;
        }

        public Builder withRole(Role role) {
            user.setRole(role);
            return this;
        }

        public User build() {
            return user;
        }
    }
}