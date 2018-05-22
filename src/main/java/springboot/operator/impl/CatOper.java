package springboot.operator.impl;

import springboot.operator.Operator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ${ lknny@163.com } on 2018/5/21.
 */
public class CatOper implements Operator {
	@Override
	public List<String> oper(String[] parms, List<String> values) {
		List<String> result = new ArrayList<String>();
		if (parms.length != 0) {
			String filePath = parms[0];

			FileReader reader = null;
			try {
				reader = new FileReader(filePath);
				BufferedReader br = new BufferedReader(reader);
				String str = null;
				while (( str = br.readLine()) != null) {
					result.add(str + "/n");
				}
				br.close();
				reader.close();
			} catch (Exception e) {
				return Collections.emptyList();
			}
		}


		return result;
	}
}
