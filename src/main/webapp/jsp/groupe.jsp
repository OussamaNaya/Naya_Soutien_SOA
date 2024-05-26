<%@ page import="java.net.URI" %>

<%@page import="javax.ws.rs.core.MediaType"%>
<%@page import="javax.ws.rs.core.UriBuilder"%>

<%@ page import="com.sun.jersey.api.client.Client" %>
<%@ page import="javax.ws.rs.core.UriBuilder" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.core.JsonProcessingException" %>
<%@ page import="com.sun.jersey.api.client.ClientResponse" %>
<%@ page import="com.sun.jersey.api.client.UniformInterfaceException" %>
<%@ page import="com.sun.jersey.api.client.config.DefaultClientConfig" %>


<%@page import="DAO.DAOAbsence"%>
<%@page import="DAO.DAOEtudiant"%>
<%@page import="DAO.DAOGroupe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Models.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Naya_Soutien</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../template/lib/animate/animate.min.css" rel="stylesheet">
    <link href="../template/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../template/" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../template/css/style.css" rel="stylesheet">
    <link href="../template/css/paquit.css" rel="stylesheet">

    <!-- Css of table -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../template/table-03/css/style.css">
</head>

<%
	Client client = Client.create(new DefaultClientConfig());
	URI uri = UriBuilder.fromUri("http://localhost:8080/Naya_Soutien/rs/").build();
	ObjectMapper mapper = new ObjectMapper();
	ClientResponse resp;
	String corpsRepHttp;
	String JsonBody;
%>

