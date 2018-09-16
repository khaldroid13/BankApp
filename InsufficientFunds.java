/*
 * Title: InsufficientFunds.java
 * Author: Khaled Basrawi
 * Date: 15 Sep 18
 * Purpose: Creates an InsufficientFunds exception and associated message
*/


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InsufficientFunds extends Exception{
    
    public InsufficientFunds(){
        
    }
    
    public InsufficientFunds(String exceptionMessage){
        super(exceptionMessage);
        JFrame exceptionFrame = new JFrame();
        JOptionPane.showMessageDialog(exceptionFrame,"Insufficient Funds!");
    }
}
