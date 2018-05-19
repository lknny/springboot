package springboot;

/**
 * Created by ${ lknny@163.com } on 2018/5/19.
 */
public class StringTest {

	public static void main(String[] args) {
		Integer a = 100;
		Integer b = 100;
		System.out.println(a == b);
		Integer aa = new Integer(110);
		Integer bb = new Integer(110);
		System.out.println(aa == bb);

		String s1 = "123";
		String s2 = new String("123");
		System.out.println(s1 == s2);
		String ss1 = s2.intern();
		System.out.println(s1 == ss1);

		String q = "12";
		String w = "3";
		String e = "123";
		System.out.println(e == (q + w));

		final String qq = "12";
		final String ww = "3";
		System.out.println(e == (qq + ww));

	}
}
