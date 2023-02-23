package com.example.soribada_renewal_pro.entity;


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
@Table(name = "album_info")
public class AlbumInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_seq")
    private Long seq;

    @Column(name = "ai_img")
    private String img;

    @Column(name = "ai_name")
    private String name;

    @Column(name = "ai_pub_dt")
    private Integer pubDt;

    @Column(name = "ai_foreign")
    private Integer foreign;

    @Column(name = "ai_introduce")
    private String introduce;
}
