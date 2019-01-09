
public abstract class BankAccount {
	
	//fields
	private static int nextAcctNum = 0;
	private String name;
	private int acctNum;
	private double balance;
	
	//constructors
	public BankAccount(String name)
	{
		this.name = name;
		acctNum = nextAcctNum;
		nextAcctNum++;
		balance = 0;
	}
	
	public BankAccount(String name, double balance)
	{
		this.name = name;
		acctNum = nextAcctNum;
		nextAcctNum++;
		this.balance = balance;
	}
	
	//methods
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void transfer(BankAccount other, double amt)
	{
		if(!other.getName().equals(getName()) || amt < 0 || balance - amt < 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
		withdraw(amt);
		other.deposit(amt);
		}
	}
	
	public String toString()
	{
		return "" + acctNum + "\t" + name + "\t$" + balance;
	}
	
	public abstract void endOfMonthUpdate();
}
