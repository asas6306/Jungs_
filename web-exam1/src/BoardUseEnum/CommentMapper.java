package BoardUseEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment>{

	@Override
	public Comment getRow(ResultSet rs) throws SQLException {
		Comment c = new Comment();
		
		c.setCommnetid(rs.getInt("id"));
		c.setUserid(rs.getInt("userid"));
		c.setArticleid(rs.getInt("articleid"));
		c.setDate(rs.getString("date"));
		c.setBody(rs.getString("body"));
		c.setNickname(rs.getString("nickname"));
		
		return c;
	}
	
}
