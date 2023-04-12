package quanlysv;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FormQLPhong extends JFrame {
	private JTextField txtMaPhong;
	private JTextField txtMaKhu;
	private JTextField txtTenPhong;
	private JTextField txtTang;
	
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	
	private JTable tblDanhSachPhong;
	
	List<DanhSachPhong> dsPhong = new ArrayList<>();
	DBCPhong dbp = new DBCPhong();
	public FormQLPhong() {
		Container cp = getContentPane();
		JPanel pnMain = new JPanel();
		setContentPane(pnMain);
		pnMain.setLayout(new BorderLayout());
		
		pnMain.setBorder(new EmptyBorder(10,10,10,10));
		
		//pnTitle
		JPanel pnTitle = new JPanel(new FlowLayout());
		JLabel lblSinhVien = new JLabel("Quản lý phòng ");
		lblSinhVien.setFont(new Font("serif",Font.BOLD,20));
		pnTitle.add(lblSinhVien);
		pnMain.add(pnTitle,BorderLayout.PAGE_START);
		
		//pnCenter
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		//pnInput
		
		JPanel pnInput = new JPanel(new GridLayout(13,2,6,6));
		pnInput.add(new JLabel("Mã phòng:"));
		txtMaPhong = new JTextField();
		pnInput.add(txtMaPhong);
		pnInput.add(new JLabel("Mã khu:"));
		txtMaKhu = new JTextField();
		pnInput.add(txtMaKhu);
		pnInput.add(new JLabel("Tên phòng:"));
		txtTenPhong = new JTextField();
		pnInput.add(txtTenPhong);
		pnInput.add(new JLabel("Tầng:"));
		txtTang = new JTextField();
		pnInput.add(txtTang);
		
		
		pnCenter.add(pnInput);
		//pnButton
		JPanel pnButton = new JPanel(new FlowLayout());
		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maphong = txtMaPhong.getText();
				String makhu = txtMaKhu.getText();
				String tenphong = txtTenPhong.getText();
				String tang = txtTang.getText();
			
				DanhSachPhong dsp = new DanhSachPhong(maphong, makhu, tenphong, tang);
				DBCPhong.insert(dsp);
				JOptionPane.showMessageDialog(cp, "Lưu thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				loadData();
			}
		});
		pnButton.add(btnLuu);
		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maphong = txtMaPhong.getText();
				String makhu = txtMaKhu.getText();
				String tenphong = txtTenPhong.getText();
				String tang = txtTang.getText();
			
				DanhSachPhong dsp = new DanhSachPhong(maphong, makhu, tenphong, tang);
				DBCPhong.update(dsp);
				JOptionPane.showMessageDialog(cp, "Sửa thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				loadData();
			}
		});
		pnButton.add(btnSua);
		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					DanhSachPhong dsp = new DanhSachPhong();
					dsp.setMaPhong(txtMaPhong.getText());
					DBCPhong.delete(dsp.getMaPhong());
					JOptionPane.showMessageDialog(cp, "Xóa thành công !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					loadData();
				}
							
		});
		pnButton.add(btnXoa);
		pnCenter.add(pnButton);
		pnMain.add(pnCenter,BorderLayout.CENTER);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaPhong.setText("");
				txtMaKhu.setText("");
				txtTenPhong.setText("");
				txtTang.setText("");
				
			}
		});
		pnButton.add(btnLamMoi);
		//pnBottom
		JPanel pnBottom = new JPanel();
		tblDanhSachPhong = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblDanhSachPhong);
		pnBottom.add(scrollPane);
		pnMain.add(pnBottom,BorderLayout.EAST);
		
		setTitle("Quản lý phòng");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	public void loadData() {
		
		//Tạo table model
		DefaultTableModel model = new DefaultTableModel();
		//Lấy dữ liệu từ CSDL
		ResultSet rs = dbp.getAll();
		
		//load tiêu đề cột
		try {
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] arr = new String[colNumber];
			for (int i = 0; i < colNumber; i++) {
				arr[i] = rsMD.getColumnName(i+1);
			}
			model.setColumnIdentifiers(arr);
			//load dữ liệu 
			while(rs.next()) {
				for (int i = 0; i < colNumber; i++) {
					arr[i] = rs.getString(i+1);
				}
				model.addRow(arr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblDanhSachPhong.setModel(model);
	
	tblDanhSachPhong.addMouseListener(new MouseListener() {
		
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
			// TODO Auto-generated method stub
			int selectedRow = tblDanhSachPhong.getSelectedRow();
			if(selectedRow >= 0) {
				txtMaPhong.setText((String)(model.getValueAt(selectedRow, 0)));
				txtMaKhu.setText((String)(model.getValueAt(selectedRow, 1)));
				txtTenPhong.setText((String)(model.getValueAt(selectedRow, 2)));
				txtTang.setText((String)(model.getValueAt(selectedRow, 3)));
				
				
			}
			
		}
	});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FormQLPhong f = new FormQLPhong();
		f.loadData();
	}

}
