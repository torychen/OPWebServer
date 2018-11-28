package util;


public class Util {
	public static boolean isEmpty(String string) {
		boolean flag = false;
		if (string == null || string == "") {
			flag = true;
		}
		return flag;
	}
	
	public void dbg(String msg) {
		System.out.println(msg);
	}
	
	public void dbgg(String msg) {
		//Do nothing just disable dbg by a additonal 'g'
		
	}

}
