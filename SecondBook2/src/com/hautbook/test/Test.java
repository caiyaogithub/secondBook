package com.hautbook.test;





public class Test {
	public static final int BIT_MASK_COLOR = 0x00f ;
	public static final int BIT_MASK_SHAPE = 0XFF0 ;
	public static final int COLOR_RED = 0X001 ;
	public static final int COLOR_GREEN = 0x002 ;
	public static final int COLOR_YELLOW = 0x004 ;
	public static final int SHAPE_RECTANGLE = 0x010 ;
	public static final int SHAPE_CIRCLE = 0x020 ;
	public static final int SHAPE_TRIANGLE = 0x040 ;
	public static final int SHAPE_ELLIPSE = 0x080 ;
	
	public static void main(String[] args) throws Exception {
		// 计算绿色三角形
		System.out.println(COLOR_GREEN | SHAPE_TRIANGLE);
	}
}
