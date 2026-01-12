package at.Technikum;

public class Customer {
    public String CustomerID;
    public String name;

    public Customer(){}

    public Customer (String name,String CustomerID) {this.name = name; this.CustomerID = CustomerID;}


    public String getCustomerID () {return this.CustomerID;}
    public String getName () {return this.name;}


    public String toString()
    {return CustomerID + ";" + name;}


}
