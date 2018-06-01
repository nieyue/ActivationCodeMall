package com.nieyue.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.nieyue.exception.AccountAlreadyAuthException;


public class Test1 {
	/** 
	 *  
	 * 遍历对象属性值(利用反射实现)，可以在需要对 对象中的每个字段都执行相同的处理时使用 
	 * 
	 */  
	public static Object dispose(Object object){  
		Field[] field = object.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组  
		        try {  
		            for (int j = 0; j < field.length; j++) { // 遍历所有属性  
		                String name = field[j].getName(); // 获取属性的名字  
		                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法  
		                String type = field[j].getGenericType().toString(); // 获取属性的类型  
		                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名  
		                    Method m = object.getClass().getMethod("get" + name);  
		                    String value = (String) m.invoke(object); // 调用getter方法获取属性值  
		                    //.....处理开始........  
		                    value+="1";
		                  System.out.println("执行方法"+value); 
		                    //.....处理结束........  
		                    m = object.getClass().getMethod("set"+name,String.class);  
		                    m.invoke(object, value);  
		                }  
		                if (type.equals("class java.lang.Integer")) {  
		                    Method m = object.getClass().getMethod("get" + name);  
		                    Integer value = (Integer) m.invoke(object);  
		                    if (value == null) {  
		                        m = object.getClass().getMethod("set"+name,Integer.class);  
		                        m.invoke(object, 1);  
		                    }  
		                }  
		                if (type.equals("class java.lang.Boolean")) {  
		                    Method m = object.getClass().getMethod("get" + name);  
		                    Boolean value = (Boolean) m.invoke(object);  
		                    if (value == null) {  
		                        m = object.getClass().getMethod("set"+name,Boolean.class);  
		                        m.invoke(object, false);  
		                    }  
		                }  
		                if (type.equals("class java.util.Date")) {  
		                    Method m = object.getClass().getMethod("get" + name);  
		                    Date value = (Date) m.invoke(object);  
		                    if (value == null) {  
		                        m = object.getClass().getMethod("set"+name,Date.class);  
		                        m.invoke(object, new Date());  
		                    }  
		                }  
		                // 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断  
		            }  
		        } catch (NoSuchMethodException e) {  
		            e.printStackTrace();  
		        } catch (SecurityException e) {  
		            e.printStackTrace();  
		        } catch (IllegalAccessException e) {  
		            e.printStackTrace();  
		        } catch (IllegalArgumentException e) {  
		            e.printStackTrace();  
		        } catch (InvocationTargetException e) {  
		            e.printStackTrace();  
		        }  
		          
		        return object;  
		} 
	


	public static void main(String[] args) throws IOException, AccountAlreadyAuthException {
		//System.out.println((new Test1().dispose(new Account())).toString());
		  //设置Headless模式
//        System.setProperty("java.awt.headless","true");
//        BufferedImage bi = new BufferedImage(200, 100,BufferedImage.TYPE_INT_RGB);
//        Graphics g = bi.getGraphics();
//        String s ="Headless模式测试";
//        g.drawString(new String(s.getBytes(),"UTF8"), 50, 50);
//        ImageIO.write(bi,"jpeg", new File("test.jpg"));
			//System.out.println(generateShortUuid());
		/*Enumeration<CommPortIdentifier> em = CommPortIdentifier.getPortIdentifiers();
		 while (em.hasMoreElements()) {
		 String name = em.nextElement().getName();
		 	System.out.println(name);
        }
		 
		 throw new AccountAlreadyAuthException();*/
		ExecutorService es = Executors.newFixedThreadPool(5);
		/*es.submit(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Math.random());
				
			}
		}));*/
		Future<Integer> future =  es.submit(new Callable<Integer>() {
			@Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                return sum;
            }
        });
		
		try {
			System.out.println(future.get());
			System.out.println(100*99/2);
			es.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}