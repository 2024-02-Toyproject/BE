package smu.toyproject1.dto;

import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.entity.SavingProduct;

import java.util.List;

@Getter
@Setter
public class SavingResponse {
    private List<SavingProduct> savingProducts;

    public SavingResponse(List<SavingProduct> savingProducts) {
        this.savingProducts = savingProducts;
    }
}
