package org.flyisland.examples.PTx.pof;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

@Portable
public class Balance {
	@PortableProperty(0)	private String	accountId;
	@PortableProperty(1)	private double	balance;
	@PortableProperty(2)	private String	ops;


	public Balance() {
		super();
	}

	public Balance(String accountId, double balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.ops = "";
	}

	public String getOps() {
		return ops;
	}

	public void setOps(String ops) {
		this.ops = ops;
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
		return "Balance [accountId=" + accountId + ", balance=" + balance + ", ops=" + ops + "]";
	}
}
