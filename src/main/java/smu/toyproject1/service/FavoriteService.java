package smu.toyproject1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.entity.Favorite;
import smu.toyproject1.repository.FavoriteRepository;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public void saveFavorite(String memberId, String bankName, String productName) {
        Favorite favorite = new Favorite(memberId, bankName, productName);
        favoriteRepository.save(favorite);
    }

    // 필요한 경우 다른 메소드 추가
}