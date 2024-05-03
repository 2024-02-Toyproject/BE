package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.dto.SavingRequest;
import smu.toyproject1.entity.SavingProduct;
import smu.toyproject1.repository.SavingRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SavingService {

    @Autowired
    private SavingRepository savingRepository;

    // 적금 데이터 전체를 조회
    public List<SavingProduct> getAllSavings() {
        return savingRepository.findAll("적금");
    }

    //이전 요청 정보를 저장하기 위한 간단한 캐시 클래스
    class RequestCache {
        static String lastBank = null;
        static String lastJoinWay = null;
        static String lastJoinObject = null;
        static String lastSearchWord = null;
    }

    public List<SavingProduct> getFilteredSavings(SavingRequest request) {
        String bank = request.getBank() != null ? request.getBank() : SavingService.RequestCache.lastBank;
        String joinWay = request.getJoinWay() != null ? request.getJoinWay() : SavingService.RequestCache.lastJoinWay;
        String joinObject = request.getJoinObject() != null ? request.getJoinObject() : SavingService.RequestCache.lastJoinObject;
        String sortWay = request.getSortWay();

        // 검색어가 null이 아니면 새로운 검색어를 사용하고, null이면 이전 검색어를 사용
        Object searchWord = request.getSearchWord() != null ? request.getSearchWord() : SavingService.RequestCache.lastSearchWord;

        List<SavingProduct> allSavings = savingRepository.findAll("적금");

        Stream<SavingProduct> filteredStream = allSavings.stream();

        // 필터링 옵션 적용
        filteredStream = filteredStream.filter(saving -> (bank == null || "전체".equals(bank) || saving.getCompany().contains(bank)) &&
                (joinWay == null || "전체".equals(joinWay) || saving.getJoinMethod().contains(joinWay)) &&
                (joinObject == null || "전체".equals(joinObject) || saving.getTargetCustomers().contains(joinObject)));


        // 검색어가 변경된 경우에만 검색어 필터링 적용
        // 검색어 필터링 적용
        if (searchWord != null && !searchWord.toString().isEmpty()) {
            filteredStream = filteredStream.filter(saving ->
                    saving.getCompany().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 은행명
                            saving.getProductName().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 상품명
                            saving.getJoinMethod().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입방법
                            saving.getMaturityInterestRate().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 만기후 이자율
                            saving.getPreferentialConditions().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 우대조건
                            saving.getEligibilityRestrictions().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입제한
                            saving.getTargetCustomers().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입대상
                            Integer.toString(saving.getMaximumLimit()).contains(searchWord.toString().toLowerCase()) || // 최고한도
                            saving.getSavingsInterestRateType().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 저축금리유형명
                            Double.toString(saving.getSavingsInterestRate()).contains(searchWord.toString().toLowerCase()) || // 저축금리
                            Double.toString(saving.getMaximumPreferentialRate()).contains(searchWord.toString().toLowerCase())); // 최고우대금리

        }

        // 캐시 업데이트
        RequestCache.lastBank = bank;
        RequestCache.lastJoinWay = joinWay;
        RequestCache.lastJoinObject = joinObject;
        RequestCache.lastSearchWord = searchWord != null ? searchWord.toString() : null;

        List<SavingProduct> filteredSavings = filteredStream.collect(Collectors.toList());

        // 정렬 옵션 적용
        if ("기본금리순".equals(sortWay)) {
            filteredSavings.sort(Comparator.comparing(SavingProduct::getSavingsInterestRate));
        } else if ("최고금리순".equals(sortWay)) {
            filteredSavings.sort(Comparator.comparing(SavingProduct::getSavingsInterestRate).reversed());
        }
        return filteredSavings;
    }
}
