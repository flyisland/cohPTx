package org.flyisland.examples.PTx;

import org.flyisland.examples.PTx.ep.UpdateBalanceEP;
import org.flyisland.examples.PTx.pof.AccountId;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class UpdateBalanceByEP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheFactory.ensureCluster();
        NamedCache nc_act = CacheFactory.getCache("accounts");
        nc_act.invoke(new AccountId("a30"), new UpdateBalanceEP(10, ""));
	}

}
