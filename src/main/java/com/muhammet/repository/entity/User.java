package com.muhammet.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    String password;
    String ad;
    String adres;
    String cinsiyet;
    Integer yas;
    String il;
    String ilce;
    String mahalle;
    String sokak;
    String postakodu;
    String telefon;
    String babaadi;
    String notlar;
    String avatar;
    Long createat;
    Long updateat;
    int state;
    boolean active;


}
