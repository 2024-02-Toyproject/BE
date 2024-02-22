package smu.toyproject1.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Getter @Setter
public class InstallmentSavingProduct {
    private String company;
    private String productName;
    private String accrualMethod;
    private String preTaxInterestRate;
    private String postTaxInterestRate;
    private String postTaxInterest;
    private String maximumPreferentialInterestRate;
    private String membershipRestrictionStatus;
    private String interestCalculationMethod;

    public InstallmentSavingProduct(String company, String productName, String accrualMethod, String preTaxInterestRate, String postTaxInterestRate, String postTaxInterest, String maximumPreferentialInterestRate, String membershipRestrictionStatus, String interestCalculationMethod) {
        this.company = company;
        this.productName = productName;
        this.accrualMethod = accrualMethod;
        this.preTaxInterestRate = preTaxInterestRate;
        this.postTaxInterestRate = postTaxInterestRate;
        this.postTaxInterest = postTaxInterest;
        this.maximumPreferentialInterestRate = maximumPreferentialInterestRate;
        this.membershipRestrictionStatus = membershipRestrictionStatus;
        this.interestCalculationMethod = interestCalculationMethod;
    }
}
