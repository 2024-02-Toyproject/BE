package smu.toyproject1.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Getter @Setter
public class InstallmentSavingProduct {
    private String company;
    private String productName;
    private String applicationMethod;
    private String maturityInterestRate;
    private String preferentialConditions;
    private String eligibilityRestrictions;
    private String targetCustomers;
    private Long maximumLimit;
    private String savingsInterestRateTypeName;
    private Double savingsInterestRate;
    private Double maximumPreferentialRate;

    public InstallmentSavingProduct(String company, String productName, String applicationMethod,
                                    String maturityInterestRate, String preferentialConditions,
                                    String eligibilityRestrictions, String targetCustomers, Long maximumLimit,
                                    String savingsInterestRateTypeName, Double savingsInterestRate, Double maximumPreferentialRate) {
        this.company = company;
        this.productName = productName;
        this.applicationMethod = applicationMethod;
        this.maturityInterestRate = maturityInterestRate;
        this.preferentialConditions = preferentialConditions;
        this.eligibilityRestrictions = eligibilityRestrictions;
        this.targetCustomers = targetCustomers;
        this.maximumLimit = maximumLimit;
        this.savingsInterestRateTypeName = savingsInterestRateTypeName;
        this.savingsInterestRate = savingsInterestRate;
        this.maximumPreferentialRate = maximumPreferentialRate;
    }
}
