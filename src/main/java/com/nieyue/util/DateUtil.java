package com.nieyue.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.thymeleaf.util.StringUtils;

import net.sf.json.JSONObject;

/**
 * 日期格式化类
 * @author yy
 *
 */
public class DateUtil {
	/**
	 *@param n为小时段，默认24段，即
	 * 段时间的时间设置
	 * @return
	 */
	public static Date getDayPeriod(int n ){ 
		if(n<=0 ||n>24){
			n=24;
		}
		//long dayseconds=86400;//一天的秒数；
		long daystart=getStartTime().getTime()/1000;//当日初始秒数
		Date date =new Date();
		long daynow = date.getTime()/1000;//现在的秒数
		long daycha=daynow-daystart;//相差秒数
		
		long every=24/n;//如n=3;every=8 即8小时记录一次
		long everyseconds=every*60*60;//小时段的秒数
		long nown=daycha/everyseconds;//第几次
		Date rd = new Date((nown*everyseconds+daystart)*1000);
		return rd;
	}  
	/**
	 *@param n为小时段，默认24段，即
	 * 段时间的时间设置
	 * @return
	 */
	public static Date getDayPeriod(int n ,Date date){ 
		if(n<=0 ||n>24){
			n=24;
		}
		//long dayseconds=86400;//一天的秒数；
		long daystart=getStartTime().getTime()/1000;//当日初始秒数
		 date =new Date();
		long daynow = date.getTime()/1000;//现在的秒数
		long daycha=daynow-daystart;//相差秒数
		
		long every=24/n;//如n=3;every=8 即8小时记录一次
		long everyseconds=every*60*60;//小时段的秒数
		long nown=daycha/everyseconds;//第几次
		Date rd = new Date((nown*everyseconds+daystart)*1000);
		return rd;
	}
	/**
	 * 获取当日开始时间
	 * @return
	 */
	 public static Date getStartTime(){  
		Date date =new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		long nd = date.getTime()/1000*1000;
		return new Date(nd);
	}  
	 /**
	  * 获取指定日期开始时间
	  * @return
	  */
	 public static Date getStartTime(Date date){  
		 date.setHours(0);
		 date.setMinutes(0);
		 date.setSeconds(0);
		 long nd = date.getTime()/1000*1000;
		 return new Date(nd);
	 }  
	  
