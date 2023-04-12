package quanlysv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.StackWalker.Option;
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
import javax.swing.table.TableModel;

import quanlysv.DbConnect;

public class Form extends JFrame {
	private JTextField txtMaSV;
	private JTextField txtHoTen;
	private JTextField txtLop;
	private JTextField txtSoPhong;
	private JTextField txtSoTienPhaiTra;
	private JTextField txtSoNguoiO;
	
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	
	private JTable tblDanhSachSinhVien;
	
	List<DanhSachSinhVien> sinhVien = new ArrayList<>();
	DbConnect db = new DbConnect();
	public Form() {
		Container cp = getContentPane();
		JPanel pnMain = new JPanel();
		setContentPane(pnMain);
		pnMain.setLayout(new BorderLayout());
		
		pnMain.setBorder(new EmptyBorder(10,10,10,10));
		
		//pnTitle
		JPanel pnTitle = new JPanel(new FlowLayout());
		JLabel lblSinhVien = new JLabel("Quản lý sinh viên kí túc xá ");
		lblSinhVien.setFont(new Font("serif",Font.BOLD,20));
		pnTitle.add(lblSinhVien);
		pnMain.add(pnTitle,BorderLayout.PAGE_START);
		
		//pnCenter
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		//pnInput
		
		JPanel pnInput = new JPanel(new GridLayout(13,2,6,6));
		pnInput.add(new JLabel("Mã sinh viên:"));
		txtMaSV = new JTextField();
		pnInput.add(txtMaSV);
		pnInput.add(new JLabel("Họ tên:"));
		txtHoTen = new JTextField();
		pnInput.add(txtHoTen);
		pnInput.add(new JLabel("Lớp học:"));
		txtLop = new JTextField();
		pnInput.add(txtLop);
		pnInput.add(new JLabel("Số phòng ở:"));
		txtSoPhong = new JTextField();
		pnInput.add(txtSoPhong);
		pnInput.add(new JLabel("Số tiền phải trả:"));
		txtSoTienPhaiTra = new JTextField();
		pnInput.add(txtSoTienPhaiTra);
		pnInput.add(new JLabel("Số người ở:"));
		txtSoNguoiO = new JTextField();
		pnInput.add(txtSoNguoiO);
		
		pnCenter.add(pnInput);
		//pnButton
		JPanel pnButton = new JPanel(new FlowLayout());
		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String masinhvien = txtMaSV.getText();
				String fullname = txtHoTen.getText();
				String lop = txtLop.getText();
				String sophong = txtSoPhong.getText();
				String sotientra = txtSoTienPhaiTra.getText();
				String songuoio = txtSoNguoiO.getText();
				DanhSachSinhVien dssv = new DanhSachSinhVien(masinhvien, fullname, lop, sophong, sotientra, songuoio);
				DbConnect.insert(dssv);
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
				String masinhvien = txtMaSV.getText();
				String fullname = txtHoTen.getText();
				String lop = txtLop.getText();
				String sophong = txtSoPhong.getText();
				String sotientra = txtSoTienPhaiTra.getText();
				String songuoio = txtSoNguoiO.getText();
				DanhSachSinhVien dssv = new DanhSachSinhVien(masinhvien, fullname, lop, sophong, sotientra, songuoio);
				DbConnect.update(dssv);
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
					DanhSachSinhVien sv = new DanhSachSinhVien();
					sv.setMaSV(txtMaSV.getText());
					DbConnect.delete(sv.getMaSV());
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
				txtMaSV.setText("");
				txtHoTen.setText("");
				txtLop.setText("");
				txtSoPhong.setText("");
				txtSoTienPhaiTra.setText("");
				txtSoNguoiO.setText("");
			}
		});
		pnButton.add(btnLamMoi);
		//pnBottom
		JPanel pnBottom = new JPanel();
		tblDanhSachSinhVien = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblDanhSachSinhVien);
		pnBottom.add(scrollPane);
		pnMain.add(pnBottom,BorderLayout.EAST);
		
		setTitle("Quản lý sinh viên kí túc xá");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	public void loadData() {
		
		//Tạo table model
		DefaultTableModel model = new DefaultTableModel();
		//Lấy dữ liệu từ CSDL
		ResultSet rs = db.getAll();
		
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
		tblDanhSachSinhVien.setModel(model);
	
	tblDanhSachSinhVien.addMouseListener(new MouseListener() {
		
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
			int selectedRow = tblDanhSachSinhVien.getSelectedRow();
			if(selectedRow >= 0) {
				txtMaSV.setText((String)(model.getValueAt(selectedRow, 0)));
				txtHoTen.setText((String)(model.getValueAt(selectedRow, 1)));
				txtLop.setText((String)(model.getValueAt(selectedRow, 2)));
				txtSoPhong.setText((String)(model.getValueAt(selectedRow, 3)));
				txtSoTienPhaiTra.setText((String)(model.getValueAt(selectedRow, 4)));
				txtSoNguoiO.setText((String)(model.getValueAt(selectedRow, 5)));
				
			}
			
		}
	});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Form f = new Form();
		f.loadData();
	}

}
