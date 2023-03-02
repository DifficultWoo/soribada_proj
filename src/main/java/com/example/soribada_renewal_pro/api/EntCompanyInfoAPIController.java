package com.example.soribada_renewal_pro.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.soribada_renewal_pro.service.EntCompanyInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entCompany")
public class EntCompanyInfoAPIController {
    private final EntCompanyInfoService entService;

    // 엔터 회사 입력 //405번 Post put 이런거 잘못입력
    @PostMapping("/add")
    public ResponseEntity<Object> postEntCompanyAdd(@RequestParam String name) {
        Map<String, Object> map = entService.addEntCompany(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 엔터 회사 조회
    @GetMapping("/list")
    public ResponseEntity<Object> getEntCompanyList(
        @RequestParam @Nullable String keyword,
        @PageableDefault(size = 10, sort="seq", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if(keyword == null) keyword = "";
        return new ResponseEntity<>(entService.getEntCompanyList(keyword, pageable), HttpStatus.OK);
    }

    // 엔터 회사 검색 - 번호
    @GetMapping("/search")
    public ResponseEntity<Object> getEntCompanySearch(
        @RequestParam Long seq,
        @RequestParam @Nullable Integer page,
        @RequestParam @Nullable String keyword
    ) {
        if(page == null) page = 0;
        if(keyword == null) keyword = "";
        Map<String, Object> map = entService.getEntCompanySearch(seq);
        if((boolean) map.get("status")){
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    // 엔터 회사 수정
    @PatchMapping("/update")    
    public ResponseEntity<Object> patchGenreUpdate(@RequestParam Long seq, @RequestParam String name) {
        Map<String, Object> map = entService.updateEntCompany(seq, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 엔터 회사 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteGenre(@RequestParam Long seq) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        entService.deleteEntCompany(seq);
        map.put("message", "장르정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    
}
