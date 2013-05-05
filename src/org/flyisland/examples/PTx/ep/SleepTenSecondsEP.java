package org.flyisland.examples.PTx.ep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

@Portable
public class SleepTenSecondsEP extends AbstractProcessor {

	static Logger logger = LogManager.getLogger(SleepTenSecondsEP.class.getName());
	private static final long serialVersionUID = 3780996713190259962L;

	public SleepTenSecondsEP() {
		super();
	}

	@Override
	public Object process(Entry arg0) {
		
		logger.trace("===> Entered");
		int i, i_sleep = 10;
		for (i=i_sleep; i>0; i--){
			try {
				logger.trace("*** "+i);
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
		logger.trace("===> Exited");
	
		return null;
	}

	@Override
	public String toString() {
		return "SleepTenSecondsEP";
	}

}
