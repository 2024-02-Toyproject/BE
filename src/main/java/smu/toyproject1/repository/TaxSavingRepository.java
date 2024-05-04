package smu.toyproject1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.TaxSavingProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static smu.toyproject1.repository.JdbcDBConnection.closeConnection;
import static smu.toyproject1.repository.JdbcDBConnection.getConnection;

@Repository
@RequiredArgsConstructor
public class TaxSavingRepository {

    // 테이블에서 절세금융 상품의 데이터를 조회
    public static List<TaxSavingProduct> findAll(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TaxSavingProduct> taxSavingProducts = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String financialProduct = resultSet.getString("금융상품");
                    String mainSalesCompany = resultSet.getString("주요판매회사");
                    String categoryType = resultSet.getString("구분");
                    String taxBenefits = resultSet.getString("세제혜택");
                    String eligibility = resultSet.getString("가입대상");
                    String subscriptionLimit = resultSet.getString("가입한도");
                    String legalBasis = resultSet.getString("근거법령");

                    TaxSavingProduct taxSavingProduct = new TaxSavingProduct(financialProduct, mainSalesCompany, categoryType, taxBenefits, eligibility, subscriptionLimit, legalBasis);
                    taxSavingProducts.add(taxSavingProduct);
                }
            }
        } catch (SQLException e) {
            System.out.println("데이터 조회 오류입니다.");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(connection);
        }
        return taxSavingProducts;
    }
}
