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


<%@page import="Models.Directeur"%>
<%@page import="Models.Admin"%>
<%@page import="Models.Enseignant"%>
<%@page import="Models.Etudiant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Naya_Soutien</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="../template/img/favicon.ico" rel="icon">

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
    <link href="../template/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../template/css/style.css" rel="stylesheet">
</head>
<%
	Client client = Client.create(new DefaultClientConfig());
	URI uri = UriBuilder.fromUri("http://localhost:8080/Naya_Soutien/rs/").build();
	ObjectMapper mapper = new ObjectMapper();
	ClientResponse resp;
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
		                        <a href="emplois.jsp?m=BAC Math" class="dropdown-item">BAC MATH</a>
		                        <a href="emplois.jsp?m=BAC PC" class="dropdown-item">BAC PC</a>
		                        <a href="emplois.jsp?m=BAC SVT" class="dropdown-item">BAC SVT</a>
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
        				if(session.getAttribute("dir") != null)
                		{
                			 Directeur dir = (Directeur) session.getAttribute("dir");
                			
                			%>
                			  <div class="collapse navbar-collapse" id="navbarCollapse">
        			            <div class="navbar-nav ms-auto p-4 p-lg-0">	         
        			                <a href="index.jsp" class="nav-item nav-link active"> <b style="color: black;" >Directeur</b> <%=dir.getNom() %>  <%=dir.getPrenom() %></a>        			                
        			                <a href="enseignant.jsp" class="nav-item nav-link" >Enseignanat</a>
        			                <a href="admins.jsp" class="nav-item nav-link" >Admins</a>
        			                <div class="nav-item dropdown">
        			                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Etat de Paiement</a>
        			                    <div class="dropdown-menu fade-down m-0">
        			                        <a href="paiement.jsp?m=admin" class="dropdown-item">Admins</a>
        			                        <a href="paiement.jsp?m=enseignant" class="dropdown-item">Enseignants</a>
        			                        <a href="paiement.jsp?m=etudiant" class="dropdown-item">Etudiants</a>
        			                    </div>
        			                </div>
        			                <a href="contact.jsp" class="nav-item nav-link ">Contact</a>
        			            </div>
        			            <a href="../enseignants?op=disconnect"  class="btn btn-primary py-4 px-lg-5 d-none d-lg-block ">Disconnect<i
        			                    class="fa fa-arrow-right ms-3 "></i></a>
        		             </div>
                			
                			<%
                		}else
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
         }
        %>
    </nav>
    <!-- Navbar End -->


    <!-- Carousel Start -->
    <div class="container-fluid p-0 mb-5 ">
        <div class="owl-carousel header-carousel position-relative ">
            <div class="owl-carousel-item position-relative ">
                <img class="img-fluid " src="../template/img/carousel-1.jpg " alt=" ">
                <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center " style="background: rgba(24, 29, 56, .7); ">
                    <div class="container ">
                        <div class="row justify-content-start ">
                            <div class="col-sm-10 col-lg-8 ">
                                <h5 class="text-primary text-uppercase mb-3 animated slideInDown ">Best Online Courses
                                </h5>
                                <h1 class="display-3 text-white animated slideInDown ">The Best Learning Platform
                                </h1>
                                <p class="fs-5 text-white mb-4 pb-2 ">Academic support is an effective solution for students who are struggling in their studies. Our courses are taught by experienced and qualified teachers, who adapt to the needs of each student. We offer group or individual
                                    lessons for all levels and subjects.</p>
                                <a href=" " class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft ">Read
                                    More</a>
                                <a href="colorlib/index.html " class="btn btn-light py-md-3 px-md-5 animated slideInRight ">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="owl-carousel-item position-relative ">
                <img class="img-fluid " src="../template/img/carousel-2.jpg " alt=" ">
                <div class="position-absolute top-0 start-0 w-100 h-100 d-flex align-items-center " style="background: rgba(24, 29, 56, .7); ">
                    <div class="container ">
                        <div class="row justify-content-start ">
                            <div class="col-sm-10 col-lg-8 ">
                                <h5 class="text-primary text-uppercase mb-3 animated slideInDown ">Best Online Courses
                                </h5>
                                <h1 class="display-3 text-white animated slideInDown ">Get Educated Online From Your Home
                                </h1>
                                <p class="fs-5 text-white mb-4 pb-2 ">Our experienced and qualified tutors can help you with any subject, from math and science to English and history. They will work with you at your own pace to identify your strengths and weaknesses, and develop a personalized
                                    plan to help you achieve your goals.</p>
                                <a href="#welcome " class="btn btn-primary py-md-3 px-md-5 me-3 animated slideInLeft ">Read
                                    More</a>
                                <a href="colorlib/index.html " class="btn btn-light py-md-3 px-md-5 animated slideInRight ">Join Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Carousel End -->


    <!-- Service Start -->
    <div class="container-xxl py-5 ">
        <div class="container ">
            <div class="row g-4 ">
                <div class="col-lg-3 col-sm-6 wow fadeInUp " data-wow-delay="0.1s ">
                    <div class="service-item text-center pt-3 ">
                        <div class="p-4 ">
                            <i class="fa fa-3x fa-graduation-cap text-primary mb-4 "></i>
                            <h5 class="mb-3 ">Skilled Instructors</h5>
                            <p>killed instructors are able to use effective teaching methods and techniques to deliver content in a way that is both engaging and effective. They are also able to differentiate instruction to meet the needs of all learners.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 wow fadeInUp " data-wow-delay="0.3s ">
                    <div class="service-item text-center pt-3 ">
                        <div class="p-4 ">
                            <i class="fa fa-3x fa-globe text-primary mb-4 "></i>
                            <h5 class="mb-3 ">Classes</h5>
                            <p>Communicate complex concepts clearly and concisely. Skilled instructors are able to communicate complex concepts in a way that is clear and easy to understand. They use plain language and avoid jargon.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 wow fadeInUp " data-wow-delay="0.5s ">
                    <div class="service-item text-center pt-3 ">
                        <div class="p-4 ">
                            <i class="fa fa-3x fa-home text-primary mb-4 "></i>
                            <h5 class="mb-3 ">Home Projects</h5>
                            <p>Home projects are a great way for students to learn new skills, apply what they have learned in class, and take pride in their work. There are many different types of home projects that students can do, depending on their age,
                                skill level, and interests.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-sm-6 wow fadeInUp " data-wow-delay="0.7s ">
                    <div class="service-item text-center pt-3 ">
                        <div class="p-4 ">
                            <i class="fa fa-3x fa-book-open text-primary mb-4 "></i>
                            <h5 class="mb-3 ">Book Library</h5>
                            <p>Libraries offer a variety of benefits to their users. They provide access to a wide range of books, including fiction, non-fiction, and children's books.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Service End -->


    <!-- About Start -->
    <div class="container-xxl py-5 ">
        <div class="container ">
            <div class="row g-5 ">
                <div class="col-lg-6 wow fadeInUp " data-wow-delay="0.1s " style="min-height: 400px; ">
                    <div class="position-relative h-100 ">
                        <img class="img-fluid position-absolute w-100 h-100 " src="../template/img/about.jpg " alt=" " style="object-fit: cover; ">
                    </div>
                </div>
                <div class="col-lg-6 wow fadeInUp " data-wow-delay="0.3s ">
                    <h6 class="section-title bg-white text-start text-primary pe-3 ">About Us</h6>
                    <h1 class="mb-4 " id="welcome ">Welcome to eLEARNING</h1>
                    <p class="mb-4 ">I am so excited to have you here. This website is a resource for you to learn, grow, and succeed. I will be posting regular updates on my courses, as well as additional resources and support materials.</p>
                    <p class="mb-4 ">I am committed to providing you with the best possible education. I want you to leave my courses feeling confident and prepared for the next step in your journey.
                    </p>
                    <div class="row gy-2 gx-4 mb-4 ">
                        <div class="col-sm-6 ">
                            <p class="mb-0 "><i class="fa fa-arrow-right text-primary me-2 "></i>Skilled Instructors</p>
                        </div>
                        <div class="col-sm-6 ">
                            <p class="mb-0 "><i class="fa fa-arrow-right text-primary me-2 "></i>Classes</p>
                        </div>
                        <div class="col-sm-6 ">
                            <p class="mb-0 "><i class="fa fa-arrow-right text-primary me-2 "></i>International Certificate
                            </p>
                        </div>
                        <div class="col-sm-6 ">
                            <p class="mb-0 "><i class="fa fa-arrow-right text-primary me-2 "></i>Skilled Instructors</p>
                        </div>
                        <div class="col-sm-6 ">
                            <p class="mb-0 "><i class="fa fa-arrow-right text-primary me-2 "></i>Online Classes</p>
                        </div>
                        <div class="col-sm-6 ">
                            <p class="mb-0 "><i class="fa fa-arrow-right text-primary me-2 "></i>International Certificate
                            </p>
                        </div>
                    </div>
                    <a class="btn btn-primary py-3 px-5 mt-2 " href=" ">Read More</a>
                </div>
            </div>
        </div>
    </div>
    <!-- About End -->


 <!-- Categories Start -->
    <div class="container-xxl py-5 category">
        <div class="container">
            <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                <h6 class="section-title bg-white text-center text-primary px-3">Categories</h6>
                <h1 class="mb-5">BAC Categories</h1>
            </div>
            <div class="row g-3">
                <div class="col-lg-7 col-md-6">
                    <div class="row g-3">
                        <div class="col-lg-12 col-md-12 wow zoomIn" data-wow-delay="0.1s">
                            <a class="position-relative d-block overflow-hidden" href="paquit.jsp?m=Mathematique">
                                <img class="img-fluid" src="../template/img/math2.jpg" alt="">
                                <div class="bg-white text-center position-absolute bottom-0 end-0 py-2 px-3" style="margin: 1px;">
                                    <h5 class="m-0">Mathematique</h5>
                                    <small class="text-primary">49 Courses</small>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-md-12 wow zoomIn" data-wow-delay="0.3s">
                            <a class="position-relative d-block overflow-hidden" href="paquit.jsp?m=SVT">
                                <img class="img-fluid" src="../template/img/svtjpg.jpg" alt="">
                                <div class="bg-white text-center position-absolute bottom-0 end-0 py-2 px-3" style="margin: 1px;">
                                    <h5 class="m-0">Sciences de la vie et de la Terre</h5>
                                    <small class="text-primary">49 Courses</small>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-md-12 wow zoomIn" data-wow-delay="0.5s">
                            <a class="position-relative d-block overflow-hidden" href="paquit.jsp?m=PC">
                                <img class="img-fluid" src="../template/img/pc.jpg" alt="">
                                <div class="bg-white text-center position-absolute bottom-0 end-0 py-2 px-3" style="margin: 1px;">
                                    <h5 class="m-0">Phisique Chimie</h5>
                                    <small class="text-primary">49 Courses</small>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-md-6 wow zoomIn" data-wow-delay="0.7s" style="min-height: 350px;">
                    <a class="position-relative d-block h-100 overflow-hidden" href="paquit.jsp?m=Economie">
                        <img class="img-fluid position-absolute w-100 h-100" src="../template/img/economie.jpg" alt="" style="object-fit: cover;">
                        <div class="bg-white text-center position-absolute bottom-0 end-0 py-2 px-3" style="margin:  1px;">
                            <h5 class="m-0">Economie</h5>
                            <small class="text-primary">49 Courses</small>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- Categories Start -->


    <!-- Courses Start -->
    <div class="container-xxl py-5 ">
        <div class="container ">
            <div class="text-center wow fadeInUp " data-wow-delay="0.1s ">
                <h6 class="section-title bg-white text-center text-primary px-3 ">Courses</h6>
                <h1 class="mb-5 ">Popular Courses</h1>
            </div>
            <div class="row g-4 justify-content-center ">
                <div class="col-lg-4 col-md-6 wow fadeInUp " data-wow-delay="0.1s ">
                    <div class="course-item bg-light ">
                        <div class="position-relative overflow-hidden ">
                            <img class="img-fluid " src="../template/img/math3.jpg " alt=" ">
                            <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4 ">
                                <a href="# " class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end " style="border-radius: 30px 0 0 30px; ">Read More</a>
                                <a href="# " class="flex-shrink-0 btn btn-sm btn-primary px-3 " style="border-radius: 0 30px 30px 0; ">Join Now</a>
                            </div>
                        </div>
                        <div class="text-center p-4 pb-0 ">
                            <h3 class="mb-0 ">$149.00</h3>
                            <div class="mb-3 ">
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small>(123)</small>
                            </div>
                            <h5 class="mb-4 ">Mathematique & Course for Beginners</h5>
                        </div>
                        <div class="d-flex border-top ">
                            <small class="flex-fill text-center border-end py-2 "><i
                                    class="fa fa-user-tie text-primary me-2 "></i>John Doe</small>
                            <small class="flex-fill text-center border-end py-2 "><i
                                    class="fa fa-clock text-primary me-2 "></i>1.49 Hrs</small>
                            <small class="flex-fill text-center py-2 "><i class="fa fa-user text-primary me-2 "></i>30
                                Students</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp " data-wow-delay="0.3s ">
                    <div class="course-item bg-light ">
                        <div class="position-relative overflow-hidden ">
                            <img class="img-fluid " src="../template/img/course-2.jpg " alt=" ">
                            <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4 ">
                                <a href="# " class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end " style="border-radius: 30px 0 0 30px; ">Read More</a>
                                <a href="# " class="flex-shrink-0 btn btn-sm btn-primary px-3 " style="border-radius: 0 30px 30px 0; ">Join Now</a>
                            </div>
                        </div>
                        <div class="text-center p-4 pb-0 ">
                            <h3 class="mb-0 ">$149.00</h3>
                            <div class="mb-3 ">
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small>(123)</small>
                            </div>
                            <h5 class="mb-4 ">Economie & Course for Beginners</h5>
                        </div>
                        <div class="d-flex border-top ">
                            <small class="flex-fill text-center border-end py-2 "><i
                                    class="fa fa-user-tie text-primary me-2 "></i>John Doe</small>
                            <small class="flex-fill text-center border-end py-2 "><i
                                    class="fa fa-clock text-primary me-2 "></i>1.49 Hrs</small>
                            <small class="flex-fill text-center py-2 "><i class="fa fa-user text-primary me-2 "></i>30
                                Students</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp " data-wow-delay="0.5s ">
                    <div class="course-item bg-light ">
                        <div class="position-relative overflow-hidden ">
                            <img class="img-fluid " src="../template/img/course-3.jpg " alt=" ">
                            <div class="w-100 d-flex justify-content-center position-absolute bottom-0 start-0 mb-4 ">
                                <a href="# " class="flex-shrink-0 btn btn-sm btn-primary px-3 border-end " style="border-radius: 30px 0 0 30px; ">Read More</a>
                                <a href="# " class="flex-shrink-0 btn btn-sm btn-primary px-3 " style="border-radius: 0 30px 30px 0; ">Join Now</a>
                            </div>
                        </div>
                        <div class="text-center p-4 pb-0 ">
                            <h3 class="mb-0 ">$149.00</h3>
                            <div class="mb-3 ">
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small class="fa fa-star text-primary "></small>
                                <small>(123)</small>
                            </div>
                            <h5 class="mb-4 ">Phisique & Course for Intermediate</h5>
                        </div>
                        <div class="d-flex border-top ">
                            <small class="flex-fill text-center border-end py-2 "><i
                                    class="fa fa-user-tie text-primary me-2 "></i>John Doe</small>
                            <small class="flex-fill text-center border-end py-2 "><i
                                    class="fa fa-clock text-primary me-2 "></i>1.49 Hrs</small>
                            <small class="flex-fill text-center py-2 "><i class="fa fa-user text-primary me-2 "></i>30
                                Students</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Courses End -->


    <!-- Team Start -->
    <div class="container-xxl py-5 ">
        <div class="container ">
            <div class="text-center wow fadeInUp " data-wow-delay="0.1s ">
                <h6 class="section-title bg-white text-center text-primary px-3 ">Instructors</h6>
                <h1 class="mb-5 ">Expert Instructors</h1>
            </div>
            <div class="row g-4 ">
                <div class="col-lg-3 col-md-6 wow fadeInUp " data-wow-delay="0.1s ">
                    <div class="team-item bg-light ">
                        <div class="overflow-hidden ">
                            <img class="img-fluid " src="../template/img/team-1.jpg " alt=" ">
                        </div>
                        <div class="position-relative d-flex justify-content-center " style="margin-top: -23px; ">
                            <div class="bg-light d-flex justify-content-center pt-2 px-1 ">
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-facebook-f "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i class="fab fa-twitter "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-instagram "></i></a>
                            </div>
                        </div>
                        <div class="text-center p-4 ">
                            <h5 class="mb-0 ">Instructor Name</h5>
                            <small>Designation</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 wow fadeInUp " data-wow-delay="0.3s ">
                    <div class="team-item bg-light ">
                        <div class="overflow-hidden ">
                            <img class="img-fluid " src="../template/img/team-2.jpg " alt=" ">
                        </div>
                        <div class="position-relative d-flex justify-content-center " style="margin-top: -23px; ">
                            <div class="bg-light d-flex justify-content-center pt-2 px-1 ">
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-facebook-f "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i class="fab fa-twitter "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-instagram "></i></a>
                            </div>
                        </div>
                        <div class="text-center p-4 ">
                            <h5 class="mb-0 ">Instructor Name</h5>
                            <small>Designation</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 wow fadeInUp " data-wow-delay="0.5s ">
                    <div class="team-item bg-light ">
                        <div class="overflow-hidden ">
                            <img class="img-fluid " src="../template/img/team-3.jpg " alt=" ">
                        </div>
                        <div class="position-relative d-flex justify-content-center " style="margin-top: -23px; ">
                            <div class="bg-light d-flex justify-content-center pt-2 px-1 ">
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-facebook-f "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i class="fab fa-twitter "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-instagram "></i></a>
                            </div>
                        </div>
                        <div class="text-center p-4 ">
                            <h5 class="mb-0 ">Instructor Name</h5>
                            <small>Designation</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 wow fadeInUp " data-wow-delay="0.7s ">
                    <div class="team-item bg-light ">
                        <div class="overflow-hidden ">
                            <img class="img-fluid " src="../template/img/team-4.jpg " alt=" ">
                        </div>
                        <div class="position-relative d-flex justify-content-center " style="margin-top: -23px; ">
                            <div class="bg-light d-flex justify-content-center pt-2 px-1 ">
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-facebook-f "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i class="fab fa-twitter "></i></a>
                                <a class="btn btn-sm-square btn-primary mx-1 " href=" "><i
                                        class="fab fa-instagram "></i></a>
                            </div>
                        </div>
                        <div class="text-center p-4 ">
                            <h5 class="mb-0 ">Instructor Name</h5>
                            <small>Designation</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Team End -->


    <!-- Testimonial Start -->
    <div class="container-xxl py-5 wow fadeInUp " data-wow-delay="0.1s ">
        <div class="container ">
            <div class="text-center ">
                <h6 class="section-title bg-white text-center text-primary px-3 ">Testimonial</h6>
                <h1 class="mb-5 ">Our Students Say!</h1>
            </div>
            <div class="owl-carousel testimonial-carousel position-relative ">
                <div class="testimonial-item text-center ">
                    <img class="border rounded-circle p-2 mx-auto mb-3 " src="../template/img/testimonial-1.jpg " style="width: 80px; height: 80px; ">
                    <h5 class="mb-0 ">Student Name</h5>
                    <p>Profession</p>
                    <div class="testimonial-text bg-light text-center p-4 ">
                        <p class="mb-0 ">Your website is so helpful! I love being able to access all of my course materials in one place.</p>
                    </div>
                </div>
                <div class="testimonial-item text-center ">
                    <img class="border rounded-circle p-2 mx-auto mb-3 " src="../template/img/testimonial-2.jpg " style="width: 80px; height: 80px; ">
                    <h5 class="mb-0 ">Student Name</h5>
                    <p>Profession</p>
                    <div class="testimonial-text bg-light text-center p-4 ">
                        <p class="mb-0 ">I really appreciate the additional resources you provide on your website, such as the study guides and practice problems.</p>
                    </div>
                </div>
                <div class="testimonial-item text-center ">
                    <img class="border rounded-circle p-2 mx-auto mb-3 " src="../template/img/testimonial-3.jpg " style="width: 80px; height: 80px; ">
                    <h5 class="mb-0 ">Student Name</h5>
                    <p>Profession</p>
                    <div class="testimonial-text bg-light text-center p-4 ">
                        <p class="mb-0 ">Your website is so helpful! I love being able to access all of my course materials in one place.</p>
                    </div>
                </div>
                <div class="testimonial-item text-center ">
                    <img class="border rounded-circle p-2 mx-auto mb-3 " src="../template/img/testimonial-4.jpg " style="width: 80px; height: 80px; ">
                    <h5 class="mb-0 ">Student Name</h5>
                    <p>Profession</p>
                    <div class="testimonial-text bg-light text-center p-4 ">
                        <p class="mb-0 ">Your blog is a great way to learn more about your teaching philosophy and to get insights into the topics we are covering in class.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Testimonial End -->


    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn " data-wow-delay="0.1s ">
        <div class="container py-5 ">
            <div class="row g-5 ">
                <div class="col-lg-3 col-md-6 ">
                    <h4 class="text-white mb-3 ">Quick Link</h4>
                    <a class="btn btn-link " href=" ">About Us</a>
                    <a class="btn btn-link " href=" ">Contact Us</a>
                    <a class="btn btn-link " href=" ">Privacy Policy</a>
                    <a class="btn btn-link " href=" ">Terms & Condition</a>
                    <a class="btn btn-link " href=" ">FAQs & Help</a>
                </div>
                <div class="col-lg-3 col-md-6 ">
                    <h4 class="text-white mb-3 ">Contact</h4>
                    <p class="mb-2 "><i class="fa fa-map-marker-alt me-3 "></i>123 Street, New York, USA</p>
                    <p class="mb-2 "><i class="fa fa-phone-alt me-3 "></i>+012 345 67890</p>
                    <p class="mb-2 "><i class="fa fa-envelope me-3 "></i>info@example.com</p>
                    <div class="d-flex pt-2 ">
                        <a class="btn btn-outline-light btn-social " href=" "><i class="fab fa-twitter "></i></a>
                        <a class="btn btn-outline-light btn-social " href=" "><i class="fab fa-facebook-f "></i></a>
                        <a class="btn btn-outline-light btn-social " href=" "><i class="fab fa-youtube "></i></a>
                        <a class="btn btn-outline-light btn-social " href=" "><i class="fab fa-linkedin-in "></i></a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 ">
                    <h4 class="text-white mb-3 ">Gallery</h4>
                    <div class="row g-2 pt-2 ">
                        <div class="col-4 ">
                            <img class="img-fluid bg-light p-1 " src="../template/img/course-1.jpg " alt=" ">
                        </div>
                        <div class="col-4 ">
                            <img class="img-fluid bg-light p-1 " src="../template/img/course-2.jpg " alt=" ">
                        </div>
                        <div class="col-4 ">
                            <img class="img-fluid bg-light p-1 " src="../template/img/course-3.jpg " alt=" ">
                        </div>
                        <div class="col-4 ">
                            <img class="img-fluid bg-light p-1 " src="../template/img/course-2.jpg " alt=" ">
                        </div>
                        <div class="col-4 ">
                            <img class="img-fluid bg-light p-1 " src="../template/img/course-3.jpg " alt=" ">
                        </div>
                        <div class="col-4 ">
                            <img class="img-fluid bg-light p-1 " src="../template/img/course-1.jpg " alt=" ">
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 ">
                    <h4 class="text-white mb-3 ">Newsletter</h4>
                    <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                    <div class="position-relative mx-auto " style="max-width: 400px; ">
                        <input class="form-control border-0 w-100 py-3 ps-4 pe-5 " type="text " placeholder="Your email ">
                        <button type="button " class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2 ">SignUp</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container ">
            <div class="copyright ">
                <div class="row ">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0 ">
                        &copy; <a class="border-bottom " href="#
                    ">Naya_Soutien</a>, All Right Reserved.

                        <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal ". Thank you for your support. ***/-->
                    </div>
                    <div class="col-md-6 text-center text-md-end ">
                        <div class="footer-menu ">
                            <a href=" ">Home</a>
                            <a href=" ">Cookies</a>
                            <a href=" ">Help</a>
                            <a href=" ">FQAs</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="# " class="btn btn-lg btn-primary btn-lg-square back-to-top "><i class="bi bi-arrow-up "></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js "></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js "></script>
    <script src="../template/lib/wow/wow.min.js "></script>
    <script src="../template/lib/easing/easing.min.js "></script>
    <script src="../template/lib/waypoints/waypoints.min.js "></script>
    <script src="../template/lib/owlcarousel/owl.carousel.min.js "></script>

    <!-- Template Javascript -->
    <script src="js/main.js "></script>
</body>

</html>
<script src="../template/lib/waypoints/waypoints.min.js "></script>
<script src="../template/lib/owlcarousel/owl.carousel.min.js "></script>

<!-- Template Javascript -->
<script src="../template/js/main.js "></script>
</body>

</html>