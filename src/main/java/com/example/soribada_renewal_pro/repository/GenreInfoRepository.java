package com.example.soribada_renewal_pro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;

public interface GenreInfoRepository extends JpaRepository<GenreInfoEntity, Long>{
    //JPA countBy로 같은 것이 1개 이상있는지 확인하기 위해서
    public Integer countByName(String genreName);
    
    //JPA 페이징처리 하기 Contains 는 SQL Like 문과 비슷함
    public Page<GenreInfoEntity> findByNameContains(String name, Pageable pageable);
}
