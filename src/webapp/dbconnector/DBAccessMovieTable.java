package webapp.dbconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import webapp.models.MovieModel;

public class DBAccessMovieTable {
	private Connection connection;

	public DBAccessMovieTable(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}
	
	public ArrayList<MovieModel> fetchMoviesOfStar(int starId){
		ArrayList<MovieModel> movielist = new ArrayList<MovieModel>();
		try {			
			// Select statement customer table
			Statement select = this.connection.createStatement();
			String sql_query = "select movie_id from stars_in_movies where star_id="+starId+";";
			ResultSet res = select.executeQuery(sql_query);
			
			if(res.getFetchSize() > 1) {
				return null;
			}
			while(res.next()) {
				movielist.add(fetchMovieDetail(res.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movielist;
	}


	public MovieModel fetchMovieDetail(int movieId){
		MovieModel movie = new MovieModel();
		try{
			Statement select = this.connection.createStatement();
			String sql_query = "select * from movies where id="+movieId+";";
			ResultSet res = select.executeQuery(sql_query);
			
			if(res.getFetchSize() > 1) {
				return movie;
			}
			while(res.next()) {
				movie.setId(res.getInt(1));
				movie.setTitle(res.getString(2));
				movie.setYear(res.getInt(3));
				movie.setDirector(res.getString(4));
				movie.setBanner_url(res.getString(5));
				movie.setTrailer_url(res.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	
	
	public ArrayList<Integer> fetchMovieList(ArrayList<String> values){
		ArrayList<Integer> movieIds = new ArrayList<Integer>();
		try {
			
			// Select statement customer table
			Statement select = this.connection.createStatement();
			String moviesTable = "movies";
			String starsTable = "stars";
			String moviesStarsMappingTable = "stars_in_movies";
			
			String whereClause = "";
			
			if(values.get(0)!=null) {
				whereClause = " "+moviesTable+"."+"title"+"='"+values.get(0)+"' ";
			}
			if(values.get(1)!=null) {
				if(whereClause.isEmpty())
					whereClause = " "+moviesTable+"."+"year"+"="+values.get(1)+" ";
				else
					whereClause = whereClause + " and "+moviesTable+"."+"year"+"="+values.get(1)+" ";
			}
			if(values.get(2)!=null) {
				if(whereClause.isEmpty())
					whereClause = " "+moviesTable+"."+"director"+"='"+values.get(2)+"' ";
				else
					whereClause = whereClause + " and "+moviesTable+"."+"director"+"='"+values.get(2)+"' ";
			}
			if(values.get(3)!=null) {
				if(whereClause.isEmpty())
					whereClause = " "+starsTable+"."+"first_name"+"='"+values.get(3)+"' ";
				else
					whereClause = whereClause + " and "+starsTable+"."+"first_name"+"='"+values.get(3)+"' ";
			}
			if(values.get(4)!=null) {
				if(whereClause.isEmpty())
					whereClause = " "+starsTable+"."+"last_name"+"='"+values.get(4)+"' ";
				else
					whereClause = whereClause + " and "+starsTable+"."+"last_name"+"='"+values.get(4)+"' ";
			}
			
			String sql_query = "select distinct "+moviesTable+"."+"id"+" from "+moviesTable+" "+moviesTable+" , "+starsTable+" "+starsTable+" , "+moviesStarsMappingTable+" "+moviesStarsMappingTable+" where "+moviesTable+".id = "+moviesStarsMappingTable+".movie_id and "+moviesStarsMappingTable+".star_id = "+starsTable+".id and "+whereClause+" ;";
			System.out.println("Queryyyyy:- "+sql_query);
			ResultSet res = select.executeQuery(sql_query);
			if(res.getFetchSize() > 1) {
				return movieIds;
			}
			while(res.next()) {
//				System.out.println(res.getInt(1));
				movieIds.add(res.getInt(1));
			}
			return movieIds;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieIds;
	}
}
