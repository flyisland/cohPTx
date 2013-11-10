package org.flyisland.examples.PTx;

import java.util.Map;

import org.flyisland.examples.PTx.ep.NullEP;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;

public class Batch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheFactory.ensureCluster();
        NamedCache nc_act = CacheFactory.getCache("accounts");
        
        System.out.println("==> Staring batch process!");
        Map  m = nc_act.invokeAll((Filter)null, new NullEP());
        System.out.println("The size of result of invokeAll is : "+m.size());
	}

}
