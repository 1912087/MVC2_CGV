package com.mycgv.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycgv.vo.CgvMemberVO;

public class CgvMemberDAO extends DBConn{
	//totalCount : ��ü �Խñ� �ο� �� ��ȸ
	public int totalCount() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM CGV_MEMBER";
		try {
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//totalCount()
	
	//login() : �α���
	public int login(CgvMemberVO vo) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM CGV_MEMBER WHERE ID = ? AND PASS = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//login()
	
	//select(id) : ȸ�� ���� �� ��ȸ
	public CgvMemberVO select(String id) {
		CgvMemberVO vo = new CgvMemberVO();
		String sql = "SELECT ID, NAME, ZONECODE, ADDR1, ADDR2, PNUMBER, HOBBYLIST, INTRO, MDATE FROM CGV_MEMBER WHERE ID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setZonecode(rs.getString(3));
				vo.setAddr1(rs.getString(4));
				vo.setAddr2(rs.getString(5));
				vo.setPnumber(rs.getString(6));
				vo.setHobbyList(rs.getString(7));
				vo.setIntro(rs.getString(8));
				vo.setMdate(rs.getString(9));
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}//select(id)
	
	//select : ȸ�� ��ȸ
	public List<CgvMemberVO> select(int startCount, int endCount){
		List<CgvMemberVO> list = new ArrayList<CgvMemberVO>();
		String sql = "SELECT RNO, ID, NAME, PNUMBER, MDATE "
				+ " FROM (SELECT ROWNUM RNO, ID, NAME, PNUMBER, TO_CHAR(MDATE, 'YYYY-MM-DD') MDATE "
				+ " FROM (SELECT ID, NAME, PNUMBER, MDATE FROM CGV_MEMBER ORDER BY MDATE DESC)) "
				+ " WHERE RNO BETWEEN ? AND ?";
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvMemberVO vo = new CgvMemberVO();
				vo.setRno(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPnumber(rs.getString(4));
				vo.setMdate(rs.getString(5));
				
				list.add(vo);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}//select()
	
	//idCheck(id) : ���̵� üũ
	public int idCheck(String id) {
		int result = 0;
		String sql = "SELECT COUNT(ID) FROM CGV_MEMBER WHERE ID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//idCheck()
	
	//insert : ȸ������
	public int insert(CgvMemberVO vo) {
		int result = 0;
		String sql = "INSERT INTO CGV_MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,sysdate)";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZonecode());
			pstmt.setString(7, vo.getAddr1());
			pstmt.setString(8, vo.getAddr2());
			pstmt.setString(9, vo.getHp());
			pstmt.setString(10, vo.getPnumber());
			pstmt.setString(11, vo.getHobbyList());
			pstmt.setString(12, vo.getIntro());
			
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//insert()
}
