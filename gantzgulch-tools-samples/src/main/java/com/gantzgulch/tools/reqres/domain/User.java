package com.gantzgulch.tools.reqres.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.tools.domain.AbstractJsonObject;

public class User extends AbstractJsonObject {

    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    @JsonProperty("avatar")
    private String avatar;

    public User() {
    }

    
    public User(final String firstName, final String lastName, final String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
