package smu.toyproject1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.FavEntity;
import smu.toyproject1.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavRepository extends JpaRepository<FavEntity, Long> {
}
