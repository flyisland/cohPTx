package org.flyisland.examples.PTx.pof;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.cache.KeyAssociation;
import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

@Portable
public class BalanceId implements KeyAssociation {
	@PortableProperty(0)
	String	account_id;
	@PortableProperty(1)
	String	balance_id;

	public BalanceId() {
		super();
	}

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

	@Override
	public String toString() {
		return "BalanceId [account_id=" + account_id + ", balance_id="
				+ balance_id + "]";
	}
	
}
