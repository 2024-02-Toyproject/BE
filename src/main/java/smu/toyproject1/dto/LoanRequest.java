package smu.toyproject1.dto;

import lombok.Data;

@Data
public class LoanRequest {
    private String bank;
    private String joinWay;
    private String loanType;
    private String sortWay;
    private Object searchWord;
}
