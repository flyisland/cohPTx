package org.flyisland.examples.PTx;

import java.io.IOException;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.cache.KeyAssociation;
import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

public class BalanceId implements PortableObject, KeyAssociation {
	String	account_id;
	String	balance_id;

	public BalanceId(String account_id, String balance_id) {
		super();
		this.account_id = account_id;
		this.balance_id = balance_id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(String balance_id) {
		this.balance_id = balance_id;
	}

	@Override
	public Object getAssociatedKey() {
		return account_id;
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {
		setAccount_id(reader.readString(0));
		setBalance_id(reader.readString(1));
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, getAccount_id());
		writer.writeString(1, getBalance_id());
	}

	@Override
	public int hashCode() {
		return HashHelper.hash(account_id, 
				HashHelper.hash(balance_id,0));
	}

	@Override
	public boolean equals(Object toThat) {
		if (this == toThat)
			return true;
		if (toThat == null)
			return false;
		if (getClass() != toThat.getClass())
			return false;
		BalanceId other = (BalanceId) toThat;
		return Base.equals(getAccount_id(), other.getAccount_id()) &&
				Base.equals(getBalance_id(), other.getBalance_id());
	}
	
}
