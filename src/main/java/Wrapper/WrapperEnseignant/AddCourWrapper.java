package Wrapper.WrapperEnseignant;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Cour;
import Models.Enseignant;
import Models.Etudiant;

public class AddCourWrapper {
	int id;
	
	@JsonProperty("e")
	Enseignant e;
	@JsonProperty("c")
	Cour c;
	/**
	 * 
	 */
	public AddCourWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param e
	 * @param c
	 */
	public AddCourWrapper(Enseignant e, Cour c) {
		this.e = e;
		this.c = c;
	}
	
	public AddCourWrapper(String eJson, String cJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
            this.e = mapper.readValue(eJson, Enseignant.class);
            this.c = mapper.readValue(cJson, Cour.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		
	}
	public Enseignant getE() {
		return e;
	}
	public void setE(Enseignant e) {
		this.e = e;
	}
	public Cour getC() {
		return c;
	}
	public void setC(Cour c) {
		this.c = c;
	}
	
}
