package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cost;
import util.DBUtil;

public class CostDao implements Serializable {
	
	/**
	 * @return
	 * 总的思路: 创建数据库连接;结果集遍历后添加到集合
	 */
	public  List<Cost> findAll(){
		Connection conn=null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()){
				Cost c = new Cost();
				c.setCostId(rs.getInt("cost_id"));
				c.setName(rs.getString("name"));
				c.setBaseDuration(rs.getInt("base_duration"));
				c.setBaseCost(rs.getDouble("base_cost"));
				c.setUnitCost(rs.getDouble("unit_cost"));
				c.setStatus(rs.getString("status"));
				c.setDescr(rs.getString("descr"));
				c.setCreatime(rs.getTimestamp("creatime"));
				c.setStartime(rs.getTimestamp("startime"));
				c.setCostType(rs.getString("cost_type"));
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询资费失败",e);
		} finally{
			DBUtil.close(conn);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn= DBUtil.getConnection();
		System.out.println(conn);
	}
}
