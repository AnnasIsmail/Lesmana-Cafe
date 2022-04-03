package Customer;
import Customer.MainCustomer;
import Inventory.Inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Cart.*;
import Inventory.*;
import Customer.*;
public class AddOrder extends JInternalFrame{
	public AddOrder() {
		
		ui();
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	}
	private JLabel labelImageKanan;
	int j = 0, totalPrice = 0;;
	String sourceGambarKanan ;
	BufferedImage gambarKanan = null;
	JTextField fieldEnterName,fieldIdMenu,fieldidCategory,fieldSource;
	JLabel namaMenuNull,hargaNull, categoryNull ;
	JSpinner quantitySpinner;
	JPanel atasKanan21,panelSpinner;
	JTable tabelKanan, tabelTotal;
	DefaultTableModel deftabKanan;
	JButton RemovefromCartButton;
	
	private void ui() {
		setLayout(new GridLayout());
		JPanel atasKanan22 = new JPanel();
		
		Object[] kolomCart = {"No","Menu Name","Price","Quantity","Total"};
		
		Object[][] dataCart = {};
		
		String[] Category = {
				"Coffe","Non Coffe","Snack","Heavy Meal","Desserts"	
		};	
		
		JPanel kiri = new JPanel();
		add(kiri,BorderLayout.CENTER);
		kiri.setBorder(new EmptyBorder(10,10,0,0));
		
		JButton backToMainMenu = new JButton("< Back To Main Menu");
		backToMainMenu.setHorizontalAlignment(SwingConstants.LEFT);
		kiri.add(backToMainMenu,BorderLayout.PAGE_START);
		
		JLabel menuLabel = new JLabel("MENU");
		menuLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		menuLabel.setBorder(new EmptyBorder(10,110,10,250));
		kiri.add(menuLabel);

		JPanel sisiKiri = new JPanel();
		sisiKiri.setLayout(new BoxLayout(sisiKiri, BoxLayout.Y_AXIS));
		sisiKiri.setBorder(new EmptyBorder(0,0,0,5));
		
		JScrollPane scrollMenu = new JScrollPane(sisiKiri);
		scrollMenu.setPreferredSize(new Dimension(580, 765));
		scrollMenu.getVerticalScrollBar().setUnitIncrement(25);
		kiri.add(scrollMenu,BorderLayout.CENTER);
		
		JPanel[] categoryPanel = new JPanel[Category.length];
		
		for (int l = 0; l < Category.length; l++) {
		 categoryPanel[l] = new JPanel();
		categoryPanel[l].setPreferredSize(new Dimension(250,50));
		sisiKiri.add(categoryPanel[l]);
			
		JLabel categoryLabel = new JLabel(Category[l]);
		categoryLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		categoryLabel.setBorder(new EmptyBorder(10,0,5,0));
		categoryPanel[l].add(categoryLabel);
		
		JPanel isiCategory = new JPanel(new GridLayout(0,3,5,5));
		isiCategory.setAlignmentX(Component.CENTER_ALIGNMENT);
		sisiKiri.add(isiCategory);
		
		
		Object[][] Sementara = null;

		if (Category[l].equals("Coffe")) {
			Sementara = new Object[Inventory.Coffe.size()][];
		Sementara = Inventory.Coffe.toArray(Sementara);
		}else if (Category[l].equals("Non Coffe")) {
			Sementara = new Object[Inventory.NonCoffe.size()][];
			Sementara = Inventory.NonCoffe.toArray(Sementara);
		}else if (Category[l].equals("Snack")) {
			Sementara = new Object[Inventory.Snack.size()][];
			Sementara = Inventory.Snack.toArray(Sementara);
		}else if (Category[l].equals("Heavy Meal")) {
			Sementara = new Object[Inventory.HeavyMeal.size()][];
			Sementara = Inventory.HeavyMeal.toArray(Sementara);
		}else if (Category[l].equals("Desserts")) {
			Sementara = new Object[Inventory.Desserts.size()][];
			Sementara = Inventory.Desserts.toArray(Sementara);
		}
		
		JButton menu[] = new JButton[Sementara.length];
		JLabel[] namaMenu = new JLabel[Sementara.length];
		
		for (int i = 0; i < menu.length; i++) {
			
			String menuName,menuImage;
			int price,stock,idCategory,idMenu;
			
			idCategory = (Integer) Sementara[i][0];
			idMenu = (Integer) Sementara[i][1];
			menuName = Sementara[i][2].toString();
			menuImage = Sementara[i][5].toString();
			price = (Integer) Sementara[i][3];
			stock = (Integer) Sementara[i][4];
			
			menu[i] = new JButton();
			menu[i].setPreferredSize(new Dimension(180,230));
			if (stock == 0) {
				
			}else {				
				isiCategory.add(menu[i]);
			}
			
			BufferedImage gambar = null;
			try {
				gambar = ImageIO.read(getClass().getResource(menuImage));			
			} catch (Exception e) {
				System.out.println("gagal Render Bro"+i);
			}
			Image imageToIcon = gambar.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
			ImageIcon imageCart = new ImageIcon(imageToIcon);
			JLabel labelImage = new JLabel(imageCart);
			labelImage.setPreferredSize(new Dimension(160,160));
			labelImage.setBorder(new EmptyBorder(40,0,80,0));
			menu[i].add(labelImage);
			
			String menuSementara = menuName;
			namaMenu[i] = new JLabel(menuSementara);
			namaMenu[i].setBorder(new EmptyBorder(150,0,0,0));
			namaMenu[i].setFont(new Font("Dialog", Font.BOLD, 18));
			menu[i].add(namaMenu[i]);
			
			JLabel hargaMenu = new JLabel("Rp. "+Sementara[i][3].toString());
			hargaMenu.setBorder(new EmptyBorder(195,0,0,0));
			hargaMenu.setFont(new Font("Dialog", Font.PLAIN, 13));
			menu[i].add(hargaMenu);
			int o=i;
		
		
		Thread textAnimation = new Thread(new Runnable() {

			public void run() {
				
				int panjang = menuSementara.length();
				for (; j < panjang; j++) {
					String potong = menuSementara.substring(j,panjang);
					namaMenu[o].setText(potong);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						
					}
					if (potong.length() == 1) {
						j=0;
						namaMenu[o].setText(menuSementara);
						run();
					}
				}
			}
		});
		
		menu[i].addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {

		        	try {
		        		textAnimation.start();

					} catch (Exception e2) {
						j=0;
			        	textAnimation.resume();
					}
		    }
		    public void mouseExited(MouseEvent e) {
		    	textAnimation.suspend();
		        namaMenu[o].setText(menuSementara);
		    }

		});
		
