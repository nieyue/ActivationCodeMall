package com.nieyue.util;

import java.util.HashMap;


/**
 * HashMap单例
 * @author 聂跃
 * @date 2018年3月9日
 */
public class SingletonHashMap {
	
    private SingletonHashMap() {}

    private static class SingletonInstance {
        private static final HashMap<String,Object> INSTANCE = new HashMap<String,Object>();
    }

    public static HashMap<String,Object> getInstance() {
        return SingletonInstance.INSTANCE;
    }
    
    public static void main(String[] args) {
		HashMap<String,Object> a=  SingletonHashMap.getInstance(); 
		HashMap<String,Object> b=  SingletonHashMap.getInstance(); 
		a.put("accountId"+1000, 1000);
		b.put("accountId"+1000, 1000);
		b.put("accountId"+1001, 1001);
		b.put("accountId"+1002, "");
		b.put("accountId"+1003, null);
		System.out.println(a.get("accountId"+1003));
		System.out.println(a);
		System.out.println(b);
		System.out.println(a==b);
		System.out.println(a.equals(b));
	}
}