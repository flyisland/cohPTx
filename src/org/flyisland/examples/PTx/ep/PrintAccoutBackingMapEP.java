package org.flyisland.examples.PTx.ep;

import org.flyisland.examples.PTx.pof.AccountId;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.util.Converter;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.ObservableMap;
import com.tangosol.util.processor.AbstractProcessor;
import com.tangosol.util.BinaryEntry;

@Portable
public class PrintAccoutBackingMapEP extends AbstractProcessor {

	private static final long serialVersionUID = 4601827301540707135L;

	@Override
	public String toString() {
		return "PrintAccoutBackingMapEP";
	}

	@Override
	public Object process(Entry entry) {
		System.out.println("=== Start EP : "+this.toString());
		
		ObservableMap	map = ((BinaryEntry)entry).getBackingMap();
		Converter cvt_ki2o = ((BinaryEntry)entry).getBackingMapContext().getManagerContext().getKeyFromInternalConverter();

		for (Object o : map.keySet()){
			AccountId	id = (AccountId)cvt_ki2o.convert(o);
			System.out.println("\t"+id);
		}
		System.out.println("There are "+map.size()+" objects in this backingmap");
		System.out.println("=== End EP : "+this.toString());
		return null;
	}

}
