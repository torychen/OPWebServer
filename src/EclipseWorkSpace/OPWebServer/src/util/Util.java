package util;


public class Util {
	public static boolean isEmpty(String string) {
		boolean flag = false;
		if (string == null || string == "") {
			flag = true;
		}
		return flag;
	}

}
