import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
class MyTest{
    @BeforeAll
    static void init(){

    }
    @Test
    void unitTest(){
        assertEquals(1,1);
    }

}