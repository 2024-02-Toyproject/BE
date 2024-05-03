package smu.toyproject1.dto;

import lombok.Data;

@Data
public class SavingRequest {
    private String bank;
    private String joinWay;
    private String joinObject;
    private String sortWay;
    private Object searchWord;
}
