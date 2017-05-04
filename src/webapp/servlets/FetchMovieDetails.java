package webapp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import webapp.dbconnector.Connector;
import webapp.dbconnector.DBAccessMovieTable;
import webapp.dbconnector.DBAccessStarTable;
import webapp.models.MovieModel;
import webapp.models.StarModel;


/**
 * Servlet implementation class FetchMovieDetails
 */
@WebServlet("/movie")
public class FetchMovieDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchMovieDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("movieid");
		if(id == null) {
			// need to handle
		} else {
			int movieid = Integer.parseInt(id);
			
			// Create DB Connection
			Connector dbcon = new Connector();
			dbcon.createConnection();
			// Fetch Movie Details
			DBAccessMovieTable movieCon = new DBAccessMovieTable(dbcon.getConnection());
			MovieModel movie = movieCon.fetchMovieDetail(movieid);
			if(movie.getId() == -1) {
				//No movie found
			} else {
				// Movie found
				// Fetch list of stars in movie
				DBAccessStarTable starcon = new DBAccessStarTable(dbcon.getConnection());
				ArrayList<StarModel> starslist = starcon.fetchStarsInMovies(movieid);
				movie.setStartList(starslist);
				
				// Json object
				Gson jsonMarshal = new Gson();
				String jsonString = jsonMarshal.toJson(movie);
				System.out.println(jsonString);
				
				response.setContentType("application/json");
				response.getWriter().write(jsonString);
				dbcon.closeConnection();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
