package Staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Inventory.Inventory;
import order.viewOrderData;
import order.viewOrderDataDetail;

public class OrderINIncome extends JInternalFrame{

	public OrderINIncome(int id) {
		ui(id);
		setVisible(true);
		setSize(500,680);
		setMaximizable(false);
		setIconifiable(false);
		setClosable(true);
		setLocation(45,140);
	}
	
	JLabel orderNameNull, orderDateNull, orderStatusNull, TKorDINull, orderPaymentNull;
	JTable tabelTotal;
	
	private void ui(int id) {
		
		try {
			new viewOrderData();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		JLabel orderDetail = new JLabel("Order Detail",SwingConstants.CENTER);
		orderDetail.setBorder(new EmptyBorder(10,0,0,0));
		orderDetail.setAlignmentX(CENTER_ALIGNMENT);
		orderDetail.setFont(new Font("Calibri", Font.BOLD,22));
		add(orderDetail,BorderLayout.PAGE_START);
		
		JPanel panelUtama = new JPanel();
		add(panelUtama);
		
		JPanel panelUtama1 = new JPanel(new GridLayout(0,2,30,3));
		panelUtama1.setBorder(new EmptyBorder(0,0,0,100));
		panelUtama.add(panelUtama1, BorderLayout.PAGE_START);
		
		JLabel orderName = new JLabel("Name Order :");
		orderName.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(orderName);
		
		orderNameNull = new JLabel("null");
		orderNameNull.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(orderNameNull);
		
		
		JLabel orderDate = new JLabel("Date : ");
		orderDate.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(orderDate);
		
		orderDateNull = new JLabel("null");
		orderDateNull.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(orderDateNull);
		
		JLabel TKorDI = new JLabel("Take Away / Dine In : ");
		TKorDI.setBorder(new EmptyBorder(3,0,0,0));
		TKorDI.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(TKorDI);
		
		TKorDINull = new JLabel("null");
		TKorDINull.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(TKorDINull);
		
		JLabel orderPayment = new JLabel("Payment By : ");
		orderPayment.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(orderPayment);
		
		orderPaymentNull = new JLabel("null");
		orderPaymentNull.setFont(new Font("Calibri", Font.PLAIN, 19));
		panelUtama1.add(orderPaymentNull);
	
		JPanel panelUtama2 = new JPanel(new GridLayout());
		panelUtama.add(panelUtama2, BorderLayout.CENTER);
		
		Object[] kolomOrder = {"No","Menu Name","Price","Quantity","Total Price"};
		Object[][] dataOrder = {};
		
		DefaultTableModel deftabKanan  = new DefaultTableModel(dataOrder,kolomOrder);
		JTable tabelKanan= new JTable(deftabKanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tabSortKanan = new TableRowSorter<>(deftabKanan);
		tabelKanan.setRowSorter(tabSortKanan);
		JScrollPane scrollTabKanan = new JScrollPane(tabelKanan);
		scrollTabKanan.setBorder( new MatteBorder(1, 1, 1, 1, Color.black));
		panelUtama2.add(scrollTabKanan,BorderLayout.CENTER);
		
		tabelKanan.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		scrollTabKanan.setPreferredSize(new Dimension(470,450));
		tabelKanan.setFont(new Font("Calibri", Font.PLAIN, 18));
		tabelKanan.setRowHeight(40);
		tabelKanan.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelKanan.getColumnModel().getColumn(1).setPreferredWidth(130);
		tabelKanan.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabelKanan.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabelKanan.getColumnModel().getColumn(4).setPreferredWidth(70);
		
		JPanel panelUtama3 = new JPanel(new GridLayout());
		panelUtama.add(panelUtama3, BorderLayout.PAGE_END);
		
		String[] kolomTotal = {"Total Price","Total"};
		Object[][] isiTableTotal = {{"Total Price ",0}};
		
		tabelTotal = new JTable(isiTableTotal,kolomTotal);
		tabelTotal.setPreferredSize(new Dimension(470,20));
		tabelTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		tabelTotal.setBackground(new Color(214,217,223));
		tabelTotal.setSelectionBackground(new Color(214,217,223));
		tabelTotal.setSelectionForeground(Color.BLACK);
		tabelTotal.setBorder( new MatteBorder(0, 1, 1, 1, Color.black));
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tabelTotal.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		panelUtama3.add(tabelTotal,BorderLayout.CENTER);
		
		int index = 0;
		for (int i = 0; i < viewOrderData.viewOrderHeader.size(); i++) {
			if ((int) viewOrderData.viewOrderHeader.get(i)[0] == id) {
				index = i;
			}
		}
		
		int indexOrder =(int) viewOrderData.viewOrderHeader.get(index)[0];
		String orderName1 = (String) viewOrderData.viewOrderHeader.get(index)[1];
		String orderTAorDI = (String) viewOrderData.viewOrderHeader.get(index)[2];
		String orderPayment1 = (String) viewOrderData.viewOrderHeader.get(index)[3];
		String orderDate1 =  (String) viewOrderData.viewOrderHeader.get(index)[4];
		int orderTotalPurchase = (int) viewOrderData.viewOrderHeader.get(index)[5];
		String orderStatus = (String) viewOrderData.viewOrderHeader.get(index)[6];
		
		deftabKanan.setRowCount(0);

		try {
			new viewOrderDataDetail(indexOrder);
		} catch (SQLException e2) {
			System.out.println(e2);
		}
		
		orderNameNull.setText(orderName1);
		TKorDINull.setText(orderTAorDI);
		orderPaymentNull.setText(orderPayment1);
		orderDateNull.setText(orderDate1);

		for (int i = 0; i < viewOrderData.viewOrderDetail.size(); i++) {
			int inventoryID = (Integer) viewOrderData.viewOrderDetail.get(i)[1];
			int quantityMenu =(Integer) viewOrderData.viewOrderDetail.get(i)[2];

			String menuName="";
			int menuPrice=0,totalPrice=0;
			for (int j = 0; j < Inventory.AllData.size(); j++) {
				if (Inventory.AllData.get(j)[1].equals(inventoryID)) {
					menuName = (String) Inventory.AllData.get(j)[2];
					menuPrice = (Integer) Inventory.AllData.get(j)[3];
					totalPrice = menuPrice * quantityMenu;
				}
			}
		
		deftabKanan.addRow(new Object[] {
				i+1,menuName,menuPrice,quantityMenu,totalPrice
		});
		}
		tabelTotal.setValueAt(orderTotalPurchase, 0, 1);	
		
addInternalFrameListener(new InternalFrameListener() {
			
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				viewIncome.frameTerbuka = true;
				
			}
			
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
				new MainStaff().closeOrderINIncome();
				
			}
			
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				viewIncome.frameTerbuka = false;
				
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				viewIncome.frameTerbuka = false;
				
			}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
