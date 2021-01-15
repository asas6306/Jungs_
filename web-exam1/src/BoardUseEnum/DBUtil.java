package BoardUseEnum;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// boolean existCheck(String sql, Object... iv) / 중복확인(이미 존재여부)
// void updateSQL(String sql, Object... iv) / DB데이터 수정(delete, update etc...)
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// getRows > ArrayList<T>
// getRow > T
// existCheck > 존재유무 확인
// updateSQL > 
public class DBUtil {
	EnterDriver ed = new EnterDriver();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 공통 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	public PreparedStatement getPstmt(String sql, Object[] iv) throws SQLException { // iv = input value
		PreparedStatement pstmt = null;
		conn = ed.getConnection();
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < iv.length; i++) {
			if (iv[i] instanceof Integer) {
				pstmt.setInt(i + 1, (int) iv[i]);
			} else {
				pstmt.setString(i + 1, (String) iv[i]);
			}
		}
		return pstmt;
	}

	public <T> ArrayList<T> getRows(String sql, RowMapper<T> mapper, Object... iv) { // 모르겟서여
		iv = transObj(iv);

		ArrayList<T> at = new ArrayList<>();
		pstmt = null;
		rs = null;

		try {
			pstmt = getPstmt(sql, iv);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				T obj = mapper.getRow(rs);
				at.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return at;
	}

	public <T> T getRow(String sql, RowMapper<T> mapper, Object... iv) {
		T result = null;

		if (getRows(sql, mapper, iv).size() != 0) {
			result = getRows(sql, mapper, iv).get(0);
		}

		return result;
	}

	public void updateSQL(String sql, Object... iv) {
		iv = transObj(iv);

		try {
			pstmt = getPstmt(sql, iv);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}

	public boolean existCheck(String sql, Object... iv) {
		try {
			pstmt = getPstmt(sql, iv);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return false;
	}

	public Object[] transObj(Object... iv) {
		if (iv.length != 0 && iv[0] instanceof Object[]) {
			iv = (Object[]) iv; // params = (Object[])params[0]; ?
		}
		return iv;
	}

	public void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getLikeCnt(String sql, Object... iv) {
		iv = transObj(iv);
		
		int likeCnt = 0;

		try {
			pstmt = getPstmt(sql, iv);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				likeCnt = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		return likeCnt;
	}
}
