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
        this.memberId = memberId;
        this.bankName = bankName;
        this.productName = productName;
    }
}
