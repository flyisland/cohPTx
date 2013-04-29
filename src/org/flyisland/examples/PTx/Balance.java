package org.flyisland.examples.PTx;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class Balance implements PortableObject {
	String	accountId;
	double	balance;
	
	
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
	public void readExternal(PofReader reader) throws IOException {
		setAccountId(reader.readString(0));
		setBalance(reader.readDouble(1));
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, getAccountId());
		writer.writeDouble(1, getBalance());
	}

}
