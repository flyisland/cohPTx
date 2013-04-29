package org.flyisland.examples.PTx;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class Balance {
	@PortableProperty(0)	private String	accountId;
	@PortableProperty(1)	private double	balance;
	
	
	public Balance() {
		super();
	}

	public Balance(String accountId, double balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Balance [accountId=" + accountId + ", balance=" + balance + "]";
	}
}
