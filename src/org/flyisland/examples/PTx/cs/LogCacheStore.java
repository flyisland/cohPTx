package org.flyisland.examples.PTx.cs;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tangosol.net.cache.CacheStore;

public class LogCacheStore implements CacheStore {
	static Logger logger = LogManager.getLogger(LogCacheStore.class.getName());

	private	String	m_cachename;

	public LogCacheStore(String cachename) {
		super();
		this.m_cachename = cachename;
		logger.trace(this.toString()+" is created");
	}

	@Override
	public Object load(Object oKey) {
		logger.trace("load("+oKey.toString()+")");
		return null;
	}

	@Override
	public Map loadAll(Collection colKeys) {
		logger.trace("loadAll()=>"+colKeys.toString());
		return null;
	}

	@Override
	public void erase(Object oKey) {
		logger.trace("erase("+oKey.toString()+")");
	}

	@Override
	public void eraseAll(Collection colKeys) {
		logger.trace("eraseAll()=>"+colKeys.toString());
	}

	@Override
	public void store(Object oKey, Object oValue) {
		logger.trace("store("+oKey.toString()+", "+oValue.toString()+")");
	}

	@Override
	public void storeAll(Map mapEntries) {
		logger.trace("storeAll()=>" + mapEntries.toString());
	}

	@Override
	public String toString() {
		return "LogCacheStore [" + m_cachename + "]";
	}

}
