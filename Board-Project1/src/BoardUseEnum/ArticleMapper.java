package BoardUseEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleMapper implements RowMapper<Article> {
	@Override
	public Article getRow(ResultSet rs) throws SQLException {
		Article a = new Article();
		
		
		a.setAid(rs.getInt("article.aid"));
		a.setTitle(rs.getString("title"));
		a.setBody(rs.getString("body"));
		a.setUid(rs.getInt("uid"));
		a.setDate(rs.getString("regDate"));
		a.setHit(rs.getInt("hit"));
		a.setNickname(rs.getString("nickname"));
		a.setLike(rs.getInt("like"));
		
		return a;
	}
	
		
}
