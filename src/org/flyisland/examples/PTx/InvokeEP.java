package org.flyisland.examples.PTx;

import java.util.Scanner;

import org.flyisland.examples.PTx.ep.PrintAccoutBackingMapEP;
import org.flyisland.examples.PTx.ep.SleepTenSecondsEP;
import org.flyisland.examples.PTx.ep.UpdateBalanceEP;
import org.flyisland.examples.PTx.pof.AccountId;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.InvocableMap.EntryProcessor;

public class InvokeEP {

	public static void main(String[] args) {
		CacheFactory.ensureCluster();
        NamedCache nc_act = CacheFactory.getCache("accounts");
        
		String	str_act_id, ep_name;
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input 'Account ID':");
		str_act_id = sc.next();
		System.out.print("Please input the name of EP to invoke(sleep/backingmap):");
		ep_name = sc.next();
		sc.close();

		EntryProcessor	ep = null;
		if (ep_name.equalsIgnoreCase("sleep"))
			ep = new SleepTenSecondsEP();
		else if (ep_name.equalsIgnoreCase("backingmap"))
			ep = new PrintAccoutBackingMapEP();
		else
			return;
		
        nc_act.invoke(new AccountId(str_act_id), ep);
	}

}
