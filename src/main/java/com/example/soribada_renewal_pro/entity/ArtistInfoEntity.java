package com.example.soribada_renewal_pro.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "artist_info")
public class ArtistInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_seq")
    private Long art_seq;

    @Column(name = "art_name")
    private String art_name;

    @Column(name = "art_agi_seq")
    private Long art_agi_seq;

    @Column(name = "art_birth")
    private Date art_birth;

    @Column(name = "art_img")
    private String art_img;

}
