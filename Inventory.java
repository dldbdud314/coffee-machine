//Inventory.java

public class Inventory {
	static int numOfCups = 100, 
			amountOfWater = 1000, 
			amountOfMilk = 1000, 
			amountOfCoffee = 1000, 
			amountOfCream = 1000;
	int cup = 0;
	int water = 0;
	int milk = 0; 
	int coffee = 0;
	int cream = 0;
	boolean inventoryState = true;
	
	public Inventory() { }
	
	public static int getIng(int k) {
		if(k == 0)
			return numOfCups;
		else if(k == 1)
			return amountOfWater;
		else if(k == 2)
			return amountOfMilk;
		else if(k == 3)
			return amountOfCoffee;
		else
			return amountOfCream;
	}
	
	public boolean getCheckState() {
		return inventoryState;
	}
	
	public int getCup() {
		return cup;
	}
	public int getWater() {
		return water;
	}
	public int getMilk() {
		return milk;
	}
	public int getCoffee() {
		return coffee;
	}
	public int getCream() {
		return cream;
	}
	
	public void setInventoryState(int cup, int water, int milk, int coffee, int cream) {
		this.cup = cup;
		this.water = water;
		this.milk = milk;
		this.coffee = coffee;
		this.cream = cream;
		if(numOfCups-cup>=0 && amountOfWater-water>=0 && amountOfMilk-milk>=0 && //모든 필요한 재료가 존재하면 
				amountOfCoffee-coffee>=0 && amountOfCream-cream>=0) {//어떤 재료들이 필요 없는 경우는?
			inventoryState = true;
		}
		else {
			inventoryState = false;
		}
	}
	
	static public void updateInventory(int cup, int water, int milk, int coffee, int cream) {
		numOfCups+=cup;
		amountOfWater+=water;
		amountOfMilk+=milk;
		amountOfCoffee+=coffee;
		amountOfCream+=cream;
	}
}
