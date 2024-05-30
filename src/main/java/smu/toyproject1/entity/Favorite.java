package smu.toyproject1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

    @Column
    private String bankName;

    @Column
    private String productName;

    // public 또는 protected 기본 생성자 추가
    protected Favorite() {
    }

    public Favorite(String memberId, String bankName, String productName) {
        // 아래 코드도 수정합니다. 올바르게 초기화하려면 매개변수 값을 필드에 할당해야 합니다.
        this.memberId = memberId;
        this.bankName = bankName;
        this.productName = productName;
    }
}
