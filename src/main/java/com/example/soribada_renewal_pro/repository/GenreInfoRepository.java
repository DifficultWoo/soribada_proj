package com.example.soribada_renewal_pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;

public interface GenreInfoRepository extends JpaRepository<GenreInfoEntity, Long>{
    public Integer countByName(String genreName);
}
