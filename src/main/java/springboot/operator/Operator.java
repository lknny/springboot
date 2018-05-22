package springboot.operator;

import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public interface Operator {

	/**
	 * @param parms 操作符入参
	 * @param values 操作数据
	 * @return 返回数据
	 */
	public List<String> oper(String[] parms,List<String> values);
}
