package smu.toyproject1.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditLoanProduct {

    private String company;
    private String productName;
    private String method;
    private String loanType;
    private String cbCompany;
    private String rateType;
    private double averageRate;

    public CreditLoanProduct(String company, String productName, String method, String loanType, String cbCompany, String rateType, double averageRate) {
        this.company = company;
        this.productName = productName;
        this.method = method;
        this.loanType = loanType;
        this.cbCompany = cbCompany;
        this.rateType = rateType;
        this.averageRate = averageRate;

    }

    /*
    각 변수에 대한 Getter & Setter 생성
    : lombok 사용 -> annotation(@Getter @Setter)으로 처리
    */
}