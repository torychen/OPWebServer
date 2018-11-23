package product.json;

import net.sf.json.JSONObject;

import com.google.gson.JsonObject;

public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static String createJsonString(String key, Object object) {
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put(key, object);
		return jsonObject1.toString();
	}

}
