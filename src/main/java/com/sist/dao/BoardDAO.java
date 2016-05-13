package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import java.util.*;
@Repository
public class BoardDAO {
	 @Autowired
     private MyDataSource ds;
	 private CallableStatement cs;
	 
	 public List<BoardVO> boardAllData(Map map)
	 {
		 List<BoardVO> list=new ArrayList<BoardVO>();
		 try
		 {
			 // ds.getConnection() => @Before
			 /*
			  *   CREATE OR REPLACE PROCEDURE boardAllData(
					   pStart IN board.no%TYPE,
					   pEnd IN board.no%TYPE,
					   pResult OUT SYS_REFCURSOR
				)
			  */
			 String sql="{CALL boardAllData(?,?,?)}";
			 cs=ds.getConn().prepareCall(sql);
			 cs.setInt(1, (Integer)map.get("start"));
			 // Integer i=10;
			 cs.setInt(2, (Integer)map.get("end"));
			 cs.registerOutParameter(3, OracleTypes.CURSOR);
			 cs.executeUpdate(); // 프로시저 호출 
			 ResultSet rs=(ResultSet)cs.getObject(3);
			 while(rs.next())
			 {
				 BoardVO vo=new BoardVO();
				 vo.setNo(rs.getInt(1));
				 vo.setName(rs.getString(3));
				 vo.setSubject(rs.getString(2));
				 vo.setRegdate(rs.getDate(4));
				 vo.setHit(rs.getInt(5));
				 vo.setGroup_tab(rs.getInt(6));
				 list.add(vo);
			 }
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
		 return list;
	 }
	 public int boardTotalPage()
	 {
		 int total=0;
		 try
		 {
			 /*
			  *   CREATE OR REPLACE PROCEDURE boardTotalPage(
					   pCount OUT board.no%TYPE
					)
			  */
			 String sql="{CALL boardTotalPage(?)}";
			 cs=ds.getConn().prepareCall(sql);
			 cs.registerOutParameter(1, OracleTypes.INTEGER);
			 cs.executeUpdate();
			 total=cs.getInt(1);
			 
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
		 return total;
	 }
	 // 추가
	 public void boardInsert(BoardVO vo)
	 {
		 try{
			 String sql = "{CALL boardInsert(?,?,?,?)}";
			 cs = ds.getConn().prepareCall(sql);
			 cs.setString(1, vo.getName());
			 cs.setString(2, vo.getSubject());
			 cs.setString(3, vo.getContent());
			 cs.setString(4, vo.getPwd());
			 cs.executeUpdate();
			 
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	 }
	 public BoardVO boardContent(int no)
	 {
		 BoardVO vo = new BoardVO();
		 try{
			 String sql ="{CALL boardContent(?,?)}";
			 cs = ds.getConn().prepareCall(sql);
			 cs.setInt(1, no);
			 cs.registerOutParameter(2, OracleTypes.CURSOR);
			 cs.executeUpdate();
			 
			 ResultSet rs = (ResultSet)cs.getObject(2);
			 rs.next();
			 vo.setNo(rs.getInt(1));
			 vo.setName(rs.getString(2));
			 vo.setSubject(rs.getString(3));
			 vo.setContent(rs.getString(4));
			 vo.setRegdate(rs.getDate(5));
			 vo.setHit(rs.getInt(6));
			 rs.close();
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
		 return vo;
	 }
	 
}








