package Staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.nio.file.DirectoryStream.Filter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Cart.Cart;
import Customer.MainCustomer;
import Inventory.*;

public class viewInventory extends JInternalFrame{

	public viewInventory() {
		
		try {
			new Inventory();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ui();
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	}

	String[] Category = { "Coffe","Non Coffe","Snack","Heavy Meal","Desserts"};	
	Object[] kolomCart = {"Image","Category","ID","Menu Name","Price","Stock"};
	Object[][] dataCart = {};
	String fileName = null;
	File dest = new File("C:\\Users\\annas\\OneDrive\\Desktop\\eclipse-workspace\\FinalProject\\src\\image\\a\\");
	JComboBox categoryCombo = new JComboBox(Category);
	JTextField fieldInvenName =  new JTextField();
	JSpinner spinPrice = new JSpinner(new SpinnerNumberModel(1000, 1000, 100000, 1000));
	JSpinner spinStock = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
	JLabel noImageChoose =  new JLabel("No Image");
	JSpinner id = new JSpinner();	
	DefaultTableModel deftabKanan;
	JTable tabelKanan;
	
	private void ui() {
		JPanel panelAtas = new JPanel();
		panelAtas.setBorder(new EmptyBorder(15,0,0,0));
		add(panelAtas, BorderLayout.PAGE_START);
		
		JButton backToMainMenu = new JButton("< Back To Main Menu");
		backToMainMenu.setVerticalAlignment(SwingConstants.CENTER);
		backToMainMenu.setFont(new Font("Calibri", Font.PLAIN,18));
		panelAtas.add(backToMainMenu,BorderLayout.LINE_START);
		
		JButton refresh = new JButton("' Refresh");
		refresh.setVerticalAlignment(SwingConstants.CENTER);
		refresh.setFont(new Font("Calibri", Font.PLAIN,18));
		panelAtas.add(refresh,BorderLayout.LINE_START);
		
		JLabel InventoryLabel = new JLabel("INVENTORY");
		InventoryLabel.setFont(new Font("Calibri", Font.BOLD,40));
		InventoryLabel.setBorder(new EmptyBorder(2,150,0,100));
		panelAtas.add(InventoryLabel);
		
		JButton addMenu = new JButton("+ Add Menu");
		addMenu.setVerticalAlignment(SwingConstants.CENTER);
		addMenu.setFont(new Font("Calibri", Font.PLAIN,18));
		panelAtas.add(addMenu,BorderLayout.LINE_END);
		
		JButton updateMenu = new JButton("* Update Menu");
		updateMenu.setVerticalAlignment(SwingConstants.CENTER);
		updateMenu.setFont(new Font("Calibri", Font.PLAIN,18));
		updateMenu.setEnabled(false);
		panelAtas.add(updateMenu,BorderLayout.LINE_END);
	
		JButton deleteMenu = new JButton("- Delete Menu");
		deleteMenu.setVerticalAlignment(SwingConstants.CENTER);
		deleteMenu.setFont(new Font("Calibri", Font.PLAIN,18));
		deleteMenu.setEnabled(false);
		panelAtas.add(deleteMenu,BorderLayout.LINE_END);
		
		JPanel panelTengah = new JPanel(new BorderLayout() );
		add(panelTengah, BorderLayout.CENTER);
		
		
		deftabKanan  = new DefaultTableModel(dataCart,kolomCart){
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==0) return ImageIcon.class;
                return Object.class;
            } };
		tabelKanan= new JTable(deftabKanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tabSortKanan = new TableRowSorter<>(deftabKanan);
		tabelKanan.setRowSorter(tabSortKanan);
		JScrollPane scrollTabKanan = new JScrollPane(tabelKanan);
		scrollTabKanan.setPreferredSize(new Dimension(500,700));
		panelTengah.add(scrollTabKanan,BorderLayout.CENTER);
		
		tabelKanan.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		tabelKanan.setFont(new Font("Calibri", Font.PLAIN, 18));
		tabelKanan.setRowHeight(100);
		tabelKanan.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabelKanan.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabelKanan.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabelKanan.getColumnModel().getColumn(3).setPreferredWidth(200);
		tabelKanan.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabelKanan.getColumnModel().getColumn(5).setPreferredWidth(100);
				
		for (int i = 0; i < Inventory.AllData.size(); i++) {
			int CategoryID = (Integer) Inventory.AllData.get(i)[0];
			int InventoryID = (Integer) Inventory.AllData.get(i)[1];
			String menuName = (String) Inventory.AllData.get(i)[2];
			int menuPrice = (Integer) Inventory.AllData.get(i)[3];
			int stockMenu =(Integer) Inventory.AllData.get(i)[4];
			String source = (String) Inventory.AllData.get(i)[5];
			
			BufferedImage gambarTabel = null;
			try {
				gambarTabel = ImageIO.read(getClass().getResource(source));			
			} catch (Exception e) {
				System.out.println(e);
			}
			Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon imageTable = new ImageIcon(imageToIconTabel);
			String categoryName = Category[CategoryID-1];
			
		deftabKanan.addRow(new Object[] {
				imageTable,categoryName,InventoryID,menuName,menuPrice,stockMenu
		});
		}
	
		backToMainMenu.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new MainStaff().openMainMenu();
		
	}
});
		
		addMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kosongkanField();
				addOrEditProduct("add");
			}
		});
		
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addOrEditProduct("update");
				
			}
		});
		
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int j =JOptionPane.showConfirmDialog(getParent(), "Do you want to delete the menu "+fieldInvenName.getText());
				
				if (j == JOptionPane.YES_OPTION) {
					try {
						new deleteInventory((int) id.getValue());
						deftabKanan.setRowCount(0);
						
						new Inventory();
						
						for (int i = 0; i < Inventory.AllData.size(); i++) {
							int CategoryID = (Integer) Inventory.AllData.get(i)[0];
							int InventoryID = (Integer) Inventory.AllData.get(i)[1];
							String menuName = (String) Inventory.AllData.get(i)[2];
							int menuPrice = (Integer) Inventory.AllData.get(i)[3];
							int stockMenu =(Integer) Inventory.AllData.get(i)[4];
							String source = (String) Inventory.AllData.get(i)[5];
							
							BufferedImage gambarTabel = null;
							try {
								gambarTabel = ImageIO.read(getClass().getResource(source));			
							} catch (Exception e1) {
								System.out.println("gagal Render Bro");
							}
							Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
							ImageIcon imageTable = new ImageIcon(imageToIconTabel);
							String categoryName = Category[CategoryID-1];
							
						deftabKanan.addRow(new Object[] {
								imageTable,categoryName,InventoryID,menuName,menuPrice,stockMenu
						});
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}
		});
		
		tabelKanan.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
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
				String StringCategory =  (String) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 1);
				int idInventory =  (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 2);
				String nameInventory =  (String) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 3);
				int priceInventory =  (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 4);
				int stockInvemtory =  (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 5);
				
				if (idInventory == (int) id.getValue()) {
					tabelKanan.setSelectionMode(0);
					addMenu.setEnabled(true);
					updateMenu.setEnabled(false);
					deleteMenu.setEnabled(false);
					id.setValue(0);
				}else {
					
				
				if (tabelKanan.getSelectedRow() >= 0 ) {
					addMenu.setEnabled(false);
					updateMenu.setEnabled(true);
					deleteMenu.setEnabled(true);
					
					String source = "";
					
					for (int i = 0; i < Inventory.AllData.size(); i++) {
						if ((int)Inventory.AllData.get(i)[1] == idInventory) {
							source = (String) Inventory.AllData.get(i)[5];
						}
					}
					
					int idCategory=0;
					
					for (int j = 0; j < Category.length; j++) {
						if (StringCategory.equals(Category[j])) {
							idCategory = j;
						}
					}
					
					categoryCombo.setSelectedIndex(idCategory);
					id.setValue(idInventory);
					fieldInvenName.setText(nameInventory);
					spinPrice.setValue(priceInventory);
					spinStock.setValue(stockInvemtory);
					noImageChoose.setText(source);
					fileName = source;
				}
				}
				
			}
		});
		
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainStaff().openInventory();
				
			}
		});
		
	}

	public void kosongkanField() {
		categoryCombo.setSelectedIndex(0);
		id.setValue(0);
		fieldInvenName.setText("");
		spinPrice.setValue(1000);
		spinStock.setValue(1);
		noImageChoose.setText("No Image");
		fileName = "";
	}
	
	public void addOrEditProduct(String doIng) {
		JFrame FrameUtama = new JFrame();
		if (doIng.equals("add")) {
			FrameUtama.setTitle("Add New Menu");
		}else if(doIng.equals("update")) {
			FrameUtama.setTitle("Update Menu");
		}else if (doIng.equals("delete")) {
			FrameUtama.setTitle("Delete Menu");
		}
		
		JPanel JpanelUtama = new JPanel();
		JpanelUtama.setLayout(new GridLayout(0,2));
		JpanelUtama.setBorder(new EmptyBorder(3,10,10,5));
		FrameUtama.add(JpanelUtama);
		
		JLabel categoryLabel = new JLabel("Category");
		JpanelUtama.add(categoryLabel);
		
		JpanelUtama.add(categoryCombo);
		
		JLabel invenNameLabel = new JLabel("Inventory Name");
		JpanelUtama.add(invenNameLabel);
		
		JpanelUtama.add(fieldInvenName);
		
		JLabel priceLabel = new JLabel("Price");
		JpanelUtama.add(priceLabel);		
		
		JpanelUtama.add(spinPrice);
		
		JLabel stockLabel = new JLabel("Stock");
		JpanelUtama.add(stockLabel);
		
		JpanelUtama.add(spinStock);
		
		JLabel imageLabel = new JLabel("Choose Image");
		JpanelUtama.add(imageLabel);
		
		JPanel panelBrowse = new JPanel();
		JpanelUtama.add(panelBrowse);
		
		noImageChoose.setPreferredSize(new Dimension(100,10));
		panelBrowse.add(noImageChoose);
		
		JButton chooseImageButton = new JButton("Browse");
		panelBrowse.add(chooseImageButton);
		
		chooseImageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser imageChoose = new JFileChooser();
				imageChoose.showOpenDialog(null);
				try {
					
					if (imageChoose.getSelectedFile().getAbsolutePath().equals("")) {
						System.out.println("masuk");
					}else {
						fileName = imageChoose.getSelectedFile().getAbsolutePath();							
						noImageChoose.setText(fileName);
					}
				} catch (Exception e2) {
					
				}
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		JpanelUtama.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameUtama.setVisible(false);
				
			}
		});
		
		JButton confirmButton = new JButton("Confirm");
		JpanelUtama.add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int getCategoryId = categoryCombo.getSelectedIndex()+1;
				String inventoryName = fieldInvenName.getText();
				int inventoryPrice = (int) spinPrice.getValue();
				int inventoryStock = (int) spinStock.getValue();
			
				if (inventoryName.equals("")) {
					JOptionPane.showMessageDialog(getParent(), "Please fill in the Inventory Name'","Alert",JOptionPane.WARNING_MESSAGE);
				}else if (inventoryPrice == 1000) {
					JOptionPane.showMessageDialog(getParent(), "Price Inventory cannot be 0'","Alert",JOptionPane.WARNING_MESSAGE);
				}else if (inventoryStock == 1) {
					JOptionPane.showMessageDialog(getParent(), "Stock Inventory cannot be 0'","Alert",JOptionPane.WARNING_MESSAGE);
				}else if (fileName == null) {
					JOptionPane.showMessageDialog(getParent(), "Please fill in select Photo Inventory'","Alert",JOptionPane.WARNING_MESSAGE);
				}else {
					int awalPotong = fileName.lastIndexOf("\\");
					int panjangSource = fileName.length();
					String inventorySource = "";

					if (awalPotong == -1) {
					 inventorySource =  fileName.substring(awalPotong+1,panjangSource);
					}else {
						 inventorySource = "/image/" + fileName.substring(awalPotong+1,panjangSource);
					}
					
					if (doIng.equals("add")) {
						
						try {
							new addInventory(getCategoryId,inventoryName,inventoryPrice,inventoryStock,inventorySource);
							FrameUtama.setVisible(false);
							deftabKanan.setRowCount(0);
							
							new Inventory();
							
							for (int i = 0; i < Inventory.AllData.size(); i++) {
								int CategoryID = (Integer) Inventory.AllData.get(i)[0];
								int InventoryID = (Integer) Inventory.AllData.get(i)[1];
								String menuName = (String) Inventory.AllData.get(i)[2];
								int menuPrice = (Integer) Inventory.AllData.get(i)[3];
								int stockMenu =(Integer) Inventory.AllData.get(i)[4];
								String source = (String) Inventory.AllData.get(i)[5];
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
								String categoryName = Category[CategoryID-1];
								
							deftabKanan.addRow(new Object[] {
									imageTable,categoryName,InventoryID,menuName,menuPrice,stockMenu
							});
							}
						} catch (SQLException e1) {
							System.out.println(e1);
						}
					}else if(doIng.equals("update")) {
						try {
							new updateInventory(getCategoryId, inventoryName, inventoryPrice, inventoryStock, inventorySource, (int)id.getValue());
							FrameUtama.setVisible(false);
							deftabKanan.setRowCount(0);
							
							new Inventory();
							
							for (int i = 0; i < Inventory.AllData.size(); i++) {
								int CategoryID = (Integer) Inventory.AllData.get(i)[0];
								int InventoryID = (Integer) Inventory.AllData.get(i)[1];
								String menuName = (String) Inventory.AllData.get(i)[2];
								int menuPrice = (Integer) Inventory.AllData.get(i)[3];
								int stockMenu =(Integer) Inventory.AllData.get(i)[4];
								String source = (String) Inventory.AllData.get(i)[5];
								
								BufferedImage gambarTabel = null;
								try {
									gambarTabel = ImageIO.read(getClass().getResource(source));			
								} catch (Exception e1) {
									System.out.println("gagal Render Bro");
								}
								Image imageToIconTabel = gambarTabel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
								ImageIcon imageTable = new ImageIcon(imageToIconTabel);
								String categoryName = Category[CategoryID-1];
								
							deftabKanan.addRow(new Object[] {
									imageTable,categoryName,InventoryID,menuName,menuPrice,stockMenu
							});
							}
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}else if (doIng.equals("delete")) {
						
					}
				}
			}
		});
		
		
		FrameUtama.setVisible(true);
		FrameUtama.setSize(500,300);
		FrameUtama.setLocationRelativeTo(null);
		FrameUtama.setResizable(false);
	}
	
}
