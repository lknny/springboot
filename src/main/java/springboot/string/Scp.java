package springboot.string;

/**
 * Created by ${ lknny@163.com } on 2019/8/6.
 */
public class Scp {
	public static void main(String[] args) {

		for (int i = 1; i <46 ; i++) {
			System.out.println("scp -P 9001 teachers@192.168.225.185:/export/home/teachers/20190805/Linux_shell/*.txt /export/home/stu"+i+"/20190805/Linux_shell");
		}

	}
}
