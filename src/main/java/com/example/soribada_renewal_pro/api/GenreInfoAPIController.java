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

import com.example.soribada_renewal_pro.service.GenreInfoService;
import com.example.soribada_renewal_pro.vo.Genre.GenreListResponseVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreInfoAPIController {
    private final GenreInfoService genreService;

    // 장르 정보 입력
    // PUT http://localhost:9999/api/genre/add
    // Body에서 key > name value 이름
    @PostMapping("/add")
    public ResponseEntity<Object> postGenreAdd(@RequestParam String name) {
        Map<String, Object> map =genreService.addGenreInfo(name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // // 장르 정보 조회 
    // @GetMapping("/list")
    // // ResponseEntity에서 돌려줄 객체가 List형태의 vo에서 가져온 것
    // public ResponseEntity<List<GenreInfoVO>> getGenreList() {
    //     // ResponseEntity에서 service기능에서 만들어 놓은 GenreInfolist()를 가져오고 상태메시지도 띄워줌
    //     return new ResponseEntity<List<GenreInfoVO>>(genreService.GenreInfolist(), HttpStatus.OK);
    // }

    // 장르 정보 조회 - 페이징 처리 하기!
    @GetMapping("/list")
    // ResponseEntity에서 돌려줄 객체가 List형태의 vo에서 가져온 것
    public ResponseEntity<GenreListResponseVO> getGenreList(
        @RequestParam @Nullable String keyword,
        @PageableDefault(size = 10, sort="seq", direction = Sort.Direction.DESC) Pageable pageable
        ) {
            if(keyword == null) keyword = "";
        // ResponseEntity에서 service기능에서 만들어 놓은 GenreInfolist()를 가져오고 상태메시지도 띄워줌
        return new ResponseEntity<>(genreService.getGenreInfoList(keyword, pageable), HttpStatus.OK);
    }

    // 장르 검색 - 페이징 처리 - 번호검색
    @GetMapping("/search")
    public ResponseEntity<Object> getGenreSearch(
        @RequestParam Long seq,
        @RequestParam @Nullable Integer page,
        @RequestParam @Nullable String keyword
    ) {
        if(page == null) page = 0;
        if(keyword == null) keyword = "";
        Map<String, Object> map = genreService.getGenreSearch(seq);
        if((boolean) map.get("status")){
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

    }

    // 장르 정보 수정
    // PATCH http://localhost:9999/api/genre/update?seq=9&name=CCM
    @PatchMapping("/update")    
    // ResponseEntity는 Http상태메시지를 알려줘야 하기 때문에 씀
    public ResponseEntity<Object> patchGenreUpdate(@RequestParam Long seq, @RequestParam String name) {
        // Service 기능구현에서 Map형태로 저장을 했기때문에 Map형식으로 가져와야함
        Map<String, Object> map = genreService.updateGenreInfo(seq, name);
        // 가져온 map과 ResponseEntity에서 Http상태메시지를 보여줘야 하기 때문에
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 장르 정보 삭제
    // DELETE http://localhost:9999/api/genre/delete?seq=16
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteGenre(@RequestParam Long seq) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        genreService.deleteGenre(seq);
        map.put("message", "장르정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }


}
