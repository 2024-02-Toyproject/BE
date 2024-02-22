package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import smu.toyproject1.entity.CreditLoanProduct;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.entity.InstallmentSavingProduct;
import smu.toyproject1.entity.TaxSavingProduct;
import smu.toyproject1.repository.JdbcDBConnection;

import java.util.List;

@Controller
public class ProductController {

    /*
    jdbcDBConnection 객체를 올바르게 초기화하기 위해
    1. @Autowired 어노테이션을 사용하여 의존성 주입을 수행
    2. 생성자를 통해 jdbcDBConnection 객체를 주입받도록 수정
     */
    @Autowired // 의존성 주입
    private JdbcDBConnection jdbcDBConnection;

    // 신용대출 상품 목록 조회
    @GetMapping("/creditLoan")
    public String getCreditLoanProducts(Model model) {
        List<CreditLoanProduct> creditLoans = jdbcDBConnection.retrieveCLDataFromTable("신용대출");
        model.addAttribute("creditLoans", creditLoans);
        return "products/creditLoanProductsList";
    }

    // 정기예금 상품 목록 조회
    @GetMapping("/fixedDeposit")
    public String getFixedDepositProducts(Model model) {
        List<FixedDepositProduct> fixedDeposits = jdbcDBConnection.retrieveFDDataFromTable("정기예금");
        model.addAttribute("fixedDeposits", fixedDeposits);
        return "products/fixedDepositProductsList";
    }

    // 적금 상품 목록 조회
    @GetMapping("/installmentSaving")
    public String getInstallmentSavingProducts(Model model) {
        List<InstallmentSavingProduct> installmentSavings = jdbcDBConnection.retrieveISDataFromTable("적금");
        model.addAttribute("installmentSavings", installmentSavings);
        return "products/installmentSavingProductsList";
    }

    // 절세금융 상품 목록 조회
    @GetMapping("/taxSaving")
    public String getTaxSavingProducts(Model model) {
        List<TaxSavingProduct> taxSavings = jdbcDBConnection.retrieveTSDataFromTable("절세금융상품");
        model.addAttribute("taxSavings", taxSavings);
        return "products/taxSavingProductsList";
    }
}


