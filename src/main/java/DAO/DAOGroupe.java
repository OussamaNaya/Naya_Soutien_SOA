package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Models.Cour;
import Models.Groupe;
import Models.Salle;
import tools.DBInteraction;

@Path("/groupes")
@Produces(MediaType.APPLICATION_JSON)
public class DAOGroupe {
	@POST
	@Path("/CreeGroupe")
	@Consumes(MediaType.APPLICATION_JSON)
	public int CreeGroupe(Groupe g) throws ClassNotFoundException, SQLException
    {
		int nb = 0;
    	DBInteraction.connect();
        nb = DBInteraction.Maj("insert into groupe values(null,'"+g.getNom()+"','"+g.getNiveau()+"',"+g.getCapacite()+")");
    	DBInteraction.disconnect();
    	return nb;
    }
	@GET
	@Path("ALL")
    public List<Groupe> All() throws ClassNotFoundException, SQLException
    {
    	List<Groupe> le = new ArrayList<Groupe>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from groupe");
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String niveau = rs.getString(3);
    		int capacite = rs.getInt(4);
    		le.add(new Groupe(id, nom, niveau, capacite));
    	}
    	DBInteraction.disconnect();
    	return le; 
    }
	//http://localhost:8080/Naya_Soutien/rs/groupes/All_Niveau?m=BAC%20SVT
	@GET
	@Path("/All_Niveau")
    public List<Groupe> All_Niveau(@QueryParam(value="m") String Niveau) throws ClassNotFoundException, SQLException
    {
    	List<Groupe> le = new ArrayList<Groupe>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from groupe where niveau = '"+Niveau+"'");
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String niveau = rs.getString(3);
    		int capacite = rs.getInt(4);
    		le.add(new Groupe(id, nom, niveau, capacite));
    	}
    	DBInteraction.disconnect();
    	return le;
    }
	//http://localhost:8080/Naya_Soutien/rs/groupes/Find_ID?id=1
	@GET
	@Path("Find_ID")
    public Groupe Find_ID(@QueryParam(value = "id") int Id) throws ClassNotFoundException, SQLException
    {
    	Groupe g = null;
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from groupe where id = "+Id);
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String niveau = rs.getString(3);
    		int capacite = rs.getInt(4);
    		g = new Groupe(id, nom, niveau, capacite);
    	}
    	DBInteraction.disconnect();
    	return g;
    }
    public int Modifier(Groupe g1,Groupe g2) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
		nbr = DBInteraction.Maj("update groupe set nom='"+g2.getNom()+"',niveau='"+g2.getNiveau()+"',capacite="+g2.getCapacite()+" where id = "+g1.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @PUT
    @Path("/Modifier_2")
    @Consumes(MediaType.APPLICATION_JSON)
    public int Modifier_2(Groupe g) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
		nbr = DBInteraction.Maj("update groupe set nom='"+g.getNom()+"',niveau='"+g.getNiveau()+"',capacite="+g.getCapacite()+" where id = "+g.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @DELETE
    @Path("/supprimer")
    @Consumes(MediaType.APPLICATION_JSON)
    public int supprimer(Groupe g) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("delete from groupe where id = "+g.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DAOGroupe g = new DAOGroupe();
		for(Groupe s : g.All_Niveau("BAC SVT")) {
			System.out.println(s);
		}
		
	}
}
