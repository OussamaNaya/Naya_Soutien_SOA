package DAO;

import java.io.IOException;
import java.net.URI;
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
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import Models.Cour;
import Models.Enseignant;
import Models.Salle;
import Wrapper.WrapperAdmin.AffecterCour;
import Wrapper.WrapperAdmin.SupprimerCourAffecterWrapper;
import tools.DBInteraction;

@Path("/salles")
@Produces(MediaType.APPLICATION_JSON)
public class DAOSalle {
	@POST
	@Path("/CreeSalle")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean CreeSalle(Salle s) throws ClassNotFoundException, SQLException
    {
		int nb = 0;
    	DBInteraction.connect();
    	if(s.getLc() == null)
    	{
        	nb = DBInteraction.Maj("insert into salle values(null,"+s.getCapacite()+",null,'"+s.getNom()+"')");

    	}
    	else {
    		 for (Cour c : s.getLc()) {
    	         nb = DBInteraction.Maj("insert into salle values(null,"+s.getCapacite()+","+c.getId()+",'"+s.getNom()+"')");
			}
    	}
    	//DBInteraction.Maj("update utilisateur set etat = 0 where idU = "+u.getIdU());
    	if(nb != 0)
    	{
    		return true;
    	}
    	DBInteraction.disconnect();
    	return false;
    }
	//http://localhost:8080/Naya_Soutien/rs/salles/ALL
	@GET
	@Path("/ALL")
    public List<Salle> All() throws ClassNotFoundException, SQLException
    {
    	List<Salle> le = new ArrayList<Salle>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle");
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom = rs.getString(4);
    		Salle s = new Salle(id, nom, capacite);
    		le.add(s);
    	}
    	DBInteraction.disconnect();
    	return le;
    }
    public List<Cour> All_Courses(Salle s) throws ClassNotFoundException, SQLException
    {
    	List<Cour> le = new ArrayList<Cour>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle where nom = '"+s.getNom()+"'");
    	DAOCour daoc = new DAOCour();
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom = rs.getString(4);
    		Cour c = daoc.Find_ID(id_cour);
    		le.add(c);
    	}
    	DBInteraction.disconnect();
    	return le;
    }
    @GET
    @Path("/All_Courses_2")
    public List<Cour> All_Courses_2(@QueryParam(value = "nomSalle") String nom) throws ClassNotFoundException, SQLException
    {
    	List<Cour> le = new ArrayList<Cour>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle where nom = '"+nom+"'");
    	DAOCour daoc = new DAOCour();
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom11 = rs.getString(4);
    		Cour c = daoc.Find_ID(id_cour);
    		le.add(c);
    	}
    	DBInteraction.disconnect();
    	return le;
    }
    public List<Cour> All_Courses_Not_In_Salle(Salle s) throws ClassNotFoundException, SQLException
    {
    	List<Cour> le = new ArrayList<Cour>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle where nom != '"+s.getNom()+"'");
    	DAOCour daoc = new DAOCour();
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom = rs.getString(4);
    		Cour c = daoc.Find_ID(id_cour);
    		le.add(c);
    	}
    	DBInteraction.disconnect();
    	return le;
    }
    @GET
    @Path("/All_Courses_Not_In_Salle_2")
    public List<Cour> All_Courses_Not_In_Salle_2(@QueryParam(value = "nomSalle") String nom) throws ClassNotFoundException, SQLException
    {
    	List<Cour> le = new ArrayList<Cour>();
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle where nom != '"+nom+"'");
    	DAOCour daoc = new DAOCour();
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom1 = rs.getString(4);
    		Cour c = daoc.Find_ID(id_cour);
    		le.add(c);
    	}
    	DBInteraction.disconnect();
    	return le;
    }
    //http://localhost:8080/Naya_Soutien/rs/salles/Find_Salle_Id?id=3
    @GET
    @Path("/Find_Salle_Id")
    public Salle Find_Salle_Id(@QueryParam(value = "id") int Id) throws ClassNotFoundException, SQLException
    {
    	Salle s = null;
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle where id="+Id);
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom = rs.getString(4);
    		s = new Salle(id, nom, capacite);
    	}
    	DBInteraction.disconnect();
    	return s;
    }
    //http://localhost:8080/Naya_Soutien/rs/salles/Find_Id?id=2
    @GET
    @Path("/Find_Id")
    public Salle Find_Id(@QueryParam(value="id") int Id) throws ClassNotFoundException, SQLException
    {
    	Salle s = null;
    	DBInteraction.connect();
    	ResultSet rs = DBInteraction.Select("select * from salle where id_c="+Id);
    	while(rs.next())
    	{
    		int id = rs.getInt(1);
    		int capacite = rs.getInt(2);
    		int id_cour = rs.getInt(3);
    		String nom = rs.getString(4);
    		s = new Salle(id, nom, capacite);
    	}
    	DBInteraction.disconnect();
    	return s;
    }


    public int Modifier(Salle s1,Salle s2) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	if(s2.getLc() == null)
    	{
    		nbr  += DBInteraction.Maj("update salle set capacite="+s2.getCapacite()+",id_c=null,nom= '"+s2.getNom()+"' where id = "+s1.getId());
    	}
    	else
    	{
    	     for (Cour c : s2.getLc()) {
    	   		nbr  += DBInteraction.Maj("update salle set capacite="+s2.getCapacite()+",id_c= "+c.getId()+",nom= '"+s2.getNom()+"' where id = "+s1.getId());
			}	
    	}
    	
    	DBInteraction.disconnect();
    	return nbr;
    }
    @PUT
    @Path("/Modifier_2")
    @Consumes(MediaType.APPLICATION_JSON)
    public int Modifier_2(Salle s) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	if(s.getLc() == null)
    	{
    		nbr  += DBInteraction.Maj("update salle set capacite="+s.getCapacite()+",id_c=null,nom= '"+s.getNom()+"' where id = "+s.getId());
    	}
    	else
    	{
    	     for (Cour c : s.getLc()) {
    	   		nbr  += DBInteraction.Maj("update salle set capacite="+s.getCapacite()+",id_c= "+c.getId()+",nom= '"+s.getNom()+"' where id = "+s.getId());
			}	
    	}
    	
    	DBInteraction.disconnect();
    	return nbr;
    }
    public int affecter_Cour(Salle s,Cour c) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
        nbr  += DBInteraction.Maj("insert into salle values(null,"+s.getCapacite()+","+c.getId()+",'"+s.getNom()+"')");
    	DBInteraction.disconnect();
    	return nbr;
    }
    @POST
    @Path("/affecter_Cour_2")
    @Consumes(MediaType.APPLICATION_JSON)
    public int affecter_Cour_2(AffecterCour a) throws ClassNotFoundException, SQLException
    {
    	Salle s = a.getS();
    	Cour c = a.getC();
    	
    	int nbr = 0;
    	DBInteraction.connect();
        nbr  += DBInteraction.Maj("insert into salle values(null,"+s.getCapacite()+","+c.getId()+",'"+s.getNom()+"')");
    	DBInteraction.disconnect();
    	return nbr;
    }
    @POST
    @Path("/affecter_Cour_3")
    public int affecter_Cour_3(@QueryParam(value = "capaciteSalle") int capaciteS, @QueryParam(value = "nomSalle") String nomS, @QueryParam(value = "idCour") int idC) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
        nbr  += DBInteraction.Maj("insert into salle values(null,"+capaciteS+","+idC+",'"+nomS+"')");
    	DBInteraction.disconnect();
    	return nbr;
    }
    public int Supprimer_CourAffecter(Salle s,Cour c) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
        nbr  += DBInteraction.Maj("update salle set id_c = null where nom = '"+s.getNom()+"' and id_c = "+c.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @PUT
    @Path("/Supprimer_CourAffecter_2")
    @Consumes(MediaType.APPLICATION_JSON)
    public int Supprimer_CourAffecter_2(SupprimerCourAffecterWrapper supprimer) throws ClassNotFoundException, SQLException
    {
    	Salle s = supprimer.getS();
    	Cour c = supprimer.getC();
    	
    	int nbr = 0;
    	DBInteraction.connect();
        nbr  += DBInteraction.Maj("update salle set id_c = null where nom = '"+s.getNom()+"' and id_c = "+c.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @DELETE
    @Path("/supprimer")
    @Consumes(MediaType.APPLICATION_JSON)
    public int supprimer(Salle s) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("delete from salle where id = "+s.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    public int supprimer_By_ID_Cour(Cour c) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("delete from salle where id_c = "+c.getId());
    	DBInteraction.disconnect();
    	return nbr;
    }
    @DELETE
    @Path("/supprimer_By_ID_Cour_2")
    public int supprimer_By_ID_Cour_2(@QueryParam(value = "idC") int idC) throws ClassNotFoundException, SQLException
    {
    	int nbr = 0;
    	DBInteraction.connect();
    	nbr = DBInteraction.Maj("delete from salle where id_c = "+idC);
    	DBInteraction.disconnect();
    	return nbr;
    }
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    	Client client = Client.create(new DefaultClientConfig());
    	URI uri = UriBuilder.fromUri("http://localhost:8080/Naya_Soutien/rs/").build();
    	ObjectMapper mapper = new ObjectMapper();
    	ClientResponse resp;
    	String corpsRepHttp;
    	String JsonBody; 
    	
    	int idCour = 2;
    	//Cour c = daoc.Find_ID(idCour);
		resp = client.resource(uri)
		        .path("cours")
		        .path("Find_ID")
		        .queryParam("id", idCour+"")
		        .get(ClientResponse.class);
		corpsRepHttp = resp.getEntity(String.class);
		Cour c = mapper.readValue(corpsRepHttp, Cour.class);
		System.out.println("c = "+c.getNom());
		
		int idSalle = 85;
		//Salle s = daos.Find_Salle_Id(idSalle);
		resp = client.resource(uri)
		        .path("salles")
		        .path("Find_Salle_Id")
		        .queryParam("id", idSalle+"")
		        .get(ClientResponse.class);
		corpsRepHttp = resp.getEntity(String.class);
		Salle s = mapper.readValue(corpsRepHttp, Salle.class);
		System.out.println("s = "+s.getNom());
		
		//daos.Supprimer_CourAffecter(s,c);
		JsonBody = "{" +
		        "\"s\":" + mapper.writeValueAsString(s) + ", " +
		        "\"c\":" + mapper.writeValueAsString(c) +
		        "}";;
		resp = client.resource(uri)
		        .path("salles")
		        .path("Supprimer_CourAffecter_2")
		        .type(MediaType.APPLICATION_JSON)
		        .put(ClientResponse.class, JsonBody);
		corpsRepHttp = resp.getEntity(String.class);
		
	}
}
