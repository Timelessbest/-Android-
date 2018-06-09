package cqcet.sqldao;

import java.io.Serializable;

public class DingDan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String content;
	private float price;
	private String telephone;
    private String image;
    private String username;
	public DingDan( String name, String content, float price, String telephone, String image, String username) {
		super();
		
		this.name = name;
		this.content = content;
		this.price = price;
		this.telephone = telephone;
		this.image = image;
		this.username = username;
	}
	public DingDan() {
		super();
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
