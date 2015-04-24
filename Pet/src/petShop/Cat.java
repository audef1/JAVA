package petShop;

public class Cat extends APet<CatFood>{

	public Cat(String name) throws WrongNameEx{
		super(name);
	}

	@Override
	public void stroke() {
		// TODO Auto-generated method stub
	}

	@Override
	public void feed(CatFood food) throws WrongFoodEx,SickEx{
		if (food == null){
			super.setSick(true);
			throw new WrongFoodEx();
		}
		if (super.isSick()){
			throw new SickEx();
		}
	}
	
}
