package org.flyisland.examples.PTx;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class LoadCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheFactory.ensureCluster();

		AccountId	act_id = new AccountId("x01");
        NamedCache nc = CacheFactory.getCache("accounts");
        nc.put(act_id, "Jerry");
	}

}
