package smu.toyproject1.dto;

import lombok.Getter;
import lombok.Setter;
import smu.toyproject1.entity.CreditLoanProduct;
import smu.toyproject1.entity.FixedDepositProduct;

import java.util.List;

@Getter
@Setter
public class LoanResponse {
    private List<CreditLoanProduct> loanProducts;

    public LoanResponse(List<CreditLoanProduct> loanProducts) {
        this.loanProducts = loanProducts;
    }
}
