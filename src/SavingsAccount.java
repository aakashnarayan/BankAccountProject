/**
 * 
 * @author Aakash Narayan
 * AP Computer Science Period 7
 * class SavingsAccount
 * 
 */

public class SavingsAccount extends BankAccount 
{

	//fields
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	private double intRate;
	
	//constructors
	/**
	 * Constructs savings account
	 * @param n This is the name of the bank account holder. 
	 * @param b	This is the balance of the bank account.
	 * @param r This is the interest rate of the bank account.
	 * @param mb This is the minimum balance of the bank account.
	 * @param mbf This is the minimum balance fee of the bank account.
	 */
	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * Constructs savings account with balance set to 0.
	 * @param n This is the name of the bank account holder. 
	 * @param r This is the interest rate of the bank account.
	 * @param mb This is the minimum balance of the bank account.
	 * @param mbf This is the minimum balance fee of the bank account.
	 */
	SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n, 0);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	//methods
	
	public void deposit (double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			super.deposit(amt);
		}
	}
	public void withdraw (double amt)
	{
		if(super.getBalance() - amt < 0 || amt < 0)
		{
			throw new IllegalArgumentException();
		}
		
		if(super.getBalance() - amt < MIN_BAL)
		{
			super.withdraw(amt + MIN_BAL_FEE);
		}
		else
		{
			super.withdraw(amt);
		}
	}
	
	public void addInterest()
	{
		super.deposit(super.getBalance()*intRate);
	}
	
	public void endOfMonthUpdate()
	{
		addInterest();
	}
}
