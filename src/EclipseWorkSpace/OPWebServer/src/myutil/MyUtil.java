package myutil;


public class MyUtil {
	public static boolean isEmpty(String string) {
		boolean flag = false;
		if (string == null || string == "") {
			flag = true;
		}
		return flag;
	}
	
	public static void dbg(String msg) {
		System.out.println(msg);
	}
	
	public static void dbgg(String msg) {
		//Do nothing just disable dbg by a additonal 'g'
		
	}


}
