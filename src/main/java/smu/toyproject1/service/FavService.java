package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import smu.toyproject1.dto.FavDTO;
import smu.toyproject1.dto.MemberDTO;
import smu.toyproject1.entity.FavEntity;
import smu.toyproject1.entity.MemberEntity;
import smu.toyproject1.repository.FavRepository;
import smu.toyproject1.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavService {
    private final FavRepository favRepository;

    public void save(FavDTO favDTO) {
        FavEntity favEntity = FavEntity.toFavEntity(favDTO);
        favRepository.save(favEntity);
    }


    @PostMapping("/fav/save")
    public FavDTO addFav(FavDTO favDTO) {
        // DTO를 Entity로 변환
        FavEntity favEntity = FavEntity.toFavEntity(favDTO);
        // Entity를 저장
        favEntity = favRepository.save(favEntity);
        // 저장된 Entity를 다시 DTO로 변환하여 반환
        return FavDTO.toFavDTO(favEntity);
    }

}

