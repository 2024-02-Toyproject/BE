package smu.toyproject1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.dto.FavDTO;

@Entity
@Setter
@Getter
@Table(name="user_interest")
public class FavEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String loginName;

    @Column // unique 제약조건 추가
    private String company;

    @Column
    private String productName;

    public static FavEntity toFavEntity(FavDTO favDTO){
        FavEntity favEntity = new FavEntity();
        favEntity.setLoginName(favDTO.getLoginName());
        favEntity.setCompany(favDTO.getCompany());
        favEntity.setProductName(favDTO.getProductName());
        return favEntity;
    }

}