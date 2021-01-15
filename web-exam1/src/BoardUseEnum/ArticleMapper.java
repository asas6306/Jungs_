package BoardUseEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleMapper implements RowMapper<Article> {
	@Override
	public Article getRow(ResultSet rs) throws SQLException {
		Article a = new Article();
		
		
		a.setId(rs.getInt("article.id"));
		a.setTitle(rs.getString("title"));
		a.setBody(rs.getString("body"));
		a.setUserid(rs.getInt("userid"));
		a.setDate(rs.getString("date"));
		a.setHit(rs.getInt("hit"));
		a.setNickname(rs.getString("nickname"));
		a.setLike(rs.getInt("like"));
		
		return a;
	}
	
		
}
