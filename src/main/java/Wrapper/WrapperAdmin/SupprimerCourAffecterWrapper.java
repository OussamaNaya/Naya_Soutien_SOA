package Wrapper.WrapperAdmin;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Cour;
import Models.Salle;

public class SupprimerCourAffecterWrapper {
	Salle s;
	Cour c;
	/**
	 * 
	 */
	public SupprimerCourAffecterWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param s
	 * @param c
	 */
	public SupprimerCourAffecterWrapper(Salle s, Cour c) {
		this.s = s;
		this.c = c;
	}
	public SupprimerCourAffecterWrapper(String sJson, String cJson) throws IOException {
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
