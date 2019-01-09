
public class SavingsAccount extends BankAccount 
{

	//fields
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	private double intRate;
	
	//constructors
	SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
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
