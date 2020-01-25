//Manufacture.java

public class Manufacture {
	int selectionData;
	
	public Manufacture(int selectionData) {
		this.selectionData = selectionData;
	}
	public void makeCoffee(int cup, int water, int milk, int coffee, int cream) {
		getCup(cup);
		fill_ingredient(milk, coffee, cream);
		fill_water(water);
	}
	private void getCup(int cup) {
		Inventory.numOfCups-=cup;
	}
	private void fill_ingredient(int milk, int coffee, int cream) {
		Inventory.amountOfMilk-=milk;
		Inventory.amountOfCoffee-=coffee;
		Inventory.amountOfCream-=cream;
	}
	private void fill_water(int water) {
		Inventory.amountOfWater-=water;
	}
}
