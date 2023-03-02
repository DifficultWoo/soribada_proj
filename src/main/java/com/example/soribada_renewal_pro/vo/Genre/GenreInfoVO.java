package com.example.soribada_renewal_pro.vo.Genre;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreInfoVO {
    private Long seq;
    private String name;

    public GenreInfoVO(GenreInfoEntity entity) {
        this.seq = entity.getSeq();
        this.name = entity.getName();
    }
}
