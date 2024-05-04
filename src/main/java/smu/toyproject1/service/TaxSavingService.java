package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.dto.DepositRequest;
import smu.toyproject1.dto.TaxSavingRequest;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.entity.TaxSavingProduct;
import smu.toyproject1.repository.TaxSavingRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TaxSavingService {

    @Autowired
    private TaxSavingRepository taxSavingRepository;

    // 절세금융 데이터를 전체 조회
    public List<TaxSavingProduct> getAllTaxSavings() { return taxSavingRepository.findAll("절세금융상품"); }

    //이전 요청 정보를 저장하기 위한 간단한 캐시 클래스
    class RequestCache {
        static String lastSearchWord = null;
    }

    public List<TaxSavingProduct> getFilteredTaxSavings(TaxSavingRequest request) {
        // 검색어가 null이 아니면 새로운 검색어를 사용하고, null이면 이전 검색어를 사용
        Object searchWord = request.getSearchWord() != null ? request.getSearchWord() : TaxSavingService.RequestCache.lastSearchWord;

        List<TaxSavingProduct> allTaxSavings = taxSavingRepository.findAll("절세금융상품");

        Stream<TaxSavingProduct> filteredStream = allTaxSavings.stream();

        // 검색어가 변경된 경우에만 검색어 필터링 적용
        // 검색어 필터링 적용
        if (searchWord != null && !searchWord.toString().isEmpty()) {
            filteredStream = filteredStream.filter(taxSaving ->
                    taxSaving.getFinancialProduct().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 금융상품
                            taxSaving.getMainSalesCompany().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 주요판매회사
                            taxSaving.getCategoryType().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 구분
                            taxSaving.getTaxBenefits().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 세제혜택
                            taxSaving.getEligibility().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입대상
                            taxSaving.getSubscriptionLimit().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입한도
                            taxSaving.getLegalBasis().toLowerCase().contains(searchWord.toString().toLowerCase())); // 근거법령

        }

        // 캐시 업데이트
        TaxSavingService.RequestCache.lastSearchWord = searchWord != null ? searchWord.toString() : null;

        List<TaxSavingProduct> filteredTaxSavings = filteredStream.collect(Collectors.toList());

        return filteredTaxSavings;
    }
}
