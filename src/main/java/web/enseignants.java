package web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import DAO.DAOAbsence;
import DAO.DAOCour;
import DAO.DAOEnseignant;
import DAO.DAOEtudiant;
import DAO.DAOSalle;
import Models.Absence_Etudiant;
import Models.Cour;
import Models.Enseignant;
import Models.Etudiant;
import Models.Salle;

/**
 * Servlet implementation class enseignants
 */
public class enseignants extends HttpServlet {
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
    public enseignants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print("Assalamo alikom ostad");
		
		if(request.getParameter("op") != null)
		{
			if(request.getParameter("op").equals("disconnect"))
			{
				HttpSession ses = request.getSession();
				ses.removeAttribute("e");
				ses.invalidate();
				response.sendRedirect("jsp/index.jsp");
			}
		}
		if(request.getParameter("op") != null)
		{
			if(request.getParameter("op").equals("suppCour"))
			{
				out.print("Vous etes dans op = suppCour");
				int id =  Integer.parseInt(request.getParameter("id"));
				DAOCour daoc = new DAOCour();
				DAOEnseignant daoensg = new DAOEnseignant();
				DAOSalle daos = new DAOSalle();
				int nbr = 0;
				
				//Cour c = daoc.Find_ID(id);
				resp = client.resource(uri)
				        .path("cours")
				        .path("Find_ID")
				        .queryParam("id", id+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Cour c = mapper.readValue(corpsRepHttp, Cour.class);
				
				//nbr += daoc.sapprimer(c);
				resp = client.resource(uri)
				        .path("cours")
				        .path("sapprimer_2")
				        .queryParam("idC", c.getId()+"")
				        .delete(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
				
				//nbr += daoensg.supprimer_Id_Cour(c);
				resp = client.resource(uri)
				        .path("enseignants")
				        .path("supprimer_Id_Cour_2")
				        .queryParam("idC", c.getId()+"")
				        .delete(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
				
				//nbr += daos.supprimer_By_ID_Cour(c);
				resp = client.resource(uri)
				        .path("salles")
				        .path("supprimer_By_ID_Cour_2")
				        .queryParam("idC", c.getId()+"")
				        .delete(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				nbr += Integer.parseInt(corpsRepHttp);
				
				out.print("le nbr = "+nbr);
				if(nbr != 0 )
				{
					response.sendRedirect("jsp/courses.jsp");
				}
			}
		}
		if(request.getParameter("op") != null)
		{
			if(request.getParameter("op").equals("abs"))
			{
				int idE = Integer.parseInt(request.getParameter("idE"));
				int idG = Integer.parseInt(request.getParameter("idG"));
				
				out.print("     idEtudiant = "+idE);
				HttpSession ses = request.getSession();
				if(ses.getAttribute("ensg") != null)
				{
					Enseignant ensg = (Enseignant) ses.getAttribute("ensg");
					DAOEtudiant daoe = new DAOEtudiant();
					DAOAbsence daoabs = new DAOAbsence();
					LocalDateTime currentDateTime = LocalDateTime.now();
					Etudiant e = null;
					try {
						e = daoe.Find_Id(idE);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Absence_Etudiant abse = new Absence_Etudiant(0, currentDateTime.toString(), e);
				    try {
						out.print("                ,L'etat d'absence est : "+daoabs.CreeAbsence_Etudiant(abse));
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    response.sendRedirect("jsp/groupe.jsp?op=consulter&idGroup="+idG);   
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
		out.print("Assalamo alikom");
		
		if(request.getParameter("save") != null)
		{
			out.print("                    request.getParameter(\"save\") != null");
			int id = Integer.parseInt(request.getParameter("id"));
			out.print("   id = "+id);
			String nom = request.getParameter("nome");
			out.print("   nome = "+nom);
			String niveau = request.getParameter("niveau");
			out.print("   niveau = "+niveau);
			int heur = Integer.parseInt(request.getParameter("heur"));
			out.print("   heur = "+heur);
			String dateD = request.getParameter("dateD");
			out.print("   dateD = "+dateD);
			String dateF = request.getParameter("dateF");
			out.print("   dateF = "+dateF);
			int prix = Integer.parseInt(request.getParameter("prix"));
			out.print("   prix = "+prix);
			
			DAOCour daoc = new DAOCour();
			Cour c1 = null;
			
			//c1 = daoc.Find_ID(id);
			resp = client.resource(uri)
			        .path("cours")
			        .path("Find_ID")
			        .queryParam("id", id+"")
			        .get(ClientResponse.class);
			corpsRepHttp = resp.getEntity(String.class);
			c1 = mapper.readValue(corpsRepHttp, Cour.class);
			
			Cour c2 = new Cour(id, nom, niveau, heur, dateD, dateF, prix);
			int nbr = 0;
			try {
			    nbr = daoc.Modifier(c1, c2);
			    
			    JsonBody = "{" +
			            "\"c1\":" + mapper.writeValueAsString(c1) + ", " +
			            "\"c2\":" + mapper.writeValueAsString(c2) +
			            "}";
			    resp = client.resource(uri)
				        .path("cours")
				        .path("Modifier_2")
				        .type(MediaType.APPLICATION_JSON)
				        .put(ClientResponse.class, JsonBody);
			    corpsRepHttp = resp.getEntity(String.class);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("Le nbr = "+corpsRepHttp);
			response.sendRedirect("jsp/courses.jsp");
			
			
			//out.print(nom+"  ,  "+heur+"  ,  "+dateD+"  ,  "+dateF+"   ,  "+prix);
		}
		if(request.getParameter("ajouter") != null)
		{
			out.print("                    request.getParameter(\"ajouter\") != null");
			
			
			String nom = request.getParameter("nome");
			out.print("   nome = "+nom);
			String niveau = request.getParameter("niveau");
			out.print("   niveau = "+niveau);
			int heur = Integer.parseInt(request.getParameter("heur"));
			out.print("   heur = "+heur);
			String dateD = request.getParameter("dateD");
			out.print("   dateD = "+dateD);
			String dateF = request.getParameter("dateF");
			out.print("   dateF = "+dateF);
			int prix = Integer.parseInt(request.getParameter("prix"));
			out.print("   prix = "+prix);
			int idSalle = Integer.parseInt(request.getParameter("idSalle"));
			out.print("    isSalle = "+idSalle);
		    
			
			DAOCour daoc = new DAOCour();
			Cour c = new Cour(0, nom, niveau, heur, dateD, dateF, prix);
			
			//out.print(daoc.CreeCour(c));
			resp = client.resource(uri)
			        .path("cours")
			        .path("CreeCour_2")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, mapper.writeValueAsString(c));
			
			DAOSalle daos = new DAOSalle();
			//Salle s = daos.Find_Salle_Id(idSalle);
		    resp = client.resource(uri)
			        .path("salles")
			        .path("Find_Salle_Id")
			        .queryParam("id", idSalle+"")
			        .get(ClientResponse.class);
			corpsRepHttp = resp.getEntity(String.class);
			Salle s = mapper.readValue(corpsRepHttp, Salle.class);
			
			List<Cour> lc = new ArrayList<Cour>();
			//c = daoc.Find_Cour_Id(c);
			resp = client.resource(uri)
			        .path("cours")
			        .path("Find_Cour_Id_2")
			        .queryParam("nom", c.getNom())
			        .queryParam("niveau", c.getNiveau())
			        .get(ClientResponse.class);
			corpsRepHttp = resp.getEntity(String.class);
			c = mapper.readValue(corpsRepHttp, Cour.class);
			
			lc.add(c);
			out.print("    lc != null : "+lc);
			s.setLc(lc);
			out.print("    s.getLc() != null : "+s.getLc());
			
			//daos.CreeSalle(s);
			resp = client.resource(uri)
			        .path("salles")
			        .path("CreeSalle")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, mapper.writeValueAsString(s));	
		    
			//c = daoc.Find_Cour_Id(c);
			resp = client.resource(uri)
			        .path("cours")
			        .path("Find_Cour_Id_2")
			        .queryParam("nom", c.getNom())
			        .queryParam("niveau", c.getNiveau())
			        .get(ClientResponse.class);
			corpsRepHttp = resp.getEntity(String.class);
			c = mapper.readValue(corpsRepHttp, Cour.class);
			
			HttpSession ses = request.getSession();
			Enseignant ensg =(Enseignant) ses.getAttribute("ensg");
			DAOEnseignant daoensg = new DAOEnseignant();
			out.print("       Le nom du Enseignanat est : "+ensg.getNom());
			
			//daoensg.ADD_Cour(ensg, c);
			JsonBody = "{" +
			        "\"e\":" + mapper.writeValueAsString(ensg) + ", " +
			        "\"c\":" + mapper.writeValueAsString(c) +
			        "}";
			resp = client.resource(uri)
			        .path("enseignants")
			        .path("ADD_Cour_2")
			        .type(MediaType.APPLICATION_JSON)
			        .post(ClientResponse.class, JsonBody);
			response.sendRedirect("jsp/courses.jsp");
			
		}
	}

}
