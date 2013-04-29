package org.flyisland.examples.PTx.pof;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.cache.KeyAssociation;
import com.tangosol.util.Base;
import com.tangosol.util.HashHelper;

@Portable
public class AccountId implements KeyAssociation {
	@PortableProperty(0)
	String	id;
	
	public AccountId() {
		super();
	}

	public AccountId(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public Object getAssociatedKey() {
		return id;
	}

	@Override
	public int hashCode() {
		return HashHelper.hash(id, 0);
	}

	@Override
	public boolean equals(Object toThat) {
		if (this == toThat)
			return true;
		if (toThat == null)
			return false;
		if (getClass() != toThat.getClass())
			return false;
		AccountId other = (AccountId) toThat;
		return Base.equals(getId(), other.getId());
	}

	@Override
	public String toString() {
		return "AccountId [id=" + id + "]";
	}
}
