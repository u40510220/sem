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
        Scanner scanner = new Scanner(System.in);
        String territory;
        int topFilter;

        // Create new Application
        App app = new App();

        // Connect to database
        app.connect();
        System.out.println("hi there2");
        // Get Employee
        //Employee emp = app.getEmployee2(255530);
        app.Q1();
        System.out.println("hi there");
        // Display results
        //app.displayEmployee(emp);
        // Disconnect from database
        app.disconnect();

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
                    Enter the report number here:""");
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

    public void getEmployee3()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //String strSelect="SHOW TABLES";
            String strSelect="Select Name " +"From country " + "Order By country.Population Desc";


            //city
                //  CountryCode
                //  District
                //  ID
                //  Name
                //  Population
            //country
                //  Capital
                //  Code
                //  Code2
                //  Continent
                //  GNP
                //  GNPOld
                //  GovernmentForm
                //  HeadOfState
                //  IndepYear
                //  LifeExpectancy
                //  LocalName
                //  Name
                //  Population
                //  Region
                //  SurfaceArea

            //countrylanguage
                //  CountryCode
                //  Language
                //  IsOfficial
                //  Percentage

            //String strSelect= "SELECT TABLE_NAME " +
            //        "FROM INFORMATION_SCHEMA.TABLES " +
            //        "WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_CATALOG='world'";

            //String strSelect =
            //        "SELECT emp_no, first_name, last_name "
            //                + "FROM employees "
            //                + "WHERE emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned

            ResultSetMetaData rsmd = rset.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rset.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rset.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }

            //if (rset.next())
            //{
            //    //rset.
            //    Employee emp = new Employee();
            //    emp.emp_no = rset.getInt("emp_no");
            //    emp.first_name = rset.getString("first_name");
            //    emp.last_name = rset.getString("last_name");
            //    return emp;
            //}
            //else
            //    return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            //return null;
        }
    }

    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
    }

    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
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