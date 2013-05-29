package com.joyveb.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

import com.joyveb.cassctrl.CassCtrl;

public class CassCtrlTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File file = new File("node.properties");
			FileInputStream ips = new FileInputStream(file);
			Properties p = new Properties();
			p.load(ips);
			Set keys = p.keySet();
			for(Object key : keys){
				String value = p.getProperty((String) key);
				String[] values = value.split(" ");
				//System.out.println(key + " " + p.getProperty((String) key));
				CassCtrl.execCmd((String)key, Integer.valueOf(values[0]), values[1], values[2], "date");
			}
			System.out.println("congraturations. done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
