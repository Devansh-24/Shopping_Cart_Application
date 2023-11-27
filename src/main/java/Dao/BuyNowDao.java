package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.BuyNow;
import Model.Product;

public class BuyNowDao {

	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public BuyNowDao(Connection con) {
	this.con=con;
	}
  
	public boolean insertorder(BuyNow model)
	{
		 boolean result =false;
		 try {
			 query="insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			 ps=this.con.prepareStatement(query);
			 ps.setInt(1, model.getId());
			 ps.setInt(2, model.getUid());
			 ps.setInt(3, model.getQuantity());
			 ps.setString(4,model.getDate());
			 
			 ps.executeUpdate();
			 
			 result= true;
			 
			 
			 
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		return result;
		
	}
	
	public List<BuyNow> userorders(int id)
	
	{
		List<BuyNow> list=new ArrayList<>();
		try {
			 query="SELECT * FROM orders WHERE u_id = ? ORDER BY o_id DESC";  // inner join use 
			 ps=this.con.prepareStatement(query);
			 ps.setInt(1, id);
			 
			 
			rs = ps.executeQuery();
			 while(rs.next())
			 {
				 BuyNow order = new BuyNow();
				 ProductDao productDao = new ProductDao(this.con);
				 int pId =rs.getInt("p_id");
				 
				 Product product = productDao.getSingleProduct(pId);
				 order.setOrderid(rs.getInt("o_id"));
				 order.setId(pId);
				 order.setName(product.getName());
				 order.setCategory(product.getCategory());
				 order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
				 order.setQuantity(rs.getInt("o_quantity"));
				 order.setDate(rs.getString("o_date"));
				 
				 list.add(order);
	 
			 }
	

		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		
		return list;
	}
	
	public void cancelOrder(int id)
	{
		try {
			query="delete from orders where o_id=?";
			ps=this.con.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
