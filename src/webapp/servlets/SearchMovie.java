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
import webapp.models.MovieModel;

/**
 * Servlet implementation class SearchMovie
 */
@WebServlet("/search")
public class SearchMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> values = new ArrayList<String>();
		values.add(request.getParameter("title"));
		values.add(request.getParameter("year"));
		values.add(request.getParameter("director"));
		values.add(request.getParameter("firstname"));
		System.out.println(request.getParameter("firstname"));
		values.add(request.getParameter("lastname"));
		// Create connection with mysql db
		Connector dbcon = new Connector();
		dbcon.createConnection();
		DBAccessMovieTable moviecon = new DBAccessMovieTable(dbcon.getConnection());
		ArrayList<Integer> movieIds = moviecon.fetchMovieList(values);
		ArrayList<MovieModel> movieList = new ArrayList<MovieModel>();
		for(int i=0; i<movieIds.size(); i++){
			movieList.add(moviecon.fetchMovieDetail(movieIds.get(i)));
		}
		
		// Json object
		Gson jsonMarshal = new Gson();
		String jsonString = jsonMarshal.toJson(movieList);
		System.out.println(jsonString);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
		dbcon.closeConnection();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
