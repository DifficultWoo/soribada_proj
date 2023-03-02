package com.example.soribada_renewal_pro.vo.Genre;

import java.util.List;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreListResponseVO {
    private List<GenreInfoEntity> list; // 장르리스트
    private Long total;                 // 총 장르 수
    private Integer totalPage;          // 총 페이지 수
    private Integer currentPage;        // 현재 조회한 페이지

}
