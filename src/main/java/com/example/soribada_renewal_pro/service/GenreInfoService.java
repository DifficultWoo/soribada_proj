package com.example.soribada_renewal_pro.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;
import com.example.soribada_renewal_pro.repository.GenreInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreInfoService {
    private final GenreInfoRepository genreRepo;

    //장르 정보 입력
    public Map<String, Object> addGenreInfo(String name) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if(genreRepo.countByName(name) == 0) {
            GenreInfoEntity entity = GenreInfoEntity.builder()
            .name(name)
            .build();
            resultMap.put("status", true);
            resultMap.put("message", "장르정보 추가");
        }
        else {
            resultMap.put("status", false);
            resultMap.put("message", name+"는 이미 존재합니다.");
        }
        return resultMap;
    }
}
