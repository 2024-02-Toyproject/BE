package smu.toyproject1.repository;

import org.springframework.stereotype.Component;
import smu.toyproject1.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class JdbcDBConnection {
    public static Connection getConnection() {
        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://database-1.c9i0ceu2mgty.ap-northeast-2.rds.amazonaws.com:3306/febtoyproject1"; // 데이터베이스 URL
        String username = "shLim"; // 사용자명
        String password = "febtoy222*"; // 비밀번호

        Connection connection = null;

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, username, password);

            // 연결 성공 시 메시지
            System.out.println("데이터베이스 연결 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 오류입니다.");
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("데이터베이스 연결 해제 오류입니다.");
                e.printStackTrace();
            }
        }
    }

    // 테이블에서 적금 상품의 데이터를 조회
    public static List<InstallmentSavingProduct> retrieveISDataFromTable(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<InstallmentSavingProduct> installmentSavings = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String company = resultSet.getString("금융회사명");
                    String productName = resultSet.getString("금융상품명");
                    String applicationMethod = resultSet.getString("가입방법");
                    String maturityInterestRate = resultSet.getString("만기후이자율");
                    String preferentialConditions = resultSet.getString("우대조건");
                    String eligibilityRestrictions = resultSet.getString("가입제한");
                    String targetCustomers = resultSet.getString("가입대상");
                    Long maximumLimit = resultSet.getLong("최고한도");
                    String savingsInterestRateTypeName = resultSet.getString("저축금리유형명");
                    Double savingsInterestRate = resultSet.getDouble("저축금리");
                    Double maximumPreferentialRate = resultSet.getDouble("최고우대금리");

                    InstallmentSavingProduct installmentSaving = new InstallmentSavingProduct(company, productName, applicationMethod, maturityInterestRate
                            , preferentialConditions, eligibilityRestrictions, targetCustomers, maximumLimit, savingsInterestRateTypeName, savingsInterestRate, maximumPreferentialRate);
                    installmentSavings.add(installmentSaving);
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
        return installmentSavings;
    }

    // 테이블에서 절세금융상품의 데이터를 조회
    public static List<TaxSavingProduct> retrieveTSDataFromTable(String tableName) {
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

    // 관심상품 목록 db 불러오기
    public static List<Favorite> retrieveFavDataFromTable(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Favorite> favorites = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String loginName = resultSet.getString("login_name");
                    String company = resultSet.getString("company");
                    String productName = resultSet.getString("product_name");

                    Favorite favorite = new Favorite(loginName, company, productName);
                    favorites.add(favorite);
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
        return favorites;
    }


}
