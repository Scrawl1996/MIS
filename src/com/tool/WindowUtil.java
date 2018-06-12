package com.tool;
/**
 * 工具类
 * 使窗口显示在屏幕正中央
 */
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
public class WindowUtil {
	private static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();//获取屏幕的分辨率

	public static Point setLocation(int width, int height) {
		int x = (int) ((screenSize.getWidth() - width) / 2);
		int y = (int) ((screenSize.getHeight() - height) / 2);
		
		return new Point(x, y);
	}
}