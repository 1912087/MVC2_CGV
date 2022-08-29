package com.mycgv.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycgv.vo.CgvBoardVO;

public class CgvBoardDAO extends DBConn{
	//totalCount() : 전체 게시글의 수
	public int totalCount() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM CGV_BOARD";
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
	}//totalCount
	
	//select() : 전체 게시글 조회(페이징 처리)
	public List<CgvBoardVO> select(int startCount, int endCount){
		List<CgvBoardVO> list = new ArrayList<CgvBoardVO>();
		String sql = "SELECT RNO, BID, BTITLE, BHITS, BDATE "
				+ " FROM (SELECT ROWNUM RNO, BID, BTITLE, BHITS, TO_CHAR(BDATE, 'YYYY-MM-DD') BDATE "
				+ " FROM (SELECT BID, BTITLE, BHITS, BDATE FROM CGV_BOARD ORDER BY BDATE DESC)) "
				+ " WHERE RNO BETWEEN ? AND ?";
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvBoardVO vo = new CgvBoardVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBhits(rs.getInt(4));
				vo.setBdate(rs.getString(5));
				
				list.add(vo);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}//select()
	
	//updateHits() : 조회수 업데이트
	public int updateHits(String bid) {
		int result = 0;
		String sql = "UPDATE CGV_BOARD SET BHITS = BHITS + 1 WHERE BID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//updateHits()
	
	//select(id) : 게시글 상세 정보 조회
	public CgvBoardVO select(String bid) {
		CgvBoardVO vo = new CgvBoardVO();
		String sql = "SELECT BID, BTITLE, BCONTENT, BHITS, BDATE FROM CGV_BOARD WHERE BID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setBid(rs.getString(1));
				vo.setBtitle(rs.getString(2));
				vo.setBcontent(rs.getString(3));
				vo.setBhits(rs.getInt(4));
				vo.setBdate(rs.getString(5));
			}
			//close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}//select(bid)
	
	//insert : 게시글 추가
	public int insert(CgvBoardVO vo) {
		int result = 0 ;
		String sql = "INSERT INTO CGV_BOARD VALUES('b_'||SEQU_CGV_BOARD.NEXTVAL, ?, ?, '', '' , 0, SYSDATE)";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//insert()
	
	//update : 게시글 수정
	public int update(CgvBoardVO vo) {
		int result = 0;
		String sql = "UPDATE CGV_BOARD SET BTITLE = ?, BCONTENT = ? WHERE BID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBcontent());
			pstmt.setString(3, vo.getBid());
			result = pstmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//update()
	
	//delete : 게시글 삭제
	public int delete(String bid) {
		int result = 0;
		String sql = "DELETE FROM CGV_BOARD WHERE BID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//delete()
}
