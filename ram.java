//Siddharth Singh

import java.util.*;
import java.io.*;
public class ram
{
    Scanner sc = new Scanner(System.in);
    void homepage() //first page shown when the user visits the website
    {
        ram obj = new ram();
        System.out.println("Welcome to Rent A Movie");
        System.out.println("Would you like to sign up or login? Please type your input");
                String inp = sc.nextLine();
        try{
            if(inp.toLowerCase().equals("sign up"))
                obj.signup();
            else if(inp.toLowerCase().equals("login"))
                obj.login();
            else
            {
                System.out.println("Invalid Input! Please try again");
                obj.homepage();
            }}
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }
    void signup() throws FileNotFoundException, IOException //creates a new account for the user
    {
        ram obj = new ram();
        FileWriter fout = new FileWriter("UserDatabase.txt", true);
        BufferedWriter bout = new BufferedWriter(fout);
        PrintWriter pout = new PrintWriter(fout);
        System.out.println("Enter email");
        String e = sc.nextLine();
        System.out.println("Enter password");
        String p = sc.nextLine();
        pout.println(e);
        pout.println(p);
        pout.close();
        bout.close();
        fout.close();
        System.out.println("Your account has been created! Please login with your new account");
        obj.login();
    }
    void login() throws FileNotFoundException, IOException //makes the user login with their already existing account
{
    ram obj = new ram();
    System.out.println("Enter email");
    String e = sc.nextLine();
    System.out.println("Enter password");
    String p = sc.nextLine();
    FileReader fin = new FileReader("UserDatabase.txt");
    BufferedReader bin = new BufferedReader(fin);
    boolean auth = false;
    String email,password;
    while((email = bin.readLine())!=null)
    {
        password = bin.readLine();
        if((email.equals(e))&&(password.equals(p)))
            auth = true;
    }
    if(auth == true)
    {
        System.out.println("Authorized");
        obj.movies();
    }
    if(auth==false)
    {
        System.out.println("Wrong email or password! Please try again");
        obj.login();
    }
    bin.close();
    fin.close();
}
    void movies() throws FileNotFoundException, IOException //helps the user to browse throught the library of movies available
{
    ram obj = new ram();
    System.out.println("Welcome to our large and expanding library of movies!");
    System.out.println("Please type 'list' to view our large library of movies, type 'search' to search for a movie");
    String inp = sc.nextLine();
    String m,p;
    if(inp.toLowerCase().equals("list"))
    {
        FileReader fin = new FileReader("RentingDatabase.txt");
        BufferedReader bin = new BufferedReader(fin);
        while((m = bin.readLine()) != null)
        {
            p = bin.readLine();
            System.out.println("Movie: " + m);
            System.out.println("Rate: Rs." + p + "/day");
        }
        bin.close();
        fin.close();
        obj.movies();
    }
    else if(inp.toLowerCase().equals("search"))
    {
        obj.bill();
    }
    else
    {
        System.out.println("Invalid input! Please try again");
        obj.movies();
    }
}
    void bill() throws FileNotFoundException, IOException //generates bill
    {
        ram obj = new ram();
        String movie;
        int price = 0;
        boolean searchQueryFound = false;
        System.out.println("Enter the name of the movie you would like to rent");
                String m = sc.nextLine();
        m = m.toLowerCase();
        FileReader fin = new FileReader("RentingDatabase.txt");
        BufferedReader bin = new BufferedReader(fin);
        while((movie = bin.readLine()) != null)
        {
            if(m.equals(movie))
            {
                price = Integer.parseInt(bin.readLine());
                searchQueryFound = true;
            }
            if(searchQueryFound == true)
            {
                System.out.println("Movie found!");
                break;
            }
        }
        if(searchQueryFound == false)
        {
            System.out.println("Movie not found, please try again");
            obj.bill();
        }
        if(searchQueryFound==true)
        {
            System.out.println("Please enter your name");
            String name = sc.nextLine();
            System.out.println("Enter the number of days you would like to rent the movie for");
            int days = sc.nextInt();
            FileWriter fout = new FileWriter("Bill.txt");
            BufferedWriter bout = new BufferedWriter(fout);
            PrintWriter pout = new PrintWriter(fout);
            pout.println("Customer name: " + name);
            pout.println("Movie name: " + m);
            pout.println("Rate: Rs." + price + "/day");
            pout.println("Days: " + days);
            pout.println("Total cost: Rs." + (price*days));
            pout.close();
            bout.close();
            pout.close();
            String text;
            System.out.println("Bill:");
            FileReader fin1 = new FileReader("Bill.txt");
            BufferedReader bin1 = new BufferedReader(fin1);
            while((text = bin1.readLine()) != null)
            {
                System.out.println(text);
            }
            bin.close();
            fin.close();
            System.out.println("The total cost displayed in the bill will be shortly deducted from your registered method of payment.");
                    System.out.println("You can find a copy of your bill in Bill.txt stored in your system");
                            System.out.println("Please note that all transactions are nonrefundable");
            System.out.println("Thank you and have a wonderful day!");
            System.out.println("Copyright © Rent A Movie. All Rights Reserved.");
        }
    }
    public static void main(String args[]) //main method
    {
        ram obj = new ram();
        obj.homepage();
    }
}