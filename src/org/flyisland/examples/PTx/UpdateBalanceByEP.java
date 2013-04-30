package org.flyisland.examples.PTx;

import java.util.Scanner;

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
        
		String	str_act_id, ops;
		int		value=0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input 'Account ID':");
		str_act_id = sc.next();
		System.out.print("Please input 'balance to change':");
		value = sc.nextInt();
		System.out.print("Please input 'Operation(sleep/fail)':");
		ops = sc.next();
		sc.close();

        nc_act.invoke(new AccountId(str_act_id), new UpdateBalanceEP(value, ops));
	}

}
