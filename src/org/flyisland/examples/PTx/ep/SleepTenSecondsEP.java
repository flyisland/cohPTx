package org.flyisland.examples.PTx.ep;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

@Portable
public class SleepTenSecondsEP extends AbstractProcessor {

	private static final long serialVersionUID = 3780996713190259962L;

	public SleepTenSecondsEP() {
		super();
	}

	@Override
	public Object process(Entry arg0) {
		
		System.out.println("=== Start EP : "+this.toString());
		int i, i_sleep = 10;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		for (i=i_sleep; i>0; i--){
			try {
				System.out.println("\t*** "+sdf.format(new Date())+" *** "+i);
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
		System.out.println("=== End EP : "+this.toString());
	
		return null;
	}

	@Override
	public String toString() {
		return "SleepTenSecondsEP";
	}

}
