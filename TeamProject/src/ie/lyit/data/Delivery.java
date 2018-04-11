package ie.lyit.data;
public class Delivery
{
   private String deliveryDate;
   private String deliveryTime;
   private String deliveryDets;
   
   public Delivery(/*String deliveryDate, String deliveryTime,*/ String deliveryDets)
   {//, String password, int orders) {
		super();
		//this.deliveryDate = deliveryDate;
		//this.deliveryTime = deliveryTime;
		this.deliveryDets = deliveryDets;
		//this.password = password;
		//this.orders = orders;
	}

   public void setDeliveryDate(String delDateIn)
   {
      deliveryDate = delDateIn;
   }
   public void setDeliveryTime(String delTimeIn)
   {
      deliveryTime = delTimeIn;
   }
   public String getDeliveryTime()
   {
      return deliveryTime;
   }
   public String getDeliveryDate()
   {
      return deliveryDate;
   }
   public void setDeliveryDetails(String detsIn)
   {
      deliveryDets = detsIn;
   }
   public  String getDeliveryDetails()
   {
      return "" + deliveryDate + " " + deliveryTime;
   }
   public String getAllDetails()
   {
      return deliveryDets;
   }

}