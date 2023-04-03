package Model;

public class IngredientVO {
	private String meatpatty;
	private String shrimppatty;
	private String chickenpatty;
	private String bacon;
	private String tomato;
	private String pickle;
	private String cabbage;
	private String bread;
	private String mustard;
	private String ketchup;
	private String cheese;
	private String any;
	
	public IngredientVO(String any) {
		this.any = any;
	}
	public IngredientVO(String meatpatty, String shrimppatty, String chickenpatty, String bacon, String tomato,
			String pickle, String cabbage, String bread, String mustard, String ketchup, String cheese) {
		super();
		this.meatpatty = meatpatty;
		this.shrimppatty = shrimppatty;
		this.chickenpatty = chickenpatty;
		this.bacon = bacon;
		this.tomato = tomato;
		this.pickle = pickle;
		this.cabbage = cabbage;
		this.bread = bread;
		this.mustard = mustard;
		this.ketchup = ketchup;
		this.cheese = cheese;
	}
	public String getAny() {
		return any;
	}
	public String getMeatpatty() {
		return meatpatty;
	}
	public void setMeatpatty(String meatpatty) {
		this.meatpatty = meatpatty;
	}
	public String getShrimppatty() {
		return shrimppatty;
	}
	public void setShrimppatty(String shrimppatty) {
		this.shrimppatty = shrimppatty;
	}
	public String getChickenpatty() {
		return chickenpatty;
	}
	public void setChickenpatty(String chickenpatty) {
		this.chickenpatty = chickenpatty;
	}
	public String getBacon() {
		return bacon;
	}
	public void setBacon(String bacon) {
		this.bacon = bacon;
	}
	public String getTomato() {
		return tomato;
	}
	public void setTomato(String tomato) {
		this.tomato = tomato;
	}
	public String getPickle() {
		return pickle;
	}
	public void setPickle(String pickle) {
		this.pickle = pickle;
	}
	public String getCabbage() {
		return cabbage;
	}
	public void setCabbage(String cabbage) {
		this.cabbage = cabbage;
	}
	public String getBread() {
		return bread;
	}
	public void setBread(String bread) {
		this.bread = bread;
	}
	public String getMustard() {
		return mustard;
	}
	public void setMustard(String mustard) {
		this.mustard = mustard;
	}
	public String getKetchup() {
		return ketchup;
	}
	public void setKetchup(String ketchup) {
		this.ketchup = ketchup;
	}
	public String getCheese() {
		return cheese;
	}
	public void setCheese(String cheese) {
		this.cheese = cheese;
	}
	
	
	
	
}
