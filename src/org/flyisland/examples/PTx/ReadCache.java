package org.flyisland.examples.PTx;

import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.BalanceId;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class ReadCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheFactory.ensureCluster();

		AccountId	act_id = new AccountId("a25");
		BalanceId	bal_id = new BalanceId("a20", "b62");
        NamedCache nc_act = CacheFactory.getCache("accounts");
        NamedCache nc_bal = CacheFactory.getCache("balances");
        
        System.out.println("=== Value of "+act_id+" is '"+nc_act.get(act_id)+"'");
        System.out.println("=== Value of "+bal_id+"]is '"+nc_bal.get(bal_id)+"'");
	}

}
