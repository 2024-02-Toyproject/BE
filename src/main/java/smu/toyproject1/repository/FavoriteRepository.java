package smu.toyproject1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import smu.toyproject1.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 필요한 경우 커스텀 쿼리 메소드 추가
}