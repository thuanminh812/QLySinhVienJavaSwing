package quanlysv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBCNhanVien {
	private static Connection conn = null;
	private static String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
	private static String user = "root";
	private static String password = "";
	//Phương thức kết nối tới CSDL
	public void connect() {
		try {
			//1.Tạo đối tượng Connection để kết nối tới CSDL
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connect success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet getAll() {
		ResultSet rs = null;
		try {
			connect();
			//2.Tạo đối tượng Statement để tương tác với CSDL
			Statement st = conn.createStatement();
			//3.Tạo xâu chứa câu truy vấn
			String query = "SELECT * FROM quanlynhanvien";	
			//4.THực thi câu lệnh
			rs = st.executeQuery(query);
			//5.Xử lý kết quả truy vấn
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
	//Phương thức lấy nhân viên theo mã
		public static List<DanhSachNhanVien> getId(String maNV) {
			List<DanhSachNhanVien> nhanVien = new ArrayList<>();
			//Tạo xâu chứa câu truy vấn
			String query = "SELECT * FROM quanlynhanvien WHERE MaSv=?";
			//Tạo đối tượng Prepared Statement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//truyền tham số
				pst.setString(1,maNV);
				//Thực thi câu lệnh
				ResultSet rs = pst.executeQuery();
				//Xử lý kết quả truy vấn
				while(rs.next()) {
					DanhSachNhanVien nv = new DanhSachNhanVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					nhanVien.add(nv);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					//6.Đóng kết nối
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return nhanVien;
		}
	
		//Phương thức thêm dữ liệu vào bảng sinh viên
		public static void insert(DanhSachNhanVien nv) {
			String query = "INSERT INTO quanlynhanvien VALUES(?,?,?,?)";
			//Tạo đối tường PreparedStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1, nv.getMaNV());
				pst.setString(2, nv.getTenNV());
				pst.setString(3, nv.getSoPhong());
				pst.setString(4, nv.getGhiChu());
				
				//Thực thi câu lệnh
				if(pst.executeUpdate()>0) {
					System.out.println("Thêm thành công");
				}else {
					System.out.println("Lỗi!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					//Đóng kết nối
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		public static void update(DanhSachNhanVien nv) {
			//Tạo xâu chứa câu truy vấn
			String query = "UPDATE quanlynhanvien SET maNV =?,maPhong=?, ghiChu=? WHERE tenNV=?";
			//Tạo đối tượng PreparedStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1, nv.getMaNV());
				pst.setString(2, nv.getTenNV());
				pst.setString(3, nv.getSoPhong());
				pst.setString(4, nv.getGhiChu());
				
				//Thực thi câu lệnh
				if(pst.executeUpdate()>0) {
					System.out.println("Cập nhật thành công!");
				}else {
					System.out.println("Lỗi!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//Đóng kết nối
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		public static void delete(String maNV) {
			//Tạo xâu chưa câu truy vấn
			String query = "DELETE FROM quanlynhanvien WHERE maNV =?";
			//Tạo đối tượng PrepareStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1,maNV);
				//Thực thi câu lệnh
				if(pst.executeUpdate()>0) {
					System.out.println("Xóa thành công!");
				}else {
					System.out.println("Lỗi!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBCNhanVien dbc = new DBCNhanVien();
		dbc.connect();
	}

}
