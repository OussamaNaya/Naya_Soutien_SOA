package web;

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
import java.time.LocalDate;

import DAO.DAOAdmin;
import DAO.DAODirecteur;
import DAO.DAOEnseignant;
import DAO.DAOEtudiant;
import DAO.DAOPaiement;
import Models.Admin;
import Models.Directeur;
import Models.Enseignant;
import Models.Etudiant;
import Models.Paiement_Admin;
import Models.Paiement_Enseignant;
import Models.Paiement_Etudiant;

/**
 * Servlet implementation class directeurs
 */
public class directeurs extends HttpServlet {
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
    public directeurs() {
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
			if(request.getParameter("op").equals("supprimerAdmin"))
			{
				out.print(" request.getParameter(\"op\").equals(\"supprimerAdmin\")     ");
				int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
				out.print("     , idAdmin = "+idAdmin);
				DAOAdmin daoa = new DAOAdmin();
				
				//daoa.sapprimer(daoa.Find_Id(idAdmin));
				resp = client.resource(uri)
				        .path("admins")
				        .path("Find_Id")
				        .queryParam("id", idAdmin+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Admin a = mapper.readValue(corpsRepHttp, Admin.class);
				
				JsonBody = mapper.writeValueAsString(a);
				resp = client.resource(uri)
				        .path("admins")
				        .path("sapprimer")
				        .type(MediaType.APPLICATION_JSON)
				        .delete(ClientResponse.class, JsonBody);
				response.sendRedirect("jsp/admins.jsp");
			}
			if(request.getParameter("op").equals("modiferEtat"))
			{
				if(request.getParameter("idAdmin") != null)
				{
					int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
					out.print("        idAdmin = "+idAdmin);
					DAOAdmin daoa = new DAOAdmin();
					DAOPaiement daop = new DAOPaiement();
					
					//Admin a = daoa.Find_Id(idAdmin);
					resp = client.resource(uri)
					        .path("admins")
					        .path("Find_Id")
					        .queryParam("id", idAdmin+"")
					        .get(ClientResponse.class);
					corpsRepHttp = resp.getEntity(String.class);
					Admin a = mapper.readValue(corpsRepHttp, Admin.class);
					
					LocalDate dateActuelle = LocalDate.now();
					Paiement_Admin p = new Paiement_Admin(idAdmin,""+dateActuelle , a, idAdmin);
					//daop.Admin(p);
					JsonBody = mapper.writeValueAsString(p);
					resp = client.resource(uri)
					        .path("paiements")
					        .path("Admin")
					        .type(MediaType.APPLICATION_JSON)
					        .post(ClientResponse.class, JsonBody);
					response.sendRedirect("jsp/paiement.jsp?m=admin");
				}
				if(request.getParameter("idEnseignant") != null)
				{
					int idEnseignant = Integer.parseInt(request.getParameter("idEnseignant"));
					out.print("        idEnseignant = "+idEnseignant);
					DAOEnseignant daoe = new DAOEnseignant();
					DAOPaiement daop = new DAOPaiement();
					
					//Enseignant e = daoe.Find_Id(idEnseignant);
					resp = client.resource(uri)
					        .path("enseignants")
					        .path("Find_Id")
					        .queryParam("id", idEnseignant+"")
					        .get(ClientResponse.class);
					corpsRepHttp = resp.getEntity(String.class);
					Enseignant e = mapper.readValue(corpsRepHttp, Enseignant.class);
					
					LocalDate dateActuelle = LocalDate.now();
					Paiement_Enseignant p = new Paiement_Enseignant(idEnseignant,""+dateActuelle , e, idEnseignant);
					
					//daop.Enseignant(p);
					JsonBody = mapper.writeValueAsString(p);
					resp = client.resource(uri)
					        .path("paiements")
					        .path("Enseignant")
					        .type(MediaType.APPLICATION_JSON)
					        .post(ClientResponse.class, JsonBody);
					response.sendRedirect("jsp/paiement.jsp?m=enseignant");
				}
				if(request.getParameter("idEtudiant") != null)
				{
					int idEtudiant = Integer.parseInt(request.getParameter("idEtudiant"));
					out.print("        idEtudiant = "+idEtudiant);
					DAOEtudiant daoe = new DAOEtudiant();
					DAOPaiement daop = new DAOPaiement();
					
					//Etudiant e = daoe.Find_Id(idEtudiant);
					resp = client.resource(uri)
					        .path("etudiants")
					        .path("Find_Id/"+idEtudiant)
					        .get(ClientResponse.class);
					corpsRepHttp = resp.getEntity(String.class);
					Etudiant e = mapper.readValue(corpsRepHttp, Etudiant.class);
					
					LocalDate dateActuelle = LocalDate.now();
					Paiement_Etudiant p = new Paiement_Etudiant(idEtudiant,""+dateActuelle , e, idEtudiant);
					//daop.Etudiant(p);
					JsonBody = mapper.writeValueAsString(p);
					resp = client.resource(uri)
					        .path("paiements")
					        .path("Etudiant")
					        .type(MediaType.APPLICATION_JSON)
					        .post(ClientResponse.class, JsonBody);
					response.sendRedirect("jsp/paiement.jsp?m=etudiant");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print("Assalmao Alikom");
		if(request.getParameter("ajouterAdmin") != null)
		{
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			int age = Integer.parseInt(request.getParameter("age"));
			String gmail = request.getParameter("gmail");
			String genre = request.getParameter("genre");
			String tele = request.getParameter("tele");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			
			DAOAdmin daoa  = new DAOAdmin();
			Admin a = new Admin(0, nom, prenom, age, gmail, genre, tele, login, pass);
			
			//daoa.CreeCompte(a);
			JsonBody = mapper.writeValueAsString(a);
			resp = client.resource(uri)
			        .path("admins")
			        .path("CreeCompte")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, JsonBody);
			corpsRepHttp = resp.getEntity(String.class);
			response.sendRedirect("jsp/admins.jsp");
		}
		if(request.getParameter("modiferAdmin") != null)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			int age = Integer.parseInt(request.getParameter("age"));
			String gmail = request.getParameter("gmail");
			String genre = request.getParameter("genre");
			String tele = request.getParameter("tele");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			
			Admin a = new Admin(id, nom, prenom, age, gmail, genre, tele, login, pass); 
			DAOAdmin daoa = new DAOAdmin();
			
			//daoa.Modifier(a, a);
			JsonBody = mapper.writeValueAsString(a);
			resp = client.resource(uri)
			        .path("admins")
			        .path("Modifier_2")
			        .type(MediaType.APPLICATION_JSON)
			        .put(ClientResponse.class, JsonBody);
			response.sendRedirect("jsp/admins.jsp");
		}
	}

}
