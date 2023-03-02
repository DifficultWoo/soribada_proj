package com.example.soribada_renewal_pro.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.soribada_renewal_pro.entity.EntCompanyInfoEntity;

public interface EntCompanyInfoRepository extends JpaRepository<EntCompanyInfoEntity, Long>{
    // countBy로 같은거 있는지 확인용
    public Integer countByName(String name);

    // JPA 페이징처리
    public Page<EntCompanyInfoEntity> findByNameContains(String name, Pageable pageable);
}
