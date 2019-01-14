/**
 * 
 * @author Aakash Narayan
 * AP Computer Science Period 7
 * class BankAccount
 * 
 */

public abstract class BankAccount 
{
	
	//fields
	private static int nextAcctNum = 0;
	private String name;
	private int acctNum;
	private double balance;
	
	//constructors
	/**
	 * Constructs bank account with balance set to 0
	 * @param name This is the name of the bank account holder.
	 */
	public BankAccount(String name)
	{
		this.name = name;
		nextAcctNum++;
		acctNum = nextAcctNum;
		balance = 0;
	}
	
	/**
	 * Constructs bank account 
	 * @param name This is the name of the bank account holder.
	 * @param balance This is the balance of the bank account.
	 */
	public BankAccount(String name, double balance)
	{
		this.name = name;
		acctNum = nextAcctNum;
		nextAcctNum++;
		this.balance = balance;
	}
	
	//methods
	/**
	 * Method deposit
	 * Deposits money into bank account
	 * @param amt This is the amount to be deposited.
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	/**
	 * Method withdraw
	 * Withdraws money from bank account
	 * @param amt This is the amount to be withdrawn.
	 */
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	
	/**
	 * Method getName
	 * @return Returns String name of bank account holder.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Method getBalance
	 * @return Returns double balance of bank account.
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * Method getAcctNum
	 * @return Returns int account number of bank account.
	 */
	public int getAcctNum()
	{
		return acctNum;
	}
	
	/**
	 * Method transfer
	 * Withdraws money from one bank account and deposits into another bank account.
	 * @param other This is the other bank account into which money will be deposited.
	 * @param amt This is the amount to be deposited/withdrawn.
	 */
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
	
	/**
	 * Method toString
	 * @return Returns String containing account number followed by name followed by balance
	 */
	public String toString()
	{
		return "" + acctNum + "\t" + name + "\t$" + balance;
	}
	
	/**
	 * Method endOfMonthUpdate
	 * Implemented in subclasses
	 */
	public abstract void endOfMonthUpdate();
}
