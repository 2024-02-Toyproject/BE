//package smu.toyproject1.repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//
//import javax.xml.crypto.Data;
//
//public class DataProcessor {
//    // 검색어에 해당하는 데이터를 처리하고 필터링하여 조회하는 메서드
//    public List<Data> processData(HttpServletRequest request, String companyValue, String methodValue, String loanTypeValue) {
//        List<Data> filteredDataList = new ArrayList<>();
//
//        // 데이터베이스 연결
//        Connection connection = JdbcDBConnection.getConnection();
//
//        if (connection != null) {
//            PreparedStatement statement = null;
//            ResultSet resultSet = null;
//
//            try {
//                // HTTP 요청의 본문(body)에서 검색어 추출
//                ObjectMapper objectMapper = new ObjectMapper();
//                String requestBody = request.getReader().lines().collect(Collectors.joining());
//                JsonNode jsonNode = objectMapper.readTree(requestBody);
//                String searchKeyword = jsonNode.get("searchKeyword").asText();
//
//                // SQL 쿼리 작성
//                String query = "SELECT * FROM data_table WHERE company LIKE ? AND method LIKE ? AND loan_type LIKE ?";
//
//                // PreparedStatement 객체 생성
//                statement = connection.prepareStatement(query);
//
//                // 파라미터 설정
//                statement.setString(1, "%" + companyValue + "%");
//                statement.setString(2, "%" + methodValue + "%");
//                statement.setString(3, "%" + loanTypeValue + "%");
//
//                // 쿼리 실행
//                resultSet = statement.executeQuery();
//
//                // 결과 처리
//                while (resultSet.next()) {
//                    String company = resultSet.getString("company");
//                    String productName = resultSet.getString("product_name");
//
//                    // 검색어로 필터링
//                    if (company.contains(searchKeyword) || productName.contains(searchKeyword)) {
//                        Data data = new Data(company, productName);
//                        filteredDataList.add(data);
//                    }
//                }
//            } catch (SQLException e) {
//                System.out.println("데이터 처리 오류입니다.");
//                e.printStackTrace();
//            } catch (Exception e) {
//                System.out.println("예외가 발생했습니다.");
//                e.printStackTrace();
//            } finally {
//                // 리소스 해제
//                if (resultSet != null) {
//                    try {
//                        resultSet.close();
//                    } catch (SQLException e) {
//                        System.out.println("ResultSet을 닫는데 실패했습니다.");
//                        e.printStackTrace();
//                    }
//                }
//
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        System.out.println("PreparedStatement를 닫는데 실패했습니다.");
//                        e.printStackTrace();
//                    }
//                }
//
//                // 데이터베이스 연결 해제
//                JdbcDBConnection.closeConnection(connection);
//            }
//        }
//        return filteredDataList;
//    }
//
//    // 새로운 데이터를 추가하는 메서드
//    public void addData(HttpServletRequest request, Data newData) {
//        // 데이터베이스 연결
//        Connection connection = JdbcDBConnection.getConnection();
//
//        if (connection != null) {
//            PreparedStatement statement = null;
//
//            try {
//                // HTTP 요청의 본문(body)에서 데이터 추출
//                ObjectMapper objectMapper = new ObjectMapper();
//                String requestBody = request.getReader().lines().collect(Collectors.joining());
//                JsonNode jsonNode = objectMapper.readTree(requestBody);
//                // 새로운 데이터 필드 추출
//                String company = jsonNode.get("company").asText();
//                String productName = jsonNode.get("productName").asText();
//
//                // SQL 쿼리 작성
//                String query = "INSERT INTO data_table (company, product_name) VALUES (?, ?)";
//
//                // PreparedStatement 객체 생성
//                statement = connection.prepareStatement(query);
//
//                // 파라미터 설정
//                statement.setString(1, company);
//                statement.setString(2, productName);
//
//                // 쿼리 실행
//                statement.executeUpdate();
//
//            } catch (SQLException e) {
//                System.out.println("데이터 처리 오류입니다.");
//                e.printStackTrace();
//            } catch (Exception e) {
//                System.out.println("예외가 발생했습니다.");
//                e.printStackTrace();
//            } finally {
//                // 리소스 해제
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        System.out.println("PreparedStatement를 닫는데 실패했습니다.");
//                        e.printStackTrace();
//                    }
//                }
//
//                // 데이터베이스 연결 해제
//                JdbcDBConnection.closeConnection(connection);
//            }
//        }
//    }
//
//    // 데이터를 수정하는 메서드
//    public void updateData(HttpServletRequest request, String company, String productName) {
//        // 데이터베이스 연결
//        Connection connection = JdbcDBConnection.getConnection();
//
//        if (connection != null) {
//            PreparedStatement statement = null;
//
//            try {
//                // HTTP 요청의 본문(body)에서 데이터 추출
//                ObjectMapper objectMapper = new ObjectMapper();
//                String requestBody = request.getReader().lines().collect(Collectors.joining());
//                JsonNode jsonNode = objectMapper.readTree(requestBody);
//                // 수정할 데이터 필드 추출
//                String newCompany = jsonNode.get("newCompany").asText();
//                String newProductName = jsonNode.get("newProductName").asText();
//
//                // SQL 쿼리 작성
//                String query = "UPDATE data_table SET company = ?, product_name = ? WHERE company = ? AND product_name = ?";
//
//                // PreparedStatement 객체 생성
//                statement = connection.prepareStatement(query);
//
//                // 파라미터 설정
//                statement.setString(1, newCompany);
//                statement.setString(2, newProductName);
//                statement.setString(3, company);
//                statement.setString(4, productName);
//
//                // 쿼리 실행
//                statement.executeUpdate();
//
//            } catch (SQLException e) {
//                System.out.println("데이터 처리 오류입니다.");
//                e.printStackTrace();
//            } catch (Exception e) {
//                System.out.println("예외가 발생했습니다.");
//                e.printStackTrace();
//            } finally {
//                // 리소스 해제
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        System.out.println("PreparedStatement를 닫는데 실패했습니다.");
//                        e.printStackTrace();
//                    }
//                }
//
//                // 데이터베이스 연결 해제
//                JdbcDBConnection.closeConnection(connection);
//            }
//        }
//    }
//
//    // 데이터를 삭제하는 메서드
//    public void deleteData(HttpServletRequest request, String company, String productName) {
//        // 데이터베이스 연결
//        Connection connection = JdbcDBConnection.getConnection();
//
//        if (connection != null) {
//            PreparedStatement statement = null;
//
//            try {
//                // SQL 쿼리 작성
//                String query = "DELETE FROM data_table WHERE company = ? AND product_name = ?";
//
//                // PreparedStatement 객체 생성
//                statement = connection.prepareStatement(query);
//
//                // 파라미터 설정
//                statement.setString(1, company);
//                statement.setString(2, productName);
//
//                // 쿼리 실행
//                statement.executeUpdate();
//
//            } catch (SQLException e) {
//                System.out.println("데이터 처리 오류입니다.");
//                e.printStackTrace();
//            } catch (Exception e) {
//                System.out.println("예외가 발생했습니다.");
//                e.printStackTrace();
//            } finally {
//                // 리소스 해제
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        System.out.println("PreparedStatement를 닫는데 실패했습니다.");
//                        e.printStackTrace();
//                    }
//                }
//
//                // 데이터베이스 연결 해제
//                JdbcDBConnection.closeConnection(connection);
//            }
//        }
//    }
//}
//
