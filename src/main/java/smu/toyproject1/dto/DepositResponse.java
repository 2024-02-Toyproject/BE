package smu.toyproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.entity.FixedDepositProduct;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
public class DepositResponse {
    private List<FixedDepositProduct> depositProducts;

    public DepositResponse(List<FixedDepositProduct> depositProducts) {
        this.depositProducts = depositProducts;
    }
}

