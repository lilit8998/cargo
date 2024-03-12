package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "messageForAdmin")
@Data
public class MessageForAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String subject;
    private String text;


}
