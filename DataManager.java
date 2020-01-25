//DataManager.java

public class DataManager {
	int selectionData;
	Inventory i;
	
	public DataManager(Inventory i) {
		this.i = i;
	}
	
	public void inputData(int selectionData) { //selectionData에 따라 필요한 재료 확인
		switch(selectionData) {
			case 0:
				i.setInventoryState(1, 800, 0, 200, 0);
				break;
			case 1:
				i.setInventoryState(1, 0, 200, 200, 600);
				break;
			case 2:
				i.setInventoryState(1, 0, 600, 200, 200);
				break;
			case 3:
				i.setInventoryState(1, 0, 500, 500, 0);
				break;
		}
	}
}
