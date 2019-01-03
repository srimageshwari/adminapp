package com.b2s.service.adminapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
    private int userId;
    private String password;
    private String name;
    private String address;
    private String city;
    private String state;
    private int zip;

    public UserInfo() {
    }

    private UserInfo(UserAttributesBuilder builder) {
        this.userId = builder.userId;
        this.password = builder.password;
        this.name = builder.name;
        this.address = builder.address;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
    }


    @JsonCreator
    public static UserInfo create(
            @JsonProperty("userId") final int theUserId,
            @JsonProperty("password") final String thePassword,
            @JsonProperty("name") final String theName,
            @JsonProperty("address") final String theAddress,
            @JsonProperty("city") final String theCity,
            @JsonProperty("state") final String theState,
            @JsonProperty("uzip") final int theZip

    ) {
        return builder().setName(theName).setPassword(thePassword).setUserId(theUserId).setAddress(theAddress).
                setCity(theCity).setState(theState).setZip(theZip).build();
    }

    public static UserAttributesBuilder builder() {
        return new UserAttributesBuilder();
    }


    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo that = (UserInfo) o;

        if (getUserId() != that.getUserId()) return false;
        if (getZip() != that.getZip()) return false;
        if (!getPassword().equals(that.getPassword())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getAddress().equals(that.getAddress())) return false;
        if (!getCity().equals(that.getCity())) return false;
        return getState().equals(that.getState());

    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getState().hashCode();
        result = 31 * result + getZip();
        return result;
    }

    public static class UserAttributesBuilder {
        private int userId;
        private String password;
        private String name;
        private String address;
        private String city;
        private String state;
        private int zip;

        public UserAttributesBuilder() {
        }

        public UserAttributesBuilder(int userId, String password, String name, String address, String city, String state, int zip) {
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip = zip;

        }

        public UserAttributesBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserAttributesBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserAttributesBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserAttributesBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserAttributesBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public UserAttributesBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public UserAttributesBuilder setZip(int zip) {
            this.zip = zip;
            return this;
        }


        public UserInfo build() {
            return new UserInfo(this);
        }

    }
}
