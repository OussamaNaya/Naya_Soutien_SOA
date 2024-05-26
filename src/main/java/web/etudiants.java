package web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.cj.Session;

import java.net.URI;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.w3c.dom.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import DAO.DAOAdmin;
import DAO.DAOContact;
import DAO.DAODirecteur;
import DAO.DAOEnseignant;
import DAO.DAOEtudiant;
import Models.Admin;
import Models.Contact;
import Models.Cour;
import Models.Directeur;
import Models.Enseignant;
import Models.Etudiant;

/**
 * Servlet implementation class etudiants
 */
public class etudiants extends HttpServlet {
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
    public etudiants() {
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
		if(request.getParameter("contact") != null)
		{
			String name = request.getParameter("name");
		    String email = request.getParameter("email");
		    String subject = request.getParameter("subject");
		    String msg = request.getParameter("msg");
		    Contact c = new Contact(0, name, email, subject, msg);
		    
		    JsonBody = mapper.writeValueAsString(c);
		    resp = client.resource(uri)
	                .path("contacts")
	                .path("CreeContact")
	                .type(MediaType.APPLICATION_JSON)
	                .post(ClientResponse.class, JsonBody);
		    String b = resp.getEntity(String.class);
		    
		   // DAOContact daoc = new DAOContact();
		    String repense = "";
		    if(b.equals("true"))
			{
				repense = "Merci pour votre Message !";
			}
			else
			{
				repense = "Essayer une autre fois  !";
			}
			response.sendRedirect("jsp/contact.jsp?response="+repense);
		}
		if(request.getParameter("op") != null && request.getParameter("op").equals("valider"))
		{
			HttpSession ses = request.getSession();
			if(ses.getAttribute("e") != null)
			{
				Etudiant e = (Etudiant) ses.getAttribute("e");
				List<Cour> lc = (List<Cour>) ses.getAttribute("lc");
				out.print("L'etudiant est : "+e.getNom());
				out.print("Courses : ");
				for (Cour cour : lc) {
					out.print(cour);
				}
				DAOEtudiant daoe = new DAOEtudiant();
				for (Cour cour : lc) {
					//daoe.ajouter_Cour(e, cour);
					JsonBody = "{" +
					        "\"u\":" + mapper.writeValueAsString(e) + ", " +
					        "\"c\":" + mapper.writeValueAsString(cour) +
					        "}";;
					resp = client.resource(uri)
					        .path("etudiants")
					        .path("ajouter_Cour_2")
					        .type(MediaType.APPLICATION_JSON)
					        .post(ClientResponse.class, JsonBody);
				}
				response.sendRedirect("jsp/Done.jsp");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Assslamo Alikom");
		DAOEtudiant daoetu = new DAOEtudiant();
		DAOEnseignant daoensg = new DAOEnseignant();
		DAOAdmin daoadm = new DAOAdmin();
		
		if(request.getParameter("signup") != null)
		{
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String genre = request.getParameter("genre");
			String tele = request.getParameter("tele");
			String telP = request.getParameter("teleP");
			String gmail = request.getParameter("gmail");
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			out.println("\nDone signup.....");
			out.print(fname+" ,  "+lname+"   ,   "+genre+"  ,   "+"  ,  "+tele+"  ,  "+telP+"    ,   "+login+"     ,    "+pass);
			
			Etudiant e = new Etudiant(0,fname, lname, gmail, genre, tele, telP, login, pass);
			int nbr = 0;
			resp = client.resource(uri)
			    .path("etudiants")
			    .path("CreeCompte")
			    .type(MediaType.APPLICATION_JSON)
			    .post(ClientResponse.class, mapper.writeValueAsString(e));
			   corpsRepHttp = resp.getEntity(String.class);
			response.sendRedirect("jsp/login.jsp?m=succeRegister");
		}
		if(request.getParameter("signin") != null)
		{
			String login = request.getParameter("login");
			String pass = request.getParameter("pass");
			String role = request.getParameter("role");
			out.print("signin Done ....");
			out.print(login+"   ,    "+pass);
			if(role.equals("Etudiant"))
			{
				Etudiant e = new Etudiant(login,pass);
				resp = client.resource(uri)
				        .path("etudiants")
				        .path("authentifie_2")
				        .queryParam("login", login)
				        .queryParam("password", pass) 
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				e = mapper.readValue(corpsRepHttp, Etudiant.class);
				//e = daoetu.authentifie(e);
				if(e == null)
				{
					response.sendRedirect("jsp/login.jsp?m=echecIn");
				}
				else
				{
					HttpSession ses = request.getSession();
					ses.setAttribute("e", e);
					response.sendRedirect("jsp/index.jsp");
				}
			}
			if(role.equals("Enseignant"))
			{
				Enseignant ensg = new Enseignant(login, pass);
				//ensg = daoensg.authentifie(ensg);
				resp = client.resource(uri)
				        .path("enseignants")
				        .path("authentifie_2")
				        .queryParam("login", login)
				        .queryParam("password", pass) 
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				ensg = mapper.readValue(corpsRepHttp, Enseignant.class);
				
				if(ensg == null)
				{
					response.sendRedirect("jsp/login.jsp?m=echecIn");
				}
				else
				{
					HttpSession ses = request.getSession();
					ses.setAttribute("ensg", ensg);
					response.sendRedirect("jsp/index.jsp");
				}
			}
			if(role.equals("Admin"))
			{
				Admin adm = new Admin(login, pass);
				//adm = daoadm.authentifie(adm);
				resp = client.resource(uri)
				        .path("admins")
				        .path("authentifie_2")
				        .queryParam("login", login)
				        .queryParam("password", pass) 
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				adm = mapper.readValue(corpsRepHttp, Admin.class);
				
				if(adm == null)
				{
					response.sendRedirect("jsp/login.jsp?m=echecIn");
				}
				else
				{
					HttpSession ses = request.getSession();
					ses.setAttribute("adm", adm);
					response.sendRedirect("jsp/index.jsp");
				}
			}
			if(role.equals("Directeur"))
			{
				out.print("              role.equals(\"Dericteur\")");
				Directeur d = new Directeur(login, pass);
				out.print("            d.getLogin() = "+d.getLogin());
				out.print("            d.getPass() = "+d.getPass());
				DAODirecteur daod = new DAODirecteur();
				
				//d = daod.authentifie(d);
				resp = client.resource(uri)
				        .path("directeurs")
				        .path("authentifie_2")
				        .queryParam("login", d.getLogin())
				        .queryParam("password", d.getPass())
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				d = mapper.readValue(corpsRepHttp, Directeur.class);
				
				if(d == null)
				{
					response.sendRedirect("jsp/login.jsp?m=echecIn");
				}
				else
				{
					//out.print(" id  =  "+d.getId());
					out.print(" nom  =  "+d.getNom());
					out.print(" prenom  =  "+d.getPrenom());
					out.print(" login  =  "+d.getLogin());
					out.print(" pass  =  "+d.getPass());
					HttpSession ses = request.getSession();
					ses.setAttribute("dir", d);
					response.sendRedirect("jsp/index.jsp");
				}
			}
			
		}
	}

}
