package Model;

public class BuyNow extends Product{
	
	private int orderid;
	private int uid;
	private int quantity;
	private String date;
	public BuyNow(int id, String name, String category, double price, String image, int orderid, int uid, int quantity,
			String date) {
		super(id, name, category, price, image);
		this.orderid = orderid;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}
	public BuyNow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BuyNow [orderid=" + orderid + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
	
	
	

}
