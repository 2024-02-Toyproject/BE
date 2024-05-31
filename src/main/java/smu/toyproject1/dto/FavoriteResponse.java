package smu.toyproject1.dto;

import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.entity.Favorite;
import smu.toyproject1.entity.FixedDepositProduct;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
public class FavoriteResponse {
    private List<Favorite> favoriteList;

    public FavoriteResponse(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }
}

