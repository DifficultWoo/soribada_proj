package com.example.soribada_renewal_pro.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;
import com.example.soribada_renewal_pro.repository.GenreInfoRepository;
import com.example.soribada_renewal_pro.vo.GenreInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreInfoService {
    private final GenreInfoRepository genreRepo;

    //장르 정보 입력
    public Map<String, Object> addGenreInfo(String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        if(genreRepo.countByName(name) == 0) {
            GenreInfoEntity entity = GenreInfoEntity.builder()
            .name(name)
            .build();
            genreRepo.save(entity);
            map.put("status", true);
            map.put("message", "장르정보 추가");
        }
        else {
            map.put("status", false);
            map.put("message", name+"는 이미 존재합니다.");
        }
        return map;
    }

    // 장르 정보 조회
    public List<GenreInfoVO> GenreInfolist() {
        List<GenreInfoVO> voList = new ArrayList<>();
        List<GenreInfoEntity> entity = genreRepo.findAll();
        for(GenreInfoEntity e : entity) {
            voList.add(new GenreInfoVO(e)); //가져올때 vo에 생성자 생성
        }
        return voList;
    }

    // 장르 정보 수정
    public Map<String, Object> updateGenreInfo(Long seq, String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<GenreInfoEntity> entity = genreRepo.findById(seq);
        if(entity.isEmpty()) {
            map.put("status", false);
        }
        return null;
    }


}
