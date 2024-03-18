package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import smu.toyproject1.entity.*;
import smu.toyproject1.repository.JdbcDBConnection;
import smu.toyproject1.service.DepositService;

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
    @Autowired
    private DepositService depositService;

    // 정기예금 상품 목록 조회 (수정완료)
    @GetMapping("/deposit")
    public String getFixedDepositProducts(Model model) {
        List<FixedDepositProduct> fixedDeposits = depositService.getAllDeposits();
        // 가져온 데이터들을 model에 담아서 fixedDepositProductsList에 넘김
        model.addAttribute("fixedDeposits", fixedDeposits);
//        return "products/fixedDepositProductsList";
        return "pages/product/DepositPage";
    }

    @PostMapping("/deposit")
    public String searchAndFilterDeposits(
            @RequestParam("searchWord") String searchWord,
            @RequestParam("selectedBank") String selectedBank,
            @RequestParam("selectedJoinWay") String selectedJoinWay,
            @RequestParam("selectedJoinObject") String selectedJoinObject,
            @RequestParam("selectedSortWay") String selectedSortWay) {

        // 검색어를 포함한 모든 예금 상품 데이터를 불러옴
        depositService.getSearchedDeposits(searchWord);

        // 필터링된 정기예금 상품 목록 조회
        depositService.getFilteredDeposits(selectedBank, selectedJoinWay, selectedJoinObject, selectedSortWay);

        return "redirect:/deposit";
    }



//     //검색어로 정기예금 상품 목록 조회
//    @PostMapping("/deposit")
//    public String getSearchedDeposits(@RequestParam("searchWord") String searchWord) {
//        // 검색어를 포함한 모든 예금 상품 데이터를 불러옴
//        depositService.getSearchedDeposits(searchWord);
//
//        return "redirect:/deposit";
//    }

//    // 필터링된 정기예금 상품 목록 조회
//    @PostMapping("/deposit")
//    public String getFilteredDeposits(@RequestParam("selectedBank") String selectedBank,
//                                      @RequestParam("selectedJoinWay") String selectedJoinWay,
//                                      @RequestParam("selectedJoinObject") String selectedJoinObject,
//                                      @RequestParam("selectedSortWay") String selectedSortWay) {
//
//        depositService.getFilteredDeposits(selectedBank, selectedJoinWay, selectedJoinObject, selectedSortWay);
//        return "redirect:/deposit";
//    }

    // 신용대출 상품 목록 조회
    @GetMapping("/creditLoan")
    public String getCreditLoanProducts(Model model) {
        List<CreditLoanProduct> creditLoans = jdbcDBConnection.retrieveCLDataFromTable("신용대출");
        model.addAttribute("creditLoans", creditLoans);
        return "products/creditLoanProductsList";
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
    @GetMapping("/member/myPage")
    public String getFavorite(Model model) {
        List<Favorite> favorites = jdbcDBConnection.retrieveFavDataFromTable("user_interest");
        model.addAttribute("favorites", favorites);
        return "mypage";
    }

}


