package com.nieyue.verification;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

/**
 * 验证码
 * @author 聂跃
 * @date 2017年6月12日
 */
@Component
public class VerificationCode {
private ByteArrayInputStream inputStream;
	
	public String creatRandomString() {
		String str = "";
		for(int i = 0; i < 4 ; i++) {
			str += Integer.toString((new Double(Math.random() * 10)).intValue());
		}
		return str;
	}

	public Color createsRandomColor() {
		int r = (new Double(Math.random() * 256)).intValue();
		int g = (new Double(Math.random() * 256)).intValue();
		int b = (new Double(Math.random() * 256)).intValue();
		return new Color(r, g, b);
	}
	
	public BufferedImage createImage(String str) {
		int width = 60;
		int height = 22;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_555_RGB);
		//获取图形上下文
		Graphics g = image.getGraphics();
		//设定背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		//画边框
		g.setColor(Color.pink);
		g.drawRect(0, 0, width - 1, height - 1);
		//将认证码显示到图像中
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
		//使用随便颜色
		g.setColor(this.createsRandomColor());
		//将随机字符串的每个数字分别写到图片上
		g.drawString(Character.toString(str.charAt(0)), 8, 17);
		g.drawString(Character.toString(str.charAt(1)), 20, 17);
		g.drawString(Character.toString(str.charAt(2)), 33, 17);
		g.drawString(Character.toString(str.charAt(3)), 45, 17);
		//图像生效
		g.dispose();
		return image;
	}
	//将图片以字节形式写到InputStream里
	public ByteArrayOutputStream createInuptStream(HttpSession session) throws Exception {
		//获取随机字符串
		String str = this.creatRandomString();
		BufferedImage image = this.createImage(str);
		//将产生的字符串写入session，供校验时使用;
		session.setAttribute("random", str);
		//ActionContext actionContext = ActionContext.getContext();
		//Map session = actionContext.getSession();
		//session.put("random", str);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		//ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
		output.close();
		//return input;
		return output;
	}
	public ByteArrayOutputStream execute(HttpSession session) throws Exception {
		return createInuptStream(session);
	}
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
}
