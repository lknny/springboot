package springboot.string;

/**
 * Created by ${ lknny@163.com } on 2018/5/19.
 */
public class solution {
	public int totalHammingDistance(int[] nums) {
		int totalHammingDistance = 0;
		for (int i = 0; i < nums.length; i++) {
			String iSource = transformBinary(nums[i]);
			for (int j = i + 1; j < nums.length; j++) {
				if (i == j) {
					continue;
				}
				int flag = 0;
				String jSource = transformBinary(nums[j]);
				for (int k = 0; k < jSource.length(); k++) {
					try {
						if (iSource.charAt(k) != jSource.charAt(k)) {
							flag++;
						}
					} catch (Exception e) {
						System.out.println();
					}

				}
				System.out.println(iSource + "," + jSource + "," + flag);
				totalHammingDistance += flag;
			}
		}
		return totalHammingDistance;
	}

	public String transformBinary(int num) {
		int fullSize = 10 ^ 8;
		String sourceS = java.lang.Integer.toBinaryString(num);
		StringBuffer sourceSB = new StringBuffer(sourceS);
		if (sourceSB.length() < fullSize) {
			for (int addnum = fullSize - sourceSB.length(); addnum > 0; addnum--) {
				sourceSB.insert(0, "0");
			}
		}
		return sourceSB.toString();
	}

	public static void main(String[] args) {
		int[] nums = new int[]{4, 14, 2};
		new solution().totalHammingDistance(nums);
	}

}
