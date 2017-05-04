package webapp.models;

import java.sql.Date;
import java.util.ArrayList;

public class StarModel {
	private int id;
	private String firstname;
	private String lastname;
	private Date dob;
	private String photo_url;
	private ArrayList<MovieModel> movieList;
	
	// Constructor
	public StarModel() {
		setId(-1);
		setFirstname(null);
		setLastname(null);
		setDob(null);
		setPhoto_url(null);
		setMovieList(null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public ArrayList<MovieModel> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<MovieModel> movieList) {
		this.movieList = movieList;
	}
	
}
