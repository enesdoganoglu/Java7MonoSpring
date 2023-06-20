package com.muhammet.repository.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblfollow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long followerId;
    Long followingId;
    Long followDate;
}
