package Wrapper.WrapperAdmin;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Cour;
import Models.Etudiant;
import Models.Groupe;
import Models.Salle;

public class AffecterCour implements Serializable{
	@JsonProperty("s")
	Salle s;
	@JsonProperty("c")
	Cour c;
	/**
	 * 
	 */
	public AffecterCour() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AffecterCour(Salle s, Cour c) {
		this.s = s;
		this.c = c;
	}
	
	public AffecterCour(String sJson, String cJson) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        try {
            this.s = mapper.readValue(sJson, Salle.class);
            this.c = mapper.readValue(cJson, Cour.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}

	public Salle getS() {
		return s;
	}

	public void setS(Salle s) {
		this.s = s;
	}

	public Cour getC() {
		return c;
	}

	public void setC(Cour c) {
		this.c = c;
	}
	
	
}
