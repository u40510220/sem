//import com.napier.sem.App;
import com.napier.sem.App;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;



public class MyTest {
    static App app;
    static String continent = "Europe";
    static String region = "Polynesia";
    static String country = "Spain";
    static String district = "Shanghai";
    static String city = "Madrid";
    static int top = 5;


    @BeforeAll
    static void init(){
        // Connect to database
        app = new App();
        app.connect();

    }
    @BeforeAll
    static void IntegrationDB() throws SQLException {
        boolean tableFound=false;
        if(app.con != null) {

            Statement stmt = app.con.createStatement();

            String strSelect = "CREATE TABLE world.s(id);";

            stmt.executeQuery(strSelect);

            strSelect = "SHOW TABLES\n" +
                    "FROM world;";

            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                if(rset.getString("Tables_in_world")=="s"){
                    tableFound=true;

                    stmt = app.con.createStatement();

                    strSelect = "DROP TABLE world.s;";

                    stmt.executeQuery(strSelect);
                }
            }
        }
        assertEquals(tableFound, true);
    }

    @Test
    void Q1()
    {
        app.Q1();
    }
    @Test
    void Q2()
    {
        app.Q2(continent);
        //TODO test different inputs
    }
    @Test
    void Q3()
    {
        app.Q3(region);
    }
    @Test
    void Q4()
    {
        app.Q4(top);
    }
    @Test
    void Q5()
    {
        app.Q5(continent,top);
    }
    @Test
    void Q6()
    {
        app.Q6(region,top);
    }
    @Test
    void Q7()
    {
        app.Q7();
    }@Test
    void Q8()
    {
        app.Q8(continent);
    }
    @Test
    void Q9()
    {
        app.Q9(region);
    }
    @Test
    void Q10()
    {
        app.Q10(country);
    }
    @Test
    void Q11()
    {
        app.Q11(district);
    }
    @Test
    void Q12()
    {
        app.Q12(top);
    }
    @Test
    void Q13()
    {
        app.Q13(continent, top);
    }
    @Test
    void Q14()
    {
        app.Q14(region, top);
    }
    @Test
    void Q15()
    {
        app.Q15(country, top);
    }
    @Test
    void Q16()
    {
        app.Q16(district, top);
    }
    @Test
    void Q17()
    {
        app.Q17();
    }
    @Test
    void Q18()
    {
        app.Q18(continent);
    }
    @Test
    void Q19()
    {
        app.Q19(region);
    }
    @Test
    void Q20()
    {
        app.Q20(top);
    }
    @Test
    void
    Q21()
    {
        app.Q21(continent, top);
    }
    @Test
    void Q22()
    {
        app.Q22(region, top);
    }
    @Test
    void Q23()
    {
        app.Q23();
    }
    @Test
    void Q24()
    {
        app.Q24();
    }
    @Test
    void Q25()
    {
        app.Q25();
    }
    @Test
    void Q31()
    {
        app.Q31();
    }
    @Test
    void Q32()
    {
        app.Q32(continent);
    }
    @Test
    void Q33()
    {
        app.Q33(region);
    }
    @Test
    void Q34()
    {
        app.Q34(country);
    }
    @Test
    void Q35()
    {
        app.Q35(district);
    }
    @Test
    void Q36()
    {
        app.Q36(city);
    }
    @Test
    void Q37()
    {
        app.Q37();
    }


    @Test
    void connect(){
        app.connect();
        //if connection successful useless assertEquals confirms it
    }

    @AfterAll
    static void finalise() {
        app.disconnect();
    }

}
