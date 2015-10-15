package org.flyisland.examples.PTx;

import org.flyisland.examples.PTx.pof.AccountId;
import org.flyisland.examples.PTx.pof.Balance;
import org.flyisland.examples.PTx.pof.BalanceId;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.extractor.PofExtractor;

import java.util.HashMap;

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
				System.out.println("== "+nc_act.getClass().getName()+" ==");

				HashMap<AccountId, String> map_act = new HashMap(100);
				HashMap<BalanceId, Balance> map_bal = new HashMap(100);

        nc_bal.addIndex(new PofExtractor(String.class, 0), false, null);
		for (a=1;a<=100;a++){
			str_act_id = "a"+Integer.toString(a);
			map_act.put(new AccountId(str_act_id), "Account "+Integer.toString(a));
			for (b=1;b<=b_per_a;b++){
				str_bal_id = "b"+Integer.toString(a*b_per_a+b);
				map_bal.put(new BalanceId(str_act_id, str_bal_id), new Balance(str_act_id, a*b_per_a+b));
			}
		}

		nc_act.putAll(map_act);
		nc_bal.putAll(map_bal);

		System.out.println("");
		System.out.println("== Size of cache[accounts] is "+nc_act.size());
		System.out.println("== Size of cache[balances] is "+nc_bal.size());
	}

}
