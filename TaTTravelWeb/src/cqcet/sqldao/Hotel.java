package cqcet.sqldao;

public class Hotel {

	private int id;
	private String name;
	private String content;
	private float price;
	private String telephone;
    private String image;
    private int cityid;
    private int typeid;
	public Hotel(int id, String name, String content, float price, String telephone, String image, int cityid,
			int typeid) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.price = price;
		this.telephone = telephone;
		this.image = image;
		this.cityid = cityid;
		this.typeid = typeid;
	}
	public Hotel() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
    
    
}
