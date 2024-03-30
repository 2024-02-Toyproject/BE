package smu.toyproject1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data // getter, setter 메서드를 자동 생성
public class DepositRequest {
    private String bank;
    private String joinWay;
    private String joinObject;
    private String sortWay;

//    // 기본 생성자
//    public DepositRequest() {
//    }
}

