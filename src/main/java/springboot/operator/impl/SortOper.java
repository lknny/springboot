package springboot.operator.impl;

import springboot.operator.Operator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public class SortOper implements Operator{
	@Override
	public List<String> oper(String[] parms, List<String> values) {
		String[] contents = values.toArray(new String[]{});
		Arrays.sort(contents);
		return Arrays.asList(contents);
	}
}
