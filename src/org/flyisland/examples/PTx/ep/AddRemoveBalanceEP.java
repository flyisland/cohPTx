package org.flyisland.examples.PTx.ep;

import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.Balance;
import org.flyisland.examples.PTx.pof.BalanceId;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.BackingMapContext;
import com.tangosol.util.BinaryEntry;
import com.tangosol.util.Converter;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

@Portable
public class AddRemoveBalanceEP extends AbstractProcessor {

	private static final long serialVersionUID = 830192714204591433L;
	
	@PortableProperty(0)	private String	balanceId;
	@PortableProperty(1)	private String	addremove;
	

	public AddRemoveBalanceEP() {
		super();
	}


	public AddRemoveBalanceEP(String balanceId, String addremove) {
		super();
		this.balanceId = balanceId;
		this.addremove = addremove;
	}


	@Override
	public Object process(Entry entry) {
		System.out.println("=== Start EP : "+this.toString());
		
		AccountId aid = (AccountId) entry.getKey();
		BinaryEntry be = (BinaryEntry)entry;
		BackingMapContext bmc_bal = be.getContext().getBackingMapContext("balances");
		Converter cvt_ko2i = ((BinaryEntry)entry).getBackingMapContext().getManagerContext().getKeyToInternalConverter();
		BalanceId	bal_id = new BalanceId(aid.getId(), balanceId);
		BinaryEntry be_bal = (BinaryEntry)bmc_bal.getBackingMapEntry(cvt_ko2i.convert(bal_id));
		
		if (addremove.equalsIgnoreCase("add")){
			be_bal.setValue(new Balance(aid.getId(), 0));
		} else if (addremove.equalsIgnoreCase("remove")){
			be_bal.remove(false);
		}
		
		return null;
	}


	@Override
	public String toString() {
		return "AddRemoveBalanceEP [balanceId=" + balanceId + ", addremove="
				+ addremove + "]";
	}

}
