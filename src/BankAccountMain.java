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
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		boolean wantToTerminate = false;
		while (!wantToTerminate)
		{
			boolean acceptableInput = false;
				System.out.println("Would you like to add an account (enter \"account\"), make a transaction (enter \"transaction\"), or terminate the program (enter \"terminate\")? : ");
				String answer = in.nextLine().toLowerCase();
				
			switch (answer)
			{
			case "account":
				System.out.println("Please enter your full name: ");
				String name = in.nextLine();
				
				while(!acceptableInput)
				{
					System.out.println("Please enter the type of account you would like to create (\"Checking\" or \"Savings\"): ");
					String type = in.nextLine().toLowerCase();
					
					if (type.equals("checking"))
					{
						acceptableInput = true; 
						String deposit;
						Double numDeposit;
						
					System.out.print("Please enter the amount of money that you would like to initially deposit: ");
					deposit = in.next();
					in.nextLine();
						
					while(!isNumeric(deposit))
					{
						System.out.println("Please enter a numerical amount: ");
						deposit = in.next();
						in.nextLine();	
					}
						numDeposit = Double.parseDouble(deposit);
						
						bankAccounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
						bankAccounts.get(bankAccounts.size()-1).deposit(numDeposit);
					}
					else if (type.equals("savings"))
					{
						acceptableInput = true; 
						
						System.out.println("Please enter the amount of money that you would like to initially deposit: ");
						double deposit = in.nextDouble();
						in.nextLine();
						
						while (deposit <= 0)
						{
							System.out.println("Please enter a positive amount:");
							deposit = in.nextDouble();
							in.nextLine();
						}
						
						bankAccounts.add(new SavingsAccount(name, deposit, RATE, MIN_BAL, MIN_BAL_FEE));
					}
					else
					{
						System.out.println("That is not an acceptable input. Please Try Again.");
					}
				}
				break;
				
			case "transaction":
				System.out.println("Please enter the type of transaction you would like to make (enter \"withdraw\", \"deposit\", \"transfer\", or \"get account number\"):");
				String transType = in.nextLine().toLowerCase();
				
				switch (transType)
				{
				case "withdraw":
					
					break;
				case "deposit":
					break;
				case "transfer":
					break;
				case "get account number":
					break;
				default:
					System.out.println("That is not an acceptable answer. Please Try Again.");
					break;
				}
				
				break;
				
			case "terminate":
				wantToTerminate = true;
				break;
				
			default:
				System.out.println("That is not an acceptable answer. Please Try Again.");
				break;
			}
		}
	}
	
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
