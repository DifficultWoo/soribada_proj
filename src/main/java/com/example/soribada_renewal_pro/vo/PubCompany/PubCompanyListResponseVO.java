package com.example.soribada_renewal_pro.vo.PubCompany;

import java.util.List;

import com.example.soribada_renewal_pro.entity.PubCompanyInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PubCompanyListResponseVO {
    // 리스트 가져오고
    // 페이징 처리 할 것들
    private List<PubCompanyInfoEntity> list; // 리스트
    private Long total; // 엔터 총 수
    private Integer totalPage; // 총 페이지 수
    private Integer currentPage; // 현재 페이지
}
