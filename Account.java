/*
 * Title: Account.java
 * Author: Khaled Basrawi
 * Date: 15 Sep 18
 * Purpose: Defines Account objects and associates them to functions that the GUI will implement
*/



public class Account {
    
    private double balance;
    private int numOfWithdrawals;
    
    public Account(){
        
    }
    
    public Account(double balance){
        balance = 0;
        numOfWithdrawals = 0;
        
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void withdraw(double amount)throws InsufficientFunds{
        
        if (amount > balance){
            throw new InsufficientFunds("Insufficient Funds!");
        }
        else{
        balance -= amount;
        numOfWithdrawals++;
        
    }
    }
    
    public void deposit(double amount){
        balance+= amount;
    }
    
    public int atmFeeCounter(){
        return numOfWithdrawals;
    }
    
    public void atmFee() throws InsufficientFunds{
        if(1.5>balance){
            throw new InsufficientFunds("Insufficient Funds!");
    }
    else{
    
    balance -=1.5;
    }
        
        
    }
    
}
