package com.example.soribada_renewal_pro.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.soribada_renewal_pro.service.GenreInfoService;
import com.example.soribada_renewal_pro.vo.GenreInfoVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreInfoAPIController {
    private final GenreInfoService genreService;

    //장르 정보 입력
    @PutMapping("/add")
    public ResponseEntity<Object> putGenreAdd(@RequestParam String name) {
        Map<String, Object> map =genreService.addGenreInfo(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    //장르 출력
    @GetMapping("/list")
    public ResponseEntity<List<GenreInfoVO>> getGenreList() {
        return new ResponseEntity<List<GenreInfoVO>>(genreService.GenreInfolist(), HttpStatus.OK);
    }

    //장르 정보 수정
    // @PatchMapping("/update")
    // public ResponseEntity<Object> patchGenreUpdate(@RequestBody GenreInfoVO data) {
    //      Map<String, Object> map = genreService.

    // }


}
