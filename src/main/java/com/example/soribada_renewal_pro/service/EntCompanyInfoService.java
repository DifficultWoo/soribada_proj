package com.example.soribada_renewal_pro.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soribada_renewal_pro.entity.EntCompanyInfoEntity;
import com.example.soribada_renewal_pro.repository.EntCompanyInfoRepository;
import com.example.soribada_renewal_pro.vo.EntCompany.EntCompanyListResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntCompanyInfoService {
    private final EntCompanyInfoRepository entRepo;

    // 엔터 회사 입력
    public Map<String, Object> addEntCompany(String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        if(entRepo.countByName(name) == 0) {
            // repo에 입력한 이름의 장르가 없으면 Entity(Table)에추가 true
            EntCompanyInfoEntity entity = EntCompanyInfoEntity.builder()
            .name(name)
            .build();
            entRepo.save(entity);
            map.put("status", true);
            map.put("message", "엔터 회사 추가");
        }
        // repo에 입력한 이름의 장르가 있으면 false
        else {
            map.put("status", false);
            map.put("message", name+" 는 이미 존재합니다.");
        }
        return map;
    }

    // 엔터 회사 조회
    public EntCompanyListResponseVO getEntCompanyList(String keyword, Pageable pageable) {
        Page<EntCompanyInfoEntity> page = entRepo.findByNameContains(keyword, pageable);
        EntCompanyListResponseVO response = EntCompanyListResponseVO.builder()
            .list(page.getContent())
            .total(page.getTotalElements())
            .totalPage(page.getTotalPages())
            .currentPage(page.getNumber())
            .build();
        return response;
    }
    
    // 엔터 회사 검색
    public Map<String, Object> getEntCompanySearch(Long seq) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<EntCompanyInfoEntity> optEntity = entRepo.findById(seq);
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

    // 엔터 회사 수정
    public Map<String, Object> updateEntCompany(Long seq, String name) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<EntCompanyInfoEntity> optEntity = entRepo.findById(seq);
        if(optEntity.isEmpty()) {
            // entity seq가 비어있으면(없으면)
            map.put("status", false);
            map.put("seq", seq);
            map.put("name", name);
            map.put("message", "잘못된 장르정보 입니다.");
        }
        // 기존에 있는 이름과 같다면 변경불가 단, repo에서 검증을 해서 가져오는 것이 더 좋은거 같음
        else if(optEntity.get().getName().equalsIgnoreCase(name)) {
            map.put("status", false);
            map.put("seq", seq);
            map.put("name", name);
            map.put("message", "기존 설정된 이름으로 변경 불가능합니다.");
        }
        // 이미 존재하는 장르인지 확인
        else if(entRepo.countByName(name) != 0) {
            map.put("status", false);
            map.put("seq", seq);
            map.put("name", name);
            map.put("message", name+" 장르는 이미 존재합니다.");
        }
        else {
        // 유효성 검사가 끝났으니 entity를 가져와서
        EntCompanyInfoEntity entity = EntCompanyInfoEntity.builder()
                .seq(seq)
                .name(name)
                .build();
                // 저장소에 저장을 함.
            entRepo.save(entity);
            map.put("status", true);
        }
        // 마지막까지 실행되고 난 값을 반환
        return map;
    }

    // 엔터 회사 삭제
    @Transactional
    public void deleteEntCompany(Long seq) {
        entRepo.deleteById(seq);
    }
}
