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
    private String memberEmail;
    private Long product_id;

    // FavEntity 객체를 받아 FavDTO 객체로 변환
    public static FavDTO toFavDTO(FavEntity favEntity) {
        FavDTO favDTO = new FavDTO();
        favDTO.setMemberEmail(favEntity.getMemberEmail());
        favDTO.setProduct_id(favEntity.getProduct_id());
        // 다른 필드들도 필요하다면 여기서 설정
        return favDTO;
    }

}
