package springboot.operator.impl;

import springboot.operator.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public class uniqOper implements Operator {
	@Override
	public List<String> oper(String[] parms, List<String> values) {
		List<String> result = new ArrayList<String>();

		if (parms.length != 0) {
			String para = parms[0].trim();
			if ("-c".equals(para)) {

				Map<String, Integer> index = new HashMap<String, Integer>();
				for (String value : values) {
					if (null == index.get(value)) {
						index.put(value, 1);
					} else {
						int sum = index.get(value);
						++sum;
						index.put(value, sum);
					}
				}
				for (Map.Entry<String, Integer> stringIntegerEntry : index.entrySet()) {
					result.add(stringIntegerEntry.getValue() + " " + stringIntegerEntry.getKey());
				}
			}
		}
		return result;
	}
}
