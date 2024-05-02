package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smu.toyproject1.dto.DepositRequest;
import smu.toyproject1.dto.DepositResponse;
import smu.toyproject1.dto.LoanRequest;
import smu.toyproject1.dto.LoanResponse;
import smu.toyproject1.entity.*;
import smu.toyproject1.repository.JdbcDBConnection;
import smu.toyproject1.service.DepositService;
import smu.toyproject1.service.LoanService;

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
    @Autowired
    private LoanService loanService;

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

    // 신용대출 상품 목록 조회, REST API 구현
    @GetMapping("/creditLoan")
    public ResponseEntity<List<CreditLoanProduct>> getCreditLoanProducts() {
        List<CreditLoanProduct> creditLoans = loanService.getAllLoans();
        return new ResponseEntity<>(creditLoans, HttpStatus.OK);
    }

    // 필터링 반영하여 신용대출 목록 조회
    @PostMapping("/creditLoan")
    public LoanResponse handleLoanRequest(@RequestBody LoanRequest request) {
        System.out.println("request = " + request); // 요청이 제대로 오는지 확인하기 위한 출력 코드
        List<CreditLoanProduct> loanProduct = loanService.getFilteredLoans(request);
        // depositProduct 리스트를 DepositResponse에 포함
        System.out.println("loanProduct = " + loanProduct); // 응답이 제대로 가는지 확인하기 위한 출력 코드
        return new LoanResponse(loanProduct);
    }

//    // 적금 상품 목록 조회, REST API 구현
//    @GetMapping("/installmentSaving")
//    public ResponseEntity<List<FixedDepositProduct>> getFixedDepositProducts() {
//        List<FixedDepositProduct> fixedDeposits = depositService.getAllDeposits();
//        return new ResponseEntity<>(fixedDeposits, HttpStatus.OK);
//    }
//
//    // 절세금융 상품 목록 조회, REST API 구현
//    @GetMapping("/taxSaving")
//    public ResponseEntity<List<FixedDepositProduct>> getFixedDepositProducts() {
//        List<FixedDepositProduct> fixedDeposits = depositService.getAllDeposits();
//        return new ResponseEntity<>(fixedDeposits, HttpStatus.OK);
//    }
}



