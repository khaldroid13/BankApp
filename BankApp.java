
/*
 * Title: BankApp.java
 * Author: Khaled Basrawi
 * Date: 15 Sep 18
 * Purpose: Creates a GUI for an ATM/Bank App, runs a switch to deal with inputs, and uses the Account.java and InsufficientFunds.java classes
*/

//Import appropriate packages and elements
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class BankApp extends javax.swing.JComponent implements ActionListener{
   
       
    //Set size of the frame
    	static final int FRAMEWIDTH = 450;
    	static final int FRAMEHEIGHT = 400;
        static final int BUTTONWIDTH = 200;
        static final int BUTTONHEIGHT = 100;
        private final JButton balanceButton;
        private final JButton transferButton;
        private final JButton withdrawButton;
        private final JButton depositButton ;
        private JRadioButton checkingRadio;
        private JRadioButton savingsRadio;
        private JTextField entry;
        private JLabel entryLabel;
        private final JFrame atmFrame = new JFrame();
        private final JPanel mainPanel = new JPanel();
        private final JPanel atmPanel = new JPanel();
       
        //GUI default constructor
        public BankApp(){
            //Create the panel
            atmPanel.setLayout(new GridLayout(4,2,20,20));
           
            //Create frame
            atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            atmFrame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
            atmFrame.setLocationRelativeTo(null);
            atmFrame.setTitle("Welcome to the Bank");

            //Add buttons and text fields
            balanceButton = new JButton("Balance");
            transferButton = new JButton("Transfer");
            withdrawButton = new JButton("Withdraw");
            depositButton = new JButton("Deposit");
            checkingRadio = new JRadioButton("Checking");
            savingsRadio = new JRadioButton("Savings");
            entry = new JTextField("");
            entryLabel = new JLabel("Enter an Amount:");
            
            ButtonGroup accountSelector = new ButtonGroup();
            accountSelector.add(checkingRadio);
            accountSelector.add(savingsRadio);
            
            balanceButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);
            transferButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);
            withdrawButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);
            depositButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);


            entry.setSize(200, 400);
            
            //Add components to the panel
            atmPanel.add(depositButton);
            atmPanel.add(withdrawButton);
            atmPanel.add(balanceButton);
            atmPanel.add(transferButton);
            atmPanel.add(checkingRadio);
            atmPanel.add(savingsRadio);
            atmPanel.add(entryLabel);
            atmPanel.add(entry);
            
            //Create listeners for the respective buttons
            balanceButton.addActionListener(BankApp.this);
            transferButton.addActionListener(BankApp.this);
            withdrawButton.addActionListener(BankApp.this);
            depositButton.addActionListener(BankApp.this);
            checkingRadio.addActionListener(BankApp.this);
            savingsRadio.addActionListener(BankApp.this);
            
            //Set action commands for each button
            balanceButton.setActionCommand("Balance");
            transferButton.setActionCommand("Transfer");
            withdrawButton.setActionCommand("Withdraw");
            depositButton.setActionCommand("Deposit");
            checkingRadio.setActionCommand("Checking");
            savingsRadio.setActionCommand("Savings");
            
            //Make the GUI visible
            atmFrame.add(atmPanel);
            atmPanel.setVisible(true);
            atmFrame.setVisible(true);
            atmFrame.setResizable(false);
            
            
        }//End BankApp GUI constructor

    //Method to test for number
    public boolean isNumber(String text){
            
            try{
                //Receive entry and convert to double
                double entryNumber = Double.parseDouble(text);
                
            }catch(NumberFormatException ex){
                
                //Create error message based on number format exception
                JFrame numberFormatFrame = new JFrame();
                JOptionPane.showMessageDialog(numberFormatFrame,"Please enter a number.");
                return false;
                }

     return true;
     
    }//End isNumber

    //Method to test for increment of 20
    public boolean isIncrement(String text){
        //Receive entry and convert to double
        double withdrawalAmt = Double.parseDouble(text);
                
                if(withdrawalAmt%20==0){
                    return true;
                }else{
                     //Create error message based on number format exception
                     JFrame incrementFrame = new JFrame();
                     JOptionPane.showMessageDialog(incrementFrame,"Withdrawals must be in $20 increments.");
                    return false;
                }

    }//End isIncrement

     
//Define method to deal with individual actions based on listeners defined in GUI

    public void buttonPressed(ActionEvent e){

    //Assign a string to represent a button being pressed/selected
    String buttonPress = e.getActionCommand();

    //Switch includes cases for each button. Insufficient funds exception is caught as appropriate

    switch(buttonPress){

        case "Withdraw":

            String text = entry.getText();

            //Test if input is a number and an increment of $20
            if(isNumber(text) == true){
                if (isIncrement(text)==true){

                    if (checkingRadio.isSelected()){
                       try{

                        checkingAcct.withdraw(Double.parseDouble(entry.getText()));

                       }catch(InsufficientFunds ife){

                         }

                    } 
                    if (savingsRadio.isSelected()){

                        try{

                             savingsAcct.withdraw(Double.parseDouble(entry.getText()));

                         }catch(InsufficientFunds ife){

                              }
                        if(savingsAcct.atmFeeCounter() + checkingAcct.atmFeeCounter()%4 == 0){
                               if (checkingRadio.isSelected()){
                                   try{
                                       checkingAcct.atmFee();
                                   }catch (InsufficientFunds ife){

                                     }
                               }

                                if (savingsRadio.isSelected()){
                                    try{
                                        savingsAcct.atmFee();
                                    }catch(InsufficientFunds ife){

                                     }
                                }

                        }

                    }
                }
            }
        //End withdrawal case
        break;

        case "Deposit":

            text = entry.getText();
            if(isNumber(text) == true){

                    if (checkingRadio.isSelected()){
                        try{

                        checkingAcct.deposit(Double.parseDouble(entry.getText()));

                        }catch(InsufficientFunds ife){

                    } 
                    }
                    if (savingsRadio.isSelected()){

                        try{
                        savingsAcct.deposit(Double.parseDouble(entry.getText()));

                        }catch(InsufficientFunds ife){

                         }
                    }
            }
        //End Deposit case
        break;

        case "Balance":

        double balanceAmt;
        if (checkingRadio.isSelected()){

            checkingAcct.getBalance();
            JFrame balanceFrame = new JFrame();
            JOptionPane.showMessageDialog(balanceFrame, "Your balance is: " + checkingAcct.getBalance());

        }else if(savingsRadio.isSelected()){

            savingsAcct.getBalance();
            JFrame balanceFrame = new JFrame();
            JOptionPane.showMessageDialog(balanceFrame, "Your balance is: " + savingsAcct.getBalance());

        }
        //End Balance case
        break;

        case "Transfer":

            text = entry.getText();
            if(isNumber(text) == true){

                if (checkingRadio.isSelected()){
                    try{

                    savingsAcct.withdraw(Double.parseDouble(entry.getText()));
                    checkingAcct.deposit(Double.parseDouble(entry.getText()));

                    }catch(InsufficientFunds ife){

                     }

                 if (savingsRadio.isSelected()){

                    try{

                    savingsAcct.deposit(Double.parseDouble(entry.getText()));
                    checkingAcct.withdraw(Double.parseDouble(entry.getText()));

                    }catch(InsufficientFunds ife){

                     }
                 }
                }
            }//End ifNumber

            //End Transfer case
            break;

        }//end switch
    }//End buttonPressed method
            

    public static void main(String[] args){

           BankApp bankAppOne = new BankApp();
           Account checkingAcct = new Account(0);
           Account savingsAcct = new Account(0);
          


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
 