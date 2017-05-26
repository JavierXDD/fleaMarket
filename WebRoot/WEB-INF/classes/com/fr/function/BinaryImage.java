package com.fr.function;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fr.data.core.db.BinaryObject;
import com.fr.script.AbstractFunction;
import com.sun.jna.Library;
import com.sun.jna.Native;

public class BinaryImage extends AbstractFunction{

	//加载dll，"E:\\bmp\\WltRS"是dll的文件完整路径，但不带后缀名，生成WltRS.class
	static WltRS wltrs = (WltRS) Native.loadLibrary("E:\\bmp\\WltRS", WltRS.class);
	
	static int index = 0;
	
	public Object run(Object[] args) {
		
		int current = index;
		
		//args[0] 是 BinaryObject对象，取为bo
		BinaryObject bo = (BinaryObject)args[0];
		
		//将bo转换为.wlt文件，并保存在位置E:\bmp\；本地方法GetBmp的第一个参数是wlt文件的路径
		getFile(bo.getBytes(), "E:\\bmp\\", current + ".wlt");
		
		//读取.wlt为文件
		File file = new File("E:\\bmp\\" + current + ".wlt");  
		
		//调用本地方法，在相同路径下生产.bmp
		wltrs.GetBmp("E:\\bmp\\" + current + ".wlt", 1);
		
		//读取并返回图片
		File imagefile = new File("E:\\bmp\\" + current + ".bmp");
		BufferedImage buffer = null;
		try {
			buffer = ImageIO.read(imagefile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		index = (++index)%300;
		return buffer;
	}
	
	
	// byte[]转换为file的方法
	public static void getFile(byte[] bfile, String filePath, String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists() && dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }
}

//用jna调用本地方法的必须步骤，具体含义不明
interface WltRS extends Library{
	//定义要调用的本地方法
	void GetBmp(String str, int i);
}