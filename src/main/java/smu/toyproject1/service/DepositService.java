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
    public List<FixedDepositProduct> getFilteredDeposits(DepositRequest request) {
        // 클라이언트에서 요청한 옵션 정보 받아오는 객체
        String bank = request.getBank();
        String joinWay = request.getJoinWay();
        String joinObject = request.getJoinObject();
        String sortWay = request.getSortWay();

        // 필터링된 데이터 담을 객체
        List<FixedDepositProduct> filteredDeposits;
        // 전체 데이터 객체
        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");

        filteredDeposits = allDeposits.stream()
                .filter(deposit -> deposit.getBankName().equals(bank) ||
                        deposit.getJoinMethod().equals(joinWay) ||
                        deposit.getTargetCustomers().equals(joinObject))
                .collect(Collectors.toList());

        if ("기본금리순".equals(sortWay)) {
            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getMaximumPreferentialRate));
        } else if ("최고금리순".equals(sortWay)) {
            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getMaximumPreferentialRate).reversed());
        }

        return filteredDeposits;
    }
}

