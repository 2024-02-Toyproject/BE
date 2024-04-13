package smu.toyproject1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowire;

@Data // getter, setter 메서드를 자동 생성
public class DepositRequest {
    private String bank;
    private String joinWay;
    private String joinObject;
    private String sortWay;
    private Object searchWord; // (추가) 검색어, 데이터 타입 상관없이 넘겨받을 수 있음

//    // 기본 생성자
//    public DepositRequest() {
//    }
}

