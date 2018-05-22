package springboot.operator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public class App {

	public static Map<String, Operator> OperFactory = new HashMap<String, Operator>();

	public static List<String> result = null;

	public static void main(String[] args) {

		if (null == args || args.length < 2) {
			return;
		}

		String key = args[0];
		String[] paras = key.trim().split("|");
		if (paras.length == 1) {
			String[] operAndPara = paras[0].split(" ");
			String oper = operAndPara[0].trim();
			String para = operAndPara[1].trim();
			if (null != OperFactory.get(oper)) {
				result = OperFactory.get(oper).oper(new String[]{para}, null);
			}
		}
		if (paras.length > 1) {
			for (int i = 1; i < paras.length; i++) {
				if (null != OperFactory.get(paras[1])) {
					String[] operAndPara = paras[1].split(" ");
					String oper = operAndPara[0].trim();
					String para = operAndPara[1].trim();
					result = OperFactory.get(paras[1]).oper(new String[]{para}, result);
				}
			}
		}

		for (String s : result) {
			System.out.println(s);
		}

	}

}
