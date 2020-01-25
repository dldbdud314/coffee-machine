//Control.java

public class Control implements CoffeeMachine{
	int selectionData, selectionPrice, cash;
	MoneyManager mm;
	Manufacture m;
	Inventory inv;
	DataManager dm;
	UserPanel u;
	
	public Control(UserPanel u) {
		this.u = u;
	}
	
	public void inputInfo(int selection, int cash, int selectionPrice) {
		selectionData = selection;
		this.cash = cash;
		this.selectionPrice = selectionPrice;
		
		mm = new MoneyManager();
		mm.inputMoney(this.selectionData, this.cash, this.selectionPrice);
		checkChange();
	}
	
	private void checkChange() {
		inv = new Inventory();
		dm = new DataManager(inv);
		if(mm.getCheckState()==true)
			u.activityLog.append("[오류]선택한 음료의 가격보다 적게 입력했습니다.\n입력한 돈이 반환됩니다.\n");
		else {
			dm.inputData(selectionData);
			checkInv();
		}
	}
	private void checkInv() {
		if(inv.getCheckState()==true) {
			m = new Manufacture(selectionData);
			m.makeCoffee(inv.getCup(), inv.getWater(), inv.getMilk(), inv.getCoffee(), inv.getCream());
			giveResults();
		}
		else {
			u.disableOtherBtn();
		}
	}
	private void giveResults() {
		returnCoffee();
		returnChange();
	}
	private void returnCoffee() {
		switch(selectionData) {
			case 0:
				u.activityLog.append(">> 아메리카노 나왔습니다.\n");
				break;
			case 1:
				u.activityLog.append(">> 카푸치노 나왔습니다.\n");
				break;
			case 2:
				u.activityLog.append(">> 카페 라떼 나왔습니다.\n");
				break;
			case 3:
				u.activityLog.append(">> 카페 오레 나왔습니다.\n");
				break;	
		}
	}
	private void returnChange() {	
		u.slot.setText(Integer.toString(mm.getChange()));
		u.activityLog.append("잔돈은 "+Integer.toString(mm.getChange())+"입니다.\n");
		
		MoneyManager.total += selectionPrice;
	}
}
 