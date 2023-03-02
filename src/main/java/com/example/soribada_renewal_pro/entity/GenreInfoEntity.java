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

// @Data = @Getter, @Setter, @ToString, @EqualsAndHashCode @RequiredArgsContructor
// @AllArgsConstructor = 이 필드에 쓴 모든 생성자만 만들어줌
// @NoArgsConstructor = 기본생성자 생성
// @Builder = 선택적으로 필요한 값만 선언가능 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "genre_info")
public class GenreInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_seq")
    private Long seq;
    @Column(name = "genre_name")
    private String name;
}
