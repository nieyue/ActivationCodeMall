package com.nieyue.business;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;

import com.nieyue.util.DateUtil;

/**
 * 订单业务
 * @author 聂跃
 * @date 2018年3月2日
 */
@Configuration
public class OrderBusiness {
    public  String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };  
    /**
     * 生产短字符串
     * @return
     */
    public  String generateShortUuid() { 
    	StringBuffer shortBuffer = new StringBuffer();  
        String uuid = UUID.randomUUID().toString().replace("-", "");  
    	
        for (int i = 0; i < 8; i++) {  
            String str = uuid.substring(i * 4, i * 4 + 4);  
            int x = Integer.parseInt(str, 32);  
            shortBuffer.append(chars[x % 0x3E]);  
        }   
        return shortBuffer.toString(); 
    } 
  
	/**
	 * 订单号生产
	 */
	public String getOrderNumber(Integer accountId){
		String orderNumber=null;
		orderNumber=accountId+DateUtil.getOrdersTime()+(new Random().nextInt(9000)+1000);
		return orderNumber;
	}
	
	public static void main(String[] args) {
		Integer accountId=90001;
		accountId=accountId>=90000?accountId:(accountId+10000);
		//System.out.println(new Random().nextInt(9000));
		String u=new OrderBusiness().generateShortUuid();
		System.out.println(u);
		Set<String> set=new HashSet<>();
		for (int i = 0; i < 1000000; i++) {
			String uuid=new OrderBusiness().generateShortUuid();
			boolean b=set.add(uuid);
			if(!b){
				System.out.println(uuid);
				System.out.println(set.contains(uuid));
			}
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					String uuid=new OrderBusiness().generateShortUuid();
					boolean b=set.add(uuid);
					if(!b){
						System.out.println(uuid);
						System.out.println(set.contains(uuid));
					}
				}
				
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000000; i++) {
					String uuid=new OrderBusiness().generateShortUuid();
					boolean b=set.add(uuid);
					if(!b){
						System.out.println(uuid);
						System.out.println(set.contains(uuid));
					}
				}
				
				System.out.println(set.size());
			}
		}).start();
	}
}
