package BoardUseEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeMapper implements RowMapper {
	public Object getRow(ResultSet rs) throws SQLException {
		Like l = new Like();
		
		String nickname = rs.getString("nickname");
		l.setNickname(nickname);
		
		return l;
	}

}
