package com.muhammet.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblsale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long date;
    Long amount;
    Long price;
    Long total;
}
