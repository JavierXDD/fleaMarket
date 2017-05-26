package com.fr.data;

import com.fr.data.DefinedSubmitJob;
import com.fr.data.JobValue;
import com.fr.script.Calculator;

public class DemoSubmitJob1 extends DefinedSubmitJob {
	/**
	 * 当模板自定义事件增加的属性 名称与下面变量有对应时，则会自动赋值于此对应变量
	 */
	private JobValue studentno;   // JobValue对应单元格
	private JobValue name;
	private JobValue grade;
	private boolean isPass;       // 非单元格，则对应具体类型值

	/**
	 * 每一条记录执行一次此方法
	 * 同一提交事件在一个处理事务内，此对象是唯一的
	 */
	public void doJob(Calculator calculator) throws Exception {
		// JobValue的getValueState()方法获取此对应单元格的状态
		if (studentno.getValueState() == JobValue.VALUE_STATE_CHANGED) {
			// 此单元格的值在报表初始化后被修改过
		} else if (studentno.getValueState() == JobValue.VALUE_STATE_INSERT) {
			// 此单元格是在报表初始化后新增的(例如执行了插入行操作)
		} else if (studentno.getValueState() == JobValue.VALUE_STATE_DELETED) {
			// 此单元格所在的记录被执行了删除操作
		} else if (studentno.getValueState() == JobValue.VALUE_STATE_DEFAULT) {
			// 此单元格在报表初始化后没有变化
		}
		
		// 值获取
		System.out.print(" 学号: " + studentno.getValue());  // 通过JobValue的getValue方法获得单元格的值
		System.out.print(" 姓名: " + name.getValue());
		System.out.print(" 总分: " + grade.getValue());
		System.out.print(" 是否达标: " + isPass);
		System.out.println();
	}
}
