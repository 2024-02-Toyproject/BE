package smu.toyproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.FavEntity;

import java.util.List;

@Repository
public interface FavRepository extends JpaRepository<FavEntity, Long> {
    // 사용자의 이메일로 모든 관심 상품 엔티티를 조회합니다.
    List<FavEntity> findAllByMemberEmail(String memberEmail);
}