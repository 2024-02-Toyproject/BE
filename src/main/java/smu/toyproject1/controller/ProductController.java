package smu.toyproject1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.toyproject1.dto.*;
import smu.toyproject1.entity.*;
import smu.toyproject1.repository.JdbcDBConnection;
import smu.toyproject1.service.DepositService;
import smu.toyproject1.service.LoanService;
import smu.toyproject1.service.SavingService;
import smu.toyproject1.service.TaxSavingService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private DepositService depositService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private SavingService savingService;
    @Autowired
    private TaxSavingService taxSavingService;

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
        // CreditLoanProduct 리스트를 CreditLoanResponse에 포함
        System.out.println("loanProduct = " + loanProduct); // 응답이 제대로 가는지 확인하기 위한 출력 코드
        return new LoanResponse(loanProduct);
    }

    // 적금 상품 목록 조회, REST API 구현
    @GetMapping("/saving")
    public ResponseEntity<List<SavingProduct>> getInstallmentSavingProducts() {
        List<SavingProduct> savings = savingService.getAllSavings();
        return new ResponseEntity<>(savings, HttpStatus.OK);
    }

    // 필터링 반영하여 적금 목록 조회
    @PostMapping("/saving")
    public SavingResponse handleSavingRequest(@RequestBody SavingRequest request) {
        System.out.println("request = " + request); // 요청이 제대로 오는지 확인하기 위한 출력 코드
        List<SavingProduct> savingProduct = savingService.getFilteredSavings(request);
        // SavingProduct 리스트를 SavingResponse에 포함
        System.out.println("savingProduct = " + savingProduct); // 응답이 제대로 가는지 확인하기 위한 출력 코드
        return new SavingResponse(savingProduct);
    }

    // 절세금융 상품 목록 조회, REST API 구현
    @GetMapping("/taxSaving")
    public ResponseEntity<List<TaxSavingProduct>> getFixedTaxSavingProducts() {
        List<TaxSavingProduct> fixedTaxSavings = taxSavingService.getAllTaxSavings();
        return new ResponseEntity<>(fixedTaxSavings, HttpStatus.OK);
    }

    // 검색어 반영하여 적금 목록 조회
    @PostMapping("/taxSaving")
    public TaxSavingResponse handleSavingRequest(@RequestBody TaxSavingRequest request) {
        System.out.println("request = " + request); // 요청이 제대로 오는지 확인하기 위한 출력 코드
        List<TaxSavingProduct> taxSavingProduct = taxSavingService.getFilteredTaxSavings(request);
        // TaxSavingProduct 리스트를 TaxSavingResponse에 포함
        System.out.println("taxSavingProduct = " + taxSavingProduct); // 응답이 제대로 가는지 확인하기 위한 출력 코드
        return new TaxSavingResponse(taxSavingProduct);
    }
}



