package springboot.operator;

import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public interface Operator {

	public List<String> oper(String[] parms,List<String> values);

}
