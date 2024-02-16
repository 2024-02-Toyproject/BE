package smu.toyproject1.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedDepositProduct {

    private String bankName;
    private String productName;
    private String joinMethod;
    private String maturityInterestRate;
    private String preferentialConditions;
    private String joinRestrictions;
    private String targetCustomers;
    private int maximumLimit;
    private String interestRateType;
    private double interestRate;
    private double maximumPreferentialRate;

    // 생성자
    public FixedDepositProduct(String bankName, String productName, String joinMethod, String maturityInterestRate, String preferentialConditions, String joinRestrictions, String targetCustomers, int maximumLimit, String interestRateType, double interestRate, double maximumPreferentialRate) {
        this.bankName = bankName;
        this.productName = productName;
        this.joinMethod = joinMethod;
        this.maturityInterestRate = maturityInterestRate;
        this.preferentialConditions = preferentialConditions;
        this.joinRestrictions = joinRestrictions;
        this.targetCustomers = targetCustomers;
        this.maximumLimit = maximumLimit;
        this.interestRateType = interestRateType;
        this.interestRate = interestRate;
        this.maximumPreferentialRate = maximumPreferentialRate;
    }
}
