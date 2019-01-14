import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain
{
	private static final double OVER_DRAFT_FEE = 15;
	private static final double RATE = 0.0025;
	private static final double TRANSACTION_FEE = 1.5;
	private static final double MIN_BAL = 300;
	private static final double MIN_BAL_FEE = 10;
	private static final int FREE_TRANSACTIONS = 10;
	private static Scanner in;
	private static boolean acceptableInput = false;
	private static ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	private static BankAccount userAccount;
	private static BankAccount otherUserAccount;

	public static void main(String[] args) 
	{
		in = new Scanner(System.in);
		boolean wantToTerminate = false;
		
		while (!wantToTerminate)
		{
			acceptableInput = false;
				System.out.println("Would you like to add an account (enter \"account\"), make a transaction (enter \"transaction\"), or terminate the program (enter \"terminate\")? : ");
				String answer = in.nextLine().toLowerCase();
			
			switch (answer)
			{
				case "account":
					addAccount();
					break;
					
				case "transaction":
					
					if (bankAccounts.size() == 0)
					{
						System.out.println("There are currently no bank accounts. Please create a bank account before making a transaction.");
						break;
					}
					
					System.out.println("Please enter the type of transaction you would like to make (enter \"withdraw\", \"deposit\", \"transfer\", or \"get account number\"):");
					String transType = in.nextLine().toLowerCase();
						
						switch (transType)
						{
							case "withdraw":
								userAccount();
								withdraw();
								break;
							case "deposit":
								userAccount();
								deposit();
								break;
							case "transfer":
								transfer();
								break;
							case "get account number":
								getAcctNum();
								break;
							default:
								System.out.println("That is not an acceptable answer. Please Try Again.");
								break;
						}
					break;
	
				case "terminate":
					wantToTerminate = true;
					
					for (BankAccount a : bankAccounts)
					{
						System.out.println(a.toString());
					}
					break;
					
				default:
					System.out.println("That is not an acceptable answer. Please Try Again.");
					break;
			}
		}
		
	}

	/**
	 * Helper Method addAccount
	 * Prompts for name, type of account, and initial deposit
	 * creates bank account and adds to array list
	 */
	private static void addAccount()
	{
		System.out.println("Please enter your full name: ");
		String name = in.nextLine();
		
		while(!acceptableInput)
		{
			System.out.println("Please enter the type of account you would like to create (\"Checking\" or \"Savings\"): ");
			String type = in.nextLine().toLowerCase();
			
			if (type.equals("checking"))
			{
				acceptableInput = true; 
				bankAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				userAccount = bankAccounts.get(bankAccounts.size()-1);
				deposit();
			}
			else if (type.equals("savings"))
			{
				acceptableInput = true; 
				bankAccounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
				userAccount = bankAccounts.get(bankAccounts.size()-1);
				deposit();
			}
			else
			{
				System.out.println("That is not an acceptable input. Please Try Again.");
			}
		}
	}
	
	/**
	 * Helper Method getAcctByNum
	 * Prompts for account number
	 * Cycles through array list and finds account by account number
	 * @param n This is the account number of the bank account
	 * @return Returns account if account number is in array list, else returns null
	 */
	private static BankAccount getAcctByNum(int n)
    {
        for (BankAccount a : bankAccounts)
        {
            if (a.getAcctNum() == n) 
            {
            	return a;
            }
        }        
        return null;
    }
	
	/**
	 * Helper Method userAccount
	 * Prompts for account number
	 * Stores account with parametrically passed account number into field userAccount
	 */
	private static void userAccount()
	{
		System.out.println("Please enter your account number: ");
		String acctNum = in.next();
		in.nextLine();
		
		while (!isNumeric(acctNum))
		{
			System.out.println("This is not a number. Please enter the account number again: ");
			acctNum = in.next();
			in.nextLine();	
		}
			int acctNumber = Integer.parseInt(acctNum);
		
		userAccount = getAcctByNum(acctNumber);
		
		while (userAccount == null)
		{
			System.out.println("Your account number is not valid. Please choose whether you would like to re-enter your account number (enter \"r\") or get your account number(s) by entering your name (enter \"n\"): ");
			String choice = in.next().toLowerCase();
			in.nextLine();
			
			switch (choice)
			{
				case "r":
					
						System.out.println("Please enter your account number: ");
						acctNum = in.next().toLowerCase();
						in.nextLine();
						
							while (!isNumeric(acctNum))
							{
								System.out.println("This is not a number. Please enter the account number again: ");
								acctNum = in.next();
								in.nextLine();	
							}
						
						acctNumber = Integer.parseInt(acctNum);
						
						userAccount = getAcctByNum(acctNumber);
						
					break;
					
				case "n":
					getAcctNum();
					break;
				default:
					break;
			}
		}
		
	}
	
	//transaction methods
	/**
	 * Helper Method deposit
	 * Prompts for amount 
	 * Deposits amount into bank account
	 * Prints "Transaction Not Authorized" if input does not meet requirements
	 */
	private static void deposit()
	{
		System.out.print("Please enter the amount of money that you would like to deposit: ");
		String deposit = in.next();
		in.nextLine();
			
		while(!isNumeric(deposit))
		{
			System.out.println("Please enter a numerical amount: ");
			deposit = in.next();
			in.nextLine();	
		}
			Double numDeposit = Double.parseDouble(deposit);
			
			try
			{
				userAccount.deposit(numDeposit);
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Transaction Not Authorized");
			}  
	}
	
	/**
	 * Helper Method withdraw
	 * Prompts for amount 
	 * Withdraws amount from bank account
	 * Prints "Transaction Not Authorized" if input does not meet requirements
	 */
	private static void withdraw()
	{
		System.out.print("Please enter the amount of money that you would like to withdraw: ");
		String withdraw = in.next();
		in.nextLine();
			
		while(!isNumeric(withdraw))
		{
			System.out.println("Please enter a numerical amount: ");
			withdraw = in.next();
			in.nextLine();	
		}
			Double numWithdraw = Double.parseDouble(withdraw);
			
			try
			{
				userAccount.withdraw(numWithdraw);
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Transaction Not Authorized");
			}  
	}
	
	/**
	 * Helper Method transfer
	 * Prompts for amount 
	 * Transfers amount from one bank account into other bank account
	 * Prints "Transaction Not Authorized" if input does not meet requirements
	 */
	private static void transfer()
	{
		System.out.println("You will be asked for two sets of accounts. Please enter the account you wish deposit to first, followed by the account you wish to withdraw from. ");
		userAccount();
		otherUserAccount = userAccount;
		userAccount();
		
		System.out.print("Please enter the amount of money that you would like to transfer: ");
		String transfer = in.next();
		in.nextLine();
			
		while(!isNumeric(transfer))
		{
			System.out.println("Please enter a numerical amount: ");
			transfer = in.next();
			in.nextLine();	
		}
			Double numTransfer = Double.parseDouble(transfer);
			
		try
		{
			userAccount.transfer(otherUserAccount, numTransfer);
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Transaction Not Authorized");
		}  
	}
	
	private static void getAcctNum()
	{
		boolean nameInList = false;
		System.out.println("Please enter your name exactly: ");
		String name = in.next();
		in.nextLine();
		
		for (BankAccount a : bankAccounts)
		{
			if (a.getName().equals(name))
			{
				if(a instanceof CheckingAccount)
				{
					System.out.println("Checking Account: " + a.toString());
					nameInList = true;
				}
				else if (a instanceof SavingsAccount)
				{
					System.out.println("Savings Account: " + a.toString());
					nameInList = true;
				}
			}
		}
		if (!nameInList)
		{
			System.out.println("Your name does not appear in the list of bank accounts.");
		}
	}
	
	/**
	 * Helper Method isNumeric
	 * @param str This string will be checked to see whether it is numeric
	 * @return Returns true if numeric, else returns false
	 */
	private static boolean isNumeric(String str)
	{
		try
	{
		Double.parseDouble(str);
		return true;
	}
		catch (IllegalArgumentException e)
	{
		return false;
	}
	}
}
