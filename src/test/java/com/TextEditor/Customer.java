package com.TextEditor;

public class Customer {
    public String CustomerID;
    public String name;

    public void Customer(){}

    public void Customer (String name,String CustomerID) {this.name = name; this.CustomerID = CustomerID;}


    public String getCustomerID () {return this.CustomerID;}
    public String getName () {return this.name;}


    public String toString()
    {return CustomerID + ";" + name;}


}
