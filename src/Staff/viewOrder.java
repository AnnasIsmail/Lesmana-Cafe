package Staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.InputMapUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Cart.Cart;
import Income.addIncome;
import Inventory.Inventory;
import Login.Login;
import order.orderData;
import order.viewOrderData;
import order.viewOrderDataDetail;

public class viewOrder extends JInternalFrame{
JFrame frame = new JFrame();
	public viewOrder() {
		ui();
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	}
	
	JTable tabelTotal;
	JPanel sisiKiri2;
	JLabel orderNameNull, orderDateNull, orderStatusNull, TKorDINull, orderPaymentNull, staffNameNull;
	JLabel changeStatus;
	JButton changeStatusButton;
	JSpinner idSpinner = new JSpinner();
	 int buttonClick = 0;
	
	private void ui() {
		
		JPanel sisiAtas = new JPanel();
		sisiAtas.setBorder(new EmptyBorder(20,0,0,0));
		add(sisiAtas,BorderLayout.PAGE_START);
		
		JButton backToMainMenu = new JButton("< Back To Main Menu");
		backToMainMenu.setHorizontalAlignment(SwingConstants.LEFT);
		sisiAtas.add(backToMainMenu,BorderLayout.PAGE_START);
		
		JLabel listOrder = new JLabel("View Order");
		listOrder.setBorder(new EmptyBorder(0,380,0,480));
		listOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
		listOrder.setFont(new Font("Dialog", Font.BOLD, 28));
		sisiAtas.add(listOrder,BorderLayout.CENTER);
		
		JPanel sisiKiri = new JPanel();
		sisiKiri.setPreferredSize(new Dimension(350,100));
		sisiKiri.setBorder(new EmptyBorder(0,15,5,15));
		add(sisiKiri, BorderLayout.LINE_START);
		
		JPanel sisiKiri1 = new JPanel();
		sisiKiri1.setLayout(new BorderLayout());
		sisiKiri1.setBorder(new EmptyBorder(0,0,0,0));
		sisiKiri.add(sisiKiri1);
		
		JLabel enterYourName = new JLabel(" Enter Your Name");
		enterYourName.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKiri1.add(enterYourName,BorderLayout.PAGE_START);
		
		JTextField fieldEnterYourName = new JTextField(17);
		fieldEnterYourName.setFont(new Font("Calibri", Font.PLAIN, 17));
		sisiKiri1.add(fieldEnterYourName,BorderLayout.LINE_START);
		
		JButton buttonEnterYourName = new JButton("Search");
		buttonEnterYourName.setFont(new Font("Calibri", Font.PLAIN, 17));
		sisiKiri1.add(buttonEnterYourName,BorderLayout.CENTER);
		
		sisiKiri2 = new JPanel();
		sisiKiri2.setLayout(new BoxLayout(sisiKiri2, BoxLayout.Y_AXIS));
		sisiKiri2.setBorder(new EmptyBorder(5,5,5,5));
		
		JScrollPane scrollkiri2 = new JScrollPane(sisiKiri2);
		scrollkiri2.setPreferredSize(new Dimension(330,710));
		scrollkiri2.setBorder( new MatteBorder(1, 1, 1, 1, Color.black));
		sisiKiri.add(scrollkiri2,BorderLayout.PAGE_END);
		
		JPanel sisiKanan = new JPanel(new BorderLayout());
		add(sisiKanan,BorderLayout.CENTER);
		
		JPanel sisiKanan1 = new JPanel(new GridLayout(0,4));
		sisiKanan1.setBorder(new EmptyBorder(0,0,10,15));
		sisiKanan.add(sisiKanan1,BorderLayout.PAGE_START);
		
		JLabel orderName = new JLabel("Name Order :");
		orderName.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan1.add(orderName);
		
		orderNameNull = new JLabel("null");
		orderNameNull.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan1.add(orderNameNull);
		
		JButton allDataOrder = new JButton("All Data");
		allDataOrder.setEnabled(false);
		sisiKanan1.add(allDataOrder);
		
		JButton onGoing = new JButton("On Going");
		sisiKanan1.add(onGoing);
		
		JLabel orderDate = new JLabel("Date : ");
		orderDate.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan1.add(orderDate);
		
		orderDateNull = new JLabel("null");
		orderDateNull.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan1.add(orderDateNull);
		
		JButton unpaid = new JButton("Unpaid");
		sisiKanan1.add(unpaid);
		
		JButton done = new JButton("Paid");
		sisiKanan1.add(done);
		
		JLabel orderStatus = new JLabel("Status Order : ");
		orderStatus.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan1.add(orderStatus);
		
		orderStatusNull = new JLabel("null");
		orderStatusNull.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan1.add(orderStatusNull);
		
		changeStatus = new JLabel("");
		changeStatus.setFont(new Font("Calibri", Font.PLAIN,20));
		
		changeStatusButton = new JButton();
		
		JPanel sisiKanan2 = new JPanel(new GridLayout(1,0));
		sisiKanan2.setBorder(new EmptyBorder(0,0,0,10));
		sisiKanan.add(sisiKanan2,BorderLayout.CENTER);
		
		Object[] kolomOrder = {"No","Image","Menu Name","Price","Quantity","Total Price"};
		Object[][] dataOrder = {};
		
		DefaultTableModel deftabKanan  = new DefaultTableModel(dataOrder,kolomOrder){
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==1) return ImageIcon.class;
                return Object.class;
            } };
		JTable tabelKanan= new JTable(deftabKanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tabSortKanan = new TableRowSorter<>(deftabKanan);
		tabelKanan.setRowSorter(tabSortKanan);
		JScrollPane scrollTabKanan = new JScrollPane(tabelKanan);
		scrollTabKanan.setBorder( new MatteBorder(1, 1, 1, 1, Color.black));
		sisiKanan2.add(scrollTabKanan,BorderLayout.CENTER);
		
		tabelKanan.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		tabelKanan.setFont(new Font("Calibri", Font.PLAIN, 18));
		tabelKanan.setRowHeight(100);
		tabelKanan.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelKanan.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelKanan.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabelKanan.getColumnModel().getColumn(3).setPreferredWidth(70);
		tabelKanan.getColumnModel().getColumn(4).setPreferredWidth(50);
		tabelKanan.getColumnModel().getColumn(5).setPreferredWidth(70);
		
		JPanel sisiKanan3 =  new JPanel();
		sisiKanan3.setBorder(new EmptyBorder(0,0,0,11));
		sisiKanan.add(sisiKanan3,BorderLayout.AFTER_LAST_LINE);
		
		String[] kolomTotal = {"Total Price","Total"};
		Object[][] isiTableTotal = {{"Total Price ",0}};
		
		tabelTotal = new JTable(isiTableTotal,kolomTotal);
		tabelTotal.setPreferredSize(new Dimension(816,20));
		tabelTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		tabelTotal.setBackground(new Color(214,217,223));
		tabelTotal.setSelectionBackground(new Color(214,217,223));
		tabelTotal.setSelectionForeground(Color.BLACK);
		tabelTotal.setBorder( new MatteBorder(0, 1, 1, 1, Color.black));
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tabelTotal.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		sisiKanan3.add(tabelTotal,BorderLayout.CENTER);
		
		JPanel sisiKanan4 = new JPanel(new GridLayout(0,2,50,0));
		sisiKanan4.setBorder(new EmptyBorder(0,0,25,400));
		sisiKanan3.add(sisiKanan4,BorderLayout.LINE_END);
		
		JLabel TKorDI = new JLabel("Take Away / Dine In : ");
		TKorDI.setBorder(new EmptyBorder(3,0,0,0));
		TKorDI.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan4.add(TKorDI);
		
		TKorDINull = new JLabel("null");
		TKorDINull.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan4.add(TKorDINull);
		
		JLabel staffName = new JLabel("Staff Name : ");
		staffName.setBorder(new EmptyBorder(3,0,0,0));
		staffName.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan4.add(staffName);
		
		staffNameNull = new JLabel("null");
		staffNameNull.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan4.add(staffNameNull);
		
		JLabel orderPayment = new JLabel("Payment By : ");
		orderPayment.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan4.add(orderPayment);
		
		orderPaymentNull = new JLabel("null");
		orderPaymentNull.setFont(new Font("Calibri", Font.PLAIN, 20));
		sisiKanan4.add(orderPaymentNull);
		

		try {
			new viewOrderData();
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
				Object sementara[] = {orderName,i};
				viewOrderData.viewOrderSearch.add(sementara);
			}
		
		sisiKiri2.removeAll();
		JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
		for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
			
			int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
			hasilButton[i] = new JButton();
			
			if (viewOrderData.viewOrderSearch.size()>9) {
				hasilButton[i].setMinimumSize(new Dimension(300,75));
				hasilButton[i].setMaximumSize(new Dimension(300,75));
				hasilButton[i].setPreferredSize(new Dimension(300,75));
			}else {
				hasilButton[i].setMinimumSize(new Dimension(315,75));
				hasilButton[i].setMaximumSize(new Dimension(315,75));
				hasilButton[i].setPreferredSize(new Dimension(315,75));
			}
			sisiKiri2.add(hasilButton[i]);
			
			JLabel namaLabel = new JLabel();
			namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
			namaLabel.setBorder(new EmptyBorder(0,0,33,0));
			hasilButton[i].add(namaLabel);
			
			JLabel totalPriceLabel = new JLabel();
			totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
			totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
			hasilButton[i].add(totalPriceLabel);
			
			JLabel dateLabel = new JLabel();
			dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
			dateLabel.setBorder(new EmptyBorder(48,0,0,0));
			hasilButton[i].add(dateLabel);
			
			namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
			totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
			dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
			
			String status = (String) viewOrderData.viewOrderHeader.get(index)[6];
			
			
			buttonClick = i;
			
			hasilButton[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
					String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
					String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
					String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
					String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
					int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
					String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
					String staffName = (String) viewOrderData.viewOrderHeader.get(index)[7];

					deftabKanan.setRowCount(0);
					try {
						new viewOrderDataDetail(indexOrder);
					} catch (SQLException e2) {
						System.out.println(e2);
					}
					
					orderNameNull.setText(orderName);
					TKorDINull.setText(orderTAorDI);
					orderPaymentNull.setText(orderPayment);
					orderDateNull.setText(orderDate);
					orderStatusNull.setText(orderStatus);
					staffNameNull.setText(staffName);
					idSpinner.setValue(indexOrder);
					
					if (orderStatus.equals("Unpaid")) {
						sisiKanan1.add(changeStatus);
						sisiKanan1.add(changeStatusButton);
						changeStatus.setText("Change Status To : ");
						changeStatusButton.setText("On Going");
						repaint();
						revalidate();
					}else if (orderStatus.equals("On Going")) {
						sisiKanan1.add(changeStatus);
						sisiKanan1.add(changeStatusButton);
						changeStatus.setText("Change Status To : ");
						changeStatusButton.setText("Done");
						repaint();
						revalidate();
					}else if (orderStatus.equals("Done")) {
						sisiKanan1.remove(changeStatus);
						sisiKanan1.remove(changeStatusButton);
						repaint();
						revalidate();
					}else if (orderStatus.equals("Paid")) {
						sisiKanan1.add(changeStatus);
						sisiKanan1.add(changeStatusButton);
						changeStatus.setText("Change Status To : ");
						changeStatusButton.setText("On Going");
						repaint();
						revalidate();
					}
					 
					for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
						int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
						int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];

						String menuName="",source="";
						int menuPrice=0,totalPrice=0;
						for (int j = 0; j < Inventory.AllData.size(); j++) {
							if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
								menuName = (String) Inventory.AllData.get(j)[2];
								menuPrice = (Integer) Inventory.AllData.get(j)[3];
								totalPrice = menuPrice * quantityMenu;
								source = (String) Inventory.AllData.get(j)[5];
							}
						}
						
						BufferedImage gambarTabel = null;
						try {
							gambarTabel = ImageIO.read(getClass().getResource(source));			
						} catch (Exception e1) {
							System.out.println("gagal Render Bro");
						}
						Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon imageTable = new ImageIcon(imageToIconTabel);
					
					deftabKanan.addRow(new Object[] {
							i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
					});
					}
					tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
				}
			});
			
		}
		

		
		backToMainMenu.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new MainStaff().openMainMenu();

	}
});
		
		buttonEnterYourName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new viewOrderData();
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				String orderName = fieldEnterYourName.getText();
				viewOrderData.viewOrderSearch.clear();
				if (orderName.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please enter the name of the order you want to search","Alert",JOptionPane.WARNING_MESSAGE);
					
					try {
						new viewOrderData();
					} catch (SQLException e1) {
						System.out.println(e1);
					}
					
					for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
							Object sementara[] = {orderName,i};
							viewOrderData.viewOrderSearch.add(sementara);
						}
					
					sisiKiri2.removeAll();
					JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
					for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
						
						int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
						hasilButton[i] = new JButton();
						
						if (viewOrderData.viewOrderSearch.size()>9) {
							hasilButton[i].setMinimumSize(new Dimension(300,75));
							hasilButton[i].setMaximumSize(new Dimension(300,75));
							hasilButton[i].setPreferredSize(new Dimension(300,75));
						}else {
							hasilButton[i].setMinimumSize(new Dimension(315,75));
							hasilButton[i].setMaximumSize(new Dimension(315,75));
							hasilButton[i].setPreferredSize(new Dimension(315,75));
						}
						sisiKiri2.add(hasilButton[i]);
						
						JLabel namaLabel = new JLabel();
						namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
						namaLabel.setBorder(new EmptyBorder(0,0,33,0));
						hasilButton[i].add(namaLabel);
						
						JLabel totalPriceLabel = new JLabel();
						totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
						totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
						hasilButton[i].add(totalPriceLabel);
						
						JLabel dateLabel = new JLabel();
						dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
						dateLabel.setBorder(new EmptyBorder(48,0,0,0));
						hasilButton[i].add(dateLabel);
						
						namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
						totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
						dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
						
						String status = (String) viewOrderData.viewOrderHeader.get(index)[6];
						
						
						buttonClick = i;
						
						hasilButton[i].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
								String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
								String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
								String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
								String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
								int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
								String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
								String staffName = (String) viewOrderData.viewOrderHeader.get(index)[7];

								deftabKanan.setRowCount(0);
								try {
									new viewOrderDataDetail(indexOrder);
								} catch (SQLException e2) {
									System.out.println(e2);
								}
								
								orderNameNull.setText(orderName);
								TKorDINull.setText(orderTAorDI);
								orderPaymentNull.setText(orderPayment);
								orderDateNull.setText(orderDate);
								orderStatusNull.setText(orderStatus);
								staffNameNull.setText(staffName);
								idSpinner.setValue(indexOrder);
								
								if (orderStatus.equals("Unpaid")) {
									sisiKanan1.add(changeStatus);
									sisiKanan1.add(changeStatusButton);
									changeStatus.setText("Change Status To : ");
									changeStatusButton.setText("On Going");
									repaint();
									revalidate();
								}else if (orderStatus.equals("On Going")) {
									sisiKanan1.add(changeStatus);
									sisiKanan1.add(changeStatusButton);
									changeStatus.setText("Change Status To : ");
									changeStatusButton.setText("Done");
									repaint();
									revalidate();
								}else if (orderStatus.equals("Done")) {
									sisiKanan1.remove(changeStatus);
									sisiKanan1.remove(changeStatusButton);
									repaint();
									revalidate();
								}else if (orderStatus.equals("Paid")) {
									sisiKanan1.add(changeStatus);
									sisiKanan1.add(changeStatusButton);
									changeStatus.setText("Change Status To : ");
									changeStatusButton.setText("On Going");
									repaint();
									revalidate();
								}
								 
								for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
									int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
									int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];

									String menuName="",source="";
									int menuPrice=0,totalPrice=0;
									for (int j = 0; j < Inventory.AllData.size(); j++) {
										if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
											menuName = (String) Inventory.AllData.get(j)[2];
											menuPrice = (Integer) Inventory.AllData.get(j)[3];
											totalPrice = menuPrice * quantityMenu;
											source = (String) Inventory.AllData.get(j)[5];
										}
									}
									BufferedImage gambarTabel = null;
									try {
										gambarTabel = ImageIO.read(getClass().getResource(source));			
									} catch (Exception e1) {
										System.out.println("gagal Render Bro");
									}
									Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
									ImageIcon imageTable = new ImageIcon(imageToIconTabel);
								
								deftabKanan.addRow(new Object[] {
										i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
								});
								}
								tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
							}
						});
					}
				}else {
					
				for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
					if (orderName.equalsIgnoreCase((String) viewOrderData.viewOrderHeader.get(i)[1])) {
						Object sementara[] = {orderName,i};
						viewOrderData.viewOrderSearch.add(sementara);
					}
				}
				sisiKiri2.removeAll();
				if (viewOrderData.viewOrderSearch.size() == 0) {
					JOptionPane.showMessageDialog(getParent(), "The order name you entered does not exist.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
				for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
					
					int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
					hasilButton[i] = new JButton();
					
					if (viewOrderData.viewOrderSearch.size()>9) {
						hasilButton[i].setMinimumSize(new Dimension(300,75));
						hasilButton[i].setMaximumSize(new Dimension(300,75));
						hasilButton[i].setPreferredSize(new Dimension(300,75));
					}else {
						hasilButton[i].setMinimumSize(new Dimension(315,75));
						hasilButton[i].setMaximumSize(new Dimension(315,75));
						hasilButton[i].setPreferredSize(new Dimension(315,75));
					}
					sisiKiri2.add(hasilButton[i]);
					
					JLabel namaLabel = new JLabel();
					namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
					namaLabel.setBorder(new EmptyBorder(0,0,33,0));
					hasilButton[i].add(namaLabel);
					
					JLabel totalPriceLabel = new JLabel();
					totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
					totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
					hasilButton[i].add(totalPriceLabel);
					
					JLabel dateLabel = new JLabel();
					dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
					dateLabel.setBorder(new EmptyBorder(48,0,0,0));
					hasilButton[i].add(dateLabel);
					
					namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
					totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
					dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
					
					hasilButton[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
							String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
							String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
							String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
							String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
							int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
							String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
							String staffName = (String) viewOrderData.viewOrderHeader.get(index)[7];
							
							deftabKanan.setRowCount(0);
							try {
								new viewOrderDataDetail(indexOrder);
							} catch (SQLException e2) {
								System.out.println(e2);
							}
							
							orderNameNull.setText(orderName);
							TKorDINull.setText(orderTAorDI);
							orderPaymentNull.setText(orderPayment);
							orderDateNull.setText(orderDate);
							orderStatusNull.setText(orderStatus);
							staffNameNull.setText(staffName);
							idSpinner.setValue(indexOrder);
					
							if (orderStatus.equals("Unpaid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}else if (orderStatus.equals("On Going")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("Done");
								repaint();
								revalidate();
							}else if (orderStatus.equals("Done")) {
								sisiKanan1.remove(changeStatus);
								sisiKanan1.remove(changeStatusButton);
								repaint();
								revalidate();
							}else if (orderStatus.equals("Paid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}
							
							for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
								int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
								int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];
	
								String menuName="",source="";
								int menuPrice=0,totalPrice=0;
								for (int j = 0; j < Inventory.AllData.size(); j++) {
									if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
										menuName = (String) Inventory.AllData.get(j)[2];
										menuPrice = (Integer) Inventory.AllData.get(j)[3];
										totalPrice = menuPrice * quantityMenu;
										source = (String) Inventory.AllData.get(j)[5];
									}
								}
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
							
							deftabKanan.addRow(new Object[] {
									i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
							});
							}
							tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
						}
					});
					
				}
				
				repaint();
				revalidate();
				}
			}
		});
		
		onGoing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onGoing.setEnabled(false);
				allDataOrder.setEnabled(true);
				unpaid.setEnabled(true);
				done.setEnabled(true);
				try {
					new viewOrderData();
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				String orderStatus = "On Going";
				viewOrderData.viewOrderSearch.clear();
				
				for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
					if (orderStatus.equalsIgnoreCase((String) viewOrderData.viewOrderHeader.get(i)[6])) {
						Object sementara[] = {orderName,i};
						viewOrderData.viewOrderSearch.add(sementara);
					}
				}
				sisiKiri2.removeAll();
				JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
				for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
					
					int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
					hasilButton[i] = new JButton();
					
					if (viewOrderData.viewOrderSearch.size()>9) {
						hasilButton[i].setMinimumSize(new Dimension(300,75));
						hasilButton[i].setMaximumSize(new Dimension(300,75));
						hasilButton[i].setPreferredSize(new Dimension(300,75));
					}else {
						hasilButton[i].setMinimumSize(new Dimension(315,75));
						hasilButton[i].setMaximumSize(new Dimension(315,75));
						hasilButton[i].setPreferredSize(new Dimension(315,75));
					}
					sisiKiri2.add(hasilButton[i]);
					
					JLabel namaLabel = new JLabel();
					namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
					namaLabel.setBorder(new EmptyBorder(0,0,33,0));
					hasilButton[i].add(namaLabel);
					
					JLabel totalPriceLabel = new JLabel();
					totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
					totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
					hasilButton[i].add(totalPriceLabel);
					
					JLabel dateLabel = new JLabel();
					dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
					dateLabel.setBorder(new EmptyBorder(48,0,0,0));
					hasilButton[i].add(dateLabel);
					
					namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
					totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
					dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
					
					hasilButton[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
							String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
							String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
							String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
							String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
							int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
							String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
							String staffName = (String) viewOrderData.viewOrderHeader.get(index)[7];
							
							deftabKanan.setRowCount(0);
							try {
								new viewOrderDataDetail(indexOrder);
							} catch (SQLException e2) {
								System.out.println(e2);
							}
							
							orderNameNull.setText(orderName);
							TKorDINull.setText(orderTAorDI);
							orderPaymentNull.setText(orderPayment);
							orderDateNull.setText(orderDate);
							orderStatusNull.setText(orderStatus);
							staffNameNull.setText(staffName);
							idSpinner.setValue(indexOrder);
					
							if (orderStatus.equals("Unpaid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}else if (orderStatus.equals("On Going")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("Done");
								repaint();
								revalidate();
							}else if (orderStatus.equals("Done")) {
								sisiKanan1.remove(changeStatus);
								sisiKanan1.remove(changeStatusButton);
								repaint();
								revalidate();
							}else if (orderStatus.equals("Paid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}
							
							for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
								int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
								int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];
	
								String menuName="",source="";
								int menuPrice=0,totalPrice=0;
								for (int j = 0; j < Inventory.AllData.size(); j++) {
									if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
										menuName = (String) Inventory.AllData.get(j)[2];
										menuPrice = (Integer) Inventory.AllData.get(j)[3];
										totalPrice = menuPrice * quantityMenu;
										source = (String) Inventory.AllData.get(j)[5];
									}
								}
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
							
							deftabKanan.addRow(new Object[] {
									i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
							});
							}
							tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
						}
					});
					
				}
				
				repaint();
				revalidate();
				
			}
		});
		
		allDataOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onGoing.setEnabled(true);
				allDataOrder.setEnabled(false);
				done.setEnabled(true);
				unpaid.setEnabled(true);
				try {
					new viewOrderData();
				} catch (SQLException e1) {
					System.out.println(e1);
				}

				
				for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
						Object sementara[] = {orderName,i};
						viewOrderData.viewOrderSearch.add(sementara);
					}
				
				sisiKiri2.removeAll();
				JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
				for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
					
					int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
					hasilButton[i] = new JButton();
					
					if (viewOrderData.viewOrderSearch.size()>9) {
						hasilButton[i].setMinimumSize(new Dimension(300,75));
						hasilButton[i].setMaximumSize(new Dimension(300,75));
						hasilButton[i].setPreferredSize(new Dimension(300,75));
					}else {
						hasilButton[i].setMinimumSize(new Dimension(315,75));
						hasilButton[i].setMaximumSize(new Dimension(315,75));
						hasilButton[i].setPreferredSize(new Dimension(315,75));
					}
					sisiKiri2.add(hasilButton[i]);
					
					JLabel namaLabel = new JLabel();
					namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
					namaLabel.setBorder(new EmptyBorder(0,0,33,0));
					hasilButton[i].add(namaLabel);
					
					JLabel totalPriceLabel = new JLabel();
					totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
					totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
					hasilButton[i].add(totalPriceLabel);
					
					JLabel dateLabel = new JLabel();
					dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
					dateLabel.setBorder(new EmptyBorder(48,0,0,0));
					hasilButton[i].add(dateLabel);
					
					namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
					totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
					dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
					
					hasilButton[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
							String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
							String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
							String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
							String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
							int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
							String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
							String staffName = (String) viewOrderData.viewOrderHeader.get(index)[7];
							
							deftabKanan.setRowCount(0);
							try {
								new viewOrderDataDetail(indexOrder);
							} catch (SQLException e2) {
								System.out.println(e2);
							}
							
							orderNameNull.setText(orderName);
							TKorDINull.setText(orderTAorDI);
							orderPaymentNull.setText(orderPayment);
							orderDateNull.setText(orderDate);
							orderStatusNull.setText(orderStatus);
							staffNameNull.setText(staffName);
							idSpinner.setValue(indexOrder);
					
							if (orderStatus.equals("Unpaid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}else if (orderStatus.equals("On Going")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("Done");
								repaint();
								revalidate();
							}else if (orderStatus.equals("Done")) {
								sisiKanan1.remove(changeStatus);
								sisiKanan1.remove(changeStatusButton);
								repaint();
								revalidate();
							}else if (orderStatus.equals("Paid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}
							
							for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
								int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
								int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];

								String menuName="",source="";
								int menuPrice=0,totalPrice=0;
								for (int j = 0; j < Inventory.AllData.size(); j++) {
									if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
										menuName = (String) Inventory.AllData.get(j)[2];
										menuPrice = (Integer) Inventory.AllData.get(j)[3];
										totalPrice = menuPrice * quantityMenu;
										source = (String) Inventory.AllData.get(j)[5];
									}
								}
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
							
							deftabKanan.addRow(new Object[] {
									i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
							});
							}
							tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
						}
					});
					
				}
				repaint();
				revalidate();
				
			}
		});
		
		unpaid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onGoing.setEnabled(true);
				allDataOrder.setEnabled(true);
				unpaid.setEnabled(false);
				done.setEnabled(true);
				try {
					new viewOrderData();
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				String orderStatus = "Unpaid";
				viewOrderData.viewOrderSearch.clear();
				
				for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
					if (orderStatus.equalsIgnoreCase((String) viewOrderData.viewOrderHeader.get(i)[6])) {
						Object sementara[] = {orderName,i};
						viewOrderData.viewOrderSearch.add(sementara);
					}
				}
				sisiKiri2.removeAll();
				JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
				for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
					
					int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
					hasilButton[i] = new JButton();
					
					if (viewOrderData.viewOrderSearch.size()>9) {
						hasilButton[i].setMinimumSize(new Dimension(300,75));
						hasilButton[i].setMaximumSize(new Dimension(300,75));
						hasilButton[i].setPreferredSize(new Dimension(300,75));
					}else {
						hasilButton[i].setMinimumSize(new Dimension(315,75));
						hasilButton[i].setMaximumSize(new Dimension(315,75));
						hasilButton[i].setPreferredSize(new Dimension(315,75));
					}
					sisiKiri2.add(hasilButton[i]);
					
					JLabel namaLabel = new JLabel();
					namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
					namaLabel.setBorder(new EmptyBorder(0,0,33,0));
					hasilButton[i].add(namaLabel);
					
					JLabel totalPriceLabel = new JLabel();
					totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
					totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
					hasilButton[i].add(totalPriceLabel);
					
					JLabel dateLabel = new JLabel();
					dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
					dateLabel.setBorder(new EmptyBorder(48,0,0,0));
					hasilButton[i].add(dateLabel);
					
					namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
					totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
					dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
					
					hasilButton[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
							String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
							String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
							String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
							String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
							int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
							String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
							String staffName = (String) viewOrderData.viewOrderHeader.get(index)[7];
							
							deftabKanan.setRowCount(0);
							try {
								new viewOrderDataDetail(indexOrder);
							} catch (SQLException e2) {
								System.out.println(e2);
							}
							
							orderNameNull.setText(orderName);
							TKorDINull.setText(orderTAorDI);
							orderPaymentNull.setText(orderPayment);
							orderDateNull.setText(orderDate);
							orderStatusNull.setText(orderStatus);
							staffNameNull.setText(staffName);
							idSpinner.setValue(indexOrder);
					
							if (orderStatus.equals("Unpaid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}else if (orderStatus.equals("On Going")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("Done");
								repaint();
								revalidate();
							}else if (orderStatus.equals("Done")) {
								sisiKanan1.remove(changeStatus);
								sisiKanan1.remove(changeStatusButton);
								repaint();
								revalidate();
							}else if (orderStatus.equals("Paid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}
							
							for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
								int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
								int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];
	
								String menuName="",source="";
								int menuPrice=0,totalPrice=0;
								for (int j = 0; j < Inventory.AllData.size(); j++) {
									if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
										menuName = (String) Inventory.AllData.get(j)[2];
										menuPrice = (Integer) Inventory.AllData.get(j)[3];
										totalPrice = menuPrice * quantityMenu;
										source = (String) Inventory.AllData.get(j)[5];
									}
								}
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
							
							deftabKanan.addRow(new Object[] {
									i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
							});
							}
							tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
						}
					});
					
				}
				
				repaint();
				revalidate();
				
			}
		});
		
		done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onGoing.setEnabled(true);
				allDataOrder.setEnabled(true);
				unpaid.setEnabled(true);
				done.setEnabled(false);
				try {
					new viewOrderData();
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				String orderStatus = "Paid";
				viewOrderData.viewOrderSearch.clear();
				
				for (int i = 0; i < viewOrderData.viewOrderHeader.size() ; i++) {
					if (orderStatus.equalsIgnoreCase((String) viewOrderData.viewOrderHeader.get(i)[6])) {
						Object sementara[] = {orderName,i};
						viewOrderData.viewOrderSearch.add(sementara);
					}
				}
				sisiKiri2.removeAll();
				JButton hasilButton[] = new JButton[viewOrderData.viewOrderSearch.size()];
				for (int i = 0; i < viewOrderData.viewOrderSearch.size(); i++) {
					
					int index = (int) viewOrderData.viewOrderSearch.get(i)[1];
					hasilButton[i] = new JButton();
					
					if (viewOrderData.viewOrderSearch.size()>9) {
						hasilButton[i].setMinimumSize(new Dimension(300,75));
						hasilButton[i].setMaximumSize(new Dimension(300,75));
						hasilButton[i].setPreferredSize(new Dimension(300,75));
					}else {
						hasilButton[i].setMinimumSize(new Dimension(315,75));
						hasilButton[i].setMaximumSize(new Dimension(315,75));
						hasilButton[i].setPreferredSize(new Dimension(315,75));
					}
					sisiKiri2.add(hasilButton[i]);
					
					JLabel namaLabel = new JLabel();
					namaLabel.setFont(new Font("Calibri", Font.BOLD,25));
					namaLabel.setBorder(new EmptyBorder(0,0,33,0));
					hasilButton[i].add(namaLabel);
					
					JLabel totalPriceLabel = new JLabel();
					totalPriceLabel.setBorder(new EmptyBorder(12,0,0,0));
					totalPriceLabel.setFont(new Font("Calibri", Font.ITALIC,19));
					hasilButton[i].add(totalPriceLabel);
					
					JLabel dateLabel = new JLabel();
					dateLabel.setFont(new Font("Calibri", Font.PLAIN,15));
					dateLabel.setBorder(new EmptyBorder(48,0,0,0));
					hasilButton[i].add(dateLabel);
					
					namaLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[1]);
					totalPriceLabel.setText(String.valueOf((int) viewOrderData.viewOrderHeader.get(index)[5]));
					dateLabel.setText((String) viewOrderData.viewOrderHeader.get(index)[4]);
					
					hasilButton[i].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
							String orderName = (String) viewOrderData.viewOrderHeader.get(index)[1];
							String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
							String orderPayment = (String) viewOrderData.viewOrderHeader.get(index)[3];
							String orderDate =  (String) viewOrderData.viewOrderHeader.get(index)[4];
							int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
							String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
							
							deftabKanan.setRowCount(0);
							try {
								new viewOrderDataDetail(indexOrder);
							} catch (SQLException e2) {
								System.out.println(e2);
							}
							
							orderNameNull.setText(orderName);
							TKorDINull.setText(orderTAorDI);
							orderPaymentNull.setText(orderPayment);
							orderDateNull.setText(orderDate);
							orderStatusNull.setText(orderStatus);
							idSpinner.setValue(indexOrder);
							
							if (orderStatus.equals("Unpaid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}else if (orderStatus.equals("On Going")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("Done");
								repaint();
								revalidate();
							}else if (orderStatus.equals("Done")) {
								sisiKanan1.remove(changeStatus);
								sisiKanan1.remove(changeStatusButton);
								repaint();
								revalidate();
							}else if (orderStatus.equals("Paid")) {
								sisiKanan1.add(changeStatus);
								sisiKanan1.add(changeStatusButton);
								changeStatus.setText("Change Status To : ");
								changeStatusButton.setText("On Going");
								repaint();
								revalidate();
							}
							
							for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
								int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
								int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];
	
								String menuName="",source="";
								int menuPrice=0,totalPrice=0;
								for (int j = 0; j < Inventory.AllData.size(); j++) {
									if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
										menuName = (String) Inventory.AllData.get(j)[2];
										menuPrice = (Integer) Inventory.AllData.get(j)[3];
										totalPrice = menuPrice * quantityMenu;
										source = (String) Inventory.AllData.get(j)[5];
									}
								}
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
							
							deftabKanan.addRow(new Object[] {
									i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
							});
							}
							tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
						}
					});
					
				}
				
				repaint();
				revalidate();
				
			}
		});
		
		changeStatusButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String perintah = changeStatusButton.getText();
				int orderID = (int) idSpinner.getValue();
				int orderTotal = (int) tabelTotal.getValueAt(0, 1);
				int idstaff = MainStaff.idStaff;
				
				if (perintah.equals("Done")) {
					
					int a =JOptionPane.showConfirmDialog(getParent(), "Is the Order Completed? ");
					
					if (a == JOptionPane.YES_OPTION) {
						try {
							new order.changeStatus(orderID, perintah,idstaff);
							new addIncome(orderID,orderTotal);
						} catch (Exception e) {
							System.out.println(e);
						}
						
						orderStatusNull.setText("Done");
						staffNameNull.setText(Login.StaffName);
						sisiKanan1.remove(changeStatus);
						sisiKanan1.remove(changeStatusButton);
						repaint();
						revalidate();
						try {
							new viewOrderData();
						} catch (SQLException e1) {
							System.out.println(e1);
						}
					}
					
				}else if (perintah.equals("On Going")) {
					
					if (orderStatusNull.getText().equals("Paid")) {
						int a =JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to change the status to On Going?");
						
						if (a == JOptionPane.YES_OPTION) {
							try {
								new order.changeStatus(orderID, perintah,idstaff);
							} catch (Exception e) {
								System.out.println(e);
							}
							orderStatusNull.setText("On Going");
							staffNameNull.setText(Login.StaffName);
							sisiKanan1.add(changeStatus);
							sisiKanan1.add(changeStatusButton);
							changeStatus.setText("Change Status To : ");
							changeStatusButton.setText("Done");
							repaint();
							revalidate();
							try {
								new viewOrderData();
							} catch (SQLException e1) {
								System.out.println(e1);
							}
						}
					}else {
						int a =JOptionPane.showConfirmDialog(getParent(), "Has it been paid for by the customer? ");
						
						if (a == JOptionPane.YES_OPTION) {
							try {
								new order.changeStatus(orderID, perintah,idstaff);
							} catch (Exception e) {
								System.out.println(e);
							}
							orderStatusNull.setText("On Going");
							staffNameNull.setText(Login.StaffName);
							sisiKanan1.add(changeStatus);
							sisiKanan1.add(changeStatusButton);
							changeStatus.setText("Change Status To : ");
							changeStatusButton.setText("Done");
							repaint();
							revalidate();
							try {
								new viewOrderData();
							} catch (SQLException e1) {
								System.out.println(e1);
							}
						}
					}
				}
			}
		});
		
		}
	}

