package com.example.soribada_renewal_pro.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.soribada_renewal_pro.entity.PubCompanyInfoEntity;
import com.example.soribada_renewal_pro.repository.PubCompanyInfoRepository;
import com.example.soribada_renewal_pro.vo.PubCompany.PubCompanyListResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PubCompanyInfoService {
    private final PubCompanyInfoRepository pubRepo;

    // 발매사 입력
    public Map<String, Object> addPubCompany(String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        // 입력은 builder
        if(pubRepo.countByName(name) == 0 ) {
            PubCompanyInfoEntity entity = PubCompanyInfoEntity.builder()
                .name(name)
                .build();
            pubRepo.save(entity);
            map.put("status", true);
            map.put("message", "발매사 추가");
        }
        else {
            map.put("status", false);
            map.put("message", name+" 는 이미 존재합니다.");    
        }
        return map;
    }
    
    // 발매사 조회 VO이용 필요한 정보만 조회하면 되니까
    public PubCompanyListResponseVO getPubCompanyList(String keyword, Pageable pageable) {
        Page<PubCompanyInfoEntity> page = pubRepo.findByNameContains(keyword, pageable);
        PubCompanyListResponseVO response = PubCompanyListResponseVO.builder()
            .list(page.getContent())
            .total(page.getTotalElements())
            .totalPage(page.getTotalPages())
            .currentPage(page.getNumber())
            .build();
        return response;
    }

    // 발매사 검색
    public Map<String, Object> getPubCompanySearch(Long seq) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<PubCompanyInfoEntity> optEntity = pubRepo.findById(seq);
        if(optEntity.isEmpty()) {
            map.put("status", false);
        }
        else {
            map.put("status", true);
            map.put("seq", optEntity.get().getSeq());
            map.put("name", optEntity.get().getName());
        }
        return map;
    }


}
