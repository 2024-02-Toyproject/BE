package smu.toyproject1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smu.toyproject1.entity.FixedDepositProduct;
import smu.toyproject1.entity.TaxSavingProduct;
import smu.toyproject1.repository.TaxSavingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxSavingService {

    @Autowired
    private TaxSavingRepository taxSavingRepository;

    // 절세금융 데이터를 전체 조회
    public List<TaxSavingProduct> getAllTaxSavings() { return taxSavingRepository.findAll("절세금융상품"); }
    // 테이블에서 절세금융상품의 데이터를 조회
}
