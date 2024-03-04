package smu.toyproject1.service;

import org.springframework.stereotype.Service;
import smu.toyproject1.dto.FavDTO;
import smu.toyproject1.entity.FavEntity;
import smu.toyproject1.repository.FavRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavService {
    private final FavRepository favRepository;

    public FavService(FavRepository favRepository) {
        this.favRepository = favRepository;
    }

    public void save(FavDTO favDTO) {
        FavEntity favEntity = new FavEntity();
        favEntity.setMemberEmail(favDTO.getMemberEmail());
        favEntity.setProduct_id(favDTO.getProduct_id());
        favRepository.save(favEntity);
    }

    public List<FavDTO> getFavoriteProducts(String memberEmail) {
        List<FavEntity> favEntities = favRepository.findAllByMemberEmail(memberEmail);
        return favEntities.stream()
                .map(FavDTO::toFavDTO) // 정적 메소드 참조 사용
                .collect(Collectors.toList());

    }
}
