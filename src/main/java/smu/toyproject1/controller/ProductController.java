package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smu.toyproject1.dto.DepositRequest;
import smu.toyproject1.dto.DepositResponse;
import smu.toyproject1.entity.*;
import smu.toyproject1.repository.JdbcDBConnection;
import smu.toyproject1.service.DepositService;

import java.util.List;

@RestController
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

    // 정기예금 상품 목록 조회, REST API 구현
    @GetMapping("/fixedDeposit")
    public ResponseEntity<List<FixedDepositProduct>> getFixedDepositProducts() {
        List<FixedDepositProduct> fixedDeposits = depositService.getAllDeposits();
        return new ResponseEntity<>(fixedDeposits, HttpStatus.OK);
    }

    // 필터링 반영하여 정기예금 목록 조회
    @PostMapping("/fixedDeposit")
    public DepositResponse handleDepositRequest(@RequestBody DepositRequest request) {
        System.out.println("request = " + request); // 요청이 제대로 오는지 확인하기 위한 출력 코드
        List<FixedDepositProduct> depositProduct = depositService.getFilteredDeposits(request);
        // depositProduct 리스트를 DepositResponse에 포함
        System.out.println("depositProduct = " + depositProduct); // 응답이 제대로 가는지 확인하기 위한 출력 코드
        return new DepositResponse(depositProduct);
    }
}


        // 정기예금 검색 & 필터링 반영하여 조회
//    @PostMapping("/fixedDeposit")
//    public ResponseEntity<String> processRequest(@RequestBody YourRequestData requestData) {
//        // requestData를 이용하여 원하는 로직을 수행합니다.
//        String bank = requestData.getBank();
//        String joinWay = requestData.getJoinWay();
//        String joinObject = requestData.getJoinObject();
//        String sortWay = requestData.getSortWay();
//    }
//    public ResponseEntity<List<FixedDepositProduct>> searchAndFilterDeposits(
//            @RequestParam(value = "searchWord", required = false) String searchWord,
//            @RequestParam(value = "selectedBank", required = false) String selectedBank,
//            @RequestParam(value = "selectedJoinWay", required = false) String selectedJoinWay,
//            @RequestParam(value = "selectedJoinObject", required = false) String selectedJoinObject,
//            @RequestParam(value = "selectedSortWay", required = false) String selectedSortWay) {
//
//        // 검색어, 필터링을 적용한 데이터를 담은 객체
//        List<FixedDepositProduct> filteredDeposits = depositService.getFilteredDeposits(searchWord, selectedBank, selectedJoinWay,
//                selectedJoinObject, selectedSortWay);
//
//        // 프론트에서 요청한 데이터에 따라 검색 및 필터링된 예금 상품 데이터를 반환
//        return ResponseEntity.ok(filteredDeposits);
//    }



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

//    // 신용대출 상품 목록 조회
//    @GetMapping("/creditLoan")
//    public String getCreditLoanProducts(Model model) {
//        List<CreditLoanProduct> creditLoans = jdbcDBConnection.retrieveCLDataFromTable("신용대출");
//        model.addAttribute("creditLoans", creditLoans);
//        return "products/creditLoanProductsList";
//    }
//
//    // 적금 상품 목록 조회
//    @GetMapping("/installmentSaving")
//    public String getInstallmentSavingProducts(Model model) {
//        List<InstallmentSavingProduct> installmentSavings = jdbcDBConnection.retrieveISDataFromTable("적금");
//        model.addAttribute("installmentSavings", installmentSavings);
//        return "products/installmentSavingProductsList";
//    }
//
//    // 절세금융 상품 목록 조회
//    @GetMapping("/taxSaving")
//    public String getTaxSavingProducts(Model model) {
//        List<TaxSavingProduct> taxSavings = jdbcDBConnection.retrieveTSDataFromTable("절세금융상품");
//        model.addAttribute("taxSavings", taxSavings);
//        return "products/taxSavingProductsList";
//    }
//    @GetMapping("/member/myPage")
//    public String getFavorite(Model model) {
//        List<Favorite> favorites = jdbcDBConnection.retrieveFavDataFromTable("user_interest");
//        model.addAttribute("favorites", favorites);
//        return "mypage";
//    }



