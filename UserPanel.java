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
	
	//�� ��
	int cash;
	int selectionPrice;
	int selection;
	
	Control ctr;
	
	public UserPanel() {
		//gui
		contentPane=getContentPane();
	    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
	    contentPane.setBackground(new Color(180,150,120));
	    this.setTitle("Ŀ�����Ǳ�");
	      
	    coffeeImg[0] = new ImageIcon("americano.PNG");
	    coffeeImg[1] = new ImageIcon("cappuchino.PNG");
	    coffeeImg[2] = new ImageIcon("latte.PNG");
	    coffeeImg[3] = new ImageIcon("mocca.PNG");
	      
	    selectionBtn[0] = new JButton("�Ƹ޸�ī��(��500)",coffeeImg[0]);
	    selectionBtn[1] = new JButton("īǪġ��(��1000)",coffeeImg[1]);
	    selectionBtn[2] = new JButton("ī�� ��(��1000)",coffeeImg[2]);  
	    selectionBtn[3] = new JButton("ī�� ����(��1500)",coffeeImg[3]);
	    for(int i = 0; i < 4; i++)
	    	selectionBtn[i].setEnabled(false);
	      
	    //�޴���
	    TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(255 ,235 ,205)),"[Tea Time]"); 
	    border.setTitleFont(new Font("����",Font.BOLD, 35));
	    border.setTitleColor(new Color(255 ,235 ,205));
	    border.setTitleJustification(TitledBorder.CENTER);
	    for(int i = 0; i < 4; i++) { 
	       selectionBtn[i].setFont(new Font("����",Font.BOLD, 20));
	       selectionBtn[i].addActionListener(new CoffeeBtnActionListener());
	    }
	      
	    JPanel cashPanel = new JPanel();
	    cashPanel.setBackground(new Color(180,150,120));
	    slot = new JTextField("���Աݾ��� �Է��ϼ���", 20);
	    slot.setFont(new Font("����",Font.PLAIN, 25));
	    slot.addMouseListener(new MouseAdapter() {
	    @Override
		public void mouseClicked(MouseEvent e) {
			JTextField text = (JTextField)e.getSource();
				text.setText("");
				for(int i = 0; i < 4; i++)
			    	selectionBtn[i].setEnabled(true);
			} 
	      });
	      
	      inputBtn = new JButton("����");
	      inputBtn.setFont(new Font("����",Font.BOLD, 25));
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
	      
	      //������
	      JPanel infoPanel = new JPanel();
	      
	      Box infoBox = Box.createVerticalBox();
	      TitledBorder bord =  BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(120 ,60 ,20)),"[OUTPUT]"); 
	      bord.setTitleFont(new Font("����",Font.BOLD ,25));
	      bord.setTitleColor(new Color(120 ,60 ,20));
	      bord.setTitleJustification(TitledBorder.CENTER);
	      
	      JLabel info = new JLabel("���۵���>> 1.�ݾ� �Է� -> 2.���� ���� -> 3.[����]��ư");
	      info.setFont(new Font("����",Font.PLAIN, 25));
	      info.setAlignmentX(CENTER_ALIGNMENT);
	      
	      activityLog = new JTextArea("------------------------[�۵���]----------------------", 11, 40);
	      activityLog.setEditable(false);
	      activityLog.setFont(new Font("����",Font.BOLD, 25));
	      activityLog.setLineWrap(true);
	      
	      JScrollPane scroll =new JScrollPane(activityLog);
	      int pos = activityLog.getText().length();
	      activityLog.setCaretPosition(pos);
	      activityLog.requestFocus();
	      
	      fillStockBtn = new JButton("[��� ����]");
	      fillStockBtn.setFont(new Font("����",Font.ITALIC, 20));
	      fillStockBtn.setEnabled(false);
	      fillStockBtn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String pw = JOptionPane.showInputDialog("�н����带 �Է��ϼ���");
	    			 if(pw.equals("123456789")){
	    				  StockDialog sd = new StockDialog(frame, "��� ä�� �ֱ�");
	    				  sd.setVisible(true);
	    			 }
	    			 else { //test �غ��� �ٽ� ����
	    				 JOptionPane.showMessageDialog(frame, "�ٽ� �Է��Ͻʽÿ�.");
	    			 }
	    		}
	    	  });
	      
	      withdrawBtn = new JButton("[������ ȸ����]");
	      withdrawBtn.setFont(new Font("����",Font.ITALIC, 20));
	      withdrawBtn.setEnabled(true);
	      withdrawBtn.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent arg0) {
		    		String pw = JOptionPane.showInputDialog("�н����带 �Է��ϼ���");
		    			 if(pw.equals("123456789")){
		    				  MoneyDialog md = new MoneyDialog(frame, "�ܰ� ȸ���ϱ�");
		    				  md.setVisible(true);
		    			 }
		    			 else { //test �غ��� �ٽ� ����
		    				 JOptionPane.showMessageDialog(frame, "�ٽ� �Է��Ͻʽÿ�.");
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
	
	//Ŀ�� �޴� ��ư�� Action Listener. ��ư Ŭ�� �� index ����.
	   private class CoffeeBtnActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				switch(cmd) {
					case "�Ƹ޸�ī��(��500)":
						index = 0;
						break;
					case "īǪġ��(��1000)":
						index = 1;
						break;
					case "ī�� ��(��1000)":
						index = 2;
						break;
					case "ī�� ����(��1500)":
						index = 3;
						break;
				}
			}
		}
	   private class MoneyDialog extends JDialog{
			private JLabel moneyLabel = new JLabel("ȸ���� �׼��� �Է��Ͻÿ�.");
			private JTextField moneyField = new JTextField(10);
			private JButton confirmBtn = new JButton("����");
			
			public MoneyDialog(JFrame frame, String title) {
				super(frame, title, true);
				setLayout(new FlowLayout());
				moneyLabel.setFont(new Font("����", Font.PLAIN, 20));
				add(moneyLabel);
				moneyField.setText(Integer.toString(MoneyManager.total));
				moneyField.setFont(new Font("����", Font.PLAIN, 20));
				add(moneyField);
				add(confirmBtn);
				setSize(300, 200);
				
				confirmBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int x = Integer.parseInt(moneyField.getText());
						
						MoneyManager.updateTotal(x);
						JOptionPane.showMessageDialog(frame, "�ܰ�: "+Integer.toString(MoneyManager.total));
						setVisible(false);
						
						activityLog.append("�ܰ� ȸ���Ǿ����ϴ�.\n");
						activityLog.append("-----------------------[�۵���]---------------------");
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
			private String ingNames[] = {"�� ���� ", "�� �� ", "���� �� ", "Ŀ�� �� ", "ũ�� �� "};
			private JTextField ingField[] = new JTextField[5];
			private JButton confirmBtn = new JButton("����");
			
			public StockDialog(JFrame frame, String title) {
				super(frame, title, true);
				setLayout(new GridLayout(6, 1, 5, 5));
				for(int i = 0; i < ingLabel.length; i++) {
					ingLabel[i]= new JLabel(ingNames[i]);
					ingLabel[i].setFont(new Font("����", Font.PLAIN, 20));
				}
				for(int j = 0; j < ingField.length; j++) {
					ingField[j] = new JTextField(8);
					ingField[j].setFont(new Font("����", Font.PLAIN, 20));
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
						
						activityLog.append("��� ä�������ϴ�.\n");
						activityLog.append("-----------------------[�۵���]---------------------\n");
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
					activityLog.append(">> �Ƹ޸�ī�븦 ����, "+Integer.toString(this.cash)+"���� �Է��ϼ̽��ϴ�.\n");
					break;
				case 1:
					selectionPrice = 1000;
					activityLog.append(">> īǪġ�븦 ����, "+Integer.toString(this.cash)+"���� �Է��ϼ̽��ϴ�.\n");
					break;
				case 2:
					selectionPrice = 1000;
					activityLog.append(">> ī��󶼸� ����, "+Integer.toString(this.cash)+"���� �Է��ϼ̽��ϴ�.\n");
					break;
				case 3:
					selectionPrice = 1500;
					activityLog.append(">> ī������� ����, "+Integer.toString(this.cash)+"���� �Է��ϼ̽��ϴ�.\n");
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
			activityLog.append("���Ǳ��� ��� �����Ͽ� �Է��� ���� ��ȯ�˴ϴ�.\n");
			activityLog.append("-----------------------[�۵�����]---------------------\n");
				
			fillStockBtn.setEnabled(true);
		}
		
		/*public void returnCoffee(int selection) {
			switch(selection) {
				case 0:
					activityLog.append(">> �Ƹ޸�ī�� ���Խ��ϴ�.");
					break;
				case 1:
					activityLog.append(">> īǪġ�� ���Խ��ϴ�.");
					break;
				case 2:
					activityLog.append(">> ī�� �� ���Խ��ϴ�.");
					break;
				case 3:
					activityLog.append(">> ī�� ��ī ���Խ��ϴ�.");
					break;	
			}
		}
		public void returnChange() {
			slot.setText(Integer.toString());
			activityLog.append("�ܵ��� "+Integer.toString(change)+"�Դϴ�.");
			
			total += selectionPrice;
		}*/

	public static void main(String[] args) {
		UserPanel u = new UserPanel();
		/*u.setSize(1000,1500);
		// Frame ������ ����
		u.setResizable(false);
		// Frame ���� �ֱ�
		u.setVisible(true);*/
	}
}
