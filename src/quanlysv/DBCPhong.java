package quanlysv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBCPhong {
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
			String query = "SELECT * FROM quanlyphong";	
			//4.THực thi câu lệnh
			rs = st.executeQuery(query);
			//5.Xử lý kết quả truy vấn
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rs;
	}
	//Phương thức lấy sinh viên theo mã
		public static List<DanhSachPhong> getId(String maPhong) {
			List<DanhSachPhong> dsphong = new ArrayList<>();
			//Tạo xâu chứa câu truy vấn
			String query = "SELECT * FROM quanlyphong WHERE maPhong=?";
			//Tạo đối tượng Prepared Statement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//truyền tham số
				pst.setString(1,maPhong);
				//Thực thi câu lệnh
				ResultSet rs = pst.executeQuery();
				//Xử lý kết quả truy vấn
				while(rs.next()) {
					DanhSachPhong dsp = new DanhSachPhong(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					dsphong.add(dsp);
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
			return dsphong;
		}
	
		//Phương thức thêm dữ liệu vào bảng sinh viên
		public static void insert(DanhSachPhong dsv) {
			String query = "INSERT INTO quanlyphong VALUES(?,?,?,?)";
			//Tạo đối tường PreparedStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1, dsv.getMaPhong());
				pst.setString(2, dsv.getMaKhu());
				pst.setString(3, dsv.getTenPhong());
				pst.setString(4, dsv.getTang());
				
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
		public static void update(DanhSachPhong dsv) {
			//Tạo xâu chứa câu truy vấn
			String query = "UPDATE quanlyphong SET maKhu =?,tenPhong=?,Tang=?,  WHERE maPhong=?";
			//Tạo đối tượng PreparedStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1, dsv.getMaPhong());
				pst.setString(2, dsv.getMaKhu());
				pst.setString(3, dsv.getTenPhong());
				pst.setString(4, dsv.getTang());
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
		public static void delete(String maPhong) {
			//Tạo xâu chưa câu truy vấn
			String query = "DELETE FROM quanlyphong WHERE maPhong =?";
			//Tạo đối tượng PrepareStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1,maPhong);
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
		DBCPhong dbp = new DBCPhong();
		dbp.connect();
	}

}
