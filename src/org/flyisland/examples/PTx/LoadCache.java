package org.flyisland.examples.PTx;

import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.Balance;
import org.flyisland.examples.PTx.pof.BalanceId;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class LoadCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CacheFactory.ensureCluster();
		
		int	a, b;
		int b_per_a = 3;
		String	str_act_id;
		String	str_bal_id;
		
        NamedCache nc_act = CacheFactory.getCache("accounts");
        NamedCache nc_bal = CacheFactory.getCache("balances");
		for (a=1;a<=100;a++){
			str_act_id = "a"+Integer.toString(a);
			nc_act.put(new AccountId(str_act_id), "Account "+Integer.toString(a));
			for (b=1;b<=b_per_a;b++){
				str_bal_id = "b"+Integer.toString(a*b_per_a+b);
				nc_bal.put(new BalanceId(str_act_id, str_bal_id), new Balance(str_act_id, a*b_per_a+b));
			}
		}

		System.out.println("\n");
		System.out.println("== Size of cache[accounts] is "+nc_act.size());
		System.out.println("== Size of cache[balances] is "+nc_bal.size());
	}

}
