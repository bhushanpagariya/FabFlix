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
 * Servlet implementation class FetchStarDetails
 */
@WebServlet("/star")
public class FetchStarDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchStarDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("starid");
		if(id == null) {
			// need to handle
		} else {
			int starid = Integer.parseInt(id);
			
			// Establish connection with db
			Connector dbcon = new Connector();
			dbcon.createConnection();
			// Fetch star details
			DBAccessStarTable starcon = new DBAccessStarTable(dbcon.getConnection());			
			StarModel star = starcon.fetchStarsDetails(starid);
			if(star.getId() == -1) {
				//No star found
			} else {
				// star found		
				// fetch movie list
				DBAccessMovieTable moviecon = new DBAccessMovieTable(dbcon.getConnection());
				ArrayList<MovieModel> movieList = moviecon.fetchMoviesOfStar(star.getId());
				star.setMovieList(movieList);
				
				// Json object
				Gson jsonMarshal = new Gson();
				String jsonString = jsonMarshal.toJson(star);
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
