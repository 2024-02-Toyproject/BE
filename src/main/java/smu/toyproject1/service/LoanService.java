package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.dto.DepositRequest;
import smu.toyproject1.dto.LoanRequest;
import smu.toyproject1.entity.CreditLoanProduct;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.repository.DepositRepository;
import smu.toyproject1.repository.LoanRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // 예금데이터를 전체 조회
    public List<CreditLoanProduct> getAllLoans() {
        return loanRepository.findAll("신용대출");
    }

    //이전 요청 정보를 저장하기 위한 간단한 캐시 클래스
    class RequestCache {
        static String lastBank = null;
        static String lastJoinWay = null;
        static String lastLoanType = null;
        static String lastSearchWord = null;
    }

    public List<CreditLoanProduct> getFilteredLoans(LoanRequest request) {
        String bank = request.getBank() != null ? request.getBank() : LoanService.RequestCache.lastBank;
        String joinWay = request.getJoinWay() != null ? request.getJoinWay() : LoanService.RequestCache.lastJoinWay;
        String loanType = request.getLoanType() != null ? request.getLoanType() : RequestCache.lastLoanType;
        String sortWay = request.getSortWay();

        // 검색어가 null이 아니면 새로운 검색어를 사용하고, null이면 이전 검색어를 사용
        Object searchWord = request.getSearchWord() != null ? request.getSearchWord() : LoanService.RequestCache.lastSearchWord;

        List<CreditLoanProduct> allLoans = loanRepository.findAll("신용대출");

        Stream<CreditLoanProduct> filteredStream = allLoans.stream();

        // 필터링 옵션 적용
        filteredStream = filteredStream.filter(loan -> (bank == null || "전체".equals(bank) || loan.getCompany().contains(bank)) &&
                (joinWay == null || "전체".equals(joinWay) || loan.getMethod().contains(joinWay)) &&
                (loanType == null || "전체".equals(loanType) || loan.getLoanType().contains(loanType)));
//

        // 검색어가 변경된 경우에만 검색어 필터링 적용
        // 검색어 필터링 적용
        if (searchWord != null && !searchWord.toString().isEmpty()) {
            filteredStream = filteredStream.filter(loan ->
                    loan.getCompany().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 은행명
                            loan.getProductName().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 금융 상품명
                            loan.getMethod().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 가입방법
                            loan.getLoanType().toLowerCase().contains(searchWord.toString().toLowerCase()) || // 대출종류명
                            loan.getCbCompany().toLowerCase().contains(searchWord.toString().toLowerCase()) || // CB 회사명
                            (loan.getRateType() != null && loan.getRateType().toLowerCase().contains(searchWord.toString().toLowerCase())) || // 금리구분
                            Double.toString(loan.getAverageRate()).contains(searchWord.toString().toLowerCase())); // 평균금리
        }


        // 캐시 업데이트
        LoanService.RequestCache.lastBank = bank;
        LoanService.RequestCache.lastJoinWay = joinWay;
        LoanService.RequestCache.lastLoanType = loanType;
        LoanService.RequestCache.lastSearchWord = searchWord != null ? searchWord.toString() : null;

        List<CreditLoanProduct> filteredLoans = filteredStream.collect(Collectors.toList());

        // 정렬 옵션 적용
        if ("기본금리순".equals(sortWay)) {
            filteredLoans.sort(Comparator.comparing(CreditLoanProduct::getAverageRate));
        } else if ("최고금리순".equals(sortWay)) {
            filteredLoans.sort(Comparator.comparing(CreditLoanProduct::getAverageRate).reversed());
        }
        return filteredLoans;
    }
}
