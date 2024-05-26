package DAO;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
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

import Models.*;
import Wrapper.WrapperCour.ModifierCourWrapper;
import Wrapper.WrapperEnseignant.ModifierEnseignantWrapper;
import tools.DBInteraction;

@Path("/cours")
@Produces(MediaType.APPLICATION_JSON)
public class DAOCour {
	public boolean CreeCour(Cour c) throws ClassNotFoundException, SQLException
    {
    	DBInteraction.connect();
    	int nb = DBInteraction.Maj("insert into cour values(null,'"+c.getNom()+"','"+c.getNiveau()+"','"+c.getMatiere()+"', "+c.getHeur()+", '"+c.getDateD()+"', '"+c.getDateF()+"', "+c.getPrix()+")");
    	if(nb != 0)
    	{
    		return true;
    	}
    	DBInteraction.disconnect();
    	return false;
    }
	@POST
	@Path("/CreeCour_2")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean CreeCour_2(Cour c) throws ClassNotFoundException, SQLException
    {
    	DBInteraction.connect();
    	int nb = DBInteraction.Maj("insert into cour values(null,'"+c.getNom()+"','"+c.getNiveau()+"','"+c.getMatiere()+"', "+c.getHeur()+", '"+c.getDateD()+"', '"+c.getDateF()+"', "+c.getPrix()+")");
    	if(nb != 0)
    	{
    		return true;
    	}
    	DBInteraction.disconnect();
    	return false;
    }
	@GET
	@Path("/ALL")
    public List<Cour> All() throws ClassNotFoundException, SQLException
    {
    	List<Cour> la = new ArrayList<Cour>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from cour");
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String niveau = rs.getString(3);
    		String matiere = rs.getString(4);
    		int heur = rs.getInt(5);
    		String dateD = rs.getString(6);
    		String DateF = rs.getString(7);
    		int prix = rs.getInt(8);
    		
    		Cour c = new Cour(id, nom, niveau, matiere, heur, dateD, DateF, prix);
    		la.add(c);
    	}
    	DBInteraction.disconnect();
    	return la;
    }
	//http://localhost:8080/Naya_Soutien/rs/cours/Find_ID?id=2
	@GET
    @Path("Find_ID")
    public Cour Find_ID(@QueryParam(value = "id") int Id) throws ClassNotFoundException, SQLException
    {
    	Cour c = new Cour();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from cour where id = "+Id);
    	if(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String niveau = rs.getString(3);
    		String matiere = rs.getString(4);
    		int heur = rs.getInt(5);
    		String dateD = rs.getString(6);
    		String DateF = rs.getString(7);
    		int prix = rs.getInt(8);
    		
    		c = new Cour(id, nom, niveau, matiere, heur, dateD, DateF, prix);
    	}
    	DBInteraction.disconnect();
    	return c;
    }
	public Cour Find_Cour_Id(Cour C) throws ClassNotFoundException, SQLException
    {
    	Cour c = new Cour();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from cour where nom = '"+C.getNom()+"' and niveau = '"+C.getNiveau()+"'");
    	if(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom = rs.getString(2);
    		String niveau = rs.getString(3);
    		String matiere = rs.getString(4);
    		int heur = rs.getInt(5);
    		String dateD = rs.getString(6);
    		String DateF = rs.getString(7);
    		int prix = rs.getInt(8);
    		
    		c = new Cour(id, nom, niveau, matiere, heur, dateD, DateF, prix);
    	}
    	DBInteraction.disconnect();
    	return c;
    }
	//http://localhost:8080/Naya_Soutien/rs/cours/Find_Cour_Id_2?nom=PC&niveau=BAC SVT
	@GET
	@Path("/Find_Cour_Id_2")
    public Cour Find_Cour_Id_2(@QueryParam(value="nom") String nom, @QueryParam(value="niveau") String niveau) throws ClassNotFoundException, SQLException
    {
    	Cour c = new Cour();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from cour where nom = '"+nom+"' and niveau = '"+niveau+"'");
    	if(rs.next())
    	{
    		int id = rs.getInt(1);
    		String nom1 = rs.getString(2);
    		String niveau1 = rs.getString(3);
    		String matiere = rs.getString(4); 
    		int heur = rs.getInt(5);
    		String dateD = rs.getString(6);
    		String DateF = rs.getString(7);
    		int prix = rs.getInt(8);
    		
    		c = new Cour(id, nom1, niveau1, matiere, heur, dateD, DateF, prix);
    	}
    	DBInteraction.disconnect();
    	return c;
    }
    public int Modifier(Cour c1,Cour c2) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("update cour set nom ='"+c2.getNom()+"',niveau ='"+c2.getNiveau()+"',heur = "+c2.getHeur()+",dateD = '"+c2.getDateD()+"',DateF = '"+c2.getDateF()+"',prix = "+c2.getPrix()+"  where id = "+c1.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @PUT
    @Path("/Modifier_2")
    @Consumes(MediaType.APPLICATION_JSON)
    public int Modifier_2(ModifierCourWrapper m) throws ClassNotFoundException, SQLException
    {
    	Cour c1 = m.getC1();
    	Cour c2 = m.getC2();
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("update cour set nom ='"+c2.getNom()+"',niveau ='"+c2.getNiveau()+"',heur = "+c2.getHeur()+",dateD = '"+c2.getDateD()+"',DateF = '"+c2.getDateF()+"',prix = "+c2.getPrix()+"  where id = "+c1.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    public int sapprimer(Cour c) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("delete from cour where id = "+c.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @DELETE
    @Path("/sapprimer_2")
    public int sapprimer_2(@QueryParam(value="idC") int idC) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("delete from cour where id = "+idC);
    	DBInteraction.disconnect();
    	return nbr;
    }
}
