package smu.toyproject1.dto;

import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.entity.TaxSavingProduct;

import java.util.List;

@Getter
@Setter
public class TaxSavingResponse {
    private List<TaxSavingProduct> taxSavingProducts;

    public TaxSavingResponse(List<TaxSavingProduct> taxSavingProducts) {
        this.taxSavingProducts = taxSavingProducts;
    }
}
