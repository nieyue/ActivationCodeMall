package com.nieyue.comments;

import net.sf.json.xml.XMLSerializer;


/**
 *获取XML
 * @author 聂跃
 *
 */
public class MyXML extends XMLSerializer{
	@Override
	public void setRootName(String rootName) {
		// TODO Auto-generated method stub
		super.setRootName("xml");
	}
}
