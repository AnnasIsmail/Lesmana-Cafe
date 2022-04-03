package Customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Cart.Cart;
import order.orderAdd;

public class viewMyCart extends JInternalFrame {

	public viewMyCart() {

		if (Cart.CartDetail.size() == 0) {
			JOptionPane.showMessageDialog(getParent(), "Please put the order into the Cart first in Add Order","Alert",JOptionPane.WARNING_MESSAGE);
			new MainCustomer().openMainMenu();
		}else {
			
		ui();
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		
		}
	}
	
	String OrderStatus = "";
	public static boolean dariAdd;
	public static boolean validMasuk;
	BufferedImage gambar = null;
	ImageIcon imageCart ;
	JTextField fieldEnterName;
	JPanel panelTengahAtas;
	JLabel labelImage;
	
	private void ui() {
		
		String nama = (String) Cart.CartHeader.get(0);
		
		String[] Category = {
				"Coffe","Non Coffe","Snack","Heavy Meal","Desserts"	
		};	
		
		Object[] kolomCart = {"No","Image","Menu Name","Price","Quantity","Total Price"};
		Object[][] dataCart = {};
		
		JPanel panelAtas = new JPanel();
		panelAtas.setBorder(new EmptyBorder(15,0,0,0));
		add(panelAtas, BorderLayout.PAGE_START);
		
		String buttonBack = null;
		
		if (dariAdd == true) {
			buttonBack = "< Back To Add Cart";
		}else {
			buttonBack = "< Back To Main Menu";
		}
		
		JButton backToMainMenu = new JButton(buttonBack);
		backToMainMenu.setVerticalAlignment(SwingConstants.CENTER);
		panelAtas.add(backToMainMenu,BorderLayout.LINE_START);
		
		JLabel MyCart = new JLabel("MY CART");
		MyCart.setFont(new Font("Calibri", Font.BOLD,40));
		MyCart.setBorder(new EmptyBorder(2,380,0,430));
		panelAtas.add(MyCart);
		
		JPanel panelTengah = new JPanel(new BorderLayout() );
		add(panelTengah, BorderLayout.CENTER);
		
		JPanel panelTabel = new JPanel(new GridLayout());
		panelTabel.setBorder(new EmptyBorder(0,30,20,0));
		panelTengah.add(panelTabel,BorderLayout.CENTER);
		
		DefaultTableModel deftabKanan  = new DefaultTableModel(dataCart,kolomCart){
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
		scrollTabKanan.setPreferredSize(new Dimension(500,700));
		panelTabel.add(scrollTabKanan,BorderLayout.CENTER);
		
		tabelKanan.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		tabelKanan.setFont(new Font("Calibri", Font.PLAIN, 18));
		tabelKanan.setRowHeight(100);
		tabelKanan.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelKanan.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelKanan.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabelKanan.getColumnModel().getColumn(3).setPreferredWidth(70);
		tabelKanan.getColumnModel().getColumn(4).setPreferredWidth(50);
		tabelKanan.getColumnModel().getColumn(5).setPreferredWidth(70);
				
		for (int i = 0; i < Cart.CartDetail.size(); i++) {
			String menuName = (String) Cart.CartDetail.get(i)[0];
			int menuPrice = (Integer) Cart.CartDetail.get(i)[1];
			int quantityMenu =(Integer) Cart.CartDetail.get(i)[2];
			int totalPrice = (Integer) Cart.CartDetail.get(i)[3];
			String source = (String) Cart.CartDetail.get(i)[6];
			
			BufferedImage gambarTabel = null;
			try {
				gambarTabel = ImageIO.read(getClass().getResource(source));			
			} catch (Exception e) {
				System.out.println("gagal Render Bro");
			}
			Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon imageTable = new ImageIcon(imageToIconTabel);
		
		deftabKanan.addRow(new Object[] {
				i+1,imageTable,menuName,menuPrice,quantityMenu,totalPrice
		});
		}
		
		JPanel panelKanan = new JPanel(new GridLayout(0,1));
		panelTengah.add(panelKanan,BorderLayout.LINE_END);
		
		panelTengahAtas = new JPanel(new GridLayout());
		panelKanan.add(panelTengahAtas);
		
		try {
			gambar = ImageIO.read(getClass().getResource("/image/warna dasar.png"));			
		} catch (Exception e) {
			System.out.println("gagal Render Bro");
		}
		Image imageToIcon = gambar.getScaledInstance(370, 370, Image.SCALE_SMOOTH);
		imageCart = new ImageIcon(imageToIcon);
		labelImage = new JLabel(imageCart);
		labelImage.setPreferredSize(new Dimension(370,370));
		panelTengahAtas.add(labelImage);
		
		JPanel panelTengahKanan = new JPanel(new GridLayout(0,2,5,9));
		panelTengahKanan.setBorder(new EmptyBorder(5,20,20,20));
 		panelKanan.add(panelTengahKanan);
		
		JLabel EnterName = new JLabel("Your Name : ");
		EnterName.setFont(new Font("Calibri", Font.PLAIN,20));
		EnterName.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(EnterName,BorderLayout.PAGE_END);
		
		fieldEnterName = new JTextField(10);
		fieldEnterName.setFont(new Font("Calibri", Font.PLAIN,20));
		fieldEnterName.setText(nama);
		panelTengahKanan.add(fieldEnterName,BorderLayout.PAGE_END);
		
		JLabel CategoryLabel = new JLabel("Category : ");
		CategoryLabel.setFont(new Font("Calibri", Font.PLAIN,20));
		CategoryLabel.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(CategoryLabel);
		
		JLabel categoryNull = new JLabel("null");
		categoryNull.setFont(new Font("Calibri", Font.PLAIN,20));
		categoryNull.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(categoryNull);
		
		JLabel namaMenuLabel = new JLabel("Nama Menu : ");
		namaMenuLabel.setFont(new Font("Calibri", Font.PLAIN,20));
		namaMenuLabel.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(namaMenuLabel);
		
		JLabel namaMenuNull = new JLabel("null");
		namaMenuNull.setFont(new Font("Calibri", Font.PLAIN,20));
		namaMenuNull.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(namaMenuNull);
		
		JLabel harga = new JLabel("Price : ");
		harga.setFont(new Font("Calibri", Font.PLAIN,20));
		harga.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(harga);
		
		JLabel hargaNull = new JLabel("null");
		hargaNull.setFont(new Font("Calibri", Font.PLAIN,20));
		hargaNull.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(hargaNull);
		
		JButton removeFromCart = new JButton("Remove From Cart");
		removeFromCart.setFont(new Font("Calibri", Font.PLAIN,18));
		panelTengahKanan.add(removeFromCart);
		
		JPanel ganjelan = new JPanel();
		panelTengahKanan.add(ganjelan);
		
		JLabel labelTD = new JLabel("Take Away / Dine In : ");
		labelTD.setFont(new Font("Calibri", Font.PLAIN,20));
		labelTD.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(labelTD);
		
		JPanel GroupingTD = new JPanel();
		panelTengahKanan.add(GroupingTD);
		
		JRadioButton takeAway = new JRadioButton("Take Away");
		takeAway.setActionCommand("Take Away");
		takeAway.setFont(new Font("Calibri", Font.PLAIN,18));
		takeAway.setBorder(new EmptyBorder(0,0,25,0));
		GroupingTD.add(takeAway);
		
		JRadioButton dineIn = new JRadioButton("Dine In");
		dineIn.setActionCommand("Dine In");
		dineIn.setFont(new Font("Calibri", Font.PLAIN,18));
		dineIn.setBorder(new EmptyBorder(0,0,25,0));
		GroupingTD.add(dineIn);

		ButtonGroup bgTD = new ButtonGroup();
		bgTD.add(dineIn);
		bgTD.add(takeAway);
		
		JLabel Totalharga = new JLabel("Total Price : ");
		Totalharga.setFont(new Font("Calibri", Font.PLAIN,20));
		Totalharga.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(Totalharga);
		
		JLabel TotalhargaNull = new JLabel(String.valueOf(Cart.CartHeader.get(1)));
		TotalhargaNull.setFont(new Font("Calibri", Font.PLAIN,20));
		TotalhargaNull.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(TotalhargaNull);
		
		JLabel paymentMethodLabel = new JLabel("Payment Method : ");
		paymentMethodLabel.setFont(new Font("Calibri", Font.PLAIN,20));
		paymentMethodLabel.setVerticalAlignment(SwingConstants.CENTER);
		panelTengahKanan.add(paymentMethodLabel);
		
		String[] PaymentMethod = {"Cash","Card","OVO","GOPAY"};
		JComboBox paymentMethodComboBox = new JComboBox<>(PaymentMethod);
		paymentMethodComboBox.setFont(new Font("Calibri", Font.PLAIN,18));
		panelTengahKanan.add(paymentMethodComboBox);
		
		JButton Pay = new JButton("Pay");
		Pay.setFont(new Font("Calibri", Font.PLAIN,18));
		panelTengahKanan.add(Pay);
		
		JPanel panelBawah = new JPanel();
		add(panelBawah, BorderLayout.PAGE_END);
		
		
		backToMainMenu.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (dariAdd == true) {
			new MainCustomer().openNewOrder();;
		}else {
			new MainCustomer().openMainMenu();
		}
		
		
	}
});

		tabelKanan.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				String namaMenu = tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 2).toString(),
						Source = null;			
				int idCategory = 0,price = 0;
				
				for (int j = 0; j < Cart.CartDetail.size(); j++) {
					if (Cart.CartDetail.get(j)[0].equals(namaMenu)) {

						idCategory = (int) Cart.CartDetail.get(j)[4];
						namaMenu = (String) Cart.CartDetail.get(j)[0];
						price = (int) Cart.CartDetail.get(j)[1];
						Source =  (String) Cart.CartDetail.get(j)[6];
					}}
				
				
				categoryNull.setText(Category[idCategory-1]);
				namaMenuNull.setText(namaMenu);
				hargaNull.setText(String.valueOf(price));

				panelTengahAtas.remove(labelImage);
				try {
					gambar = ImageIO.read(getClass().getResource(Source));			
				} catch (Exception e1) {
					System.out.println("gagal Render Bro");
				}
				Image imageToIcon = gambar.getScaledInstance(370, 370, Image.SCALE_SMOOTH);
				imageCart = new ImageIcon(imageToIcon);
				labelImage = new JLabel(imageCart);
				labelImage.setPreferredSize(new Dimension(370,370));
				panelTengahAtas.add(labelImage);
				
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
		
		removeFromCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabelKanan.getRowCount() == 0) {
					JOptionPane.showMessageDialog(getParent(), "Please fill in the Cart on the Add Cart Page first'","Alert",JOptionPane.WARNING_MESSAGE);
					new MainCustomer().openNewOrder();
				}else if (tabelKanan.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(getParent(), "Please select which variant you want to delete");
				}else {
					int row =  (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 0);
					deftabKanan.removeRow(row-1);
					Cart.CartDetail.remove(row-1);
					
					int hapusSeluruhRow = tabelKanan.getRowCount();
					while(tabelKanan.getRowCount() >0) {
						deftabKanan.removeRow(hapusSeluruhRow-1);
						hapusSeluruhRow--;
					}
					
					int TotalPrice = 0;
					
					for (int i = 0; i < Cart.CartDetail.size(); i++) {
						String menuName = (String) Cart.CartDetail.get(i)[0];
						int menuPrice = (Integer) Cart.CartDetail.get(i)[1];
						int quantityMenu =(Integer) Cart.CartDetail.get(i)[2];
						int totalPrice = (Integer) Cart.CartDetail.get(i)[3];
						String source = (String) Cart.CartDetail.get(i)[6];
						
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
						TotalPrice = TotalPrice + totalPrice;
					}
					
					Cart.CartHeader.set(1, TotalPrice);
					TotalhargaNull.setText(String.valueOf(TotalPrice));
				}
			}
		});
		
		
		Pay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabelKanan.getRowCount() == 0) {
					JOptionPane.showMessageDialog(getParent(), "Please fill in the Cart on the Add Cart Page first.","Alert",JOptionPane.WARNING_MESSAGE);
					new MainCustomer().openNewOrder();
				}else if (bgTD.isSelected(null) == true) {
					JOptionPane.showMessageDialog(getParent(), "Take Away or Dine In Option Must be chosen'","Alert",JOptionPane.WARNING_MESSAGE);
				}else {
					String orderName = fieldEnterName.getText();
					String td = bgTD.getSelection().getActionCommand();
					String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
					int orderTotalPurchase = Integer.parseInt(TotalhargaNull.getText());
					
					
					JFrame qr = new JFrame("Silahkan Scan Disini");
					qr.setResizable(false);
					
					if (paymentMethod.equals("Cash")) {						
						
						OrderStatus = "Unpaid";
						
						try {
							new orderAdd(orderName, td, paymentMethod, orderTotalPurchase,OrderStatus);
						} catch (Exception e2) {
							System.out.println(e2);
						}
						
						if ( validMasuk == true ) {
							Cart.CartHeader.clear();
							Cart.CartDetail.clear();
							JOptionPane.showMessageDialog(getParent(), "Please wait for the staff to pay for your order!");
							new MainCustomer().openMainMenu();
						}			
						
					}else if (paymentMethod.equals("Card")) {
						boolean inputBenar = false;
						
						while (inputBenar == false) {
							String idCard =null;	
							Long idcardConvert;
								 idCard =JOptionPane.showInputDialog(null, "Enter your Card Number!", "Input Card Number", JOptionPane.INFORMATION_MESSAGE);
								inputBenar = true;
								if (idCard == null) {
									inputBenar = true;
									return;
								}else {
									try {
										idcardConvert = Long.parseLong(idCard);
										setNumCard(idcardConvert);
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(getParent(), "Please Enter Valid Number","Alert",JOptionPane.WARNING_MESSAGE);
										inputBenar = false;
									}
								}
						}
						
						OrderStatus = "Paid";
						
						try {
							new orderAdd(orderName, td, paymentMethod, orderTotalPurchase,OrderStatus);
						} catch (Exception e2) {
							System.out.println(e2);
						}
						
						if ( validMasuk == true ) {
							Cart.CartHeader.clear();
							Cart.CartDetail.clear();
							JOptionPane.showMessageDialog(getParent(), "Please Take Your Receipt!");
							new MainCustomer().openMainMenu();
						}			
						
					}else if (paymentMethod.equals("OVO") ) {
						
						OrderStatus = "Paid";
						BufferedImage gambarTabel = null;
						try {
							gambarTabel = ImageIO.read(getClass().getResource("/image/OVO.jpg"));			
						} catch (Exception e2) {
							System.out.println("gagal Render Bro");
						}
						Image imageToIconTabel = gambarTabel.getScaledInstance(400,567, Image.SCALE_SMOOTH);
						ImageIcon imageTable = new ImageIcon(imageToIconTabel);
						JLabel imageLabel = new JLabel(imageTable);
						qr.add(imageLabel);
						qr.setVisible(true);
						qr.setSize(420,617);
						qr.setLocationRelativeTo(null);
						
						qr.addWindowListener(new WindowListener() {
							
							@Override
							public void windowOpened(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowIconified(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowDeiconified(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowDeactivated(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowClosing(WindowEvent e) {
								// TODO Auto-generated method stub
								
								qr.setVisible(false);
								
								try {
									new orderAdd(orderName, td, paymentMethod, orderTotalPurchase,OrderStatus);
								} catch (Exception e2) {
									System.out.println(e2);
								}
								
								if ( validMasuk == true ) {
									Cart.CartHeader.clear();
									Cart.CartDetail.clear();
									JOptionPane.showMessageDialog(getParent(), "Please Take Your Purchase Receipt!");
									new MainCustomer().openMainMenu();
								}					
							}
							
							@Override
							public void windowClosed(WindowEvent e) {
							}
							
							@Override
							public void windowActivated(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
						});
						
						
					}else if ( paymentMethod.equals("GOPAY")) {
						
						BufferedImage gambarTabel = null;
						try {
							gambarTabel = ImageIO.read(getClass().getResource("/image/GOPAY.jpeg"));			
						} catch (Exception e2) {
							System.out.println("gagal Render Bro");
						}
						Image imageToIconTabel = gambarTabel.getScaledInstance(510,720, Image.SCALE_SMOOTH);
						ImageIcon imageTable = new ImageIcon(imageToIconTabel);
						JLabel imageLabel = new JLabel(imageTable);
						qr.add(imageLabel);
						qr.setVisible(true);
						qr.setSize(530,770);
						qr.setLocationRelativeTo(null);
						
						qr.addWindowListener(new WindowListener() {
							
							@Override
							public void windowClosing(WindowEvent e) {
								qr.setVisible(false);
								
								try {
									new orderAdd(orderName, td, paymentMethod, orderTotalPurchase,OrderStatus);
								} catch (Exception e2) {
									System.out.println(e2);
								}
								
								if ( validMasuk == true ) {
									Cart.CartHeader.clear();
									Cart.CartDetail.clear();
									JOptionPane.showMessageDialog(getParent(), "Please Take Your Purchase Receipt!");
									new MainCustomer().openMainMenu();
								}					
							}
							
							@Override
							public void windowOpened(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowIconified(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowDeiconified(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowDeactivated(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowClosed(WindowEvent e) {
							}
							
							@Override
							public void windowActivated(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
						});
						
						OrderStatus = "Paid";
					}
				}
			}
		});
}
	private long numCard = 0;
	
	public void setNumCard(long numCard) {
		this.numCard = numCard;
	}
	
	public Long getNumCard() {
		return numCard;
	}
}
