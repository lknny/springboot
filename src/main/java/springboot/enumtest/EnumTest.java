package springboot.enumtest;

/**
 * Created by ${ lknny@163.com } on 2019/2/19.
 */
public class EnumTest {


	enum Young{
		BOBO("bobddddo"),
		LK("lddddk");
		public String value;

		private Young(String name) {
			this.value = name;
		}

		@Override
		public String toString(){
			return value;
		}
	}

	public static void main(String[] args) {
		System.out.println(Young.BOBO);
		System.out.println(Young.LK);
	}

}
