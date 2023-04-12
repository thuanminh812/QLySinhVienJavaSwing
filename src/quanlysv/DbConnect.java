package quanlysv;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class DbConnect {
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
			String query = "SELECT * FROM danhsachsinhvien";	
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
		public static List<DanhSachSinhVien> getId(String maSV) {
			List<DanhSachSinhVien> sinhVien = new ArrayList<>();
			//Tạo xâu chứa câu truy vấn
			String query = "SELECT * FROM danhsachsinhvien WHERE MaSv=?";
			//Tạo đối tượng Prepared Statement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//truyền tham số
				pst.setString(1,maSV);
				//Thực thi câu lệnh
				ResultSet rs = pst.executeQuery();
				//Xử lý kết quả truy vấn
				while(rs.next()) {
					DanhSachSinhVien sv = new DanhSachSinhVien(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
					sinhVien.add(sv);
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
			return sinhVien;
		}
	
		//Phương thức thêm dữ liệu vào bảng sinh viên
		public static void insert(DanhSachSinhVien sv) {
			String query = "INSERT INTO danhsachsinhvien VALUES(?,?,?,?,?,?)";
			//Tạo đối tường PreparedStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1, sv.getMaSV());
				pst.setString(2, sv.getHoTen());
				pst.setString(3, sv.getLop());
				pst.setString(4, sv.getSoPhong());
				pst.setString(5, sv.getSoTienPhaiThanhToan());
				pst.setString(6, sv.getSoNguoiO());
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
		public static void update(DanhSachSinhVien sv) {
			//Tạo xâu chứa câu truy vấn
			String query = "UPDATE danhsachsinhvien SET hoTen =?,Lop=?,maPhong=?, soTienPhaiThanhToan=?,SoNguoiO=? WHERE MaSV=?";
			//Tạo đối tượng PreparedStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1, sv.getMaSV());
				pst.setString(2, sv.getHoTen());
				pst.setString(3, sv.getLop());
				pst.setString(4, sv.getSoPhong());
				pst.setString(5, sv.getSoTienPhaiThanhToan());
				pst.setString(6, sv.getSoNguoiO());
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
		public static void delete(String maSV) {
			//Tạo xâu chưa câu truy vấn
			String query = "DELETE FROM danhsachsinhvien WHERE MaSV =?";
			//Tạo đối tượng PrepareStatement để tương tác với CSDL
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				//Truyền tham số
				pst.setString(1,maSV);
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
		DbConnect db = new DbConnect();
		db.connect();
		//db.getAll();
		//DanhSachSinhVien sv = new DanhSachSinhVien("1351023129","Nguyễn Văn Tiến","CNTT14-05","401","560.000Đ","2");
		//db.insert(sv);
		//db.update(sv);
		//db.delete("1");

	}

}
