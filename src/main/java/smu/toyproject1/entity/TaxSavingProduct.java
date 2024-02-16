package smu.toyproject1.entity;

import lombok.*;

@Getter
@Setter
public class TaxSavingProduct {
    private String financialProduct;
    private String mainSalesCompany;
    private String categoryType;
    private String taxBenefits;
    private String eligibility;
    private String subscriptionLimit;
    private String legalBasis;

    public TaxSavingProduct(String financialProduct, String mainSalesCompany, String categoryType, String taxBenefits, String eligibility, String subscriptionLimit, String legalBasis) {
        this.financialProduct = financialProduct;
        this.mainSalesCompany = mainSalesCompany;
        this.categoryType = categoryType;
        this.taxBenefits = taxBenefits;
        this.eligibility = eligibility;
        this.subscriptionLimit = subscriptionLimit;
        this.legalBasis = legalBasis;
    }
}

