package com.fr.output;

import java.io.*;

import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.schedule.output.AbstractOutputFileAction;
import com.fr.schedule.output.OutputFileAction;

public class OutputExcel extends AbstractOutputFileAction{

	@Override
	public File[] getFilesToDealWith(File[] files) {
		return files;
	}

	@Override
	public void doFileAction(File[] files) {
//		OutputStream out=new BufferedOutputStream(new FileOutputStream(new File(files.)));;
		System.out.println(files[0].getName());
		for(int i=0;i<files.length;i++){
			String name=files[i].getName();
			String path="D:/"+name;
			BufferedInputStream in=null;
			OutputStream out=null;
			try {
				 out=new BufferedOutputStream(new FileOutputStream(new File(path)));
				 in=new BufferedInputStream(new FileInputStream(files[i]));
				byte[] ba=new byte[in.available()];
				in.read(ba);
				out.write(ba);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
				if(in!=null){
						in.close();
				}
				if(out!=null){
					out.close();
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}

	@Override
	public boolean isEmailNotification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OutputFileAction analyzeJSON(JSONObject arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createJSONConfig() throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsonTag() {
		// TODO Auto-generated method stub
		return null;
	}

}
