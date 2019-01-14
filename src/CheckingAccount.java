/**
 * 
 * @author Aakash Narayan
 * AP Computer Science Period 7
 * class CheckingAccount
 * 
 */

public class CheckingAccount extends BankAccount 
{

	//fields
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;

	//constructors
	
	/**
	 * Constructs checking account
	 * @param n This is the name of the bank account holder.
	 * @param b This is the balance of the bank account.
	 * @param odf This is the overdraft fee of the bank account.
	 * @param tf This is the transaction fee of the bank account.
	 * @param freeTrans This is the free transaction limit of the bank account.
	 */
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * Constructs checking account with balance set to 0.
	 * @param n This is the name of the bank account holder.
	 * @param odf This is the overdraft fee of the bank account.
	 * @param tf This is the transaction fee of the bank account.
	 * @param freeTrans This is the free transaction limit of the bank account.
	 */
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n, 0);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	//methods
	public void deposit(double amt)
	{
		if(amt < 0)
		{
			throw new IllegalArgumentException();
		}
		
		super.deposit(amt);
		numTransactions++;
		
		if(numTransactions > FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
	}
	
	public void withdraw(double amt)
	{
		if(amt < 0 || super.getBalance() < 0)
		{
			throw new IllegalArgumentException();
		}
		
		super.withdraw(amt);
		numTransactions++;

		if(numTransactions > FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
		
		if ((super.getBalance()) < 0)
		{
			super.withdraw(OVER_DRAFT_FEE);
		}
	}
	
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
}
