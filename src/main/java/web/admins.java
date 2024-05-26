package web;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.SQLException;

import DAO.DAOCour;
import DAO.DAOEmplois;
import DAO.DAOEnseignant;
import DAO.DAOEtudiant;
import DAO.DAOGroupe;
import DAO.DAOSalle;
import Models.Cour;
import Models.Enseignant;
import Models.Etudiant;
import Models.Groupe;
import Models.Salle;

/**
 * Servlet implementation class admins
 */
public class admins extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Client client = Client.create(new DefaultClientConfig());
	URI uri = UriBuilder.fromUri("http://localhost:8080/Naya_Soutien/rs/").build();
	ObjectMapper mapper = new ObjectMapper();
	ClientResponse resp;
	String corpsRepHttp;
	String JsonBody;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admins() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print("Assalmao Alikom");
		if(request.getParameter("op") != null)
		{
			out.print("         request.getParameter(\"op\") != null      ");
			if(request.getParameter("op").equals("supprimerGroupe"))
			{
				int idGroup = Integer.parseInt(request.getParameter("idGroup"));
				DAOGroupe daog = new DAOGroupe();
				DAOEtudiant daoe = new DAOEtudiant();
				DAOEnseignant daoensg = new DAOEnseignant();
				DAOEmplois daoemplois = new DAOEmplois();
				int nbr = 0;
				
				//Groupe g = daog.Find_ID(idGroup);
				resp = client.resource(uri)
				        .path("groupes")
				        .path("Find_ID")
				        .queryParam("id", idGroup+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Groupe g = mapper.readValue(corpsRepHttp, Groupe.class);
				
				//nbr += daoe.supprimer_where_Id_Groupe(g);	
				JsonBody = mapper.writeValueAsString(g);
				resp = client.resource(uri)
				        .path("etudiants")
				        .path("supprimer_where_Id_Groupe")
				        .type(MediaType.APPLICATION_JSON)
				        .delete(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
						
				//nbr += daoensg.Off_Groupe(g);
				JsonBody = mapper.writeValueAsString(g);
				resp = client.resource(uri)
				        .path("enseignants")
				        .path("Off_Groupe_2")
				        .type(MediaType.APPLICATION_JSON)
				        .put(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
				
				//nbr += daoemplois.supprimer_where_Id_Groupe(g);
				JsonBody = mapper.writeValueAsString(g);
				resp = client.resource(uri)
				        .path("emplois")
				        .path("supprimer_where_Id_Groupe")
				        .type(MediaType.APPLICATION_JSON)
				        .delete(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
				
				//nbr += daog.supprimer(g);
				JsonBody = mapper.writeValueAsString(g);
				resp = client.resource(uri)
				        .path("groupes")
				        .path("supprimer")
				        .type(MediaType.APPLICATION_JSON)
				        .delete(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
				
				out.print("  , nbr = "+nbr);
			    response.sendRedirect("jsp/groupe.jsp");
				
			}
			if(request.getParameter("op").equals("supprimerEtudiant"))
			{
				DAOEtudiant daoe = new DAOEtudiant();
				int idE = Integer.parseInt(request.getParameter("idE"));
				int idGroup = Integer.parseInt(request.getParameter("idGroup"));
			    try {
					//Etudiant e = daoe.Find_Id(idE);
					resp = client.resource(uri)
					        .path("etudiants")
					        .path("Find_Id/"+idE)
					        .get(ClientResponse.class);
					corpsRepHttp = resp.getEntity(String.class);
					Etudiant e = mapper.readValue(corpsRepHttp, Etudiant.class);
					
					daoe.supprimer(e);
					JsonBody = mapper.writeValueAsString(e);
					resp = client.resource(uri)
					        .path("etudiants")
					        .path("supprimer")
					        .type(MediaType.APPLICATION_JSON)
					        .delete(ClientResponse.class, JsonBody);
					corpsRepHttp = resp.getEntity(String.class);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    response.sendRedirect("jsp/groupe.jsp?op=consulter&idGroup="+idGroup);
			    
			}
			if(request.getParameter("op").equals("supprimerEnseignant"))
			{
				out.print(" request.getParameter(\"op\").equals(\"supprimerEnseignant\")     ");
				int idEnseignant = Integer.parseInt(request.getParameter("idEnseignant"));
				out.print("     , idEnseignant = "+idEnseignant);
				DAOEnseignant daoensg = new DAOEnseignant();
				
				//Enseignant ensg = daoensg.Find_Id(idEnseignant);
				//out.print("l'enseignant s'appelle = "+ensg.getNom());
				resp = client.resource(uri)
				        .path("enseignants")
				        .path("Find_Id")
				        .queryParam("id", idEnseignant+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Enseignant ensg = mapper.readValue(corpsRepHttp, Enseignant.class);
				
				//daoensg.supprimer(ensg);
				JsonBody = mapper.writeValueAsString(ensg);
				resp = client.resource(uri)
				        .path("enseignants")
				        .path("supprimer")
				        .type(MediaType.APPLICATION_JSON)
				        .delete(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				response.sendRedirect("jsp/enseignant.jsp");
				
			}
			if(request.getParameter("op").equals("supprimerSalle"))
			{
				int idSalle = Integer.parseInt(request.getParameter("idSalle"));
				out.print("   idSalle = "+idSalle);
				DAOSalle daos = new DAOSalle();
				Salle s = new Salle();
				
				//s = daos.Find_Salle_Id(idSalle);
				resp = client.resource(uri)
				        .path("salles")
				        .path("Find_Salle_Id")
				        .queryParam("id", idSalle+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				s = mapper.readValue(corpsRepHttp, Salle.class);
				
				out.print("   s.getId() = "+s.getId());
				//daos.supprimer(s);
				JsonBody = mapper.writeValueAsString(s);
				resp = client.resource(uri)
				        .path("salles")
				        .path("supprimer")
				        .type(MediaType.APPLICATION_JSON)
				        .delete(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				response.sendRedirect("jsp/salle.jsp");
			}
			if(request.getParameter("op").equals("supprimerCourSAlle"))
			{
				int idSalle = Integer.parseInt(request.getParameter("idSalle"));
				int idCour = Integer.parseInt(request.getParameter("idCour"));
				DAOSalle daos = new DAOSalle();
				DAOCour daoc = new DAOCour();
				
				//Cour c = daoc.Find_ID(idCour);
				resp = client.resource(uri)
				        .path("cours")
				        .path("Find_ID")
				        .queryParam("id", idCour+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Cour c = mapper.readValue(corpsRepHttp, Cour.class);
				
				//Salle s = daos.Find_Salle_Id(idSalle);
				resp = client.resource(uri)
				        .path("salles")
				        .path("Find_Salle_Id")
				        .queryParam("id", idSalle+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Salle s = mapper.readValue(corpsRepHttp, Salle.class);
				
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
				response.sendRedirect("jsp/salle.jsp?op=consulter&idSalle="+idSalle);
			}
			if(request.getParameter("op").equals("affecterCourSalle"))
			{
				int idSalle = Integer.parseInt(request.getParameter("idSalle"));
				int idCour = Integer.parseInt(request.getParameter("idCour"));
				DAOSalle daos = new DAOSalle();
				DAOCour daoc = new DAOCour();
				
				//Cour c = daoc.Find_ID(idCour);
				resp = client.resource(uri)
				        .path("cours")
				        .path("Find_ID")
				        .queryParam("id", idCour+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Cour c = mapper.readValue(corpsRepHttp, Cour.class);
				
				//Salle s = daos.Find_Salle_Id(idSalle);
				resp = client.resource(uri)
				        .path("salles")
				        .path("Find_Salle_Id")
				        .queryParam("id", idSalle+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Salle s = mapper.readValue(corpsRepHttp, Salle.class);
				
				//daos.affecter_Cour(s, c);
				JsonBody = "{" +
				        "\"s\":" + mapper.writeValueAsString(s) + ", " +
				        "\"c\":" + mapper.writeValueAsString(c) +
				        "}";;
				resp = client.resource(uri)
				        .path("salles")
				        .path("affecter_Cour_2")
				        .type(MediaType.APPLICATION_JSON)
				        .post(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				response.sendRedirect("jsp/salle.jsp?op=consulter&idSalle="+idSalle+"&op2=affecterCour");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print("Assalamo Alikom doPost");
		if(request.getParameter("ajouterGroupe") != null)
		{
			String nom = request.getParameter("nom");
			String niveau = request.getParameter("niveau");
			int capacite = Integer.parseInt(request.getParameter("capacite"));
			out.print("   , nom = "+nom);
			out.print("   , niveau = "+niveau);
			out.print("   , capacite = "+capacite);
			
			DAOGroupe daog = new DAOGroupe();
			Groupe g = new Groupe(0, nom, niveau, capacite);
			
			//daog.CreeGroupe(g);
			resp = client.resource(uri)
			        .path("groupes")
			        .path("CreeGroupe")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, mapper.writeValueAsString(g));
			corpsRepHttp = resp.getEntity(String.class);
			response.sendRedirect("jsp/groupe.jsp");
		}
		if(request.getParameter("saveG") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String niveau = request.getParameter("niveau");
			int capacite = Integer.parseInt(request.getParameter("capacite"));
			out.print("  id = "+id);
			out.print("  nom = "+nom);
			out.print("  niveau = "+niveau);
			out.print("  capacite = "+capacite);
			DAOGroupe daog = new DAOGroupe();
			Groupe g1 = new Groupe(id, nom, niveau, capacite);
			Groupe g2 = new Groupe(id, nom, niveau, capacite);
			
			//daog.Modifier(g1, g2);
			JsonBody = mapper.writeValueAsString(g2);
			resp = client.resource(uri)
			        .path("groupes")
			        .path("Modifier_2")
			        .type(MediaType.APPLICATION_JSON)
			        .put(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);
			
			response.sendRedirect("jsp/groupe.jsp");
		}
		if(request.getParameter("ajouterEtudiant") != null)
		{
			out.print("           request.getParameter(\"ajouterEtudiant\") != null");
			DAOEtudiant daoe = new DAOEtudiant();
			DAOGroupe daog = new DAOGroupe();
			int id_groupe = Integer.parseInt(request.getParameter("id_groupe"));
			String nom = request.getParameter("nom");			
			String prenom = request.getParameter("prenom");
			String gmail = request.getParameter("gmail");
			String genre = request.getParameter("genre");
			String tele = request.getParameter("phone");
			String teleP = request.getParameter("phoneP");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			out.print(" id_groupe = "+id_groupe);
			out.print(" nom = "+nom);
			out.print(" prenom = "+prenom);
			out.print(" gmail = "+gmail);
			out.print(" genre = "+genre);
			out.print(" tele = "+tele);
			out.print(" teleP = "+teleP);
			out.print(" login = "+login);
			out.print(" pass = "+pass);
			
			//Groupe g = daog.Find_ID(id_groupe);
			resp = client.resource(uri)
			        .path("groupes")
			        .path("Find_ID")
			        .queryParam("id", id_groupe+"")
			        .get(ClientResponse.class);
			corpsRepHttp = resp.getEntity(String.class);
			Groupe g = mapper.readValue(corpsRepHttp, Groupe.class);
			
			Etudiant e = new Etudiant(0, nom, prenom, gmail, genre, tele, teleP, login, pass, null, g);
			//daoe.CreeCompte_Whid_Groupe(e);
			JsonBody = mapper.writeValueAsString(e);
			resp = client.resource(uri)
			        .path("etudiants")
			        .path("CreeCompte_Whid_Groupe")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);		
			response.sendRedirect("jsp/groupe.jsp?op=consulter&idGroup="+id_groupe);
		}
		if(request.getParameter("modifierEtudiant") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String gmail = request.getParameter("gmail");
			String genre = request.getParameter("genre");
			String tele = request.getParameter("tele");
			String teleP = request.getParameter("teleP");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			int id_groupe = Integer.parseInt(request.getParameter("id_groupe"));
			
			DAOEtudiant daoe = new DAOEtudiant();
			DAOGroupe daog = new DAOGroupe();
			try {
				Etudiant e = new Etudiant(id, nom, prenom, gmail, genre, tele, teleP, login, pass);
				//Groupe g = daog.Find_ID(id_groupe);
				resp = client.resource(uri)
				        .path("groupes")
				        .path("Find_ID")
				        .queryParam("id", id_groupe+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Groupe g = mapper.readValue(corpsRepHttp, Groupe.class);
				
				//daoe.Modifier(e,e);
				JsonBody = "{" +
		                "\"e1\":" + mapper.writeValueAsString(e) + ", " +
		                "\"e2\":" + mapper.writeValueAsString(e) +
		                "}";
				resp = client.resource(uri)
				        .path("etudiants")
				        .path("Modifier_3")
				        .type(MediaType.APPLICATION_JSON)
				        .put(ClientResponse.class, JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
				
				daoe.affecter_Groupe(e, g);
				JsonBody = "{" +
		                "\"e\":" + mapper.writeValueAsString(e) + ", " +
		                "\"g\":" + mapper.writeValueAsString(g) +
		                "}";
				resp = client.resource(uri)
				        .path("etudiants")
				        .path("affecter_Groupe_2")
				        .type(MediaType.APPLICATION_JSON)
				        .post(ClientResponse.class,JsonBody);
				corpsRepHttp = resp.getEntity(String.class);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("jsp/groupe.jsp?op=consulter&idGroup="+id_groupe);
			
		}
		if(request.getParameter("ajouterEnseignant") != null)
		{
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			int age = Integer.parseInt(request.getParameter("age"));
			String matier = request.getParameter("matier");
			String genre = request.getParameter("genre");
			String tele = request.getParameter("tele");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			
			DAOEnseignant daoensg = new DAOEnseignant();
			Enseignant ensg = new Enseignant(0, nom, prenom, age, matier, genre, tele, login, pass);
			
			//daoensg.CreeCompte(ensg);
			JsonBody = mapper.writeValueAsString(ensg);
			resp = client.resource(uri)
			        .path("enseignants")
			        .path("CreeCompte")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);
			
			response.sendRedirect("jsp/enseignant.jsp");
		}
		if(request.getParameter("modiferEnseignant") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			int age = Integer.parseInt(request.getParameter("age"));
			String genre = request.getParameter("genre");
			String tele = request.getParameter("tele");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			Enseignant e1 = new Enseignant(id, nom, prenom, age, null, genre, tele, login, pass);
			DAOEnseignant daoensg = new DAOEnseignant();
			
			//daoensg.Modifier(e1, e1);
			JsonBody = "{" +
			        "\"e1\":" + mapper.writeValueAsString(e1) + ", " +
			        "\"e2\":" + mapper.writeValueAsString(e1) +
			        "}";;
			resp = client.resource(uri)
			        .path("enseignants")
			        .path("Modifier_2")
			        .type(MediaType.APPLICATION_JSON)
			        .put(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);
			response.sendRedirect("jsp/enseignant.jsp");
		}
		if(request.getParameter("ajouterSalle") != null)
		{
			String nom = request.getParameter("nom");
			int capaciter = Integer.parseInt(request.getParameter("capaciter"));
			DAOSalle daos = new DAOSalle();
			Salle s = new Salle(0, nom, capaciter);
			
			//daos.CreeSalle(s);
			JsonBody = mapper.writeValueAsString(s);
			resp = client.resource(uri)
			        .path("salles")
			        .path("CreeSalle")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);
			response.sendRedirect("jsp/salle.jsp");
		}
		if(request.getParameter("modifierSalle") != null)
		{
			String nom = request.getParameter("nom");
			int capaciter = Integer.parseInt(request.getParameter("capaciter"));
			int id = Integer.parseInt(request.getParameter("id"));
			Salle s = new Salle(id, nom, capaciter);
			DAOSalle daos = new DAOSalle();
			
			//daos.Modifier(s, s);
			JsonBody = mapper.writeValueAsString(s);
			resp = client.resource(uri)
			        .path("salles")
			        .path("Modifier_2")
			        .type(MediaType.APPLICATION_JSON)
			        .put(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);
			response.sendRedirect("jsp/salle.jsp");
		}
	}

}
