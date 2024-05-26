package Wrapper.WrapperEtudiant;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Etudiant;
import Models.Groupe;

public class AffecterGroupeWrapper {
	@JsonProperty("e1")
	private Etudiant e1;
	@JsonProperty("g")
	private Groupe g;
	/**
	 * @param e1
	 * @param g
	 */
	
	public AffecterGroupeWrapper(Etudiant e1, Groupe g) {
		this.e1 = e1; 
		this.g = g;
	}
	
	/**
	 * 
	 */
	public AffecterGroupeWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffecterGroupeWrapper(String e1Json, String gJson) throws IOException {
        // Convertir les chaînes JSON en objets Etudiant
        // Utiliser ObjectMapper pour désérialiser les chaînes JSON en objets Etudiant
        // Assurez-vous d'importer ObjectMapper de com.fasterxml.jackson.databind.ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.e1 = mapper.readValue(e1Json, Etudiant.class);
            this.g = mapper.readValue(gJson, Groupe.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

	public Etudiant getE1() {
		return e1;
	}

	public void setE1(Etudiant e1) {
		this.e1 = e1;
	}

	public Groupe getG() {
		return g;
	}

	public void setG(Groupe g) {
		this.g = g;
	}
	
}
