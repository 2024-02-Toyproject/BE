package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.dto.DepositRequest;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.repository.DepositRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    // 예금데이터를 전체 조회
    public List<FixedDepositProduct> getAllDeposits() {
        return depositRepository.findAll("정기예금");
    }

    // 검색어와 필터링 옵션을 반영한 데이터 조회
    public List<FixedDepositProduct> getFilteredDeposits(DepositRequest request) {
        String bank = request.getBank();
        String joinWay = request.getJoinWay();
        String joinObject = request.getJoinObject();
        String sortWay = request.getSortWay();
        Object searchWord = request.getSearchWord(); // 검색어 객체

        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");

        Stream<FixedDepositProduct> filteredStream = allDeposits.stream()
                .filter(deposit -> (bank == null || "전체".equals(bank) || deposit.getBankName().contains(bank)) &&
                        (joinWay == null || "전체".equals(joinWay) || deposit.getJoinMethod().contains(joinWay)) &&
                        (joinObject == null || "전체".equals(joinObject) || deposit.getTargetCustomers().contains(joinObject)));

        if (searchWord != null && !searchWord.toString().isEmpty()) {
            filteredStream = filteredStream.filter(deposit ->
                    deposit.getBankName().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 은행명
                            deposit.getProductName().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 상품명
                            deposit.getJoinMethod().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입방법
                            deposit.getMaturityInterestRate().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 만기후 이자율
                            deposit.getPreferentialConditions().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 우대조건
                            deposit.getJoinRestrictions().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입제한
                            deposit.getTargetCustomers().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입대상
                            Integer.toString(deposit.getMaximumLimit()).contains(searchWord.toString().toLowerCase()) || // 최고한도
                            deposit.getInterestRateType().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 저축금리유형명
                            Double.toString(deposit.getInterestRate()).contains(searchWord.toString().toLowerCase()) || // 저축금리
                            Double.toString(deposit.getMaximumPreferentialRate()).contains(searchWord.toString().toLowerCase())); // 최고우대금리
        }

        List<FixedDepositProduct> filteredDeposits = filteredStream.collect(Collectors.toList());

        if ("기본금리순".equals(sortWay)) {
            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getMaximumPreferentialRate));
        } else if ("최고금리순".equals(sortWay)) {
            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getMaximumPreferentialRate).reversed());
        }
        return filteredDeposits;
    }

    // 필터링 옵션을 적용한 데이터 조회 (검색어 반영 전)
//    public List<FixedDepositProduct> getFilteredDeposits(DepositRequest request) {
//        String bank = request.getBank();
//        String joinWay = request.getJoinWay();
//        String joinObject = request.getJoinObject();
//        String sortWay = request.getSortWay();
//
//        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");
//
//        List<FixedDepositProduct> filteredDeposits = allDeposits.stream()
//                .filter(deposit -> ("전체".equals(bank) || deposit.getBankName().contains(bank)) &&
//                        ("전체".equals(joinWay) || deposit.getJoinMethod().contains(joinWay)) &&
//                        ("전체".equals(joinObject) || deposit.getTargetCustomers().contains(joinObject)))
//                .collect(Collectors.toList());
//
//        if ("기본금리순".equals(sortWay)) {
//            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getMaximumPreferentialRate));
//        } else if ("최고금리순".equals(sortWay)) {
//            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getMaximumPreferentialRate).reversed());
//        }
//        return filteredDeposits;
//    }

}

