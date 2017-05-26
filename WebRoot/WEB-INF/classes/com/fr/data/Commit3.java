package com.fr.data;  

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import com.fr.base.FRContext;
import com.fr.base.Formula;
import com.fr.cache.Attachment;
import com.fr.data.ClassSubmitJob;
import com.fr.data.SubmitJob;
import com.fr.data.impl.SubmitJobValue;
import com.fr.general.FArray;
import com.fr.general.FRLogManager;
import com.fr.general.FRLogger;
import com.fr.script.Calculator;
import com.fr.stable.UtilEvalError;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLableReader;


public class Commit3 implements SubmitJob {
    private Object attach;
    private SubmitJobValue filePath; //定义文件路径

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	public void doJob(Calculator ca) throws Exception {
		FRContext.getLogger().info("begin to upload file...");
		final Object attachO = attach;
		if (attachO instanceof FArray && ((FArray) attachO).length() != 0) {
			new Thread() {
				public void run() {
					int i;
					FArray attachmentlist = (FArray) attachO;
					for (i = 0; i < attachmentlist.length(); i++) {
						if (!(attachmentlist.elementAt(i) instanceof Attachment)) {
							continue;
						} else {
							FRContext.getLogger().info("filePath.value:" + filePath.getValue().toString());
							FRContext.getLogger().info("filePath.valueState:" + filePath.getValueState() +
									"注：valueState 0,1,2,3 分别表示 默认值，插入行，值改变，删除行");

							String FilePath = filePath.getValue().toString();
							String FileName = ((Attachment) (attachmentlist.elementAt(i))).getFilename();
							String Path = FilePath + "\\" + FileName;
							File fileDir = new File(FilePath);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							try {
								mkfile(FilePath, FileName, new java.io.ByteArrayInputStream(
										((Attachment) (attachmentlist.elementAt(i))).getBytes())); //新建文件夹，并且写入内
							} catch (Exception e) {
								Logger.getLogger("FR").log(Level.WARNING,
										e.getMessage() + "/nmkfileerror", e);
							}
						}
					}
				}
			}.start();
		}
	}

    private static void mkfile(String path, String FileName,
            java.io.InputStream source) throws FileNotFoundException,
            IOException {
        File fileout = new File(path, FileName);

        if (fileout.exists()) {// 检查是否存在
            fileout.delete();// 删除文件
			FRContext.getLogger().info("old file deleted");
        }
        // 在当前目录下建立一个名为FileName的文件
        if(fileout.createNewFile()) {
			FRContext.getLogger().info(path + FileName + "created!!");
		}
        FileOutputStream outputStream = new FileOutputStream(fileout);
        byte[] bytes = new byte[1024];
        int read=source.read(bytes);
        while (read!=-1) {
            outputStream.write(bytes, 0, read);
             outputStream.flush();
             read = source.read(bytes);
        }
        outputStream.close(); //把source写入新建的文件
    }

    public void readXML(XMLableReader reader) {
        // TODO Auto-generated method stub
    }

    public void writeXML(XMLPrintWriter writer) {
    }

	@Override
	public void doFinish(Calculator arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
