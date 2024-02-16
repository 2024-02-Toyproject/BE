package smu.toyproject1.DBConnection;

import org.junit.jupiter.api.Test;
import smu.toyproject1.repository.JdbcDBConnection;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

public class JdbcDBConnectionTest {
    @Test
    public void testRetrieveDataFromTable() {
        try {
            // 데이터 베이스와 연결에 성공하면 "데이터베이스 연결 성공!" 메시지를 띄움.
            JdbcDBConnection.retrieveCLDataFromTable("신용대출");
        } catch (Exception e) {
            fail("Exception should not have been thrown.");
        }
    }
}
