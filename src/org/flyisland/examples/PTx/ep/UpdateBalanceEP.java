package org.flyisland.examples.PTx.ep;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tangosol.util.InvocableMapHelper;
import com.tangosol.util.MapIndex;
import com.tangosol.util.ObservableMap;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.PofExtractor;
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
		System.out.println("=== Start EP : "+this.toString());
		
		// 1. get account id
		AccountId aid = (AccountId) entry.getKey();
		
		// 2. get balances with the specific account id
		BinaryEntry be = (BinaryEntry)entry;
		BackingMapContext bmc_bal = be.getContext().getBackingMapContext("balances");
		ObservableMap om_balance = bmc_bal.getBackingMap();

		// to use PofExtractor in InvocableMapHelper.query(), must be sure an index has been created with PofExtractor before!
		// for example: addIndex(new PofExtractor(String.class, 0), false, null);
		Map<ValueExtractor, MapIndex> m_indexes = be.getContext().getBackingMapContext("balances").getIndexMap();
		Set<Map.Entry<?,?>> set_balances = InvocableMapHelper.query(om_balance, m_indexes, new EqualsFilter(new PofExtractor(String.class, 0), aid.getId()), true, false, null);

		// 3. update balances
		for (Map.Entry<?,?> e_bal : set_balances){
			BinaryEntry be_bal = (BinaryEntry)bmc_bal.getBackingMapEntry(e_bal.getKey());
			Balance	bal = (Balance)be_bal.getValue();
			System.out.print("\t"+bal+" -> ");
			bal.setBalance(bal.getBalance()+value);
			System.out.println(bal);
			be_bal.setValue(bal);
		}
		
		// ops == fail
		if (ops.equalsIgnoreCase("fail")){
			throw new RuntimeException("Artificial fail in "+this.toString());
		}
		
		// ops == sleep 
		if (ops.equalsIgnoreCase("sleep")){
			int i, i_sleep = 10;
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println("\t*** Sleep "+i_sleep+" seconds before exit: ");
			for (i=i_sleep; i>0; i--){
				try {
					System.out.println("\t*** "+sdf.format(new Date())+" *** "+i);
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		System.out.println("=== End EP : "+this.toString());
		return null;
	}

	@Override
	public String toString() {
		return "UpdateBalanceEP [value=" + value + ", ops=" + ops + "]";
	}

}
