package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.dto.DepositRequest;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.repository.DepositRepository;

import java.util.*;
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

    //이전 요청 정보를 저장하기 위한 간단한 캐시 클래스
    class RequestCache {
        static String lastBank = null;
        static String lastJoinWay = null;
        static String lastJoinObject = null;
        static String lastSearchWord = null;
    }

    /**
     * 필터링 옵션 -> 검색어
     * 검색어 -> 필터링 옵션
     * (단, 검색어의 상태가 바뀌었으면 반드시 한번 클릭 후 필터링 옵션을 적용해야 함.)
     * 예를 들어, '든든'이 검색되어 있던 상태에서 필터링에서 '우리은행'을 선택하였으면
     * 서버는 우리은행 상품이 조회된 상태에서 '든든'이 포함된 데이터를 불러오기 때문에 아무데이터도 뜨지 않음.
     * 이때, 검색어는 지우고 '우리은행' 필터링만 적용하고 싶다면 검색창에서 '든든'을 지우기만 하면 안되고
     * 지운 후 반드시 검색 버튼을 눌러야 적용이 됨.
     *
     */
    public List<FixedDepositProduct> getFilteredDeposits(DepositRequest request) {
        String bank = request.getBank() != null ? request.getBank() : RequestCache.lastBank;
        String joinWay = request.getJoinWay() != null ? request.getJoinWay() : RequestCache.lastJoinWay;
        String joinObject = request.getJoinObject() != null ? request.getJoinObject() : RequestCache.lastJoinObject;
        String sortWay = request.getSortWay();

        // 검색어가 null이 아니면 새로운 검색어를 사용하고, null이면 이전 검색어를 사용
        Object searchWord = request.getSearchWord() != null ? request.getSearchWord() : RequestCache.lastSearchWord;

        List<FixedDepositProduct> allDeposits = depositRepository.findAll("정기예금");

        Stream<FixedDepositProduct> filteredStream = allDeposits.stream();

        // 필터링 옵션 적용
        filteredStream = filteredStream.filter(deposit -> (bank == null || "전체".equals(bank) || deposit.getBankName().contains(bank)) &&
                (joinWay == null || "전체".equals(joinWay) || deposit.getJoinMethod().contains(joinWay)) &&
                (joinObject == null || "전체".equals(joinObject) || deposit.getTargetCustomers().contains(joinObject)));


        // 검색어가 변경된 경우에만 검색어 필터링 적용
        // 검색어 필터링 적용
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

        // 캐시 업데이트
        RequestCache.lastBank = bank;
        RequestCache.lastJoinWay = joinWay;
        RequestCache.lastJoinObject = joinObject;
        RequestCache.lastSearchWord = searchWord != null ? searchWord.toString() : null;

        List<FixedDepositProduct> filteredDeposits = filteredStream.collect(Collectors.toList());

        // 정렬 옵션 적용
        if ("기본금리순".equals(sortWay)) {
            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getInterestRate));
        } else if ("최고금리순".equals(sortWay)) {
            filteredDeposits.sort(Comparator.comparing(FixedDepositProduct::getInterestRate).reversed());
        }
        return filteredDeposits;
    }
}

