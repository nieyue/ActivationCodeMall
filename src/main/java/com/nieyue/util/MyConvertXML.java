package com.nieyue.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nieyue.filter.params.MyNamingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * xml 与 对象相互转换
 * @author yy
 *
 */
public class MyConvertXML {
	/**
	 * 对象转xml
	 * @param o
	 * @return
	 */
	public static String getXML(Object o){
		XStream xs =new XStream();
		xs.alias("xml", o.getClass());
		String xml = xs.toXML(o);
		 return xml;
	}
	/**
	 * 对象转xml 驼峰转下划线
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static String getUnderlineXML(Object o,final boolean cdata) throws JsonProcessingException{
		//XmlFriendlyNameCoder nameCoder = new XmlFriendlyNameCoder("_-", "_");
		XStream xs = new XStream(new XppDriver(new NoNameCoder()){
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out){
					//boolean cdata=true;
					@SuppressWarnings("rawtypes")
					@Override
					public void startNode(String name, Class clazz) {
						// TODO Auto-generated method stub
						super.startNode(name, clazz);
					}
					
					@Override
					public String encodeNode(String name) {
						// TODO Auto-generated method stub
						return MyNamingStrategy.camelToUnderline(name);
					}
					
					@Override
					protected void writeText(QuickWriter writer, String text) {
						if(cdata){
							writer.write("<![CDATA[");
							writer.write(text);
							writer.write("]]>");
						}else{
							writer.write(text);
						}
					}
				};
			}
		});
		xs.alias("xml", o.getClass());
		//反射获取所有属性名。再转下划线加小写
		//for (int i = 0; i < o.getClass().getDeclaredFields().length; i++) {
		//	xs.aliasField(MyNamingStrategy.camelToUnderline(o.getClass().getDeclaredFields()[i].getName()), UnifiedOrder.class, o.getClass().getDeclaredFields()[i].getName());
		//}
		String xml = xs.toXML(o);
		return xml;
	}
	
	/**
	 * xml转对象
	 * @param o
	 * @return
	 */
	/*public static Object getObj(String xml,Object o){
		XStream xs =new XStream();
		xs.alias("xml", o.getClass());
		 o = (UnifiedOrder) xs.fromXML(xml);
		return o;
	}*/
	/**
	 * xml转对象 下划线转驼峰
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public static Object getCamelObj(String xml,Object o) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		 List<Element> elementList = root.elements();
		 for (Element e : elementList)
		 {
			 e.setName(MyNamingStrategy.underlineToCamel(e.getName()));
			map.put(e.getName(), e.getTextTrim());
		 }
		 Object o2 = MyDom4jUtil.mapToBean(map, o.getClass());
		return o2;
	}
	
	public static void main(String[] args) throws Exception {
		/*UnifiedOrder uo=new UnifiedOrder();
		uo.setAppid("324234");
		uo.setDeviceInfo("sdfs_dDDf");
		uo.setBody("聂跃");
		uo.setMchId("DFSDFSDFSD#$#");
		uo.setNonceStr("DFDSFSDFSDFSDF");
		uo.setNotifyUrl("http://www.baidu.com");
		uo.setOpenid("DSDSFDSFSD324");
		uo.setOutTradeNo("dsfsdfFDDRER#");
		uo.setProductId("DFDSFDSF#");
		uo.setSign("DFDSFSD#");
		//uo.setTimeStart(timeStart);
		uo.setTotalFee(34343);
		uo.setTradeType("dfsdfDFDF");
		String xx = MyConvertXML.getUnderlineXML(uo,true);
		System.err.println(xx);
		Object no = MyConvertXML.getCamelObj(xx,uo);
		System.out.println(no);*/
		/*for (int i = 0; i < uo.getClass().getDeclaredFields().length; i++) {
			System.out.println(uo.getClass().getDeclaredFields()[i].getName());
			
		}*/
		
	}
}
