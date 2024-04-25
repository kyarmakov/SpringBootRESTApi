package com.yarmakov.SpringBootRESTApi.dto;

public class UserRequest {
    private String name;

    private String surname;

    private String email;

    private Integer age;

    private Boolean married;

    public UserRequest() {
    }

    public UserRequest(String name, String surname, String email, Integer age, Boolean married) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.married = married;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }
}
