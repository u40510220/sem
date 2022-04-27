package com.napier.sem;


//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoCollection;
//import org.bson.Document;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * This app will ask the user for the report type he wants and will then communicate with the database part of the project
 *
 * User will have to select a report type and the number selected will be send to another class that will get in return the requiered swl script
 *
 *
 */
public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    public static void main(String[] args)
    {



        // Create new Application
        App app = new App();

        // Connect to database
        app.connect();
        System.out.println("hi there2");
        // Get Employee
        //Employee emp = app.getEmployee2(255530);
        app.RunAll();
        System.out.println("hi there");
        // Display results
        //app.displayEmployee(emp);
        // Disconnect from database
        app.disconnect();
        if (true){ return;}
        Scanner scanner = new Scanner(System.in);
        String territory;
        int topFilter;
        //return;
        int aa = scanner.nextInt();
        //TODO: Add validation for inputs(easy)
        //Ask user to decide between all reports
        System.out.println("""
                1) All the countries in the world organised by largest population to smallest
                2) All the countries in a continent organised by largest population to smallest
                3) All the countries in a region organised by largest population to smallest
                4) The top N populated countries in the world where N is provided by the user
                5) The top N populated countries in a continent where N is provided by the user
                6) The top N populated countries in a region where N is provided by the user
                7) All the cities in the world organised by largest population to smallest
                8) All the cities in a continent organised by largest population to smallest
                9) All the cities in a region organised by largest population to smallest
                10) All the cities in a country organised by largest population to smallest
                11) All the cities in a district organised by largest population to smallest
                12) The top N populated cities in the world where N is provided by the user
                13) The top N populated cities in a continent where N is provided by the user
                14) The top N populated cities in a region where N is provided by the user
                15) The top N populated cities in a country where N is provided by the user
                16) The top N populated cities in a district where N is provided by the user
                17) All the capital cities in the world organised by largest population to smallest
                18) All the capital cities in a continent organised by largest population to smallest
                19) All the capital cities in a region organised by largest to smallest
                20) The top N populated capital cities in the world where N is provided by the user
                21) The top N populated capital cities in a continent where N is provided by the user
                22) The top N populated capital cities in a region where N is provided by the user
                23) The population of people, people living in cities, and people not living in cities in each continent
                24) The population of people, people living in cities, and people not living in cities in each region
                25) The population of people, people living in cities, and people not living in cities in each country
                26) Additional information reports
                Enter the report number here:""");
        int a = scanner.nextInt();

        //Ask again but for special reports
        if (a == 26){

            System.out.println("""
                    1) Population of the world
                    2) Population of a continent
                    3) Population of a region
                    4) Population of a country
                    5) Population of a district
                    6) Population od a city
                    7) Language report
                    Enter the report number here: """);
            a = 30 + scanner.nextInt();
        }

        //get the territory type specification
        if ( a == 1|| a == 4|| a == 7|| a == 12|| a == 17|| a == 20|| a == 31|| a == 37){
            territory = "WORLD";
        }
        else if ( a == 2|| a == 5|| a == 8|| a == 13|| a == 18|| a == 21|| a == 23|| a == 32){
            System.out.println("Enter the name of the continent: ");
            territory = scanner.next().toUpperCase(Locale.ROOT);
        }
        else if ( a == 3|| a == 6|| a == 9|| a == 14|| a == 19|| a == 22|| a == 24|| a == 33){
            System.out.println("Enter the name of the region: ");
            territory = scanner.next().toUpperCase(Locale.ROOT);
        }
        else if ( a == 10|| a == 15|| a == 25|| a == 34){
            System.out.println("Enter the name of the country: ");
            territory = scanner.next().toUpperCase(Locale.ROOT);
        }
        else if ( a == 11|| a == 16|| a == 35){
            System.out.println("Enter the name of the district: ");
            territory = scanner.next().toUpperCase(Locale.ROOT);
        }
        else if ( a == 36){
            System.out.println("Enter the name of the city: ");
            territory = scanner.next().toUpperCase(Locale.ROOT);
        }
        else{
            System.out.println("wrong number input...");
            territory = "no territory";
        }

        //If report requires a number for top results asks for one, else number will be 0
        //TODO: 0 must be recognised as no top filter in the code that gets this information
        if (a == 4|| a == 5|| a == 6|| a == 12| a == 2613|| a == 14|| a == 15|| a == 16|| a == 20|| a == 21|| a == 22){
            System.out.println("How many results from the top would you like to get: ");
            topFilter = scanner.nextInt();
        }
        else{
            topFilter = 0;
        }

        //Quick system out to ckeck it is working
        System.out.println("The report number: " + a + ", will be displayed about territory: " + territory + ", filtering the top: " + topFilter + " results.");





    }

    public void RunAll(){
        Q1();
        Q2("Europe");
        Q3("Polynesia");
        Q4(3);
        Q5("Europe",6);
        Q6("Polynesia",3);
        Q7();
        Q8("Europe");
        Q9("Polynesia");
        Q10("China");
        Q11("Shanghai");
        Q12(4);
        Q13("Europe",6);
        Q14("Polynesia",5);
        Q15("China",2);
        Q16("Shanghai",2);
        Q17();
        Q18("Europe");
        Q19("Polynesia");
        Q20(3);
        Q21("Europe",4);
        Q22("Polynesia",6);
        Q23();
        Q24();
        Q25();
        Q31();
        Q32("Europe");
        Q33("Polynesia");
        Q34("China");
        Q35("Shanghai");
        Q36("Tokyo");
        Q37();




    }

    public void Q0()
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
        }
    }

    public void Q1()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From country " + "Order By country.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
            //return employees;
        }
        catch (Exception e)
        {
        }
    }

    public void Q2(String Continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name,Continent " +"From country WHERE Continent='"+Continent+"' " + "Order By country.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));

            }
            //return employees;
        }
        catch (Exception e)
        {
        }
    }

    public void Q3(String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name,Continent " +"From country WHERE Region='"+Region+"' " + "Order By country.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));

            }
            //return employees;
        }
        catch (Exception e)
        {
        }
    }

    public void Q4(int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From country " + "Order By country.Population Desc "+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
            //return employees;
        }
        catch (Exception e)
        {
        }
    }

    public void Q5(String Continent,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From country WHERE Continent='"+Continent+"'  " + "Order By country.Population Desc "+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
            //return employees;
        }
        catch (Exception e)
        {
        }
    }

    public void Q6(String Region,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From country WHERE Region='"+Region+"'  " + "Order By country.Population Desc "+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
            //return employees;
        }
        catch (Exception e)
        {
        }
    }

    public void Q7()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From city " + "Order By city.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {}
    }

    public void Q8(String Continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.CountryCode = m.Code WHERE m.Continent='"+Continent+"' Order by c.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q9(String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.CountryCode = m.Code WHERE m.Region='"+Region+"' Order by c.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q10(String Country)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.CountryCode = m.Code WHERE m.Name='"+Country+"' Order by c.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q11(String District)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From city WHERE District='"+District+"' " + "Order By city.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {}
    }

    public void Q12(int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From city " + "Order By city.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {}
    }

    public void Q13(String Continent,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.CountryCode = m.Code WHERE m.Continent='"+Continent+"' Order by c.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q14(String Region,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.CountryCode = m.Code WHERE m.Region='"+Region+"' Order by c.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q15(String Country,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.CountryCode = m.Code WHERE m.Name='"+Country+"' Order by c.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q16(String District,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Name " +"From city WHERE District='"+District+"' " + "Order By city.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {}
    }

    public void Q17()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.ID = m.Capital Order by c.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q18(String Continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.ID = m.Capital WHERE m.Continent='"+Continent+"' Order by c.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q19(String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.ID = m.Capital WHERE m.Region='"+Region+"' Order by c.Population Desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q20(int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.ID = m.Capital Order by c.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q21(String Continent,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.ID = m.Capital WHERE m.Continent='"+Continent+"' Order by c.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q22(String Region,int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT c.Name FROM country m INNER JOIN city c ON c.ID = m.Capital WHERE m.Region='"+Region+"' Order by c.Population Desc"+" LIMIT "+Integer.toString(n);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q23()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT country.Continent, SUM(distinct country.population) AS \"Total population\" , SUM(distinct city.population) AS \"Poppulation in cities\", (SUM(Distinct country.population)-SUM(distinct city.population)) AS \"Population outside cities\" \n" +
                    "FROM city Right JOIN country ON city.CountryCode = country.Code\n" +
                    "GROUP BY country.Continent\n" +
                    "ORDER BY SUM(country.population) DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q24()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT country.Region, SUM(distinct country.population) AS \"Total population\" , SUM(distinct city.population) AS \"Poppulation in cities\", (SUM(distinct country.population)-SUM(distinct city.population)) AS \"Population outside cities\" \n" +
                    "FROM city Right JOIN country ON city.CountryCode = country.Code\n" +
                    "GROUP BY country.Region\n" +
                    "ORDER BY SUM(distinct country.population) DESC;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q25()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="SELECT country.Name, country.population AS \"Total population\", SUM(Distinct city.population) AS \"Poppulation in cities\", (country.population-SUM(Distinct city.population)) AS \"Population outside cities\"   \n" +
                    "FROM city left JOIN country ON city.CountryCode = country.Code  \n" +
                    "GROUP BY CountryCode \n" +
                    "ORDER BY country.population DESC;\n";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q31()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select sum(population)\n" +
                    "from country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q32(String Continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select sum(population)\n" +
                    "from country\n" +
                    "where continent = "+ Continent;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q33(String Region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select sum(population)\n" +
                    "from country\n" +
                    "where region = " + Region;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q34(String Country)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select sum(population)\n" +
                    "from country\n" +
                    "where name = " + Country;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q35(String District)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select sum(Distinct country.population)\n" +
                    "from country join city on country.code = city.countrycode\n" +
                    "where district = " + District;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void Q36(String City)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select sum(Distinct population)\n" +
                    "from city\n" +
                    "where name = " + City;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
    public void Q37()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect="Select Language, Sum(percentage)\n" +
                    "From CountryLanguage \n" +
                    "Where language = \"Chinese\" OR language = \"English\" OR language = \"Hindi\" OR language = \"Spanish\" OR language = \"Arabic\"\n" +
                    "Group by Language\n";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next())
            {
                System.out.println(rset.getString("Name"));
            }
        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }



    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
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

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
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