		menu[i].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (RemovefromCartButton.isShowing()) {
					atasKanan21.remove(RemovefromCartButton);
				}
				
				String Source = menuImage;
				String namaMenu = menuName,nameCategory;
				int priceMenu = price,stockMenu = stock, categID = idCategory, menuID = idMenu;
				
				fieldidCategory.setText(String.valueOf(idCategory));
				fieldIdMenu.setText(String.valueOf(idMenu));
				
				categoryNull.setText(Category[categID-1]);
				namaMenuNull.setText(namaMenu);
				hargaNull.setText(String.valueOf(priceMenu));
				fieldSource.setText(Source);
				
				quantitySpinner.setValue(0);
				panelSpinner.remove(quantitySpinner);
				quantitySpinner = new JSpinner(new SpinnerNumberModel(1,1,stock,1));
				panelSpinner.add(quantitySpinner);
				
 				atasKanan22.remove(labelImageKanan);
				try {
					gambarKanan = ImageIO.read(getClass().getResource(Source));			
				} catch (Exception e1) {
					System.out.println("gagal Render Bro");
				}
				Image imageToIcon = gambarKanan.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				ImageIcon imageCart = new ImageIcon(imageToIcon);
				labelImageKanan = new JLabel(imageCart);
				labelImageKanan.setPreferredSize(new Dimension(200,200));
				atasKanan22.add(labelImageKanan);
				revalidate();
				repaint();
				
			}
		});	
		}
	}
		
		JPanel kanan = new JPanel();
		add(kanan,BorderLayout.LINE_END);
		
		JPanel atasKanan = new JPanel(new BorderLayout(0,0));
		kanan.add(atasKanan,BorderLayout.PAGE_START);
		
		fieldSource = new JTextField();
		fieldidCategory = new JTextField();
		fieldIdMenu = new JTextField();
		
		JLabel cartLabel = new JLabel("CART");
		cartLabel.setFont(new Font("Calibri", Font.BOLD, 22));
		cartLabel.setBorder(new EmptyBorder(15,100,0,100));
		atasKanan.add(cartLabel,BorderLayout.PAGE_START);
		
		JPanel atasKanan2 = new JPanel(new GridLayout(0,2));
		kanan.add(atasKanan2);
		
		atasKanan21 = new JPanel(new GridLayout(0,2,0,5));
		atasKanan21.setBorder(new EmptyBorder(5,5,5,5));
		atasKanan2.add(atasKanan21);
		
		JLabel EnterName = new JLabel("Enter Your Name : ");
		EnterName.setFont(new Font("Calibri", Font.PLAIN,16));
		EnterName.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(EnterName,BorderLayout.PAGE_END);
		
		fieldEnterName = new JTextField(10);
		atasKanan21.add(fieldEnterName,BorderLayout.PAGE_END);
		
		JLabel CategoryDesc = new JLabel("Category : ");
		CategoryDesc.setFont(new Font("Calibri", Font.PLAIN,16));
		CategoryDesc.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(CategoryDesc);
		
		categoryNull = new JLabel("null");
		categoryNull.setFont(new Font("Calibri", Font.PLAIN,16));
		categoryNull.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(categoryNull);
		
		JLabel namaMenuDesc = new JLabel("Nama Menu : ");
		namaMenuDesc.setFont(new Font("Calibri", Font.PLAIN,16));
		namaMenuDesc.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(namaMenuDesc);
		
		namaMenuNull = new JLabel("null");
		namaMenuNull.setFont(new Font("Calibri", Font.PLAIN,16));
		namaMenuNull.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(namaMenuNull);
		
		JLabel harga = new JLabel("Price : ");
		harga.setFont(new Font("Calibri", Font.PLAIN,16));
		harga.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(harga);
		
		hargaNull = new JLabel("null");
		hargaNull.setFont(new Font("Calibri", Font.PLAIN,16));
		hargaNull.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(hargaNull);
		
		JLabel quantity = new JLabel("Quantity : ");
		quantity.setFont(new Font("Calibri", Font.PLAIN,16));
		quantity.setVerticalAlignment(SwingConstants.CENTER);
		atasKanan21.add(quantity);
		
		panelSpinner =  new JPanel(new GridLayout());
		atasKanan21.add(panelSpinner);
		
		quantitySpinner = new JSpinner(new SpinnerNumberModel(0,0,0,1));
		panelSpinner.add(quantitySpinner);
		                                                                   
		JButton addToCartButton = new JButton("        Add To Cart       ");
		addToCartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (fieldidCategory.getText().equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Select the Menu that you want to add to the Cart.");
					return;
				}
				
				String 
				namaCustomer = fieldEnterName.getText().trim(),
				namaMenu = namaMenuNull.getText(),
				source = fieldSource.getText();
				;
				int 
				no = deftabKanan.getRowCount(),
				IDCategory = Integer.parseInt(fieldidCategory.getText()),
				idMenu = Integer.parseInt(fieldIdMenu.getText()),
				price = Integer.parseInt(hargaNull.getText()),
				quantityINT = (int) quantitySpinner.getValue(),
				total= price*quantityINT;
				;
				
				if (namaCustomer.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please Enter your Name");
					return;
				}
				
				if (quantityINT ==0 ) {
					JOptionPane.showMessageDialog(getParent(), "Quantity cannot be 0");
					return;
				}
				
				Object[] SementaraAddCartDetail = {
						namaMenu,price,quantityINT,total,IDCategory,idMenu,source
				};
				
				for (int j = 0; j < Cart.CartDetail.size(); j++) {
					
				if (Cart.CartDetail.get(j)[0].equals(namaMenu)) {
					JOptionPane.showMessageDialog(getParent(), "Menu can not be more than one in Cart.");
					return;
				}}
				
				Cart.CartDetail.add(SementaraAddCartDetail);

				 deftabKanan.addRow(new Object[] {
						 no+1,namaMenu,price,quantityINT,total
				 });
				totalPrice =(int) tabelTotal.getValueAt(0, 1)+total;
				 
				tabelTotal.setValueAt(totalPrice, 0, 1);
				
				if (Cart.CartHeader.size() == 2) {
					Cart.CartHeader.set(1, totalPrice);
				}else {
					Cart.CartHeader.add(namaCustomer);
					Cart.CartHeader.add(totalPrice);
				}

				totalPrice=0;
			}
		});
		atasKanan21.add(addToCartButton);
		
		RemovefromCartButton = new JButton("Remove From Cart");
		
		RemovefromCartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row =  (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 0);
				deftabKanan.removeRow(row-1);
				Cart.CartDetail.remove(row-1);
				
				int hapusSeluruhRow = tabelKanan.getRowCount();
				while(tabelKanan.getRowCount() >0) {
					deftabKanan.removeRow(hapusSeluruhRow-1);
					hapusSeluruhRow--;
				}
				
				int TotalPrice = 0;
				for (int a = 0; a < Cart.CartDetail.size(); a++) {
					
				String 
				namaCustomer = (String) Cart.CartHeader.get(0),
				namaMenu = (String) Cart.CartDetail.get(a)[0],
				source = (String) Cart.CartDetail.get(a)[6];
				
				int 
				no = deftabKanan.getRowCount(),
				IDCategory = (Integer) Cart.CartDetail.get(a)[4],
				idMenu = (Integer) Cart.CartDetail.get(a)[5],
				price = (Integer) Cart.CartDetail.get(a)[1],
				quantityINT = (Integer) Cart.CartDetail.get(a)[2],
				total = (Integer) Cart.CartDetail.get(a)[3];
				
				fieldEnterName.setText(namaCustomer);	
				
				deftabKanan.addRow(new Object[] {
						 no+1,namaMenu,price,quantityINT,total
				 });
				TotalPrice = TotalPrice+total;
				}
			tabelTotal.setValueAt(TotalPrice, 0, 1);	
			Cart.CartHeader.set(1, TotalPrice);
			TotalPrice=0;

			atasKanan21.remove(RemovefromCartButton);
			
			repaint();
			revalidate();
			}
		});

		atasKanan2.add(atasKanan22);
		
		try {
			gambarKanan = ImageIO.read(getClass().getResource("/image/warna dasar.png"));			
		} catch (Exception e) {
			System.out.println("gagal Render Bro");
		}
		Image imageToIcon = gambarKanan.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon imageCart = new ImageIcon(imageToIcon);
		labelImageKanan = new JLabel(imageCart);
		labelImageKanan.setPreferredSize(new Dimension(200,200));
		atasKanan22.add(labelImageKanan);
		
		deftabKanan  = new DefaultTableModel(dataCart,kolomCart);
		tabelKanan= new JTable(deftabKanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		tabelKanan.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		tabelKanan.setRowHeight(19);
		tabelKanan.setFont(new Font("Calibri", Font.PLAIN, 18));
		TableRowSorter tabSortKanan = new TableRowSorter<>(deftabKanan);
		tabelKanan.setRowSorter(tabSortKanan);
		JScrollPane scrollTabKanan = new JScrollPane(tabelKanan);
		scrollTabKanan.setPreferredSize(new Dimension(550,500));
		scrollTabKanan.setBorder( new MatteBorder(1, 1, 1, 1, Color.black));
		kanan.add(scrollTabKanan,BorderLayout.CENTER);

		tabelKanan.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelKanan.getColumnModel().getColumn(1).setPreferredWidth(320);
		tabelKanan.getColumnModel().getColumn(2).setPreferredWidth(160);
		tabelKanan.getColumnModel().getColumn(3).setPreferredWidth(160);
		tabelKanan.getColumnModel().getColumn(4).setPreferredWidth(160);
		
		String[] kolomTotal = {"Total Price","Total"};
		Object[][] isiTableTotal = {{"Total Price ",0}};
		
		tabelTotal = new JTable(isiTableTotal,kolomTotal);
		tabelTotal.setPreferredSize(new Dimension(548,20));
		tabelTotal.setFont(new Font("Calibri", Font.BOLD, 18));
		tabelTotal.setBackground(new Color(214,217,223));
		tabelTotal.setSelectionBackground(new Color(214,217,223));
		tabelTotal.setSelectionForeground(Color.BLACK);
		tabelTotal.setBorder( new MatteBorder(0, 1, 1, 1, Color.black));
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tabelTotal.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		kanan.add(tabelTotal);
		
		JButton CheckOut = new JButton("Check Out");
		CheckOut.setPreferredSize(new Dimension(550,40));
		kanan.add(CheckOut);

		backToMainMenu.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new MainCustomer().openMainMenu();

	}
});
		CheckOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Cart.CartDetail.size()==0) {
					JOptionPane.showMessageDialog(getParent(), "Please put the order into the Cart first in Add Order","Alert",JOptionPane.WARNING_MESSAGE);
				}else {					
					viewMyCart.dariAdd = true;
					new MainCustomer().openViewCart();
				}
			}
		});
	
	tabelKanan.addMouseListener(new MouseListener() {
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			String namaMenu = tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 1).toString(),
					Source = null;
			int price = (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 2),
					Quantity = (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 3),
					IdCategory=0;
			for (int j = 0; j < Cart.CartDetail.size(); j++) {
				
				if (Cart.CartDetail.get(j)[0].equals(namaMenu)) {
					IdCategory = (int) Cart.CartDetail.get(j)[4];
					Source =  (String) Cart.CartDetail.get(j)[6];
				}
			}	  
			
			categoryNull.setText(Category[IdCategory-1]);
			namaMenuNull.setText(namaMenu);
			hargaNull.setText(String.valueOf(price));
			quantitySpinner.setValue(Quantity);
			
			atasKanan22.remove(labelImageKanan);
			try {
				gambarKanan = ImageIO.read(getClass().getResource(Source));			
			} catch (Exception e1) {
				System.out.println("gagal Render Bro");
			}
			Image imageToIcon = gambarKanan.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon imageCart = new ImageIcon(imageToIcon);
			labelImageKanan = new JLabel(imageCart);
			labelImageKanan.setPreferredSize(new Dimension(200,200));
			atasKanan22.add(labelImageKanan);
			
			atasKanan21.add(RemovefromCartButton);
			
			revalidate();
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
	if (Cart.CartDetail.isEmpty() == false) {
		
		int TotalPrice = 0;
		for (int a = 0; a < Cart.CartDetail.size(); a++) {
			
			
			String 
			namaCustomer = (String) Cart.CartHeader.get(0),
			namaMenu = (String) Cart.CartDetail.get(a)[0],
			source = (String) Cart.CartDetail.get(a)[6];
			;
			int 
			no = deftabKanan.getRowCount(),
			IDCategory = (Integer) Cart.CartDetail.get(a)[4],
			idMenu = (Integer) Cart.CartDetail.get(a)[5],
			price = (Integer) Cart.CartDetail.get(a)[1],
			quantityINT = (Integer) Cart.CartDetail.get(a)[2],
			total = (Integer) Cart.CartDetail.get(a)[3];
		fieldEnterName.setText(namaCustomer);	
		
		deftabKanan.addRow(new Object[] {
				 no+1,namaMenu,price,quantityINT,total
		 });
		totalPrice = totalPrice+total;
		}
	tabelTotal.setValueAt(totalPrice, 0, 1);	
	}
	
	}
}
