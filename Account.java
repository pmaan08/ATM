import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account{
    
    private int customerID;
    private int pinNumber;
    private double currentBalance = 0;
    private double savingsBalance = 0;
    
    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat  = new DecimalFormat("'INR'###,##0.00");
    
    public Account() {
    }
    
    public Account(int customerID, int pinNumber) {
        this.customerID = customerID;
        this.pinNumber = pinNumber;
    }
    
    public Account(int customerID, int pinNumber, double currentBalance, double savingsBalance) {
        this.customerID = customerID;
        this.pinNumber = pinNumber;
        this.currentBalance = currentBalance;
        this.savingsBalance = savingsBalance;
    }
    
    public int setCustomerID(int customerID) {
        this.customerID = customerID;
        return customerID;
    }
    
    public int getCustomerID() {
        return customerID;
    }
    
    public int setPinNumber() {
        this.pinNumber = pinNumber;
        return pinNumber;
    }
    
    public int getPinNumber() {
        return pinNumber;
    }
    
    public double getCurrentBalance() {
        return currentBalance;
    }
    
    public double getSavingsBalance() {
        return savingsBalance;
    }
    
    public double calcCurrentWithdraw(double amount) {
	currentBalance = (currentBalance - amount);
	return currentBalance;
    }

    public double calcSavingsWithdraw(double amount) {
	savingsBalance = (savingsBalance - amount);
	return savingsBalance;
    }

    public double calcCurrentDeposit(double amount) {
	currentBalance = (currentBalance + amount);
	return currentBalance;
    }

    public double calcSavingsDeposit(double amount) {
	savingsBalance = (savingsBalance + amount);
	return savingsBalance;
    }

    public void calcCurrentTransfer(double amount) {
	currentBalance = currentBalance - amount;
	savingsBalance = savingsBalance + amount;
    }

    public void calcSavingsTransfer(double amount) {
	savingsBalance = savingsBalance - amount;
	currentBalance = currentBalance + amount;
    }
    
    public void getCurrentWithdrawInput() {
        boolean end = false;
        while(!end) {
            try {
                System.out.println("\nCurrent Account Balance: " + moneyFormat.format(currentBalance));
		System.out.print("\nAmount you want to withdraw from Current Account: ");
		double amount = input.nextDouble();
		if ((currentBalance - amount) >= 0 && amount >= 0) {
		    calcCurrentWithdraw(amount);
		    System.out.println("\nCurrent Account Balance: " + moneyFormat.format(currentBalance));
		    end = true;
		} 
		else {
		    System.out.println("\nBalance Cannot be Negative.");
		}   
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice");
                input.next();
            }
        }
    }
 
    public void getSavingsWithdrawInput() {
        boolean end = false;
        while(!end) {
            try {
                System.out.println("\n Savings Account Balance: " + moneyFormat.format(savingsBalance));
		System.out.print("\nAmount you want to withdraw from Savings Account: ");
		double amount = input.nextDouble();
		if ((savingsBalance - amount) >= 0 && amount >= 0) {
		    calcSavingsWithdraw(amount);
		    System.out.println("\n Savings Account Balance: " + moneyFormat.format(savingsBalance));
		    end = true;
		} 
		else {
		    System.out.println("\nBalance Cannot be Negative.");
		}   
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice");
                input.next();
            }
        }
    }
    
    public void getCurrentDepositInput() {
	boolean end = false;
	while (!end) {
		try {
		     System.out.println("\nCurrent  Account Balance: " + moneyFormat.format(currentBalance));
		     System.out.print("\nAmount you want to deposit from Current Account: ");
		     double amount = input.nextDouble();
		     if ((currentBalance + amount) >= 0 && amount >= 0) {
			  calcCurrentDeposit(amount);
			  System.out.println("\nCurrent  Account Balance: " + moneyFormat.format(currentBalance));
			  end = true;
			} 
		     else {
			   System.out.println("\nBalance Cannot Be Negative.");
			}
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid Choice.");
			input.next();
		}
	}
    }
    
    public void getSavingsDepositInput() {
	boolean end = false;
	while (!end) {
		try {
		     System.out.println("\n Savings  Account Balance: " + moneyFormat.format(savingsBalance));
		     System.out.print("\nAmount you want to deposit from Savings Account: ");
		     double amount = input.nextDouble();
		     if ((savingsBalance + amount) >= 0 && amount >= 0) {
			  calcSavingsDeposit(amount);
			  System.out.println("\n savings  Account Balance: " + moneyFormat.format(savingsBalance));
			  end = true;
			} 
		     else {
			   System.out.println("\nBalance Cannot Be Negative.");
			}
		} catch (InputMismatchException e) {
		        System.out.println("\nInvalid Choice.");
			input.next();
		}
	}
    }
	
    public void getTransferInput(String accType) {
	boolean end = false;
	while (!end) {
		 try {
		      if (accType.equals("Current")) 
		      {
			System.out.println("\nSelect an account you wish to tranfers funds to:");
			System.out.println("1) Savings");
			System.out.println("2) Exit");
			System.out.print("\nChoice: ");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("\nCurrent Account Balance: " + moneyFormat.format(currentBalance));
				System.out.print("\nAmount you want to deposit into your Savings Account: ");
				double amount = input.nextDouble();
				if ((savingsBalance + amount) >= 0 && (currentBalance - amount) >= 0 && amount >= 0) {
                       		        calcCurrentTransfer(amount);
					System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingsBalance));
					System.out.println("\nCurrent Account Balance: " + moneyFormat.format(currentBalance));
					end = true;
					} 
				else {
				      System.out.println("\nBalance Cannot Be Negative.");
				     }
				break;
			case 2:
			       return;
			default:
				System.out.println("\nInvalid Choice.");
				break;
			}
			}
			else if (accType.equals("Savings")) 
			{
			  System.out.println("\nSelect an account you wish to tranfers funds to: ");
			  System.out.println("1) Current");
			  System.out.println("2) Exit");
			  System.out.print("\nChoice: ");
			  int choice = input.nextInt();
			  switch (choice) {
			   case 1:
				 System.out.println("\n Savings Account Balance: " + moneyFormat.format(savingsBalance));
				 System.out.print("\nAmount you want to deposit into your current account: ");
				 double amount = input.nextDouble();
				 if ((currentBalance + amount) >= 0 && (savingsBalance - amount) >= 0 && amount >= 0) {
					calcSavingsTransfer(amount);
					System.out.println("\nCurrent account balance: " + moneyFormat.format(currentBalance));
					System.out.println("\n Savings account balance: " + moneyFormat.format(savingsBalance));
	                               end = true;
				} 
				else 
				{
				 System.out.println("\nBalance Cannot Be Negative.");
				}
				break;
			   case 2:
				return;
			   default:
				System.out.println("\nInvalid Choice.");
				break;
			  }
			}
		  } catch (InputMismatchException e) {
			System.out.println("\nInvalid Choice.");
			input.next();
			}
	}
	}
}
