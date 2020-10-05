package com.AppliedArgonautics.comfort;
import java.io.Serializable;

 public class MenuItem implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override 
	public String toString(){
		return this.name; 
	 }
	
	 public String getType() {
		return type;
	}
	 
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	protected String type;
	protected String name;
	protected String description;
	protected String photoFile;
	protected String price;
	protected String notes;
	protected float rating;
	protected boolean hasRating;

	public MenuItem(String menuType, String menuName, String menuDescription, String menuPhotoFile, String menuPrice){
		this.type = menuType;
		this.name = menuName;
		this.description = menuDescription;
		this.photoFile = menuPhotoFile;
		this.price = menuPrice;
	}
	public MenuItem(){
		super();
	}
	
 }
	
	
 class Liquor extends MenuItem{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		public String toString(){
			String desc;
			if(isCocktail()){
				desc = distiller;
			}
			if (!isCocktail()){
				desc = distiller;
				if (bottling != null){
					desc += (" " + bottling);
					}
				if (age != null){
					desc += (": " + age);
				}
			}
			else {
				desc = (distiller);
			}
			return desc;
		}
	public String getAlertString(){
			String str;
			if(isCocktail()){
			str = ("Cocktail: " + distiller +
					"\nIngredients: " + bottling +
					"\nPrice: " + price);	
			}
			else{
			str = ("Distiller: " + distiller + 
				   "\nProof: " + proof);
			str += ("\nPrice: " + price);
			if (hasBottling){
				str += ("\nBottling: " + bottling);
				}
			if (age != null){
				str += ("\nAge: " + age);
			}
			}
			return str;
		}
		
		
		public String getDistiller() {
			return distiller;
		}
	
		public void setDistiller(String distiller) {
			this.distiller = distiller;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		public String getBottling() {
			return bottling;
		}
		public void setBottling(String bottling) {
			this.bottling = bottling;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getProof() {
			return proof;
		}
		public void setProof(String proof) {
			this.proof = proof;
		}
		public void setAge(String age){
			this.age = age;
		}
		
		public String getAge(){
			return age;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		
		
		private String price;
		private String distiller;
		private String place;
		private String bottling;
		private String category;
		private String proof;
		private String age;
		public boolean hasBottling;
		public boolean isCocktail(){
			boolean tmpBool;
			if (type.equalsIgnoreCase("cocktail")){
				tmpBool = true;
			}else{
				tmpBool = false;
			}
			return tmpBool;
		}
		public Liquor(String menuType, String menuName, String menuDescription,
				String menuPhotoFile, String menuPrice) {
			super(menuType, menuName, menuDescription, menuPhotoFile, menuPrice);
			
		}
		public Liquor(){
			
		}
		
	}
	
	
 class Beer extends MenuItem{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Beer(String menuType, String menuName, String menuDescription,
			String menuPhotoFile, String menuPrice) {
		super(menuType, menuName, menuDescription, menuPhotoFile, menuPrice);
		// TODO Auto-generated constructor stub
	}


		public String toString(){
			String desc;
			if (this.bottling != null){
				desc = (this.brewery + " " + this.bottling);
			}
			else {
				desc = (this.brewery);
			}
			return desc;
		}
		
		public String getAlertString(){
			String str = "Brewer: " + brewery;
			if (bottling!=null){
				str+=("\nBottling: " + bottling);
			}
			if (place!=null){
				str+=("\nPlace Brewed: " + place); 
			}
			str+=("\nPrice: " + price);
			return str;
		}
		
		
		public String getBrewery() {
			return brewery;
		}
		public void setBrewery(String brewery) {
			this.brewery = brewery;
		}
		public String getBottling() {
			return bottling;
		}
		public void setBottling(String bottling) {
			this.bottling = bottling;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		private String brewery;
		private String bottling;
		public boolean hasBottling = false;
		private String place;
		
		public Beer(){
			
		}
	}
	
	
	
		class Wine extends MenuItem{
		
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		public Wine(String menuType, String menuName,
					String menuDescription, String menuPhotoFile,
					String menuPrice) {
				super(menuType, menuName, menuDescription, menuPhotoFile, menuPrice);
				// TODO Auto-generated constructor stub
			}
		public Wine(){
			
		}
		public String toString(){
			String str = new String();
			if ((isOldWorld)&&(hasBottling)){
			str = (winery + " " + region + " " + bottling + " " + vintage);
			}
			else if ((isOldWorld)&&(!hasBottling)){
				str = (winery + " " + region + " " + vintage);
				}
			else if ((!isOldWorld) && (!hasBottling)){
			str = (winery + " " + varietals + " " + vintage);
			}
			else if ((!isOldWorld) && (hasBottling)){
			str = (winery + " " + bottling + " " + varietals + " " + vintage);
			}
			return str;
		}
		public String getAlertString(){
			String str = ("Winery: " + winery + 
				   "\nVarietals: " + varietals);
			if (hasBottling){
				str += ("\nBottling: " + bottling);
			}
			if (hasRegion){
				str += ("\nRegion: " + region);
			}
			str+= ("\nVintage: " + vintage +
				   "\nPrice: " + price +
				   "\nCountry: " + country);
			return str;
		}
		
		public String getWinery() {
			return winery;
		}
		public void setWinery(String winery) {
			this.winery = winery;
		}
		public String getVarietals() {
			return varietals;
		}
		public void setVarietals(String varietals) {
			this.varietals = varietals;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String place) {
			this.region = place;
		}
		public String getVintage() {
			return vintage;
		}
		public void setVintage(String vintage) {
			this.vintage = vintage;
		}
		public void setBottling(String bottling){
			this.bottling = bottling;
		}
		public String getBottling(){
			return bottling;
		}
		
		private String color;
		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}


		private String winery;
		private String varietals;
		private String country;
		private String region;
		private String vintage;
		private String bottling;
		public boolean isOldWorld;
		public boolean hasBottling;
		public boolean hasRegion;

}
	
