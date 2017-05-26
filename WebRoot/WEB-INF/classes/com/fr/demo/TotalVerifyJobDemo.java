package com.fr.demo;

import com.fr.base.Utils;

import com.fr.data.JobValue;
import com.fr.data.TotalVerifyJob;
import com.fr.script.Calculator;

public class TotalVerifyJobDemo extends TotalVerifyJob{
	/*
	 * type : 必须要定义此私有变量，变量名可改，表示校验状态
	 * 0  表示校验成功，默认校验状态位为0
	 * 1  表示校验失败
	 */
	private int type = 0; 
	
	@Override
	protected void doTotalJob(Data data, Calculator calculator) 
	throws Exception { // @param data 以二维表排列的所有提交数据
		int sale, min;
		JobValue salenum, minnum; 

		int row = data.getRowCount(); // 获取一共多少行数据
		for (int i = 0; i < row; i++) {  // 遍历每行，进行校验
			salenum = (JobValue) data.getValueAt(i, 0);
            sale = Integer.parseInt(Utils.objectToString(salenum.getValue())); 
            
            minnum = (JobValue) data.getValueAt(i, 1);
            min = Integer.parseInt(Utils.objectToString(minnum.getValue()));
            
            if(sale < min){ //校验判断
				type = 1;
            }
        }  
		
	}

	public String getMessage() {
		// 根据校验状态是成功还是失败，设置对应的返回信息
		if(type == 0){
			return "恭喜你，校验成功";
		}else{
			return "销量值不能小于最小基数";
		}
	}

	public int getType() {
		// 返回校验状态
		return type;
	}

}
