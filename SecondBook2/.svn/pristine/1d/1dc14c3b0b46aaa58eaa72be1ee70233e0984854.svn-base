package com.hautbook.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 
 * @author caiyao
 *
 * @function 随机验证图片生成器
 * 
 */
public class CaptchaImageGenerator {
	private static Font[] fonts = {
			new Font("Times New Roman" , Font.PLAIN , 35) ,
			new Font("Gungsuh" , Font.PLAIN , 35) ,
			new Font("Forte" , Font.PLAIN , 35) ,
			new Font("Gill Sans Ultra Bold" , Font.PLAIN , 35) ,
			new Font("Hobo Std" , Font.PLAIN , 35) ,
	} ;
	private static Color[] colors = {
			new Color(32,158,25), 
			new Color(218,42,19),
			new Color(31,75,208), 
			new Color(0,102,182), 
			new Color(171,0,85) 
	} ;
	private static Random random = new Random();
	// 验证码的宽度和高度固定
	private static int width = 130 ;
	private static int height = 40 ;
	// 验证码字符个数
	private static int captchaNum = 4 ; 
	/**
	 * 随机生成字体
	 * 注： 仅包含{@fonts}中定义的值
	 * @return 字体对象
	 */
	private static Font getRandomFont(){
		return fonts[random.nextInt(fonts.length)];
	}
	/**
	 * 生成随机颜色
	 * 注： 仅包含{@value}中定义的值
	 * @return 颜色对象
	 */
	private static Color getRandomColor(){
		return colors[random.nextInt(colors.length)] ;
	}
	/**
	 * 获取图片
	 * @return ： 图片转换成的ByteArrayInputStream流
	 * @throws IOException : 将图片转换成输入流时可能会出现的异常
	 */
	synchronized public static ByteArrayInputStream getImage(String captchaString) throws IOException{
		
		return convertImageToStream(drawImage(captchaString)) ;
	}
	private static ByteArrayInputStream convertImageToStream(BufferedImage image ) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", out) ;
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		return in ;
	}
	/**
	 * 画图片
	 * @return  bufferedImage对象
	 */
	private static BufferedImage  drawImage(String _captchaString){
		// 定义图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED) ;
		Graphics g = image.getGraphics() ;
		// 开始画边框
		g.setColor(Color.black) ;
		g.fillRect(0, 0, width, height) ;
		g.setColor(Color.white) ;
		g.fillRect(1, 1, width - 2, height - 2) ;
		// 边框绘制结束
		
		// 开始绘制验证码字符串
			// 获取验证字符串
		char[] captchaString = _captchaString.toCharArray() ;
		System.out.println(new String(captchaString));
		System.out.println(captchaString.length);
		for(int i = 0 ; i < captchaString.length ; i ++){
			g.setFont(getRandomFont());
			g.setColor(getRandomColor());
			int coordX = i*20 + 30;// X轴坐标
			int coordY = random.nextInt(2) * 5 + 30;// Y轴坐标
			g.drawString(String.valueOf(captchaString[i]), coordX, coordY) ;
		}
		// 验证码绘制结束
		
		// 绘制干扰点
		for(int i = 0 ; i < 100 ; i ++){
			int x = random.nextInt(width) ;
			int y = random.nextInt(height) ;
			Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
			g.setColor(color) ;
			g.drawOval(x, y, 0, 0) ;
			}
			// 画干扰线
			for(int i = 0 ; i < 15 ; i ++){
				int x = random.nextInt(width) ;
				int y = random.nextInt(height) ;
				int x1 = random.nextInt(width) ;
				int y1 = random.nextInt(height) ;
				Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
				g.setColor(color) ;
				g.drawLine(x, y, x1, y1) ;
			}
			g.dispose() ;
		return image ;
	}
}
