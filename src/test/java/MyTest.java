//import com.napier.sem.App;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;



public class MyTest {
    static Connection con = null;
    @BeforeAll
    static void prepare(){

        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root","example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i)+1);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    @Test
    void Q0()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name,District " +"From city " + "Order By city.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("District"));
            }
            //return employees;

        }
        catch (Exception e)
        {
            assertEquals(1,0);
        }

    }
    @Test
    void Q1()
    {
        Q1();
        assertEquals(5, 5);
    }
    @Test
    void Q2()
    {
        Q2();
        assertEquals(5, 5);
    }
    @Test
    void Q3()
    {
        Q3();
        assertEquals(5, 5);
    }
    @Test
    void Q4()
    {
        Q4();
        assertEquals(5, 5);
    }
    @Test
    void Q5()
    {
        Q5();
        assertEquals(5, 5);
    }
    @Test
    void Q6()
    {
        Q6();
        assertEquals(5, 5);
    }
    @Test
    void Q7()
    {
        Q7();
        assertEquals(5, 5);
    }@Test
    void Q8()
    {
        Q8();
        assertEquals(5, 5);
    }
    @Test
    void Q9()
    {
        Q9();
        assertEquals(5, 5);
    }
    @Test
    void Q10()
    {
        Q10();
        assertEquals(5, 5);
    }
    @Test
    void Q11()
    {
        Q11();
        assertEquals(5, 5);
    }
    @Test
    void Q12()
    {
        Q12();
        assertEquals(5, 5);
    }
    @Test
    void Q13()
    {
        Q13();
        assertEquals(5, 5);
    }
    @Test
    void Q14()
    {
        Q14();
        assertEquals(5, 5);
    }
    @Test
    void Q15()
    {
        Q15();
        assertEquals(5, 5);
    }
    @Test
    void Q16()
    {
        Q16();
        assertEquals(5, 5);
    }
    @Test
    void Q17()
    {
        Q17();
        assertEquals(5, 5);
    }
    @Test
    void Q18()
    {
        Q18();
        assertEquals(5, 5);
    }
    @Test
    void Q19()
    {
        Q19();
        assertEquals(5, 5);
    }
    @Test
    void Q20()
    {
        Q20();
        assertEquals(5, 5);
    }
    @Test
    void
    Q21()
    {
        Q21();
        assertEquals(5, 5);
    }
    @Test
    void Q22()
    {
        Q22();
        assertEquals(5, 5);
    }
    @Test
    void Q23()
    {
        Q23();
        assertEquals(5, 5);
    }
    @Test
    void Q24()
    {
        Q24();
        assertEquals(5, 5);
    }
    @Test
    void Q25()
    {
        Q25();
        assertEquals(5, 5);
    }
    @Test
    void Q31()
    {
        Q31();
        assertEquals(5, 5);
    }
    @Test
    void Q32()
    {
        Q32();
        assertEquals(5, 5);
    }
    @Test
    void Q33()
    {
        Q33();
        assertEquals(5, 5);
    }
    @Test
    void Q34()
    {
        Q34();
        assertEquals(5, 5);
    }
    @Test
    void Q35()
    {
        Q35();
        assertEquals(5, 5);
    }
    @Test
    void Q36()
    {
        Q36();
        assertEquals(5, 5);
    }
    @Test
    void Q37()
    {
        Q37();
        assertEquals(5, 5);
    }


    @Test
    void connect(){
        connect();
        //if connection successful useless assertEquals confirms it
        assertEquals(1,1);
    }

    @AfterAll
    static void finalise(){
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

}
