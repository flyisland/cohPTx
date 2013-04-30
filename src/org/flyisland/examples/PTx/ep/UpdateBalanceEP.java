package org.flyisland.examples.PTx.ep;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.Balance;
import org.flyisland.examples.PTx.pof.BalanceId;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.BackingMapContext;
import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.util.BinaryEntry;
import com.tangosol.util.Converter;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.ObservableMap;
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
		System.out.println("=== Start EP "+this.toString());
		
		// 1. get account id
		AccountId aid = (AccountId) entry.getKey();
		
		// 2. get balances with the specific account id
		BinaryEntry be = (BinaryEntry)entry;
		BackingMapContext bmc_bal = be.getContext().getBackingMapContext("balances");
		ObservableMap om_balance = bmc_bal.getBackingMap();
		BackingMapManagerContext bmmc_bal = bmc_bal.getManagerContext();
		Converter cvt_vi2o = bmmc_bal.getValueFromInternalConverter();
		Converter cvt_ki2o = bmmc_bal.getKeyFromInternalConverter();
		
//		Set<Map.Entry> set_balances= InvocableMapHelper.query(om_balance, new EqualsFilter("getAccountId", aid.getId()), true, false, null);
//		Map m_indexes = be.getContext().getBackingMapContext("balance").getIndexMap();
//		Set<Map.Entry> = InvocableMapHelper.query(m_balance, m_indexes, new EqualsFilter("getAccountId", aid.getId()), true, false, null);
		ArrayList<BinaryEntry>	al_bals = new ArrayList<BinaryEntry>();
		for (Map.Entry e : (Set<Map.Entry>)om_balance.entrySet()){
			Balance		bal = (Balance)cvt_vi2o.convert(e.getValue());
			if (bal.getAccountId().equals(aid.getId())){
				al_bals.add((BinaryEntry)bmc_bal.getBackingMapEntry(e.getKey()));
				BalanceId	bal_id = (BalanceId)cvt_ki2o.convert(e.getKey());
				System.out.println("\t"+bal_id+" -> "+bal.getBalance());
			}
		}
		
		// 3. update balances
		int i_sleep=10, i;
		if (ops.equalsIgnoreCase("sleep")){
			System.out.print("\t*** Sleep "+i_sleep+" seconds before modification: ");
			for (i=i_sleep; i>0; i--){
				try {
					System.out.print(" "+i);
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
			}
			System.out.println("");
		}

		for (BinaryEntry be_bal : al_bals){
			Balance bal = (Balance)be_bal.getValue();
			System.out.print("\t"+bal+" -> ");
			bal.setBalance(bal.getBalance()+value);
			System.out.println(bal);
			be_bal.setValue(bal);
		}
		
		if (ops.equalsIgnoreCase("sleep")){
			System.out.print("\t*** Sleep "+i_sleep+" seconds after modification: ");
			for (i=i_sleep; i>0; i--){
				try {
					System.out.print(" "+i);
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
			}
			System.out.println("");
		}
		System.out.println("=== End EP ("+this.toString());
		return null;
	}

	@Override
	public String toString() {
		return "UpdateBalanceEP [value=" + value + ", ops=" + ops + "]";
	}

}
