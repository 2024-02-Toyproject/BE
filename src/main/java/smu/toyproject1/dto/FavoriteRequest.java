package smu.toyproject1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteRequest {
    // Setter 메서드 추가
    @JsonProperty("memberId")
    private String memberId;

    @JsonProperty("bankName")
    private String bankName;

    @JsonProperty("productName")
    private String productName;

}