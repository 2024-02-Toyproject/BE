package smu.toyproject1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.FixedDepositProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static smu.toyproject1.repository.JdbcDBConnection.closeConnection;
import static smu.toyproject1.repository.JdbcDBConnection.getConnection;

@Repository
@RequiredArgsConstructor
public class DepositRepository {

    // 테이블에서 정기예금 상품의 데이터를 조회
    public static List<FixedDepositProduct> findAll(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FixedDepositProduct> fixedDeposits = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String bankName = resultSet.getString("금융회사명");
                    String productName = resultSet.getString("금융상품명");
                    String joinMethod = resultSet.getString("가입방법");
                    String maturityInterestRate = resultSet.getString("만기후이자율");
                    String preferentialConditions = resultSet.getString("우대조건");
                    String joinRestrictions = resultSet.getString("가입제한");
                    String targetCustomers = resultSet.getString("가입대상");
                    int maximumLimit = resultSet.getInt("최고한도");
                    String interestRateType = resultSet.getString("저축금리유형명");
                    double interestRate = resultSet.getDouble("저축금리");
                    double maximumPreferentialRate = resultSet.getDouble("최고우대금리");

                    FixedDepositProduct fixedDeposit = new FixedDepositProduct(bankName, productName, joinMethod, maturityInterestRate, preferentialConditions, joinRestrictions, targetCustomers, maximumLimit, interestRateType, interestRate, maximumPreferentialRate);
                    fixedDeposits.add(fixedDeposit);
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
        return fixedDeposits;
    }

}
