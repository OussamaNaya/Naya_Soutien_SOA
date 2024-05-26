package Wrapper.WrapperEtudiant;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Cour;
import Models.Etudiant;
import Models.Groupe;

public class AjouterCourWrapper {
	@JsonProperty("u")
     Etudiant u;
	@JsonProperty("c")
     Cour c;
	/**
	 * 
	 */
	public AjouterCourWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param u
	 * @param c
	 */
	public AjouterCourWrapper(Etudiant u, Cour c) {
		this.u = u;
		this.c = c;
	}
	public AjouterCourWrapper(String uJson, String cJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        try {
            this.u = mapper.readValue(uJson, Etudiant.class);
            this.c = mapper.readValue(cJson, Cour.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}
	public Etudiant getU() {
		return u;
	}
	public void setU(Etudiant u) {
		this.u = u;
	}
	public Cour getC() {
		return c;
	}
	public void setC(Cour c) {
		this.c = c;
	}
	
}
