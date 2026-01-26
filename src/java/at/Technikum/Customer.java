package at.Technikum;

public class Customer {
    public String CustomerID;
    public String name;

    public Customer (String name,String CustomerID) {this.name = name; this.CustomerID = CustomerID;}

    public String toString()
    {return CustomerID + ";" + name;}


}
