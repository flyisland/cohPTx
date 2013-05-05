package org.flyisland.examples.PTx.cs;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.tangosol.net.cache.CacheStore;

public class LogCacheStore implements CacheStore {
	private	String	m_cachename;
	private	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public LogCacheStore(String cachename) {
		super();
		this.m_cachename = cachename;
		log(this.toString()+" is created");
	}

	@Override
	public Object load(Object oKey) {
		log("load("+oKey.toString()+")");
		return null;
	}

	@Override
	public Map loadAll(Collection colKeys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void erase(Object oKey) {
		log("erase("+oKey.toString()+")");
	}

	@Override
	public void eraseAll(Collection colKeys) {
		log("eraseAll()=>"+colKeys.toString());
	}

	@Override
	public void store(Object oKey, Object oValue) {
		log("store("+oKey.toString()+", "+oValue.toString()+")");
	}

	@Override
	public void storeAll(Map mapEntries) {
		log("storeAll()=>" + mapEntries.toString());
	}
	
	private	void log(String msg){
		System.out.println("=== "+sdf.format(new Date())+" "+this.toString()+" === "+msg);
	}

	@Override
	public String toString() {
		return "LogCacheStore [" + m_cachename + "]";
	}

}
