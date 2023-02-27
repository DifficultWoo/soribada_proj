package com.example.soribada_renewal_pro.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.soribada_renewal_pro.service.GenreInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreInfoAPIController {
    private final GenreInfoService genreInfoService;

    //장르 정보 입력
    @PutMapping("/add")
    public ResponseEntity<Object> putGenreAdd(@RequestParam String name) {
        Map<String, Object> map =genreInfoService.addGenreInfo(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
