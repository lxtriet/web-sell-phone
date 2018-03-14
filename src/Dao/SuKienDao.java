package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import Connect.DBConnect;
import Model.SuKien;

public class SuKienDao {
	public ArrayList<SuKien> getListSuKien() {
        Connection cons = DBConnect.getConnecttion();
        String sql = "SELECT * FROM SuKien";
        
        ArrayList<SuKien> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	SuKien sk = new SuKien();
            	sk.setMaSK(rs.getString("MaSK"));
            	sk.setTenSK(rs.getString("TenSK"));
            	sk.setMoTaSK(rs.getString("MoTaSK"));
            	sk.setNgayBD(rs.getString("NgayBD"));
            	sk.setNgayKT(rs.getString("NgayKT"));
            	sk.setMaNV(rs.getString("MaNV"));
            	sk.setKhuyenMai(rs.getFloat("KhuyenMai"));
            	
            	list.add(sk);
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public SuKien getSuKien() {
        Connection cons = DBConnect.getConnecttion();
        String sql = "select *"
        		+" from loaisp inner join sukien on loaisp.MaLSP=sukien.MaLSP"+
        		" where sukien.NgayBD <= current_date() and sukien.NgayKT> current_date()";
        
        SuKien sk= new SuKien();
        try {
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {        
            	sk.setMaSK(rs.getString("sukien.MaSK"));
            	sk.setMaLSP(rs.getString("loaisp.MaLSP"));
            	sk.setMoTaSK(rs.getString("sukien.MoTaSK"));
            	sk.setNgayBD(rs.getString("NgayBD"));
            	sk.setNgayKT(rs.getString("NgayKT"));
            	sk.setKhuyenMai(rs.getFloat("KhuyenMai"));
            	sk.setTenSK(rs.getString("TenSK"));
            	sk.setTenLSP(rs.getString("TenLoaiSP"));
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sk;
    }
	
	public String FormatNumber(float number){
		DecimalFormat myFormatter = new DecimalFormat("###,###");
		return myFormatter.format(number);
	}
	public String FormatNumber(int number){
		DecimalFormat myFormatter = new DecimalFormat("###,###");
		return myFormatter.format(number);
	}
	public String FormatNumber(double number){
		DecimalFormat myFormatter = new DecimalFormat("###,###");
		return myFormatter.format(number);
	}
	
	 public static void main(String[] args) throws SQLException, ParseException {
		SuKien sk = new SuKien();
		SuKienDao skDAO = new SuKienDao();
		sk = skDAO.getSuKien();
		System.out.println(skDAO.FormatNumber(2299000*5/1000));
	 }

}
