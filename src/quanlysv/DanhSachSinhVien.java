package quanlysv;

public class DanhSachSinhVien {
	private String maSV;
	private String hoTen;
	private String Lop;
	private String soPhong;
	private String soTienPhaiThanhToan;
	private String SoNguoiO;
	public DanhSachSinhVien(String maSV, String hoTen, String lop, String soPhong, String soTienPhaiThanhToan,
			String soNguoiO) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.Lop = lop;
		this.soPhong = soPhong;
		this.soTienPhaiThanhToan = soTienPhaiThanhToan;
		this.SoNguoiO = soNguoiO;
	}
	public DanhSachSinhVien () {
		
	}
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getLop() {
		return Lop;
	}
	public void setLop(String lop) {
		this.Lop = lop;
	}
	public String getSoPhong() {
		return soPhong;
	}
	public void setSoPhong(String soPhong) {
		this.soPhong = soPhong;
	}
	public String getSoTienPhaiThanhToan() {
		return soTienPhaiThanhToan;
	}
	public void setSoTienPhaiThanhToan(String soTienPhaiThanhToan) {
		this.soTienPhaiThanhToan = soTienPhaiThanhToan;
	}
	public String getSoNguoiO() {
		return SoNguoiO;
	}
	public void setSoNguoiO(String soNguoiO) {
		this.SoNguoiO = soNguoiO;
	}
	
	
	
}
