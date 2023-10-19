package com.example.telegrambot.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nick_Name")
    private String nickName;

    @Column(name = "first_Name")
    private String firstName ;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "chat_id")
    private long chatId;


    @Override
    public String toString() {
        return  "id=" + id + "\n" +
                "nickName='" + nickName + '\'' + "\n" +
                "firstName='" + firstName + '\'' + "\n" +
                "lastName='" + lastName + '\'' + "\n" +
                "email='" + email + '\'';
    }
}
