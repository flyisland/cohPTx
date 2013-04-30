package org.flyisland.examples.PTx.ep;

import java.util.Map;
import java.util.Set;

import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.Balance;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.BackingMapContext;
import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.util.BinaryEntry;
import com.tangosol.util.Converter;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.InvocableMapHelper;
import com.tangosol.util.ObservableMap;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.processor.AbstractProcessor;

@Portable
public class UpdateBalanceEP extends AbstractProcessor{

	private static final long serialVersionUID = -4231456472129888799L;
	@PortableProperty(0)	private	int	value=0;
	@PortableProperty(1)	private String	ops;
	
	public UpdateBalanceEP() {
		super();
	}

	public UpdateBalanceEP(int value, String ops) {
		super();
		this.value = value;
		this.ops = ops;
	}

	@Override
	public Object process(Entry entry) {
		System.out.println("=== Start EP ("+this.getClass().getName()+")");
		
		// 1. get account id
		AccountId aid = (AccountId) entry.getKey();
		
		// 2. get balances with the specific account id
		BinaryEntry be = (BinaryEntry)entry;
		BackingMapContext bmc_bal = be.getContext().getBackingMapContext("balances");
		ObservableMap om_balance = bmc_bal.getBackingMap();
		BackingMapManagerContext bmmc_bal = bmc_bal.getManagerContext();
		Converter cvt_i2o = bmmc_bal.getValueFromInternalConverter();
		
//		Set<Map.Entry> set_balances= InvocableMapHelper.query(om_balance, new EqualsFilter("getAccountId", aid.getId()), true, false, null);
//		Map m_indexes = be.getContext().getBackingMapContext("balance").getIndexMap();
//		Set<Map.Entry> = InvocableMapHelper.query(m_balance, m_indexes, new EqualsFilter("getAccountId", aid.getId()), true, false, null);
		for (Map.Entry e : (Set<Map.Entry>)om_balance.entrySet()){
			Balance bal = (Balance)cvt_i2o.convert(e.getValue());
			if (bal.getAccountId().equals(aid.getId()))
				System.out.println(bal);
		}
		
		// 3. update balances

		System.out.println("=== End EP ("+this.getClass().getName()+")");
		return null;
	}

}
