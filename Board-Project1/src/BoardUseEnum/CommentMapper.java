package BoardUseEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment>{

	@Override
	public Comment getRow(ResultSet rs) throws SQLException {
		Comment c = new Comment();
		
		c.setCid(rs.getInt("cid"));
		c.setUid(rs.getInt("uid"));
		c.setAid(rs.getInt("aid"));
		c.setDate(rs.getString("regDate"));
		c.setBody(rs.getString("body"));
		c.setNickname(rs.getString("nickname"));
		c.setCommentReply(rs.getInt("commentreply"));
		
		return c;
	}
	
}
