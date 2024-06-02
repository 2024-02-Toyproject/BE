package smu.toyproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.toyproject1.entity.Favorite;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByMemberId(String memberId);
    Optional<Favorite> findByMemberIdAndBankNameAndProductName(String memberId, String bankName, String productName);
}