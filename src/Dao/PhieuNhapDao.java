package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connect.DBConnect;
import Model.HDBH;
import Model.PhieuNhap;
import Model.SanPham;

public class PhieuNhapDao {
	public ArrayList<PhieuNhap> getListPhieuNhap() {
        Connection cons = DBConnect.getConnecttion();
        String sql = "SELECT * FROM PhieuNhap inner join NhanVien on PhieuNhap.MaNV=NhanVien.MaNV";
        
        ArrayList<PhieuNhap> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	PhieuNhap pn = new PhieuNhap();
            	pn.setMaPN(rs.getString("MaPN"));
            	pn.setTenNV(rs.getString("NhanVien.TenNV"));
            	pn.setMaNV(rs.getString("NhanVien.MaNV"));
            	pn.setNgayNhap(rs.getString("NgayNhap"));
            	list.add(pn);
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	public boolean deletePNByMaNV(String maNV) throws SQLException {
		 Connection connection = DBConnect.getConnecttion();
	     String sql = "DELETE FROM PhieuNhap WHERE MaNV = ?";
	    try {
	       
	       PreparedStatement ps = (PreparedStatement) connection.prepareCall(sql);
	       ps.setString(1,maNV );
	       return ps.executeUpdate()==1;
	    } catch (Exception e) {
	    	return false;
	    }
	    
	}
	
	 public static void main(String[] args) throws SQLException {
		 PhieuNhapDao pnDAO = new PhieuNhapDao();
		 for(PhieuNhap pn:pnDAO.getListPhieuNhap())
		 {
			 System.out.println(pn.getTenNV()+"____"+pn.getNgayNhap());
		 }
	    }

}
