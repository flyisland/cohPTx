package org.flyisland.examples.PTx;

import java.util.Map;

import org.flyisland.examples.PTx.invocable.BatchTask;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.InvocationService;

public class BatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        System.out.println("==> Staring batch process!");
		InvocationService ivSvc = (InvocationService)CacheFactory.getService("Invocation_Test_Svc");
		Map map = ivSvc.query(new BatchTask(), null);
        System.out.println("The result of InvocationService is : \n\t"+map);
	}

}
