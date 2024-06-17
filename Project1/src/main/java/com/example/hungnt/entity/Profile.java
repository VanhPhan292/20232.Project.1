package com.example.hungnt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String FirstName;

    @Column(name = "last_name")
    private String LastName;

    @Column(name ="date_of_birth")
    private String DateofBirth;

    @Column(name="img_url")
    private String ImgUrl;

    @Column(name="gender")
    private String Gender;

    @Column(name="adress")
    private String Adress;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Profile(String firstName, String lastName, String dateofBirth, String imgUrl, String gender, String adress, User user) {
        FirstName = firstName;
        LastName = lastName;
        DateofBirth = dateofBirth;
        ImgUrl = imgUrl;
        Gender = gender;
        Adress = adress;
        this.user = user;
    }
}
