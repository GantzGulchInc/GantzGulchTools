package com.gantzgulch.tools.gg.domain;

import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.tools.domain.AbstractJsonObject;

public class GGUser extends AbstractJsonObject {

    @JsonProperty("id")
    private String id;
    
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("email")
    private String email;
    
    public GGUser() {
    }

    public GGUser(final String id, final String firstName, final String lastName, final String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Matcher<GGUser> isUser(final GGUser user) {

        return new TypeSafeMatcher<GGUser>() {

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return").appendValue(user);
            }

            @Override
            protected void describeMismatchSafely(final GGUser item, final Description mismatchDescription) {
                mismatchDescription.appendText("was").appendValue(item);
            }
            
            @Override
            protected boolean matchesSafely(GGUser item) {

                if (user == item) {
                    return true;
                }

                if (item == null) {
                    return false;
                }

                return Objects.equals(item.getId(), user.getId()) && //
                Objects.equals(item.getFirstName(), user.getFirstName()) && //
                Objects.equals(item.getLastName(), user.getLastName()) && //
                Objects.equals(item.getEmail(), user.getEmail());
            }

        };

    }
}
