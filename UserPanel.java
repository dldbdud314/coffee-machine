//UserPanel.java
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class UserPanel extends JFrame{
	//gui
	JFrame frame = this;
	Container contentPane;
	int index = -1;
	ImageIcon[] coffeeImg= new ImageIcon[4];
	JButton[] selectionBtn = new JButton[4];
	JButton inputBtn;
	JButton fillStockBtn, withdrawBtn;
	JTextField slot;
	JTextArea activityLog;
	
	//그 외
	int cash;
	int selectionPrice;
	int selection;
	
	Control ctr;
	
	public UserPanel() {
		//gui
		contentPane=getContentPane();
	    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
	    contentPane.setBackground(new Color(180,150,120));
	    this.setTitle("커피자판기");
	      
	    coffeeImg[0] = new ImageIcon("americano.PNG");
	    coffeeImg[1] = new ImageIcon("cappuchino.PNG");
	    coffeeImg[2] = new ImageIcon("latte.PNG");
	    coffeeImg[3] = new ImageIcon("mocca.PNG");
	      
	    selectionBtn[0] = new JButton("아메리카노(￦500)",coffeeImg[0]);
	    selectionBtn[1] = new JButton("카푸치노(￦1000)",coffeeImg[1]);
	    selectionBtn[2] = new JButton("카페 라떼(￦1000)",coffeeImg[2]);  
	    selectionBtn[3] = new JButton("카페 오레(￦1500)",coffeeImg[3]);
	    for(int i = 0; i < 4; i++)
	    	selectionBtn[i].setEnabled(false);
	      
	    //메뉴판
	    TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(255 ,235 ,205)),"[Tea Time]"); 
	    border.setTitleFont(new Font("돋움",Font.BOLD, 35));
	    border.setTitleColor(new Color(255 ,235 ,205));
	    border.setTitleJustification(TitledBorder.CENTER);
	    for(int i = 0; i < 4; i++) { 
	       selectionBtn[i].setFont(new Font("돋움",Font.BOLD, 20));
	       selectionBtn[i].addActionListener(new CoffeeBtnActionListener());
	    }
	      
	    JPanel cashPanel = new JPanel();
	    cashPanel.setBackground(new Color(180,150,120));
	    slot = new JTextField("투입금액을 입력하세요", 20);
	    slot.setFont(new Font("돋움",Font.PLAIN, 25));
	    slot.addMouseListener(new MouseAdapter() {
	    @Override
		public void mouseClicked(MouseEvent e) {
			JTextField text = (JTextField)e.getSource();
				text.setText("");
				for(int i = 0; i < 4; i++)
			    	selectionBtn[i].setEnabled(true);
			} 
	      });
	      
	      inputBtn = new JButton("선택");
	      inputBtn.setFont(new Font("돋움",Font.BOLD, 25));
	      inputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(slot.getText());
				
				accept(index, x);
			}
	      });
	      
	      JPanel btnPanel = new JPanel(new GridLayout(2, 2, 10, 10));
	      btnPanel.setBackground(new Color(180,150,120));
	      for(int i=0;i<2;i++) 
	         btnPanel.add(selectionBtn[i]);
	      for(int i=2;i<4;i++) 
	         btnPanel.add(selectionBtn[i]);
	      
	      cashPanel.add(slot);
	      cashPanel.add(inputBtn);
	      ((JComponent) getContentPane()).setBorder(border);
	      
	      //설명판
	      JPanel infoPanel = new JPanel();
	      
	      Box infoBox = Box.createVerticalBox();
	      TitledBorder bord =  BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(120 ,60 ,20)),"[OUTPUT]"); 
	      bord.setTitleFont(new Font("돋움",Font.BOLD ,25));
	      bord.setTitleColor(new Color(120 ,60 ,20));
	      bord.setTitleJustification(TitledBorder.CENTER);
	      
	      JLabel info = new JLabel("※작동법>> 1.금액 입력 -> 2.음료 선택 -> 3.[선택]버튼");
	      info.setFont(new Font("돋움",Font.PLAIN, 25));
	      info.setAlignmentX(CENTER_ALIGNMENT);
	      
	      activityLog = new JTextArea("------------------------[작동중]----------------------", 11, 40);
	      activityLog.setEditable(false);
	      activityLog.setFont(new Font("돋움",Font.BOLD, 25));
	      activityLog.setLineWrap(true);
	      
	      JScrollPane scroll =new JScrollPane(activityLog);
	      int pos = activityLog.getText().length();
	      activityLog.setCaretPosition(pos);
	      activityLog.requestFocus();
	      
	      fillStockBtn = new JButton("[재고 관리]");
	      fillStockBtn.setFont(new Font("돋움",Font.ITALIC, 20));
	      fillStockBtn.setEnabled(false);
	      fillStockBtn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String pw = JOptionPane.showInputDialog("패스워드를 입력하세요");
	    			 if(pw.equals("123456789")){
	    				  StockDialog sd = new StockDialog(frame, "재고 채워 넣기");
	    				  sd.setVisible(true);
	    			 }
	    			 else { //test 해보고 다시 오기
	    				 JOptionPane.showMessageDialog(frame, "다시 입력하십시오.");
	    			 }
	    		}
	    	  });
	      
	      withdrawBtn = new JButton("[관리자 회수용]");
	      withdrawBtn.setFont(new Font("돋움",Font.ITALIC, 20));
	      withdrawBtn.setEnabled(true);
	      withdrawBtn.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent arg0) {
		    		String pw = JOptionPane.showInputDialog("패스워드를 입력하세요");
		    			 if(pw.equals("123456789")){
		    				  MoneyDialog md = new MoneyDialog(frame, "잔고 회수하기");
		    				  md.setVisible(true);
		    			 }
		    			 else { //test 해보고 다시 오기
		    				 JOptionPane.showMessageDialog(frame, "다시 입력하십시오.");
		    			 }
		    		}
		    	  });
	      
	      //cashPanel.add(btnPanel);
	      infoBox.add(Box.createVerticalStrut(20));
	      infoBox.add(info);
	      infoBox.add(Box.createVerticalStrut(25));
	      infoBox.add(scroll);
	      infoBox.add(Box.createVerticalStrut(25));
	      infoBox.add(fillStockBtn);
	      infoBox.add(withdrawBtn);
	      fillStockBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	      withdrawBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	      infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	      infoPanel.add(infoBox);
	      infoPanel.setBorder(bord);
	      
	      contentPane.add(btnPanel);
	      contentPane.add(cashPanel);
	      contentPane.add(infoPanel);
	      
		  setSize(1000, 1500);
		  setResizable(false);
		  setVisible(true);
	}
	
	//커피 메뉴 버튼의 Action Listener. 버튼 클릭 시 index 저장.
	   private class CoffeeBtnActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
					case "아메리카노(￦500)":
						index = 0;
						break;
					case "카푸치노(￦1000)":
						index = 1;
						break;
					case "카페 라떼(￦1000)":
						index = 2;
						break;
					case "카페 오레(￦1500)":
						index = 3;
						break;
				}
			}
		}
	   private class MoneyDialog extends JDialog{
			private JLabel moneyLabel = new JLabel("회수할 액수를 입력하시오.");
			private JTextField moneyField = new JTextField(10);
			private JButton confirmBtn = new JButton("변경");
			
			public MoneyDialog(JFrame frame, String title) {
				super(frame, title, true);
				setLayout(new FlowLayout());
				moneyLabel.setFont(new Font("돋움", Font.PLAIN, 20));
				add(moneyLabel);
				moneyField.setText(Integer.toString(MoneyManager.total));
				moneyField.setFont(new Font("돋움", Font.PLAIN, 20));
				add(moneyField);
				add(confirmBtn);
				setSize(300, 200);
				
				confirmBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int x = Integer.parseInt(moneyField.getText());
						
						MoneyManager.updateTotal(x);
						JOptionPane.showMessageDialog(frame, "잔고: "+Integer.toString(MoneyManager.total));
						setVisible(false);
						
						activityLog.append("잔고가 회수되었습니다.\n");
						activityLog.append("-----------------------[작동중]---------------------");
						for(int i = 0; i < 4; i++)
							selectionBtn[i].setEnabled(true);
						slot.setEnabled(true);
						inputBtn.setEnabled(true);
					}
				});
			}
		}
	   
	   class StockDialog extends JDialog{
			private JLabel ingLabel[] = new JLabel[5];
			private String ingNames[] = {"컵 개수 ", "물 양 ", "우유 양 ", "커피 양 ", "크림 양 "};
			private JTextField ingField[] = new JTextField[5];
			private JButton confirmBtn = new JButton("변경");
			
			public StockDialog(JFrame frame, String title) {
				super(frame, title, true);
				setLayout(new GridLayout(6, 1, 5, 5));
				for(int i = 0; i < ingLabel.length; i++) {
					ingLabel[i]= new JLabel(ingNames[i]);
					ingLabel[i].setFont(new Font("돋움", Font.PLAIN, 20));
				}
				for(int j = 0; j < ingField.length; j++) {
					ingField[j] = new JTextField(8);
					ingField[j].setFont(new Font("돋움", Font.PLAIN, 20));
				}
				
				for(int k = 0; k < ingLabel.length; k++) {
					add(ingLabel[k]);
					ingField[k].setText(Integer.toString(Inventory.getIng(k)));
					add(ingField[k]);
				}
				add(confirmBtn);
				setSize(600, 450);
				
				confirmBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Inventory.updateInventory(Integer.parseInt(ingField[0].getText()), 
												  Integer.parseInt(ingField[1].getText()), 
												  Integer.parseInt(ingField[2].getText()), 
												  Integer.parseInt(ingField[3].getText()), 
												  Integer.parseInt(ingField[4].getText()));
						setVisible(false);
						
						activityLog.append("재고가 채워졌습니다.\n");
						activityLog.append("-----------------------[작동중]---------------------\n");
						for(int i = 0; i < 4; i++)
							selectionBtn[i].setEnabled(true);
						slot.setEnabled(true);
						inputBtn.setEnabled(true);
						
						fillStockBtn.setEnabled(false);
					}
				});
			}
		}
	   public void accept(int index, int cash) {
			this.selection = index;
			this.cash = cash;
			
			switch(index) {
				case 0:
					selectionPrice = 500;
					activityLog.append(">> 아메리카노를 선택, "+Integer.toString(this.cash)+"원을 입력하셨습니다.\n");
					break;
				case 1:
					selectionPrice = 1000;
					activityLog.append(">> 카푸치노를 선택, "+Integer.toString(this.cash)+"원을 입력하셨습니다.\n");
					break;
				case 2:
					selectionPrice = 1000;
					activityLog.append(">> 카페라떼를 선택, "+Integer.toString(this.cash)+"원을 입력하셨습니다.\n");
					break;
				case 3:
					selectionPrice = 1500;
					activityLog.append(">> 카페오레를 선택, "+Integer.toString(this.cash)+"원을 입력하셨습니다.\n");
					break;	
			}
			System.out.println("1");
			
			ctr = new Control(this);
			ctr.inputInfo(selection, cash, selectionPrice);
		}
	   
	   public void disableOtherBtn() {
			for(int i = 0; i < 4; i++)
				selectionBtn[i].setEnabled(false);
			slot.setEnabled(false);
			inputBtn.setEnabled(false);
			activityLog.append("자판기의 재고가 부족하여 입력한 돈이 반환됩니다.\n");
			activityLog.append("-----------------------[작동중지]---------------------\n");
				
			fillStockBtn.setEnabled(true);
		}
		
		/*public void returnCoffee(int selection) {
			switch(selection) {
				case 0:
					activityLog.append(">> 아메리카노 나왔습니다.");
					break;
				case 1:
					activityLog.append(">> 카푸치노 나왔습니다.");
					break;
				case 2:
					activityLog.append(">> 카페 라떼 나왔습니다.");
					break;
				case 3:
					activityLog.append(">> 카페 모카 나왔습니다.");
					break;	
			}
		}
		public void returnChange() {
			slot.setText(Integer.toString());
			activityLog.append("잔돈은 "+Integer.toString(change)+"입니다.");
			
			total += selectionPrice;
		}*/

	public static void main(String[] args) {
		UserPanel u = new UserPanel();
		/*u.setSize(1000,1500);
		// Frame 사이즈 고정
		u.setResizable(false);
		// Frame 보여 주기
		u.setVisible(true);*/
	}
}
