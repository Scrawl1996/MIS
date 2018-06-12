package com.tool;
/**
 * ������
 * ʹ������ʾ����Ļ������
 */
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
public class WindowUtil {
	private static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();//��ȡ��Ļ�ķֱ���

	public static Point setLocation(int width, int height) {
		int x = (int) ((screenSize.getWidth() - width) / 2);
		int y = (int) ((screenSize.getHeight() - height) / 2);
		
		return new Point(x, y);
	}
}