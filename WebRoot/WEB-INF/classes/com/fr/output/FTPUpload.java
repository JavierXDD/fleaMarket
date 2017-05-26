package com.fr.output;

import java.io.File;

import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.schedule.output.AbstractOutputFileAction;
import com.fr.schedule.output.FTPTransmission;
import com.fr.schedule.output.OutputFileAction;

public class FTPUpload extends AbstractOutputFileAction {
	@Override
	public File[] getFilesToDealWith(File[] files) {
		return files;
	}

	@Override
	public void doFileAction(File[] files) {
		FTPTransmission ftp=new FTPTransmission();
		ftp.setServerAddress("192.168.100.100");
		ftp.setPort(21);
		ftp.setSavePath("test");
		ftp.setUsername("1");
		ftp.setPassword("1");
		try {
			ftp.doFTP(files);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
