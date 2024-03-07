package smu.toyproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.FavEntity;

import java.util.List;

@Repository
public interface FavRepository extends JpaRepository<FavEntity, Long> {
    List<FavEntity> findByMemberEmail(String memberEmail);
    // 필요한 추가 메서드 정의 가능
}
