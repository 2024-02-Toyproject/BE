package smu.toyproject1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.entity.Favorite;
import smu.toyproject1.repository.FavoriteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public void saveFavorite(String memberId, String bankName, String productName) {
        Favorite favorite = new Favorite(memberId, bankName, productName);
        favoriteRepository.save(favorite);
    }

    public List<Favorite> findByMemberId(String memberId) {
        return favoriteRepository.findByMemberId(memberId);
    }

    public boolean checkDuplicate(String memberId, String bankName, String productName) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByMemberIdAndBankNameAndProductName(memberId, bankName, productName);
        return existingFavorite.isPresent();
    }

    public void deleteFavorite(String memberId, String bankName, String productName) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByMemberIdAndBankNameAndProductName(memberId, bankName, productName);
        if (existingFavorite.isPresent()) {
            favoriteRepository.delete(existingFavorite.get());
        }
    }
}