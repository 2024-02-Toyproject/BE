package smu.toyproject1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.SavingProduct;

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
public class SavingRepository {

    // 테이블에서 적금 상품의 데이터를 조회
    public static List<SavingProduct> findAll(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SavingProduct> savings = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String company = resultSet.getString("금융회사명");
                    String productName = resultSet.getString("금융상품명");
                    String joinMethod = resultSet.getString("가입방법");
                    String maturityInterestRate = resultSet.getString("만기후이자율");
                    String preferentialConditions = resultSet.getString("우대조건");
                    String eligibilityRestrictions = resultSet.getString("가입제한");
                    String targetCustomers = resultSet.getString("가입대상");
                    int maximumLimit = resultSet.getInt("최고한도");
                    String savingsInterestRateType = resultSet.getString("저축금리유형명");
                    double savingsInterestRate = resultSet.getDouble("저축금리");
                    double maximumPreferentialRate = resultSet.getDouble("최고우대금리");

                    SavingProduct installmentSaving = new SavingProduct(company, productName, joinMethod, maturityInterestRate
                            , preferentialConditions, eligibilityRestrictions, targetCustomers, maximumLimit, savingsInterestRateType, savingsInterestRate, maximumPreferentialRate);
                    savings.add(installmentSaving);
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
        return savings;
    }
}
