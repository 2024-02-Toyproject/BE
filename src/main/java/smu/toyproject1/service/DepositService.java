package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.repository.DepositRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    // 예금데이터를 전체 조회
    public List<FixedDepositProduct> getAllDeposits() {
        return depositRepository.findAll("정기예금");
    }

    // 검색어가 포함된 데이터를 조회
    public List<FixedDepositProduct> getSearchedDeposits(String searchWord) {

        // 검색어가 포함된 데이터를 담을 객체
        List<FixedDepositProduct> searchedDeposits = new ArrayList<>();

        // 전체 정기예금 데이터 담을 객체
        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");

        for (FixedDepositProduct deposit : allDeposits) {
            if (containsSearchWord(deposit, searchWord)) {
                searchedDeposits.add(deposit);
            }
        }
        return searchedDeposits;
    }

    public boolean containsSearchWord(FixedDepositProduct depositProduct, String searchWord) {
        String depositProductData = depositProduct.getBankName() +
                depositProduct.getProductName() +
                depositProduct.getJoinMethod() +
                depositProduct.getMaturityInterestRate() +
                depositProduct.getPreferentialConditions() +
                depositProduct.getJoinRestrictions() +
                depositProduct.getTargetCustomers() +
                depositProduct.getInterestRateType();

        return depositProductData.toLowerCase().contains(searchWord.toLowerCase());
    }

    // 필터링 옵션에 맞춰서 조회
    public List<FixedDepositProduct> getFilteredDeposits(String selectedBank, String selectedJoinWay,
                                                         String selectedJoinObject, String selectedSortWay) {
        // 필터링된 데이터 담을 객체
        List<FixedDepositProduct> filteredDeposits = new ArrayList<>();

        // 전체 정기예금 데이터 담을 객체
        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");

        for (FixedDepositProduct deposit : allDeposits) {
            if ((selectedBank.equals("all") || deposit.getBankName().equals(selectedBank)) &&
                    (selectedJoinWay.equals("all") || deposit.getJoinMethod().equals(selectedJoinWay)) &&
                    (selectedJoinObject.equals("all") || deposit.getTargetCustomers().equals(selectedJoinObject))) {
                filteredDeposits.add(deposit);
            }
        }

        // 정렬된 데이터 담을 객체 (기본금리/최고금리)
        List<FixedDepositProduct> sortedDeposits = new ArrayList<>();

        // "최고우대금리" 값을 기준으로 정렬
        if (selectedSortWay.equals("maximum")) {
            sortedDeposits = filteredDeposits.stream()
                    .sorted(Comparator.comparingDouble(FixedDepositProduct::getMaximumPreferentialRate).reversed())
                    .collect(Collectors.toList());
        } else if (selectedSortWay.equals("all")) {
            // "기본금리순"을 선택했을 때 최저금리순으로 정렬
            sortedDeposits = filteredDeposits.stream()
                    .sorted(Comparator.comparingDouble(FixedDepositProduct::getMaximumPreferentialRate))
                    .collect(Collectors.toList());
        }

        return sortedDeposits;
    }
}

