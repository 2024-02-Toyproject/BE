package smu.toyproject1.dto;
import smu.toyproject1.entity.FavEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FavDTO {
    private String member_email;
    private String company;
    private String productName;

    // FavEntity 객체를 받아 FavDTO 객체로 변환
    public static FavDTO toFavDTO(FavEntity favEntity) {
        FavDTO favDTO = new FavDTO();
        favDTO.setCompany(favEntity.getCompany());
        favDTO.setProductName(favEntity.getProductName());
        return favDTO;
    }

}