	/**
	 * 获取当日结束时间
	 * @return
	 */
	 public static Date getEndTime(){  
		Date date =new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		long nd = date.getTime()/1000*1000+999;
		return new Date(nd);
	} 
	 /**
	  * 获取当前时间到当日结束时间差  
	  * 单位 ： 秒
	  * @return
	  */
	 public static long currentToEndTime(){
		 Date date=new Date();
		 long miao = (getEndTime().getTime()-date.getTime())/1000;
		 return miao;
	 } 
	/**
	 * 格式化时间yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime(){
	Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置显示格式
	String nowTime="";
	nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
	return nowTime;
	}
	/**
	 * 格式化时间yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentTimeDay(){
	Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
	String nowTime="";
	nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
	return nowTime;
	}
	/**
	 * date格式化时间 format
	 * @return
	 */
	public static String dateFormatSimpleDate(Date date,String format){
		DateFormat df = new SimpleDateFormat(format);//设置显示格式
		String nowTime="";
		nowTime= df.format(date);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	/**
	 * 格式化时间"yyyyMMddHHmmss
	 * @return
	 */
	public static String getOrdersTime(){
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	/**
	 * 格式化时间"yyyyMMdd
	 * @return
	 */
	public static String getImgDir(){
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyyMMdd");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	
	/** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }  
    /**
     * 获取精准时间格式存储数据库
     * @param args
     */
    public static Date getFormatCurrentTime(){  
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	 String time=getCurrentTime();

    	 Timestamp time1 = null;
		try {
			time1 = new Timestamp(sdf.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
    	return time1;  
    }  
    /**
     * 获取两日期之间的相隔天数
     * @param args
     * @throws ParseException 
     */
    public static Long getSeparatedTime(Date d1,Date d2) {  
    	//String date01 = "2016-3-1 9:20:00";
    	//String date02 = "2016-3-2 9:19:00";
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//Date d1 = null;
    	//Date d2 = null;
		if(isSameDate(d1, d2)){//同一天
			return 0l;
		}else if(Math.abs((d2.getTime()-d1.getTime()))<=3600*24*1000){//差24小时以内算一天
			return 1l;
		}else{
			long daysBetween = (d2.getTime()-d1.getTime())/(3600*24*1000);//两日期之间相隔的天数 	
			return daysBetween;  
		}
    }  
    /**
     * 获取从起始日期开始几天后的日期
     * @param args
     * @throws ParseException 
     */
    public static Date getFirstToDay(Date firstDate,long coupleDay) {  
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date lastDate = new Date(coupleDay*3600*24*1000+firstDate.getTime());//两日期之间相隔的天数 
    	return lastDate;  
    }  
    /**
     * 获取从起始时间开始之后几分钟的时间
     * @param args
     * @throws ParseException 
     */
    public static Date getFirstToSecondsTime(Date firstDate,long coupleTime) {  
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date lastDate = new Date(coupleTime*60*1000+firstDate.getTime());//两日期之间相隔的天数 
    	return lastDate;  
    }  
    /**
     * 格式化时间从yyyy-MM-dd HH:mm:ss到Wed Mar 02 09:19:00 CST 2016
     * @param args
     * @throws ParseException 
     */
    public static Date parseDate(String date) throws ParseException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date da = sdf.parse(date);
    	return da;  
    }  
    /**
     * 是否同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                        .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }
    /**
     * 向前向后计算n个月
     * @param date
     * @param flag 月份减一为-1，加一为1
     * @return
     */
    public static Date nextMonth(Date date,int flag) {
    	 Calendar calendar = Calendar.getInstance();//日历对象
    	 calendar.setTime(date);//设置当前日期
    	 calendar.add(Calendar.MONTH, flag);//月份减一为-1，加一为1
    	 Date nDate =calendar.getTime();
    	 return nDate;
    	}
    /**
     * 向前向后计算n年
     * @param date
     * @param flag 年减一为-1，加一为1
     * @return
     */
    public static Date nextYear(Date date,int flag) {
    	Calendar calendar = Calendar.getInstance();//日历对象
    	calendar.setTime(date);//设置当前日期
    	calendar.add(calendar.YEAR, flag);//年减一为-1，加一为1
    	Date nDate =calendar.getTime();
    	return nDate;
    }
    /** 
     * 取得当前日期是多少周 
     * 
     * @param date 
     * @return 
     */ 
     public static int getWeekOfYear(Date date) { 
     Calendar c = new GregorianCalendar(); 
     c.setFirstDayOfWeek(Calendar.MONDAY); 
     c.setMinimalDaysInFirstWeek(7); 
     c.setTime (date);
     return c.get(Calendar.WEEK_OF_YEAR); 
     }
     /** 
     * 得到某一年周的总数 
     * 
     * @param year 
     * @return 
     */ 
     public static int getMaxWeekNumOfYear(int year) { 
     Calendar c = new GregorianCalendar(); 
     c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
     return getWeekOfYear(c.getTime()); 
     }
     /** 
     * 得到某年某周的第一天 
     * 
     * @param year 
     * @param week 
     * @return 
     */ 
     public static Date getFirstDayOfWeek(int year, int week) { 
     Calendar c = new GregorianCalendar(); 
     c.set(Calendar.YEAR, year); 
     c.set (Calendar.MONTH, Calendar.JANUARY); 
     c.set(Calendar.DATE, 1);
     Calendar cal = (GregorianCalendar) c.clone(); 
     cal.add(Calendar.DATE, week * 7);
     return getFirstDayOfWeek(cal.getTime ()); 
     }
     /** 
     * 得到某年某周的最后一天 
     * 
     * @param year 
     * @param week 
     * @return 
     */ 
     public static Date getLastDayOfWeek(int year, int week) { 
     Calendar c = new GregorianCalendar(); 
     c.set(Calendar.YEAR, year); 
     c.set(Calendar.MONTH, Calendar.JANUARY); 
     c.set(Calendar.DATE, 1);
     Calendar cal = (GregorianCalendar) c.clone(); 
     cal.add(Calendar.DATE , week * 7);
     return getLastDayOfWeek(cal.getTime()); 
     }
     /** 
      * 取得当前日期所在月的第一天 
      * 
      * @param date 
      * @return 
      */ 
      public static Date getFirstDayOfMonth() { 
      Calendar c = new GregorianCalendar(); 
      c.setTime(new Date()); 
      c.set(Calendar.DAY_OF_MONTH,1); 
      return c.getTime (); 
      }
      /** 
       * 取得指定日期所在月的第一天 
       * 
       * @param date 
       * @return 
       */ 
      public static Date getFirstDayOfMonth(Date date) { 
    	  Calendar c = new GregorianCalendar(); 
    	  c.setTime(date); 
    	  c.set(Calendar.DAY_OF_MONTH,1); 
    	  return c.getTime (); 
      }
     /** 
     * 取得指定日期所在周的第一天 
     * 
     * @param date 
     * @return 
     */ 
     public static Date getFirstDayOfWeek(Date date) { 
     Calendar c = new GregorianCalendar(); 
     c.setFirstDayOfWeek(Calendar.MONDAY); 
     c.setTime(date); 
     c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
     return c.getTime (); 
     }
     /** 
     * 取得指定日期所在周的最后一天 
     * 
     * @param date 
     * @return 
     */ 
     public static Date getLastDayOfWeek(Date date) { 
     Calendar c = new GregorianCalendar(); 
     c.setFirstDayOfWeek(Calendar.MONDAY); 
     c.setTime(date); 
     c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
     return c.getTime(); 
     } 
     
     /** 
     * 取得当前日期所在周的第一天 
     * 
     * @param date 
     * @return 
     */ 
     public static Date getFirstDayOfWeek() { 
     Calendar c = new GregorianCalendar(); 
     c.setFirstDayOfWeek(Calendar.MONDAY); 
     c.setTime(new Date()); 
     c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
     return c.getTime (); 
     }
     /** 
     * 取得当前日期所在周的最后一天 
     * 
     * @param date 
     * @return 
     */ 
     public static Date getLastDayOfWeek() { 
     Calendar c = new GregorianCalendar(); 
     c.setFirstDayOfWeek(Calendar.MONDAY); 
     c.setTime(new Date()); 
     c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
     return c.getTime(); 
     } 
    //  输出结果：  
    //  timeStamp=1417792627  
    //  date=2014-12-05 23:17:07  
    //  1417792627  
    //static HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    public static void main(String[] args) {  
    	System.out.println(getCurrentTime());
    	System.out.println(getOrdersTime());
    	String timeStamp = timeStamp();  
        System.out.println("timeStamp="+timeStamp);  
          
        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");  
        System.out.println("date="+date);  
          
        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");  
        System.out.println(timeStamp2); 
        System.out.println(getFormatCurrentTime().toLocaleString());
      //  HttpSession session = request.getSession();
       // session.setAttribute("nieyue", "dsfsdfsdf");
        //String nieyue = (String) session.getAttribute("nieyue");
       // System.out.println(session); 
        System.out.println(getFirstToDay(new Date(), 1).toLocaleString());
        System.out.println(getFirstToSecondsTime(new Date(), 1).toLocaleString());
        System.out.println(getImgDir());
        Date d1=new Date("2016/3/2 0:00:00");
        Date d2=new Date("2016/3/1 23:59:59");
        System.out.println(getSeparatedTime(d2,d1));
        
        String aa="%7B\"acountId\"%3A1000%2C\"bookOrderDetailList\"%3A%5B%7B\"billingMode\"%3A1%2C\"bookOrderDetailId\"%3A0%2C\"bookOrderId\"%3A0%2C\"createDate\"%3Anull%2C\"money\"%3A0%2C\"payType\"%3A1%2C\"realMoney\"%3A0.01%2C\"status\"%3A0%2C\"updateDate\"%3Anull%7D%5D%2C\"bookOrderId\"%3A0%2C\"createDate\"%3A%7B\"date\"%3A18%2C\"day\"%3A1%2C\"hours\"%3A10%2C\"minutes\"%3A33%2C\"month\"%3A8%2C\"seconds\"%3A58%2C\"time\"%3A1505702038564%2C\"timezoneOffset\"%3A-480%2C\"year\"%3A117%7D%2C\"orderNumber\"%3A\"27012017091810335810013\"%2C\"updateDate\"%3A%7B\"date\"%3A18%2C\"day\"%3A1%2C\"hours\"%3A10%2C\"minutes\"%3A33%2C\"month\"%3A8%2C\"seconds\"%3A58%2C\"time\"%3A1505702038564%2C\"timezoneOffset\"%3A-480%2C\"year\"%3A117%7D%7D";
        try {
			aa=URLDecoder.decode(aa, "UTF-8");
			System.out.println(aa);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.err.println(nextMonth(new Date(),5).toLocaleString());
		
		String payjson="{\"package\":\"Sign=WXPay\",\"appid\":\"wx9916b928e1ae2ccb\",\"sign\":\"9B37B64AC655A30B9F4978062322986C\",\"partnerid\":\"1489128222\",\"prepayid\":\"wx20171017102519c838e5f5bb0128546383\",\"noncestr\":\"63c844009ee348aab0ebc8c1d5d59feb\",\"timestamp\":\"1508207117\"}";
		System.out.println(JSONObject.fromObject(payjson).get("appid"));
		System.err.println(getFirstDayOfWeek(new Date()).toLocaleString());
		System.err.println(getFirstDayOfWeek(new Date()).getDay());
		System.err.println(getStartTime(getFirstDayOfMonth(new Date("2018/01/01 00:00:00"))).toLocaleString());
		String aaa="a";
		//System.err.println(StringUtils.isEmpty(aaa));
		String bbb="a";
		System.err.println(aaa.equals(bbb));
		
    }  
}
