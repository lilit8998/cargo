package com.example.cargo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "size")
@Data
public class SizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int width;
    private int height;
    private int depth;
    private int weight;
}
