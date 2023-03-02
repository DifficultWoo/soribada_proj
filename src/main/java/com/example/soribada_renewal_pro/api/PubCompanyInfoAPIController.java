package com.example.soribada_renewal_pro.api;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.soribada_renewal_pro.service.PubCompanyInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pubCompany")
public class PubCompanyInfoAPIController {
    private final PubCompanyInfoService pubService;

    // 발매사 입력
    @PostMapping("/add")
    public ResponseEntity<Object> postPubCompanyAdd(@RequestParam String name) {
        Map<String, Object> map = pubService.addPubCompany(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 발매사 조회
    @GetMapping("/list")
    public ResponseEntity<Object> getPubCompanyList(
        @RequestParam @Nullable String keyword,
        @PageableDefault(size = 10, sort="seq", direction = Sort.Direction.DESC) Pageable pageable
        ) {
        if(keyword == null) keyword = ""; // undefind를 막기 위해 초기화해줌
        return new ResponseEntity<>(pubService.getPubCompanyList(keyword, pageable), HttpStatus.OK);
    }

    // 발매사 검색 - 번호
    @GetMapping("/search")
    public ResponseEntity<Object> getEntCompanySearch(
        @RequestParam Long seq,
        @RequestParam @Nullable Integer page,
        @RequestParam @Nullable String keyword
    ) {
        if(page == null) page = 0;
        if(keyword == null) keyword = "";
        Map<String, Object> map = pubService.getPubCompanySearch(seq);
        if((boolean) map.get("status")){
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

}
