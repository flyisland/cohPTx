package org.flyisland.examples.PTx.ep;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flyisland.examples.PTx.pof.AccountId;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.net.partition.PartitionAwareBackingMap;
import com.tangosol.util.Converter;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.ObservableMap;
import com.tangosol.util.processor.AbstractProcessor;
import com.tangosol.util.BinaryEntry;

@Portable
public class PrintAccoutBackingMapEP extends AbstractProcessor {

	static Logger logger = LogManager.getLogger(PrintAccoutBackingMapEP.class.getName());
	private static final long serialVersionUID = 4601827301540707135L;

	@Override
	public String toString() {
		return "PrintAccoutBackingMapEP";
	}

	@Override
	public Object process(Entry entry) {
		logger.trace("===> Entered");
		
		ObservableMap	map = ((BinaryEntry)entry).getBackingMap();
		Converter cvt_ki2o = ((BinaryEntry)entry).getBackingMapContext().getManagerContext().getKeyFromInternalConverter();

		for (Object o : map.keySet()){
			AccountId	id = (AccountId)cvt_ki2o.convert(o);
			logger.trace(id);
		}
		if(map instanceof PartitionAwareBackingMap) {
			logger.trace("This is a 'PartitionAwareBackingMap'.");
		}
		logger.trace("There are "+map.size()+" objects in this backingmap");
		logger.trace("===> Entered");
		return null;
	}

}
