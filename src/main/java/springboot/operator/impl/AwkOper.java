package springboot.operator.impl;

import springboot.operator.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public class AwkOper implements Operator {
	@Override
	public List<String> oper(String[] parms, List<String> values) {
		List<String> result = new ArrayList<String>();
		if (parms.length != 0) {
			String para = parms[0];
			String awkContent = para.replace("\'", "");

			if (awkContent.contains("{")) {
				awkContent = awkContent.replace("{", "");
			}
			if (awkContent.contains("}")) {
				awkContent = awkContent.replace("{", "");
			}

			String[] awkParm = awkContent.split(" ");
			if (awkParm.length > 1) {
				String key = awkParm[0];
				if ("print".equals(key)) {
					String parm = awkParm[1];
					if (parm.contains("$")) {
						try {
							int intParm = Integer.parseInt(parm.replace("$", ""));
							for (String value : values) {
								String[] content = value.split(" ");
								if (content.length > 2) {
									result.add(content[2]);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}
}
