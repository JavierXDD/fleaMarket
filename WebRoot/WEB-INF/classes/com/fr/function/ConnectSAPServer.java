package com.fr.function;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class ConnectSAPServer {
	static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
	static {
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST,
				"SAP服务器IP地址");
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "系统编号");
		connectProperties
				.setProperty(DestinationDataProvider.JCO_CLIENT, "客户端编号（SAP中的，和客户端没关系）");
		connectProperties.setProperty(DestinationDataProvider.JCO_USER,
				"用户名");
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD,
				"密码");
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "ZH");
		connectProperties.setProperty(
				DestinationDataProvider.JCO_POOL_CAPACITY, "10");
		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT,
				"10");
		createDataFile(ABAP_AS_POOLED, "jcoDestination", connectProperties);
	}

	static void createDataFile(String name, String suffix, Properties properties) {
		File cfg = new File(name + "." + suffix);
		if (!cfg.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream(cfg, false);
				properties.store(fos, "SAP连接配置文件");
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException(
						"Unable to create the destination file "
								+ cfg.getName(), e);
			}
		}
	}

	public static JCoDestination Connect() {
		JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
		} catch (JCoException e) {
			e.getCause();
		}
		return destination;
	}
}