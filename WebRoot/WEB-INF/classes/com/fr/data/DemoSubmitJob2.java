package com.fr.data;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.fr.data.DefinedSubmitJob;
import com.fr.data.JobValue;
import com.fr.script.Calculator;

public class DemoSubmitJob2 extends DefinedSubmitJob {

	/**
	 * 每一条记录执行一次此方法
	 * 同一提交事件在一个处理事务内，此对象是唯一的
	 */
	public void doJob(Calculator calculator) throws Exception {
		// 同样可以直接在传入的calculator中获取定义的属性及其对应的值
		Map map = (Map)calculator.getAttribute(PROPERTY_VALUE);
		if (map == null) return;
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		Entry entry;
		// 遍历Map获取所有属性及其值
		while (it.hasNext()) {
			entry = (Entry)it.next();
			System.out.print(" " + entry.getKey() + ": ");
			
			// JobValue对应单元格
			if (entry.getValue() instanceof JobValue) {
				JobValue ce = (JobValue)entry.getValue();
				// JobValue的getValueState()方法获取此对应单元格的状态
				if (ce.getValueState() == JobValue.VALUE_STATE_CHANGED) {
					// 此单元格的值在报表初始化后被修改过
				} else if (ce.getValueState() == JobValue.VALUE_STATE_INSERT) {
					// 此单元格是在报表初始化后新增的(例如执行了插入行操作)
				} else if (ce.getValueState() == JobValue.VALUE_STATE_DELETED) {
					// 此单元格所在的记录被执行了删除操作
				} else if (ce.getValueState() == JobValue.VALUE_STATE_DEFAULT) {
					// 此单元格在报表初始化后没有变化
				}
				
				System.out.print(ce.getValue());         // 通过JobValue的getValue方法获得单元格的值
			} else {
				// 非单元格，则对应具体类型值
				System.out.print(entry.getValue().toString());
			}
		}
		System.out.println();
	}
}
