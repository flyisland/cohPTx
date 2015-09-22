package org.flyisland.examples.PTx.ep;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.Balance;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import com.tangosol.net.BackingMapContext;
import com.tangosol.util.BinaryEntry;
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

	static Logger logger = LogManager.getLogger(UpdateBalanceEP.class.getName());
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
		logger.trace("===> Entered "+this.toString());

		// 1. get account id
		AccountId aid = (AccountId) entry.getKey();

		// 2. get balances with the specific account id
		BinaryEntry be = (BinaryEntry)entry;
		BackingMapContext bmc_bal = be.getContext().getBackingMapContext("balances");
		ObservableMap om_balance = bmc_bal.getBackingMap();

		// to use PofExtractor in InvocableMapHelper.query(), must be sure an index has been created with PofExtractor before!
		// for example: addIndex(new PofExtractor(String.class, 0), false, null);
		Map<ValueExtractor, MapIndex> m_indexes = bmc_bal.getIndexMap();
		Set<Map.Entry<?,?>> set_balances = InvocableMapHelper.query(om_balance, m_indexes, new EqualsFilter(new PofExtractor(String.class, 0), aid.getId()), true, false, null);

		// 3. update balances
		Set<BinaryEntry>	set_be_bals = new HashSet<BinaryEntry>();
		for (Map.Entry<?,?> e_bal : set_balances){
			BinaryEntry be_bal = (BinaryEntry)bmc_bal.getBackingMapEntry(e_bal.getKey());
			set_be_bals.add(be_bal);
			Balance	bal = (Balance)be_bal.getValue();
			logger.trace(" -> before modification: "+bal);
		}

		// ops == sleep
		if (ops.equalsIgnoreCase("sleep")){
			int i, i_sleep = 10;
			logger.trace("*** Sleep "+i_sleep+" seconds before modification: ");
			for (i=i_sleep; i>0; i--){
				try {
					logger.trace("*** "+i);
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		}

		for (BinaryEntry be_bal : set_be_bals){
			Balance	bal = (Balance)be_bal.getValue();
			bal.setBalance(bal.getBalance()+value);
			bal.setOps(ops);
			be_bal.setValue(bal);
			logger.trace(" => after modification: "+bal);
		}

		// ops == fail
		if (ops.equalsIgnoreCase("fail")){
			throw new RuntimeException("Artificial fail in "+this.toString());
		}

		// ops == sleep
		if (ops.equalsIgnoreCase("sleep")){
			int i, i_sleep = 10;
			logger.trace("*** Sleep "+i_sleep+" seconds before exit: ");
			for (i=i_sleep; i>0; i--){
				try {
					logger.trace("*** "+i);
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		}
		logger.trace("===> Exited: "+this.toString());
		return null;
	}

	@Override
	public String toString() {
		return "UpdateBalanceEP [value=" + value + ", ops=" + ops + "]";
	}

}
