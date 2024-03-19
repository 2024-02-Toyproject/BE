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

    // 검색어 및 필터링 옵션을 적용한 데이터 조회
    public List<FixedDepositProduct> getFilteredDeposits(String searchWord, String selectedBank, String selectedJoinWay,
                                                         String selectedJoinObject, String selectedSortWay) {
        List<FixedDepositProduct> filteredDeposits = new ArrayList<>();
        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");

        for (FixedDepositProduct deposit : allDeposits) {
            String depositProductData = deposit.getBankName() +
                    deposit.getProductName() +
                    deposit.getJoinMethod() +
                    deposit.getMaturityInterestRate() +
                    deposit.getPreferentialConditions() +
                    deposit.getJoinRestrictions() +
                    deposit.getTargetCustomers() +
                    deposit.getInterestRateType();

            boolean containsSearchWord = searchWord != null && !searchWord.isEmpty() &&
                    depositProductData.toLowerCase().contains(searchWord.toLowerCase());

            boolean matchesBank = selectedBank == null || selectedBank.equals("all") || deposit.getBankName().equals(selectedBank);
            boolean matchesJoinWay = selectedJoinWay == null || selectedJoinWay.equals("all") || deposit.getJoinMethod().equals(selectedJoinWay);
            boolean matchesJoinObject = selectedJoinObject == null || selectedJoinObject.equals("all") || deposit.getTargetCustomers().equals(selectedJoinObject);


            if ((searchWord == null || containsSearchWord) && matchesBank && matchesJoinWay && matchesJoinObject) {
                filteredDeposits.add(deposit);
            }
        }

        List<FixedDepositProduct> sortedDeposits = new ArrayList<>();

        if (selectedSortWay != null && !selectedSortWay.isEmpty()) {
            if (selectedSortWay.equals("maximum")) {
                sortedDeposits = filteredDeposits.stream()
                        .sorted(Comparator.comparingDouble(FixedDepositProduct::getMaximumPreferentialRate).reversed())
                        .collect(Collectors.toList());
            } else if (selectedSortWay.equals("all")) {
                sortedDeposits = filteredDeposits.stream()
                        .sorted(Comparator.comparingDouble(FixedDepositProduct::getMaximumPreferentialRate))
                        .collect(Collectors.toList());
            }
        } else {
            sortedDeposits = filteredDeposits;
        }

        return sortedDeposits;
    }
}

