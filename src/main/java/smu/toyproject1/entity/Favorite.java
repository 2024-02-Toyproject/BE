package smu.toyproject1.entity;
import lombok.*;

@Getter
@Setter
public class Favorite {
    private String loginName;
    private String company;
    private String productName;

    public Favorite(String loginName, String company, String productName) {
        this.loginName = loginName;
        this.company = company;
        this.productName = productName;
    }
}
