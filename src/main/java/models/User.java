package models;

/**
 * Author: Domingo Pérez
 * GitHub: https://github.com/p4pupro
 */

public class User {

    private String name;
    private String surname;
    private boolean active;
    private String email;
    private String city;
    private String creationDate;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
