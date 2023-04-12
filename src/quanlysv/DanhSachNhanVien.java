package quanlysv;

public class DanhSachNhanVien {
	private String maNV;
	private String tenNV;
	private String soPhong;
	private String ghiChu;
	public DanhSachNhanVien() {
		
	}
	public DanhSachNhanVien(String maNV, String tenNV, String soPhong, String ghiChu) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soPhong = soPhong;
		this.ghiChu = ghiChu;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSoPhong() {
		return soPhong;
	}
	public void setSoPhong(String soPhong) {
		this.soPhong = soPhong;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
