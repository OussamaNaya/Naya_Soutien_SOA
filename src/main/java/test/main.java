package test;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.w3c.dom.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import DAO.DAOAbsence;
import DAO.DAOAdmin;
import DAO.DAOCour;
import DAO.DAODirecteur;
import DAO.DAOEmplois;
import DAO.DAOEnseignant;
import DAO.DAOEtudiant;
import DAO.DAOGroupe;
import DAO.DAOPaiement;
import DAO.DAOPaquet;
import DAO.DAOSalle;
import Models.Absence_Enseignant;
import Models.Absence_Etudiant;
import Models.Admin;
import Models.Cour;
import Models.Directeur;
import Models.Emplois;
import Models.Enseignant;
import Models.Etudiant;
import Models.Groupe;
import Models.Paiement_Enseignant;
import Models.Paiement_Etudiant;
import Models.Paquite;
import Models.Salle;
import Wrapper.WrapperEtudiant.EtudiantWrapper;
import DAO.*;

public class main {
 
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Assalamo alikom");
        
        Client client = Client.create(new DefaultClientConfig());
        URI uri = UriBuilder.fromUri("http://localhost:8080/Naya_Soutien/rs/").build();
        ObjectMapper mapper = new ObjectMapper();
        ClientResponse response;
        
    ////****************  CreeCompte  
        String jsonBod = mapper.writeValueAsString(new Etudiant(0, "isame", "youness", "isam@gmail.com", "Homme", "0000000", "0000000", "isam", "123", null, null));
        response = client.resource(uri)
                .path("etudiants")
                .path("CreeCompte")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, jsonBod);
        System.out.println("json est : "+jsonBod);
        System.out.println("La creation du compte fait avec : "+response.getEntity(String.class));
        
        // Envoyer les objets Etudiant directement à votre endpoint
        Etudiant e1 = new Etudiant(70, "oussama", "naya", "o@gmail.com", "Homme", "0000000", "0000000", "oussama", "123", null, null);
        Etudiant e2 = new Etudiant(70, "oussama", "naya", "oooooo@gmail.com", "Homme", "0000000", "0000000", "oussama", "123", null, null);
        
        // Convertir les objets Etudiant en JSON
        String jsonBody = "{" +
                "\"e1\":" + mapper.writeValueAsString(e1) + ", " +
                "\"e2\":" + mapper.writeValueAsString(e2) +
                "}";
        System.out.println("JSON envoyé : " + jsonBody); // Ajoutez cette ligne pour afficher le JSON envoyé
        
        // Envoyer la demande au serveur
         response = client.resource(uri)
                .path("etudiants")
                .path("Modifier_3")
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, jsonBody);
        
        System.out.println(response.getEntity(String.class));
        
        
        //******* Supprimer Etudiant
        Etudiant e3 = new Etudiant(70, "", "", "", "", "", jsonBody, jsonBody, jsonBody, null, null);
        ClientResponse resp_delete = client.resource(uri)
				.path("etudiants")
				.path("supprimer")
				.type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class, mapper.writeValueAsString(e3));
		System.out.println("Partie supprission : "+resp_delete.getEntity(String.class));
		
		
		
	/////************ Affecter Groupe a Etudiant
		 // Envoyer les objets Etudiant directement à votre endpoint
		    Etudiant e4 = new Etudiant(77, "oussama", "naya", "o@gmail.com", "Homme", "0000000", "0000000", "oussama", "123", null, null);
		    Groupe g = new Groupe(2, "", "", 0);
		    
		    // Convertir les objets Etudiant en JSON
		    String jsonBody_2 = "{" +
		            "\"e1\":" + mapper.writeValueAsString(e4) + ", " +
		            "\"g\":" + mapper.writeValueAsString(g) +
		            "}";
		    System.out.println("JSON envoyé : " + jsonBody_2); // Ajoutez cette ligne pour afficher le JSON envoyé
		    
		    // Envoyer la demande au serveur
		    ClientResponse response_GEtu = client.resource(uri)
		            .path("etudiants")
		            .path("affecter_Groupe_2")
		            .type(MediaType.APPLICATION_JSON)
		            .put(ClientResponse.class, jsonBody_2);
		    
		    System.out.println("Affecter Groupe a Etudiant : "+response_GEtu.getEntity(String.class));
		    
    /////********** supprimer_where_Id_Groupe
		    Groupe g2 = new Groupe(2, jsonBody, jsonBody_2, 0);
		    
		 // Envoyer la demande au serveur
		    ClientResponse response_dGrp = client.resource(uri)
		            .path("etudiants")
		            .path("supprimer_where_Id_Groupe")
		            .type(MediaType.APPLICATION_JSON)
		            .delete(ClientResponse.class, mapper.writeValueAsString(g2));
		    System.out.println("supprimer_where_Id_Groupe : "+response_dGrp.getEntity(String.class));
		    
    //////*********** ajouter_Cour
		    Cour c = new Cour(5, "", "", 0, "", "", 0);
		    Etudiant e6 = new Etudiant(1, "hassan", "hmidi", "hassan@gmail.com", "Homme", "0000000", "0000000", "oussama", "123", null, null);
		    
		 // Convertir les objets Etudiant en JSON
		    String jsonBody_3 = "{" +
		            "\"u\":" + mapper.writeValueAsString(e6) + ", " +
		            "\"c\":" + mapper.writeValueAsString(c) +
		            "}";
		    System.out.println("JSON envoyé : " + jsonBody_3); // Ajoutez cette ligne pour afficher le JSON envoyé
		    
		 // Envoyer la demande au serveur
		    ClientResponse response_ACour = client.resource(uri)
		            .path("etudiants")
		            .path("ajouter_Cour_2")
		            .type(MediaType.APPLICATION_JSON)
		            .post(ClientResponse.class, jsonBody_3);
		    
		    System.out.println("Ajouter_Cour a Etudiant : "+response_ACour.getEntity(String.class));
		    
  /////////////// ********************************************  Enseignant  *************************************************************************************// 
		    String jsonBody_Enseg = new String();
		    ClientResponse reponse_ensg;
		    
		    ////*************** ADD_Cour_2 
		    Enseignant ensg = new Enseignant(10, "amghar", "mohamed", 21, "Math", "simo@gmail.com", "Homme", "0000000", null, null, false, false);
		    Cour c4 = new Cour(1, "", "", 0, "", "", 0);
		    jsonBody_Enseg = "{" +
			            "\"e\":" + mapper.writeValueAsString(ensg) + ", " +
			            "\"c\":" + mapper.writeValueAsString(c4) +
			            "}";
		    System.out.println("JSON envoyé : " + jsonBody_Enseg);
		    
		    // Envoyer la demande au serveur
		    reponse_ensg = client.resource(uri)
		            .path("enseignants")
		            .path("ADD_Cour_2")
		            .type(MediaType.APPLICATION_JSON)
		            .post(ClientResponse.class, jsonBody_Enseg);
		    
		    System.out.println("ADD_Cour_2 a Enseignant : "+reponse_ensg.getEntity(String.class));
		    
		    ////**************** authentifie_2
		    	//http://localhost:8080/Naya_Soutien/rs/enseignants/authentifie_2?login=daif&password=123
		    String login = "daif";
		    String password = "123";
		 // Envoyer la demande au serveur
		    reponse_ensg = client.resource(uri)
		            .path("enseignants")
		            .path("authentifie_2")
		            .queryParam("login", login)
		            .queryParam("password", password) 
		            .get(ClientResponse.class);
		    
		    System.out.println("authentifie_2 a Enseignant : "+reponse_ensg.getEntity(String.class));
	        
		    
		    ////***************** Find_Courses_2
		    String nom = "Naya";
		    String prenom = "Oussama";
		 // Envoyer la demande au serveur
		    reponse_ensg = client.resource(uri)
		            .path("enseignants")
		            .path("Find_Courses_2")
		            .queryParam("nom", nom)
		            .queryParam("prenom", prenom) 
		            .get(ClientResponse.class);
		    
		    System.out.println("Find_Courses_2 a Enseignant : "+reponse_ensg.getEntity(String.class));
		    
		    ////**************** Find_Id
		    String id = "5";
		 // Envoyer la demande au serveur
		    reponse_ensg = client.resource(uri)
		            .path("enseignants")
		            .path("Find_Id")
		            .queryParam("id", id)
		            .get(ClientResponse.class);
		    
		    System.out.println("Find_Id a Enseignant : "+reponse_ensg.getEntity(String.class));
		    
		    ////***************** Find_Cour_Id_2
		    String id_2 = "1";
			 // Envoyer la demande au serveur
			    reponse_ensg = client.resource(uri)
			            .path("enseignants")
			            .path("Find_Cour_Id_2")
			            .queryParam("id", id_2)
			            .get(ClientResponse.class);
			    
			    System.out.println("Find_Cour_Id_2 a Enseignant : "+reponse_ensg.getEntity(String.class));
			    
		   ////**************** Modifier_2
			    Enseignant e11 = new Enseignant(16, "isam", "isamiii", 30, "Mecanique", "isame@gmail.com", "Homme", "000000000", null, null, false, false);
			    Enseignant e22 = new Enseignant(16, "isam", "isamiii", 28, "Mecanique", "isame@gmail.com", "Homme", "000000000", null, null, false, false);
			    jsonBody_Enseg = "{" +
			            "\"e1\":" + mapper.writeValueAsString(e11) + ", " +
			            "\"e2\":" + mapper.writeValueAsString(e22) +
			            "}";
			    System.out.println("JSON envoyé : " + jsonBody_Enseg);
			    
			 // Envoyer la demande au serveur
			    reponse_ensg = client.resource(uri)
			            .path("enseignants")
			            .path("Modifier_2")
			            .type(MediaType.APPLICATION_JSON)
			            .put(ClientResponse.class, jsonBody_Enseg);
			    
			    System.out.println("Find_Cour_Id_2 a Enseignant : "+reponse_ensg.getEntity(String.class));
			    
			 ////**************** Off_Groupe_2
			    Groupe g1 = new Groupe(3, "", "", 0);
			 // Envoyer la demande au serveur
			    reponse_ensg = client.resource(uri)
			            .path("enseignants")
			            .path("Off_Groupe_2")
			            .type(MediaType.APPLICATION_JSON)
			            .put(ClientResponse.class, mapper.writeValueAsString(g1));
			    
			    System.out.println("Off_Groupe_2 a Enseignant : "+reponse_ensg.getEntity(String.class));
		    
			    
    }
}
