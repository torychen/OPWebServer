package product.utils;

import java.util.UUID;

public class UUIDtools {

	public UUIDtools() {
		// TODO Auto-generated constructor stub
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6);
	}
}
