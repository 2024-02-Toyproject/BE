package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.toyproject1.dto.FavDTO;
import smu.toyproject1.entity.FavEntity;
import smu.toyproject1.repository.FavRepository;

@Service
@RequiredArgsConstructor
public class FavService {
    private final FavRepository favRepository;

    public void save(FavDTO favDTO) {
        FavEntity favEntity = new FavEntity();
        favEntity.setMemberEmail(favDTO.getMemberEmail());
        favEntity.setCompany(favDTO.getCompany());
        favEntity.setProductName(favDTO.getProductName());
        favRepository.save(favEntity);
    }
}
