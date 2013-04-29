package org.flyisland.examples.PTx;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class ReadCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheFactory.ensureCluster();

		AccountId	act_id = new AccountId("x01");
        NamedCache nc = CacheFactory.getCache("accounts");
        
        System.out.println("\n === Value of "+act_id+" is ["+nc.get(act_id)+"]");
	}

}
