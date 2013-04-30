package org.flyisland.examples.PTx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.flyisland.examples.PTx.pof.Balance;
import org.flyisland.examples.PTx.pof.BalanceId;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class ReadBalance {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		String	str_aid, str_bid;
		int		i_sleep=0;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input 'Account ID':");
		str_aid = sc.next();
		System.out.print("Please input 'Balance Id':");
		str_bid = sc.next();
		System.out.print("Please input 'Sleep Time(in second)':");
		i_sleep = sc.nextInt();
		sc.close();
		
		CacheFactory.ensureCluster();
		BalanceId	bal_id = new BalanceId(str_aid, str_bid);
        NamedCache nc_bal = CacheFactory.getCache("balances");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Balance		bal = null;

        System.out.println("");
        do {
        	bal = (Balance)nc_bal.get(bal_id);
	        System.out.println("=== "+sdf.format(new Date())+" === Value of "+bal_id+" is '"+bal+"'");
	        if (i_sleep>0) {
	        	Thread.sleep(1000);
	        	i_sleep--;
	        }
        }while(i_sleep>0);
        System.out.println("");
	}
}
