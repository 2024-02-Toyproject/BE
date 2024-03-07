package smu.toyproject1.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavDTO {
    private String memberEmail; // 사용자 이메일
    private String company;     // 금융회사 명
    private String productName; // 금융 상품명
}
