package product.service;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public boolean addProduct(List<Object> params);

	public List<Map<String, Object>> ListProduct(String proname, int start,int end);
   
	public List<Map<String, Object>> listProduct(String proname);
	   
	public int getItemCount();
    
	public  boolean deleteProduct(String[] ids);
	
	public Map<String, Object>  viewProduct(String proid);//查看详细信息
}
