package PetShop;

public interface Pet<F extends Food>{
	public void stroke();
	public boolean isSick();
	public String getName();
	public void heal();
	public void feed(F food) throws WrongFoodEx, SickEx;
}
