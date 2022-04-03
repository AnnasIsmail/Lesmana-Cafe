package Staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Income.incomeData;

public class viewIncome extends JInternalFrame{

	public viewIncome() {
		setLayout(new BorderLayout());
		ui();
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	}

public static boolean frameTerbuka = false;
LocalDateTime DateNow = LocalDateTime.now();  
DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
String DateNowFormat = DateNow.format(FormatDate);

	private void ui() {
		JPanel northPanel, leftTitlePanel, centerPanel, westPanel, rightTitlePanel, eastPanel;
		
		String leftColumn[] = {"Income ID", "Date", "Order done", "Total Income"};
		Object leftData[][] ={};
		String rightColumn[] = {"Income ID", "Order ID", "Order Name", "Staff Name", "Total Price"	};
		Object rightData[][] = {};
		
		JLabel leftTitleLabel, dailyIncome, rightTitleLabel;
		
		northPanel = new JPanel();
		
		centerPanel = new JPanel(new GridLayout(0,2,10,0));
		
		leftTitlePanel = new JPanel();
		westPanel = new JPanel(new BorderLayout());
		
		rightTitlePanel = new JPanel();
		eastPanel= new JPanel(new BorderLayout());
		
		leftTitleLabel = new JLabel("View Income");
		leftTitleLabel.setBorder(new EmptyBorder(0,350,0,470));
		leftTitleLabel.setFont(new Font("Calibri", Font.BOLD, 33));
		
		dailyIncome = new JLabel("Daily Income");
		dailyIncome.setFont(new Font("Calibri", Font.BOLD, 24));
		leftTitlePanel.add(dailyIncome);
		
		try {
			new incomeData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		DefaultTableModel deftabKiri = new DefaultTableModel(leftData,leftColumn);
			JTable tabelKiri= new JTable(deftabKiri){
				public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
				TableRowSorter tabSortKiri = new TableRowSorter<>(deftabKiri);
				tabelKiri.setRowSorter(tabSortKiri);
				JScrollPane scrollTabKiri = new JScrollPane(tabelKiri);
				scrollTabKiri.setBorder( new MatteBorder(1, 1, 1, 1, Color.black));
			
				tabelKiri.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
				tabelKiri.setFont(new Font("Calibri", Font.PLAIN, 18));
				tabelKiri.setRowHeight(30);
				
		for (int i = 0; i < incomeData.incomeHeader.size(); i++) {
			if ( incomeData.incomeHeader.get(i)[1].equals(DateNowFormat) ) {
				deftabKiri.addRow(new Object[] {
						incomeData.incomeHeader.get(i)[0] + "       (Today)",incomeData.incomeHeader.get(i)[1],incomeData.incomeHeader.get(i)[2],incomeData.incomeHeader.get(i)[3]
				});
			}else {
			deftabKiri.addRow(new Object[] {
					incomeData.incomeHeader.get(i)[0] + "       ",incomeData.incomeHeader.get(i)[1],incomeData.incomeHeader.get(i)[2],incomeData.incomeHeader.get(i)[3]
			});
			}
		}
			
		rightTitleLabel = new JLabel("Income Details");
		rightTitleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		rightTitlePanel.add(rightTitleLabel);
		
		DefaultTableModel deftabKanan  = new DefaultTableModel(rightData,rightColumn);
		JTable tabelKanan= new JTable(deftabKanan){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {return false;}};
		TableRowSorter tabSortKanan = new TableRowSorter<>(deftabKanan);
		tabelKanan.setRowSorter(tabSortKanan);
		JScrollPane scrollTabKanan = new JScrollPane(tabelKanan);
		scrollTabKanan.setBorder( new MatteBorder(1, 1, 1, 1, Color.black));
		
		tabelKanan.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		tabelKanan.setFont(new Font("Calibri", Font.PLAIN, 18));
		tabelKanan.setRowHeight(30);
		tabelKanan.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelKanan.getColumnModel().getColumn(1).setPreferredWidth(0);
		tabelKanan.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabelKanan.getColumnModel().getColumn(3).setPreferredWidth(70);
		tabelKanan.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		JButton backToMainMenu = new JButton("< Back To Main Menu");
		backToMainMenu.setHorizontalAlignment(SwingConstants.LEFT);
		northPanel.add(backToMainMenu);
		northPanel.add(leftTitleLabel);
		
		westPanel.add(leftTitlePanel, BorderLayout.NORTH);
		westPanel.add(scrollTabKiri, BorderLayout.CENTER);
		
		eastPanel.add(rightTitlePanel, BorderLayout.NORTH);
		eastPanel.add(scrollTabKanan, BorderLayout.CENTER);
		
		centerPanel.add(westPanel);
		centerPanel.add(eastPanel);
		
		add(northPanel, BorderLayout.NORTH);
		northPanel.setBorder(new EmptyBorder(10,0,0,0));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBorder(new EmptyBorder(0,10,10,10));
		
		backToMainMenu.addActionListener(new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (frameTerbuka == true) {
			new MainStaff().closeOrderINIncome();
			repaint();
			revalidate();
		}
		
		new MainStaff().openMainMenu();
	}
});
		tabelKiri.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (frameTerbuka == true) {
					new MainStaff().closeOrderINIncome();
					repaint();
					revalidate();
				}
				
				int id = Integer.parseInt( tabelKiri.getValueAt(tabelKiri.getSelectedRow(), 0).toString().substring(0,4).trim());
				deftabKanan.setRowCount(0);
				
				for (int i = 0; i < incomeData.incomeDetail.size(); i++) {
					if (id == (int) incomeData.incomeDetail.get(i)[0]) {
						deftabKanan.addRow(new Object[] {
								incomeData.incomeDetail.get(i)[0],incomeData.incomeDetail.get(i)[1],incomeData.incomeDetail.get(i)[2],incomeData.incomeDetail.get(i)[3],incomeData.incomeDetail.get(i)[4]
						});
					}
				}
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
		
		
		tabelKanan.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (frameTerbuka == true) {
					new MainStaff().closeOrderINIncome();
					repaint();
					revalidate();
				}
				
				int id = (int) tabelKanan.getValueAt(tabelKanan.getSelectedRow(), 1);
			
				new MainStaff().openOrderINIncome(id);
				
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
		
		
	}

}
