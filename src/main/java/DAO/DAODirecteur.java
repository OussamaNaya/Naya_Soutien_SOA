package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Models.Admin;
import Models.Directeur;
import tools.DBInteraction;

@Path("/directeurs")
@Produces(MediaType.APPLICATION_JSON)
public class DAODirecteur {

	public Directeur authentifie(Directeur D) throws SQLException, ClassNotFoundException
    {
    	Directeur d = null;
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from directeur where login = '"+D.getLogin()+"' and pass = '"+D.getPass()+"'");
    	if(rs.next())
    	{
    		
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String prenom = rs.getString(3);
    		int age = rs.getInt(4);
    		String login = rs.getString(5);
    		String pass = rs.getString(6);
    		
    		d = new Directeur(id, nom, prenom, age, login, pass);
    	}
    	DBInteraction.disconnect();
    	return d;
     } 
	@GET
	@Path("/authentifie_2")
	public Directeur authentifie_2(@QueryParam(value = "login")String login, @QueryParam(value = "password")String password) throws SQLException, ClassNotFoundException
    {
    	Directeur d = null;
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from directeur where login = '"+login+"' and pass = '"+password+"'");
    	if(rs.next())
    	{
    		
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String prenom = rs.getString(3);
    		int age = rs.getInt(4);
    		String login1 = rs.getString(5);
    		String pass = rs.getString(6);
    		
    		d = new Directeur(id, nom, prenom, age, login1, pass);
    	}
    	DBInteraction.disconnect();
    	return d;
     }
}
