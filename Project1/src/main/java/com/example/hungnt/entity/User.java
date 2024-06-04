package com.example.hungnt.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dbo_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_display_name",nullable = false)
    private String UserDisplayName;

    @Column(name = "password",nullable = false)
    private String Password;

    @Column(name = "creation_date",nullable = false)
    private String CreationDate;

    @Column(name = "role",nullable = false)
    private String Role;

    public User(String Email, String userDisplayName, String password, String creationDate, String role) {
        email = Email;
        UserDisplayName = userDisplayName;
        Password = password;
        CreationDate = creationDate;
        Role = role;
    }

	public String getPassword() {
		// TODO Auto-generated method stub
		return Password;
	}
}
