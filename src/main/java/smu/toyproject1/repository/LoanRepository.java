package smu.toyproject1.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smu.toyproject1.entity.CreditLoanProduct;
import smu.toyproject1.entity.FixedDepositProduct;

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
public class LoanRepository {

    // 테이블에서 신용대출 상품의 데이터를 조회
    public static List<CreditLoanProduct> findAll(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CreditLoanProduct> creditLoans = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String company = resultSet.getString("금융회사명");
                    String productName = resultSet.getString("금융 상품명");
                    String method = resultSet.getString("가입 방법");
                    String loanType = resultSet.getString("대출종류명");
                    String cbCompany = resultSet.getString("CB 회사명");
                    String rateType = resultSet.getString("금리구분");
                    double averageRate = resultSet.getDouble("평균 금리");

                    CreditLoanProduct creditLoan = new CreditLoanProduct(company, productName, method, loanType, cbCompany, rateType, averageRate);
                    creditLoans.add(creditLoan);
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
        return creditLoans;
    }
}
