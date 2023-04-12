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

public class FormNV extends JFrame{
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtMaPhong;
	private JTextField txtGhiChu;
	
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	
	private JTable tblDanhSachNhanVien;
	
	List<DanhSachNhanVien> nhanVien = new ArrayList<>();
	DBCNhanVien dbc = new DBCNhanVien();
	public FormNV() {
		Container cp = getContentPane();
		JPanel pnMain = new JPanel();
		setContentPane(pnMain);
		pnMain.setLayout(new BorderLayout());
		
		pnMain.setBorder(new EmptyBorder(10,10,10,10));
		
		//pnTitle
		JPanel pnTitle = new JPanel(new FlowLayout());
		JLabel lblSinhVien = new JLabel("Quản lý nhân viên ");
		lblSinhVien.setFont(new Font("serif",Font.BOLD,20));
		pnTitle.add(lblSinhVien);
		pnMain.add(pnTitle,BorderLayout.PAGE_START);
		
		//pnCenter
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		//pnInput
		
		JPanel pnInput = new JPanel(new GridLayout(13,2,6,6));
		pnInput.add(new JLabel("Mã nhân viên:"));
		txtMaNV = new JTextField();
		pnInput.add(txtMaNV);
		pnInput.add(new JLabel("Họ tên:"));
		txtTenNV = new JTextField();
		pnInput.add(txtTenNV);
		pnInput.add(new JLabel("Mã phòng quản lý:"));
		txtMaPhong = new JTextField();
		pnInput.add(txtMaPhong);
		pnInput.add(new JLabel("Ghi chú:"));
		txtGhiChu = new JTextField();
		pnInput.add(txtGhiChu);
		
		
		pnCenter.add(pnInput);
		//pnButton
		JPanel pnButton = new JPanel(new FlowLayout());
		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String manhannvien = txtMaNV.getText();
				String tennv = txtTenNV.getText();
				String maphong = txtMaPhong.getText();
				String ghichu = txtGhiChu.getText();
			
				DanhSachNhanVien nv = new DanhSachNhanVien(manhannvien, tennv, maphong, ghichu);
				DBCNhanVien.insert(nv);
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
				String manhannvien = txtMaNV.getText();
				String tennv = txtTenNV.getText();
				String maphong = txtMaPhong.getText();
				String ghichu = txtGhiChu.getText();
			
				DanhSachNhanVien nv = new DanhSachNhanVien(manhannvien, tennv, maphong, ghichu);
				DBCNhanVien.update(nv);
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
					DanhSachNhanVien nv = new DanhSachNhanVien();
					nv.setMaNV(txtMaNV.getText());
					DBCNhanVien.delete(nv.getMaNV());
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
				txtMaNV.setText("");
				txtTenNV.setText("");
				txtMaPhong.setText("");
				txtGhiChu.setText("");
				
			}
		});
		pnButton.add(btnLamMoi);
		//pnBottom
		JPanel pnBottom = new JPanel();
		tblDanhSachNhanVien = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblDanhSachNhanVien);
		pnBottom.add(scrollPane);
		pnMain.add(pnBottom,BorderLayout.EAST);
		
		setTitle("Quản lý nhân viên");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	public void loadData() {
		
		//Tạo table model
		DefaultTableModel model = new DefaultTableModel();
		//Lấy dữ liệu từ CSDL
		ResultSet rs = dbc.getAll();
		
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
		tblDanhSachNhanVien.setModel(model);
	
	tblDanhSachNhanVien.addMouseListener(new MouseListener() {
		
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
			int selectedRow = tblDanhSachNhanVien.getSelectedRow();
			if(selectedRow >= 0) {
				txtMaNV.setText((String)(model.getValueAt(selectedRow, 0)));
				txtTenNV.setText((String)(model.getValueAt(selectedRow, 1)));
				txtMaPhong.setText((String)(model.getValueAt(selectedRow, 2)));
				txtGhiChu.setText((String)(model.getValueAt(selectedRow, 3)));
				
				
			}
			
		}
	});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FormNV f = new FormNV();
		f.loadData();
	}

}
