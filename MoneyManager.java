//MoneyManager.java

public class MoneyManager {
	//total에 돈 추가하는 부분: 정상 작동 시(재고 충분) 추가
	
		int selectionData, cash;
		int selectionPrice;
		int change;
		boolean changeState = true;
		static int total = 1000;
		
		public MoneyManager() { }
		
		public void inputMoney(int selectionData, int cash, int selectionPrice) {
			this.selectionData = selectionData;
			this.selectionPrice = selectionPrice; 
			this.cash = cash;
			
			setChangeState(this.cash, this.selectionPrice);
		}
		
		public boolean getCheckState() {
			return changeState;
		}
		
		private void setChangeState(int cash, int selectionPrice) {
			if(cash<selectionPrice)
				changeState = true;
			else { //정상적으로 입력한 경우, 데이터 매니저로 정보 전달
				this.change = cash-selectionPrice;
				changeState = false;
			}
		}
		
		static public void updateTotal(int withdrawal) {
			total -= withdrawal; 
		}
		
		public int getChange() {
			return change;
		}
}
