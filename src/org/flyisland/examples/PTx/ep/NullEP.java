package org.flyisland.examples.PTx.ep;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

@Portable
public class NullEP extends AbstractProcessor {

	static Logger logger = LogManager.getLogger(NullEP.class.getName());
	private static final long serialVersionUID = 6698280756087317488L;

	public NullEP() {
		super();
	}

	@Override
	public Object process(Entry entry) {
		
		logger.trace(entry.getKey()+"="+entry.getValue());		
		return null;
	}

	@Override
	public Map processAll(Set setEntries) {
		logger.trace("===> Entered "+this.toString());
		for (Iterator iter = setEntries.iterator(); iter.hasNext(); )
		{
			Entry entry = (Entry) iter.next();
			logger.trace(entry.getKey()+"="+entry.getValue());		
		}
		logger.trace("===> Exited: "+this.toString());
		return null;
	}

	@Override
	public String toString() {
		return "NullEP";
	}

	
}
