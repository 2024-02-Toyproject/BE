package smu.toyproject1.repository;

import org.springframework.stereotype.Component;
import smu.toyproject1.product.CreditLoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            // 연결 성공 시 추가 작업 수행

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

    public static List<CreditLoan> retrieveDataFromTable(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CreditLoan> creditLoans = new ArrayList<>();

        try {
            connection = getConnection();
            if (connection != null) {
                String sql = "SELECT * FROM " + tableName;
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String company = resultSet.getString("금융회사 명");
                    String productName = resultSet.getString("금융 상품명");
                    String method = resultSet.getString("가입 방법");
                    String loanType = resultSet.getString("대출종류명");
                    String cbCompany = resultSet.getString("CB 회사명");
                    String rateType = resultSet.getString("금리구분");
                    int averageRate = resultSet.getInt("평균 금리");

                    CreditLoan creditLoan = new CreditLoan(company, productName, method, loanType, cbCompany, rateType, averageRate);
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








//    public static Connection getConnection() {
//        Connection conn = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://database-1.c9i0ceu2mgty.ap-northeast-2.rds.amazonaws.com:3306/febtoyproject1",
//                    "shLim",
//                    "febtoy222*");
//            conn.setAutoCommit(false);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//    public static void close(Connection conn) {
//        try {
//            if(conn != null && !conn.isClosed())
//                conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void close(Statement stmt) {
//        try {
//            if(stmt != null && !stmt.isClosed())
//                stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void close(ResultSet rset) {
//        try {
//            if(rset != null && !rset.isClosed())
//                rset.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void commit(Connection conn) {
//        try {
//            if(conn != null & !conn.isClosed())
//                conn.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void rollback(Connection conn) {
//        try {
//            if(conn != null & !conn.isClosed())
//                conn.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
