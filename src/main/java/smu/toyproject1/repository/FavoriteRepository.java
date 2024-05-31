package smu.toyproject1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import smu.toyproject1.entity.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByMemberId(String memberId);
}