package webapp.dbconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webapp.models.StarModel;


public class DBAccessStarTable{
	private Connection connection;
	
	public DBAccessStarTable(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}
	
	public ArrayList<StarModel> fetchStarsInMovies(int movieId){
		ArrayList<StarModel> starlist = new ArrayList<StarModel>();
		try {
			
			// Select statement customer table
			Statement select = this.connection.createStatement();
			String sql_query = "select star_id from stars_in_movies where movie_id="+movieId+";";
			ResultSet res = select.executeQuery(sql_query);
			
			if(res.getFetchSize() > 1) {
				return starlist;
			}
			while(res.next()) {
				starlist.add(fetchStarsDetails(res.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return starlist;
	}
	
	
	public StarModel fetchStarsDetails(int starId) {
		StarModel star = new StarModel();
		try {

			// Select statement customer table
			Statement select = this.connection.createStatement();
			String sql_query = "select * from stars where id="+starId+";";
			ResultSet res = select.executeQuery(sql_query);
			
			if(res.getFetchSize() > 1) {
				return star;
			}
			while(res.next()) {
				star.setId(res.getInt(1));
				star.setFirstname(res.getString(2));
				star.setLastname(res.getString(3));
				star.setDob(res.getDate(4));
				star.setPhoto_url(res.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return star;
	}
}
