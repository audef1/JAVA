package petShop;

public abstract class APet<F extends Food> implements Pet<F>{
	private final String name;
	private boolean isSick;
	
	public APet(String name) throws WrongNameEx{
		if (name == null) throw new WrongNameEx();
		this.name = name;
	}

	@Override
	public boolean isSick() {
		return isSick;
	}

	protected void setSick(boolean isSick){
		this.isSick = isSick;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void heal() {
		this.isSick = false;
		System.out.println(name + " wurde geheilt.");
	}

}