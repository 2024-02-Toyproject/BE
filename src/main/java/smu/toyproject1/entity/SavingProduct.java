package smu.toyproject1.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SavingProduct {
    private String company;
    private String productName;
    private String joinMethod;
    private String maturityInterestRate;
    private String preferentialConditions;
    private String eligibilityRestrictions;
    private String targetCustomers;
    private int maximumLimit;
    private String savingsInterestRateType;
    private double savingsInterestRate;
    private double maximumPreferentialRate;

    public SavingProduct(String company, String productName, String joinMethod,
                         String maturityInterestRate, String preferentialConditions,
                         String eligibilityRestrictions, String targetCustomers, int maximumLimit,
                         String savingsInterestRateType, double savingsInterestRate, double maximumPreferentialRate) {
        this.company = company;
        this.productName = productName;
        this.joinMethod = joinMethod;
        this.maturityInterestRate = maturityInterestRate;
        this.preferentialConditions = preferentialConditions;
        this.eligibilityRestrictions = eligibilityRestrictions;
        this.targetCustomers = targetCustomers;
        this.maximumLimit = maximumLimit;
        this.savingsInterestRateType = savingsInterestRateType;
        this.savingsInterestRate = savingsInterestRate;
        this.maximumPreferentialRate = maximumPreferentialRate;
    }
}
