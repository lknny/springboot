package springboot.operator.impl;

import springboot.operator.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public class GrepOper implements Operator {
	@Override
	public List<String> oper(String[] parms, List<String> values) {
		List<String> result = new ArrayList<String>();
		if (parms.length != 0) {
			String para= parms[0];
			String grepContent=para.replace("\"", "");
			for (String value : values) {
				if (value.contains(grepContent)){
					result.add(value);
				}
			}
		}
		return result;
	}
}
