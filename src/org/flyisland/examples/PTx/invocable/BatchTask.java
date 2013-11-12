package org.flyisland.examples.PTx.invocable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flyisland.examples.PTx.ep.UpdateBalanceEP;
import org.flyisland.examples.PTx.pof.AccountId;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.net.AbstractInvocable;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

@Portable
public class BatchTask extends AbstractInvocable {
	private static final long serialVersionUID = -4485636370229646488L;
	static Logger logger = LogManager.getLogger(BatchTask.class.getName());

	public BatchTask() {
		super();
	}

	@Override
	public void run() {
		logger.trace("===> Entered "+this.toString());
        NamedCache nc_act = CacheFactory.getCache("accounts");
        
		String	str_act_id="a1", ops="fail";
		int		value=10;
        nc_act.invoke(new AccountId(str_act_id), new UpdateBalanceEP(value, ops));
        setResult("ok");
		logger.trace("===> Exited: "+this.toString());
	}

}
