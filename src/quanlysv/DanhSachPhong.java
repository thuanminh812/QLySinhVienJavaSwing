package quanlysv;

public class DanhSachPhong {
	private String maPhong;
	private String maKhu;
	private String tenPhong;
	private String tang;
	public DanhSachPhong() {
		
	}
	public DanhSachPhong(String maPhong, String maKhu, String tenPhong, String tang) {
		super();
		this.maPhong = maPhong;
		this.maKhu = maKhu;
		this.tenPhong = tenPhong;
		this.tang = tang;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaKhu() {
		return maKhu;
	}
	public void setMaKhu(String maKhu) {
		this.maKhu = maKhu;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public String getTang() {
		return tang;
	}
	public void setTang(String tang) {
		this.tang = tang;
	}
	
}
