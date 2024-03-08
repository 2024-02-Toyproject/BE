package smu.toyproject1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.dto.FavDTO;

@Entity
@Setter
@Getter
@Table(name="UserInterest")
public class FavEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column // unique 제약조건 추가
    private String memberEmail;

    @Column
    private Long product_id;

    public static FavEntity toFavEntity(FavDTO favDTO){
        FavEntity favEntity = new FavEntity();
        favEntity.setMemberEmail(favDTO.getMemberEmail());
        favEntity.setProduct_id(favDTO.getProduct_id());
        return favEntity;
    }

}