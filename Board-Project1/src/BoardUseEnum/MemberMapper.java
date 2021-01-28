package BoardUseEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {

	@Override
	public Member getRow(ResultSet rs) throws SQLException {
		Member m = new Member();
		
		m.setUid(rs.getInt("uid"));
		m.setID(rs.getString("ID"));
		m.setPW(rs.getString("PW"));
		m.setNickname(rs.getString("nickname"));
		m.setRegDate(rs.getString("regDate"));
		m.setBan(rs.getBoolean("ban"));
		m.setMng(rs.getBoolean("mng"));
		
		return m;
	}

}
