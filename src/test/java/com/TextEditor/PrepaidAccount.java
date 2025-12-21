package com.TextEditor;

public class PrepaidAccount {
    public String accountID;
    public Integer Balance;

    public int viewCurrentBalance () {return this.Balance;}
   public void addBalance(int value) {this.Balance += value;}
    public void deductBalance(int value) {this.Balance -= value;}
     }

