package com.example.belajarpostman_11pplg3;

public class UserModel {
    int id;
    String name;
    String username;
    String email;
    String address;
    String phone;
    String website;
    String company;

    public UserModel(int id, String name, String username, String email, String address, String phone, String website, String company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getWebsite() { return website; }
    public String getCompany() { return company; }
}