<body>
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
        <a href="index.jsp" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Naya_Soutien</h2>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <% 
        	if(session.getAttribute("e") != null)
        	{
        		Etudiant e = (Etudiant) session.getAttribute("e");
        %>
		          <div class="collapse navbar-collapse" id="navbarCollapse">
		            <div class="navbar-nav ms-auto p-4 p-lg-0">	         
		                <a href="index.jsp" class="nav-item nav-link active"><%=e.getNom() %></a>
		                <a href="about.jsp" class="nav-item nav-link">About</a>
		                <a href="courses.jsp" class="nav-item nav-link">Courses</a>
		                <div class="nav-item dropdown">
		                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Emplois du Temps</a>
		                    <div class="dropdown-menu fade-down m-0">
		                        <a href="emplois.jsp?m= BAC Math" class="dropdown-item">BAC MATH</a>
		                        <a href="emplois.jsp?m= BAC PC" class="dropdown-item">BAC PC</a>
		                        <a href="emplois.jsp?m= BAC SVT" class="dropdown-item">BAC SVT</a>
		                    </div>
		                </div>
		                <a href="contact.jsp" class="nav-item nav-link ">Contact</a>
		            </div>
		            <a href="../etudiants?op=disconnect" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block ">Disconnect<i
		                    class="fa fa-arrow-right ms-3 "></i></a>
		        </div>
        <% 		
        	}
        	else
        	{
        		if(session.getAttribute("ensg") != null)
        		{
        			Enseignant ensg = (Enseignant) session.getAttribute("ensg");
        			
        			%>
        			  <div class="collapse navbar-collapse" id="navbarCollapse">
			            <div class="navbar-nav ms-auto p-4 p-lg-0">	         
			                <a href="index.jsp" class="nav-item nav-link active"><%=ensg.getNom() %></a>
			                <a href="about.jsp" class="nav-item nav-link">About</a>
			                <a href="courses.jsp" class="nav-item nav-link">Courses</a>
			                <a href="groupe.jsp" class="nav-item nav-link" >Groupes</a>
			                <div class="nav-item dropdown">
			                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Emplois du Temps</a>
			                    <div class="dropdown-menu fade-down m-0">
			                        <a href="emplois.jsp?m= BAC Math" class="dropdown-item">BAC MATH</a>
			                        <a href="emplois.jsp?m= BAC PC" class="dropdown-item">BAC PC</a>
			                        <a href="emplois.jsp?m= BAC SVT" class="dropdown-item">BAC SVT</a>
			                    </div>
			                </div>
			                <a href="contact.jsp" class="nav-item nav-link ">Contact</a>
			            </div>
			            <a href="../enseignants?op=disconnect"  class="btn btn-primary py-4 px-lg-5 d-none d-lg-block ">Disconnect<i
			                    class="fa fa-arrow-right ms-3 "></i></a>
		             </div>
        			
        			<%
        		}
        		else
        		{
        			if(session.getAttribute("adm") != null)
            		{
            			Admin adm = (Admin) session.getAttribute("adm");
            			
            			%>
            			  <div class="collapse navbar-collapse" id="navbarCollapse">
    			            <div class="navbar-nav ms-auto p-4 p-lg-0">	         
    			                <a href="index.jsp" class="nav-item nav-link active"> <b style="color: black;" >Admin</b> <%=adm.getNom() %>  <%=adm.getPrenom() %></a>
    			                <a href="salle.jsp" class="nav-item nav-link">Salles</a>
    			                <a href="groupe.jsp" class="nav-item nav-link" >Groupes</a>
    			                <a href="enseignant.jsp" class="nav-item nav-link" >Enseignanat</a>
    			                <div class="nav-item dropdown">
    			                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Emplois du Temps</a>
    			                    <div class="dropdown-menu fade-down m-0">
    			                        <a href="emplois.jsp?m=BAC Math" class="dropdown-item">BAC MATH</a>
    			                        <a href="emplois.jsp?m=BAC PC" class="dropdown-item">BAC PC</a>
    			                        <a href="emplois.jsp?m=BAC SVT" class="dropdown-item">BAC SVT</a>
    			                    </div>
    			                </div>
    			                <a href="contact.jsp" class="nav-item nav-link ">Contact</a>
    			            </div>
    			            <a href="../enseignants?op=disconnect"  class="btn btn-primary py-4 px-lg-5 d-none d-lg-block ">Disconnect<i
    			                    class="fa fa-arrow-right ms-3 "></i></a>
    		             </div>
            			
            			<%
            		}
        			else
        			{
        		
        %>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
                <a href="index.jsp" class="nav-item nav-link active">Home</a>
                <a href="about.jsp" class="nav-item nav-link">About</a>
                <a href="courses.jsp" class="nav-item nav-link">Courses</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Emplois du Temps</a>
                    <div class="dropdown-menu fade-down m-0">
		                        <a href="emplois.jsp?m=BAC Math" class="dropdown-item">BAC MATH</a>
		                        <a href="emplois.jsp?m=BAC PC" class="dropdown-item">BAC PC</a>
		                        <a href="emplois.jsp?m=BAC SVT " class="dropdown-item">BAC SVT</a>
		            </div>
                </div>
                <a href="contact.jsp" class="nav-item nav-link ">Contact</a>
            </div>
            <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block ">Join Now<i
                    class="fa fa-arrow-right ms-3 "></i></a>
        </div>
        <% 
        		}
        	}
         }
        %>
    </nav>
    <!-- Navbar End -->
    
    <%
    	if(request.getParameter("op") != null)
    	{
    		if(request.getParameter("op").equals("consulter"))
    		{
    			int id_groupe = Integer.parseInt(request.getParameter("idGroup"));
    			DAOGroupe daog = new DAOGroupe();
    			//Groupe g = daog.Find_ID(id_groupe);
    			resp = client.resource(uri)
				        .path("groupes")
				        .path("Find_ID")
				        .queryParam("id", id_groupe+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Groupe g = mapper.readValue(corpsRepHttp, Groupe.class);
				
    			DAOEtudiant daoe = new DAOEtudiant();
    			//List<Etudiant> le = daoe.All_By_Groupe(g);
    			resp = client.resource(uri)
				        .path("etudiants")
				        .path("All_By_Groupe_2")
				        .queryParam("idGrp", g.getId()+"")
				        .get(ClientResponse.class);
				corpsRepHttp = resp.getEntity(String.class);
				Etudiant[] le = mapper.readValue(corpsRepHttp, Etudiant[].class);
    				%>
					    <!--Liste of Cours Debut-->
					    <section class="ftco-section">
					        <div class="container">
					            <div class="row justify-content-center">					                
					            </div>
					            <div class="row">
					                <div class="col-md-12">
					                    <h4 class="text-center mb-4"><%=g.getNom() %></h4>
					                    <div class="table-wrap">
					                        <table class="table">					                            
					                            <%
					                            if(session.getAttribute("ensg") != null)
					                            {
					                            	%>
					                            		<thead class="thead-primary">
							                                <tr>
							                                    <th>Nom</th>
							                                    <th>Prenom</th>
							                                    <th>Totale Abcence</th>
							                                    <th>Phone Number</th>
							                                    <th>Ajouter Abcence</th>
							                                </tr>
							                            </thead>
							                            <tbody>
					                            	<%
								                            for(Etudiant e : le)
								                			{
								                            %>
								                                <tr>
								                                    <th><%=e.getNom() %></th>
								                                    <th><%=e.getPrenom() %></th>
								                                    <th>Totale Abcence <%=e.getId() %></th>
								                                    <th><%=e.getTele() %></th>
								                                    <%
								                                    	//DAOAbsence daoabs = new DAOAbsence();							                                        	
								                                        //Boolean b = daoabs.Existe(e);
								                                        resp = client.resource(uri)
								                            	                .path("absences")
								                            	                .path("Existe_2")
								                            	                .queryParam("idEtu", e.getId()+"")
								                            	                .get(ClientResponse.class);
								                            		    String b = resp.getEntity(String.class);
								                                        if(b.equals("true"))
								                                        {
								                                        	%>
										                                    <td>
										                                        Done
										                                    </td>
										                                    <%	
								                                        }
								                                        else
								                                        {
			
										                                    %>
										                                    <td>
										                                        <a href="../enseignants?op=abs&idE=<%=e.getId()%>&idG=<%=id_groupe%>" class="btn btn-primary">Ajouter</a>
										                                    </td>
										                                    <%
								                                         }
								                                    %>
								                                   
						                                        </tr>
						                             <%
						                			         }	
					                            }else
					                            {
					                            	if(session.getAttribute("adm") != null)
						                            {
					                            		%>
					                            			<thead class="thead-primary">
								                                <tr>
								                                    <th>Nom</th>
								                                    <th>Prenom</th>
								                                    <th>Gmail</th>
								                                    <th>Genre</th>								                                
								                                    <th>Phone Number</th>
								                                    <th>Phone Number Parent</th>
								                                    <th>Login</th>
								                                    <th>Password</th>
								                                    <th>Number of Abcence</th>
								                                    <th></th>
								                                    <th></th>
								                                </tr>
								                            </thead>
								                            <tbody>
					                            		<%
							                            for(Etudiant e : le)
							                			{
							                            	
							                            	if(request.getParameter("op2") != null && request.getParameter("op2").equals("modifierE"))
							                            	{
							                            		int idE = Integer.parseInt(request.getParameter("idE"));
							                            		if(idE == e.getId())
							                            		{
							                            			%>
							                            			<form action="../admins" method="post">
							                            				<tr>
										                                    <th><input type="text" name="nom" value="<%=e.getNom() %>"></th>
										                                    <th><input type="text" name="prenom" value="<%=e.getPrenom() %>"></th>
										                                    <th><input type="email" name="gmail" value="<%=e.getGmail() %>"></th>
										                                    <th><input type="text" name="genre" value="<%=e.getGenre() %>"></th>
										                                    <th><input type="text" name="tele" value="<%=e.getTele() %>"></th>							                                    							                                    
										                                    <th><input type="text" name="teleP" value="<%=e.getTele_parente() %>"></th>
										                                    <th><input type="text" name="login" value="<%=e.getLogin() %>"></th>
										                                    <th><input type="text" name="pass" value="<%=e.getPass() %>"></th>
										                                    <%
										                                    	//DAOAbsence daoabs = new DAOAbsence();
										                                    	//int nbr = daoabs.Nbr_Of_Abcence(e);	
										                                    	resp = client.resource(uri)
										                    					        .path("absences")
										                    					        .path("Nbr_Of_Abcence_2")
										                    					        .queryParam("id", e.getId()+"")
										                    					        .get(ClientResponse.class);
										                    					corpsRepHttp = resp.getEntity(String.class);
										                    					int nbr = Integer.parseInt(corpsRepHttp);
										                                    %>
										                                    <th><input type="number" name="nbrAbc" readonly="readonly" value="<%=nbr %>"></th>
										                                    <th>
										                                    	id Etudiant : <input type="number" readonly="readonly" name="id" value="<%=e.getId() %>">
										                                    	id Groupe : <input type="text" name="id_groupe" readonly="readonly" value="<%=id_groupe %>">
										                                    	
										                                    </th>
										                                    <th><input type="submit" name="modifierEtudiant" value="Save" class="btn btn-primary"></th>
										                                 </tr>   
							                            			</form>							                            	      
							                            		    <%
							                            		}else
							                            		{
								                            		%>
										                                <tr>
										                                    <th><%=e.getNom() %></th>
										                                    <th><%=e.getPrenom() %></th>
										                                    <th><%=e.getGmail() %></th>
										                                    <th><%=e.getGenre() %></th>
										                                    <th><%=e.getTele() %></th>							                                    							                                    
										                                    <th><%=e.getTele_parente() %></th>
										                                    <th><%=e.getLogin() %></th>
										                                    <th><%=e.getPass() %></th>
										                                    <%
										                                    	//DAOAbsence daoabs = new DAOAbsence();
										                                    	//int nbr = daoabs.Nbr_Of_Abcence(e);	
										                                    	resp = client.resource(uri)
										                    					        .path("absences")
										                    					        .path("Nbr_Of_Abcence_2")
										                    					        .queryParam("id", e.getId()+"")
										                    					        .get(ClientResponse.class);
										                    					corpsRepHttp = resp.getEntity(String.class);
										                    					int nbr = Integer.parseInt(corpsRepHttp);
										                                    %>
										                                    <th><%=nbr %></th>
										                                    <th><a href="?op=consulter&idGroup=<%=id_groupe %>&op2=modifierE&idE=<%=e.getId() %>" class="btn btn-primary">Modifier</a></th>
										                                    <th><a href="../admins?op=supprimerEtudiant&idGroup=<%=id_groupe %>&idE=<%=e.getId() %>" class="btn btn-primary">Supprimer</a></th>
										                                </tr>
										                             <%
							                            		}
							                            	}else
							                            	{
								                            	 %>
									                                <tr>
									                                    <th><%=e.getNom() %></th>
									                                    <th><%=e.getPrenom() %></th>
									                                    <th><%=e.getGmail() %></th>
									                                    <th><%=e.getGenre() %></th>
									                                    <th><%=e.getTele() %></th>							                                    							                                    
									                                    <th><%=e.getTele_parente() %></th>
									                                    <th><%=e.getLogin() %></th>
									                                    <th><%=e.getPass() %></th>
									                                    <%
									                                    	//DAOAbsence daoabs = new DAOAbsence();
									                                    	//int nbr = daoabs.Nbr_Of_Abcence(e);	
									                                    	
									                                    	resp = client.resource(uri)
									                    					        .path("absences")
									                    					        .path("Nbr_Of_Abcence_2")
									                    					        .queryParam("id", e.getId()+"")
									                    					        .get(ClientResponse.class);
									                    					corpsRepHttp = resp.getEntity(String.class);
									                    					int nbr = Integer.parseInt(corpsRepHttp);
									                                    %>
									                                    <th><%=nbr %></th>
									                                    <th><a href="?op=consulter&idGroup=<%=id_groupe %>&op2=modifierE&idE=<%=e.getId() %>" class="btn btn-primary">Modifier</a></th>
									                                    <th><a href="../admins?op=supprimerEtudiant&idGroup=<%=id_groupe %>&idE=<%=e.getId() %>" class="btn btn-primary">Supprimer</a></th>
									                                </tr>
									                             <%
							                            	}
							                            
							                			 }
						                            }  
					                            }
						                             %> 
						                             <%
						                             	if(request.getParameter("ajouterEtudiant") != null)
						                             	{
						                             		%>
						                             			<form action="../admins" method="post">
							                             			<tr>
									                                    <th><input type="text" name="nom" placeholder="Nom.."></th>
									                                    <th><input type="text" name="prenom" placeholder="Prenom.."></th>
									                                    <th><input type="email" name="gmail" placeholder="Gmail.."></th>
									                                    <th>
									                                    	<input type="radio" name="genre" value="Homme">Home
                                											<input type="radio" name="genre" value="Femme">Femme
									                                    </th>
									                                    <th><input type="tel" name="phone" placeholder="Phone.."></th>
									                                    <th><input type="tel" name="phoneP" placeholder="Phone Parent.."></th>
									                                    <th><input type="text" name="login" placeholder="Login.."></th>
									                                    <th><input type="text" name="pass" placeholder="Password.."></th>
									                                    <th>id Groupe : <input type="number" name="id_groupe" value="<%=id_groupe %>"></th>
									                                    <th colspan="2"><input type="submit" name="ajouterEtudiant" value="           Save          " class="btn btn-primary"></th>
									                                </tr>
						                             			</form>
						                             		<%
						                             	}else
						                             	{
						                             		if(session.getAttribute("adm") != null)
						                             		{

						                             %>
									                             <tfoot>
									                             	       <tr>
											                                    <th></th>
											                                    <th></th>
											                                    <th></th>
											                                    <th></th>								                                
											                                    <th></th>
											                                    <th></th>
											                                    <th></th>
											                                    <th></th>
											                                    <th colspan="3"><a href="?op=consulter&idGroup=<%=id_groupe %>&ajouterEtudiant" class="btn btn-primary">Ajouter Etudiant</a></th>
											                                    
											                                </tr>
									                             </tfoot>
						                             <%
						                             		}
						                             	}
						                             %>  				                                					
					                            </tbody>
					                        </table>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </section>
					
					    <script src="../template/table-03/js/jquery.min.js"></script>
					    <script src="../template/table-03/js/popper.js"></script>
					    <script src="../template/table-03/js/bootstrap.min.js"></script>
					    <script src="../template/table-03/js/main.js"></script>
					    <!--Liste of Cours Fin-->
    				<% 
    		}
    	}
    	else
    	{
    		
    %>

    <!--Liste of Cours Debut-->
    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-5">
                    <h2 class="heading-section">Groupes</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="table-wrap">
                        <table class="table">
                            <thead class="thead-primary">
                                <tr>
                                    <th>Nom</th>
                                    <th>niveau</th>
                                    <th>capacite</th>
                                    <th>
                                    </th>

                                </tr>
                            </thead>
                            <tbody>
                            <%
                                 //DAOGroupe daog = new DAOGroupe();
                                 //List<Groupe> lg = daog.All();
                                 
                                 resp = client.resource(uri)
             					        .path("groupes")
             					        .path("ALL")
             					        .get(ClientResponse.class);
             					corpsRepHttp = resp.getEntity(String.class);
             					Groupe[] lg = mapper.readValue(corpsRepHttp, Groupe[].class);
             					
                                 for(Groupe g : lg)
                                 {                   
                                		 %>
                                		 
                                		 <%
                                		  	if(request.getParameter("opp") != null && request.getParameter("opp").equals("modiferG"))
                                		  	{
                                		  		int idGroup = Integer.parseInt(request.getParameter("idGroup"));
                                		  		if(g.getId() == idGroup)
                                		  		{
                                		  			%>
                                		  				<form action="../admins" method="post">
                                		  					<tr>
                                		  					   <th><input type="number" name="id" readonly="readonly" value="<%=g.getId() %>"></th>
							                                    <th><input type="text" name="nom" value="<%=g.getNom() %>"></th>
							                                    <th scope="row" class="scope"><input type="text" name="niveau" value="<%=g.getNiveau() %>"></th>
							                                    <td><input type="number" name="capacite" value="<%=g.getCapacite() %>"></td>
							                                    <td>                        
							                                        <input type="submit" name="saveG" value="Save" class="btn btn-primary">			                                        
							                                    </td>
							                                    
							                                 </tr>   
                                		  				</form>
                                		  			<%
                                		  		}else
                                		  		{
                                		  			%>
                                		  					<tr>
							                                    <th><%=g.getNom() %></th>
							                                    <th scope="row" class="scope"><%=g.getNiveau() %></th>
							                                    <td><%=g.getCapacite() %></td>
							                                    <td>
							                                        <a href="?op=consulter&idGroup=<%=g.getId() %>" class="btn btn-primary">Consulter</a>			                                        
							                                    </td>
							                                    <%
							                                    	if(session.getAttribute("adm") != null)
							                                    	{
							                                    %>
							                                    		  <td>
										                                      <a href="?opp=modiferG&idGroup=<%=g.getId() %>" class="btn btn-primary">Modifier</a>			                                        						                                   
										                                      <a href="../admins?op=supprimerGroupe&idGroup=<%=g.getId() %>" class="btn btn-primary">Supprimer</a>			                                        
										                                   </td>
							                                   <%
							                                    	}
							                                    %>
							                                 </tr>                                		  				
                                		  			<% 
                                		  		}
                                		  	}else
                                		  	{
                                		 %>
                                		 	
                                	 	    <tr>
			                                    <th><%=g.getNom() %></th>
			                                    <th scope="row" class="scope"><%=g.getNiveau() %></th>
			                                    <td><%=g.getCapacite() %></td>
			                                    <td>
			                                        <a href="?op=consulter&idGroup=<%=g.getId() %>" class="btn btn-primary">Consulter</a>			                                        
			                                    </td>
			                                    <%
			                                    	if(session.getAttribute("adm") != null)
			                                    	{
			                                    %>
			                                    		  <td>
						                                      <a href="?opp=modiferG&idGroup=<%=g.getId() %>" class="btn btn-primary">Modifier</a>			                                        						                                   
						                                      <a href="../admins?op=supprimerGroupe&idGroup=<%=g.getId() %>" class="btn btn-primary">Supprimer</a>			                                        
						                                   </td>
			                                   <%
			                                    	}
			                                    %>
			                                </tr>                            	 
                                	 <% 
                                		  	} 
                                 }
                            %>              
                                           <%
                                               if(request.getParameter("opp") != null)
                                               {
                                            	  
                                            	   if(request.getParameter("opp").equals("ajouterGroupe"))
                                            	   {
                                            		   %>
                                            		   		<form action="../admins" method="post">
                                            		   			<tr>
																    <td><input type="text" name="nom" placeholder="Nom..."></td>
																	<td><input type="text" name="niveau" placeholder="Niveau..."></td>																
																	<td><input type="number" name="capacite" placeholder="Capacite..."></td>											
																	<td colspan="2">
																		<input type="submit" value="Ajouter" name="ajouterGroupe" class="btn btn-primary">											                       
												                     </td>
																 </tr>
                                            		   		</form >
                                            		   <%
                                            	   }
                                            	   else
                                            	   {
                                            		   %>
                                            		   	<tr>
															<td style="background-color: white;"></td>
															<td style="background-color: white;"></td>
															<td style="background-color: white;"></td>											
															<td colspan="2" style="background-color: white;">
										                        <a href="?opp=ajouterGroupe" class="btn btn-primary">Ajouter Groupe</a>
										                     </td>
														</tr>
                                            		   <%
                                            	 }
                                           }
                                           else
                                           {
                            				%>                                                                  
											<tr>
												<td style="background-color: white;"></td>
												<td style="background-color: white;"></td>
												<td style="background-color: white;"></td>											
												<td colspan="2" style="background-color: white;">
							                        <a href="?opp=ajouterGroupe" class="btn btn-primary">Ajouter Groupe</a>
							                     </td>
											</tr>
											<%
                                           }
											%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%
    	}
    %>

    <script src="../template/table-03/js/jquery.min.js"></script>
    <script src="../template/table-03/js/popper.js"></script>
    <script src="../template/table-03/js/bootstrap.min.js"></script>
    <script src="../template/table-03/js/main.js"></script>
    <!--Liste of Cours Fin-->

    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Quick Link</h4>
                    <a class="btn btn-link" href="">About Us</a>
                    <a class="btn btn-link" href="">Contact Us</a>
                    <a class="btn btn-link" href="">Privacy Policy</a>
                    <a class="btn btn-link" href="">Terms & Condition</a>
                    <a class="btn btn-link" href="">FAQs & Help</a>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Contact</h4>
                    <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                    <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                    <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                    <div class="d-flex pt-2">
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                        <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Gallery</h4>
                    <div class="row g-2 pt-2">
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="../template/img/course-1.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="../template/img/course-2.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="../template/img/course-3.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="../template/img/course-2.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="../template/img/course-3.jpg" alt="">
                        </div>
                        <div class="col-4">
                            <img class="img-fluid bg-light p-1" src="../template/img/course-1.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <h4 class="text-white mb-3">Newsletter</h4>
                    <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                    <div class="position-relative mx-auto" style="max-width: 400px;">
                        <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                        <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="copyright">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        &copy; <a class="border-bottom" href="#">Naya_Soutien</a>, All Right Reserved.

                        <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        <div class="footer-menu">
                            <a href="">Home</a>
                            <a href="">Cookies</a>
                            <a href="">Help</a>
                            <a href="">FQAs</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End -->

    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../template/lib/wow/wow.min.js"></script>
    <script src="../template/lib/easing/easing.min.js"></script>
    <script src="../template/lib/waypoints/waypoints.min.js"></script>
    <script src="../template/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="../template/js/main.js"></script>
</body>

</html>

</html>