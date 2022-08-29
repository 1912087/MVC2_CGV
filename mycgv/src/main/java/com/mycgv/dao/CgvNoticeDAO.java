package com.mycgv.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycgv.vo.CgvNoticeVO;

public class CgvNoticeDAO extends DBConn{
	//totalCount() : 게시글의 전체 로우 수 출력
	public int totalCount() {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM CGV_NOTICE";
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
	
	//delete() : 공지사항 삭제
	public int delete(String nid) {
		int result = 0;
		String sql = "DELETE FROM CGV_NOTICE WHERE NID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, nid);
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//delete()
	
	//update(vo) : 공지사항 업데이트
	public int update(CgvNoticeVO vo) {
		int result = 0;
		String sql = "UPDATE CGV_NOTICE SET NTITLE = ?, NCONTENT = ? WHERE NID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			pstmt.setString(3, vo.getNid());
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//update(vo)
	
	//updateNhits(id) : 조회수 카운트
	public int updateNhits(String nid) {
		int result = 0;
		String sql = "UPDATE CGV_NOTICE SET NHITS = NHITS + 1 WHERE NID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, nid);
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//updateNhits()
	
	//select(id) : 공지사항 세부 조회
	public CgvNoticeVO select(String id) {
		CgvNoticeVO vo = new CgvNoticeVO();
		String sql = "SELECT NID, NTITLE, NCONTENT, NHITS, NDATE FROM CGV_NOTICE WHERE NID = ?";
		try {
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setNid(rs.getString(1));
				vo.setNtitle(rs.getString(2));
				vo.setNcontent(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
			}
			//close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}//select(id)
	
	//insert() : 공지사항 등록
	public int insert(CgvNoticeVO vo) {
		int result = 0;
		String sql = "INSERT INTO CGV_NOTICE VALUES('b_'||SEQU_CGV_NOTICE.NEXTVAL, ?, ?, '', '', 0, SYSDATE)";
		try {
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getNtitle());
			pstmt.setString(2, vo.getNcontent());
			
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//insert()
	
	//selectAll() : 전체 공지사항 조회
	public List<CgvNoticeVO> selectAll(int startCount, int endCount){
		List<CgvNoticeVO> list = new ArrayList<CgvNoticeVO>();
		String sql = "SELECT RNO, NID, NTITLE, NHITS, NDATE" 
				+ " FROM (SELECT ROWNUM RNO, NID, NTITLE, NHITS, TO_CHAR(NDATE, 'YYYY-MM-DD') NDATE "
				+ " FROM (SELECT NID, NTITLE, NHITS, NDATE FROM CGV_NOTICE ORDER BY NDATE DESC)) "
				+ " WHERE RNO BETWEEN ? AND ?";
		try {
			getPreparedStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, endCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CgvNoticeVO vo = new CgvNoticeVO();
				
				vo.setRno(rs.getInt(1));
				vo.setNid(rs.getString(2));
				vo.setNtitle(rs.getString(3));
				vo.setNhits(rs.getInt(4));
				vo.setNdate(rs.getString(5));
				
				list.add(vo);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}//selectAll()
}
