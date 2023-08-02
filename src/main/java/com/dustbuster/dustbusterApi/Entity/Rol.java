package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authority")
    private String authority;
    @Column(name = "user_id")
    private Long UserId;

}