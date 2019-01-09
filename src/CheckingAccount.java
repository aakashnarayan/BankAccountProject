
public class CheckingAccount extends BankAccount 
{

	//fields
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;

	//constructors
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
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
