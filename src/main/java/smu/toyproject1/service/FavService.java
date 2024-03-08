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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavService {
    private final MemberRepository memberRepository;
    private final FavRepository favRepository;

    public void save(FavDTO favDTO) {
        FavEntity favEntity = FavEntity.toFavEntity(favDTO);
        favEntity.setMemberEmail(favDTO.getMemberEmail());
        favRepository.save(favEntity);
    }

    @PostMapping("/fav/save")
    public FavDTO addFav(FavDTO favDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
         */
        Optional<FavEntity> byMemberEmail = favRepository.findByMemberEmail(favDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            FavEntity favEntity = byMemberEmail.get();
            FavDTO dto = FavDTO.toFavDTO(favEntity);
            return dto;
        }
        return null;
    }

}

