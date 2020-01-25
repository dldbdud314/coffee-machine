//MoneyManager.java

public class MoneyManager {
	//total�� �� �߰��ϴ� �κ�: ���� �۵� ��(��� ���) �߰�
	
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
			else { //���������� �Է��� ���, ������ �Ŵ����� ���� ����
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
