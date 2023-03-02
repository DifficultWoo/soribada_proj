package com.example.soribada_renewal_pro.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.soribada_renewal_pro.entity.GenreInfoEntity;
import com.example.soribada_renewal_pro.repository.GenreInfoRepository;
import com.example.soribada_renewal_pro.vo.Genre.GenreInfoVO;
import com.example.soribada_renewal_pro.vo.Genre.GenreListResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreInfoService {
    private final GenreInfoRepository genreRepo;

    //장르 정보 입력
    public Map<String, Object> addGenreInfo(String name) {
        // 맵형식으로 생성.(만들때 controller와 같은 형식)
        Map<String, Object> map = new LinkedHashMap<>();
        // 맵 안에 담길 테이블 객체를 가져오고, 객체에 입력해줄 부분을 가져옴
        if(genreRepo.countByName(name) == 0) {
            // 저장소 안에 같은 이름이 없으면 저장,
            GenreInfoEntity entity = GenreInfoEntity.builder()
            .name(name)
            .build();
            genreRepo.save(entity);
            map.put("status", true);
            map.put("message", "장르정보 추가");
        }
        else {
            // 1개이상 있다면 이미 있으니 저장안됨
            map.put("status", false);
            map.put("message", name+"는 이미 존재합니다.");
        }
        // 저장된 객체를 반환
        return map;
    }

    // // 장르 정보 조회
    // // 리스트를 가져와야하기때문에 화면에 보여줄 VO형태를 List형식으로 보여준다.
    // public List<GenreInfoVO> GenreInfolist() {
    //     // 보여줄 List를 생성해주고
    //     List<GenreInfoVO> voList = new ArrayList<>();
    //     // Entity 테이블에서 리스트에 있는 것을 모두 찾는다(seq와 name밖에 없음).
    //     List<GenreInfoEntity> entity = genreRepo.findAll();
    //     // Entity 테이블을 돌면서 생성한 voList에 Entity에서 가져온 정보 e를 
    //     // voList마다 add추가 하며 List객체에 답는다.
    //     for(GenreInfoEntity e : entity) {
    //         voList.add(new GenreInfoVO(e));
    //     }
    //     // 마지막까지 실행되고 난 값을 반환
    //     return voList;
    // }

    // 장르 정보 조회 - 페이징 처리 하기!
    // 리스트를 가져와야하기때문에 화면에 보여줄 VO형태를 List형식으로 보여준다.
    // VO 안에 genreEntity List 형태로 넣고, 페이지 상태 값 넣어줌
    public GenreListResponseVO getGenreInfoList(String keyword, Pageable pageable) {
        // Page처리<Entity> 테이블에서 리스트에 있는 것을 모두 찾는다
        // 변수로 찾을 keyword와 pageable로 페이징 처리가 된 저장소repo에서 가져와야함
        Page<GenreInfoEntity> page = genreRepo.findByNameContains(keyword, pageable);
        // build로 필요한 것들만 들고옴
        GenreListResponseVO response = GenreListResponseVO.builder()
            .list(page.getContent())        // getContent 조회된 데이터
            .total(page.getTotalElements()) // getTotalElements 전체 데이터 수
            .totalPage(page.getTotalPages())// getTotalPages 전체 페이지 수
            .currentPage(page.getNumber())  // getNumber 현재 페이지
            .build();
        // 마지막까지 실행되고 난 값을 반환
        return response;
    }

    // 장르 검색
    public Map<String, Object> getGenreSearch(Long seq) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<GenreInfoEntity> optEntity = genreRepo.findById(seq);
        if(optEntity.isEmpty()) {
            map.put("status", false);
        }
        else {
            map.put("status", true);
            map.put("seq", optEntity.get().getSeq());
            map.put("name", optEntity.get().getName());
        }
        return map;
    }

    // 장르 정보 수정 ,(변수명은? 바꿔주는 변수만 가져오면 됨)
    public Map<String, Object> updateGenreInfo(Long seq, String name) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        // Optional 은 반환값이 없을 수도 있다고 미리 가정하고, 이 반환값을 통제하기 위해 사용함.
        // 정보 통제 Optional, 저장소에서 seq를 찾아온다
        Optional<GenreInfoEntity> optEntity = genreRepo.findById(seq);
        if(optEntity.isEmpty()) {
            // entity seq가 비어있으면(없으면)
            map.put("status", false);
            map.put("seq", seq);
            map.put("name", name);
            map.put("message", "잘못된 장르정보 입니다.");
        }
        // 기존에 있는 이름과 같다면 변경불가 단, repo에서 검증을 해서 가져오는 것이 더 좋은거 같음
        else if(optEntity.get().getName().equalsIgnoreCase(name)) {
            map.put("status", false);
            map.put("seq", seq);
            map.put("name", name);
            map.put("message", "기존 설정된 이름으로 변경 불가능합니다.");
        }
        // 이미 존재하는 장르인지 확인
        else if(genreRepo.countByName(name) != 0) {
            map.put("status", false);
            map.put("seq", seq);
            map.put("name", name);
            map.put("message", name+" 장르는 이미 존재합니다.");
        }
        else {
        // 유효성 검사가 끝났으니 entity를 가져와서
            GenreInfoEntity entity = GenreInfoEntity.builder()
                .seq(seq)
                .name(name)
                .build();
                // 저장소에 저장을 함.
            genreRepo.save(entity);
            map.put("status", true);
        }
        // 마지막까지 실행되고 난 값을 반환
        return map;
    }

    // 장르 정보 삭제 Delete의 경우 삭제되지 않으면 (결제나 삭제 같은 독립적인 경우에 사용)
    // @Transactional 과정중 다른연산이 끼어들수 없다, 오류가 날경우 원상태복귀, 성공할 경우 결과 반영
    @Transactional
    public void deleteGenre(Long seq) {
        // void는 리턴문이 필요없음, repo에서 delete삭제되면 끝
        genreRepo.deleteById(seq);
    }

}
