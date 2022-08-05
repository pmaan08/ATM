import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'INR'###,##0.00");
    HashMap<Integer, Account> data = new HashMap<Integer, Account>();
    
    public void getLogin() throws IOException {
        boolean end=false;
        int customerID = 0;
        int pinNumber = 0 ;
        while (!end) {
            try { 
                  System.out.print("\nEnter your bank customer id: "); 
                  customerID = menuInput.nextInt();
                  System.out.println("Enter your PIN number: ");
                  pinNumber = menuInput.nextInt();
                  Iterator itr = data.entrySet().iterator();
                  while (itr.hasNext()) {
                      Map.Entry pair = (Map.Entry) itr.next();
                      Account acc = (Account) pair.getValue();
                      if (data.containsKey(customerID) && pinNumber == acc.getPinNumber()) {
                        getAccountType(acc);
                        end = true;
                        break;
                      }
                  }
                  if (!end) {
                      System.out.println("Wrong Customer id or Pin Number");
                  }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Characters");
            }
        }
    }
    
    public void createAccount() throws IOException {
        int aadhar_no = 0;
        boolean end = false;
        while (!end) {
            try {
                 System.out.println("Enter last 4 digits of your aadhar card number");
                  aadhar_no = menuInput.nextInt();
                  Iterator itr = data.entrySet().iterator();
                  while (itr.hasNext()) {
                      Map.Entry pair = (Map.Entry) itr.next();
                      if (!data.containsKey(aadhar_no)) {
                          end = true;
                        }
                  }
                  if (!end) {
                      System.out.println("This customer number is already registered");
                  }    
            } catch(InputMismatchException e) {
                System.out.println("Invalid Choice");
                menuInput.next();
            }
        }
        System.out.println("\nEnter PIN to register");
        int pin = menuInput.nextInt();
        data.put(aadhar_no, new Account(aadhar_no, pin));
        System.out.println("Your new account has been successfully registered");
        System.out.println("Login..");
        getLogin();
    }
    
    public void getAccountType(Account acc) {
        boolean end = false;
        while(!end) {
            try {
                System.out.println("Select account you want to access");
                System.out.println("1)Current Account");
                System.out.println("2)Savings Account");
                System.out.println("3)Exit");
                int acct_type = menuInput.nextInt();
                
                switch (acct_type) {
                    case 1:
                        getCurrent(acc);
                        break;
                    case 2: 
                        getSavings(acc);
                        break;
                    case 3:
                         end = true;
                         break;
                    default:
                        System.out.println("Invalid Choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Choice[1-3]");
                menuInput.next();
            }
        }
    }
    
    public void getCurrent(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("Current Account");
                System.out.println("1) View Balance");
		System.out.println("2) Withdraw Funds");
		System.out.println("3) Deposit Funds");
		System.out.println("4) Transfer Funds");
		System.out.println("5) Exit");
		int process = menuInput.nextInt();
		
		switch (process) {
		    case 1:
		        System.out.println("\nAccount Balance: "+  moneyFormat.format(acc.getCurrentBalance()));
		        break;
		    case 2:
		        acc.getCurrentWithdrawInput();
		        break;
		    case 3:
		        acc.getCurrentDepositInput();
		        break;
		    case 4: 
		        acc.getTransferInput("Current");
		        break;
		    case 5:
		        end = true;
		        break;
		    default:
                        System.out.println("Invalid Choice");
                }
	    } catch (InputMismatchException e) {
	        System.out.println("Invalid Choice[1-3]");
                menuInput.next();
            }
	    
        }
    }
    
    public void getSavings(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("Current Account");
                System.out.println("1) View Balance");
		System.out.println("2) Withdraw Funds");
		System.out.println("3) Deposit Funds");
		System.out.println("4) Transfer Funds");
		System.out.println("5) Exit");
		int process = menuInput.nextInt();
		
		switch (process) {
		    case 1:
		        System.out.println("\nAccount Balance: "+ moneyFormat.format(acc.getSavingsBalance()));
		        break;
		    case 2:
		        acc.getSavingsWithdrawInput();
		        break;
		    case 3:
		        acc.getSavingsDepositInput();
		        break;
		    case 4: 
		        acc.getTransferInput("Savings");
		        break;
		    case 5:
		        end = true;
		        break;
		    default:
                        System.out.println("Invalid Choice");
                }
	    } catch (InputMismatchException e) {
	        System.out.println("Invalid Choice[1-3]");
                menuInput.next();
            }
	    
        }
    }
    
    public void mainMenu() throws IOException {
        //test cases
        data.put(9876, new Account(9876, 3456, 10000, 5000));
        data.put(1234, new Account(1234, 7690, 1000, 2000));
        boolean end = false;
        while(!end) {
            try {
                System.out.println("1) Login");
                System.out.println("2) Create Account");
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1: 
                          getLogin();
                          end = true;
                          break;
                    case 2:
                        createAccount();
                        end = true;
                        break;
                    default:
                        System.out.println("Invalid Choice\n");
                }
            } catch (InputMismatchException e) {
	        System.out.println("Invalid Choice[1-3]");
                menuInput.next();
            }
        }
        System.out.println("\nThank you\n");
        menuInput.close();
        System.exit(0);
    }
}