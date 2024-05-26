package Wrapper.WrapperCour;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Cour;

public class ModifierCourWrapper {
	@JsonProperty("c1")
	private Cour c1;
	@JsonProperty("c2")
	private Cour c2;
	/**
	 * 
	 */
	public ModifierCourWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param c1
	 * @param c2
	 */
	public ModifierCourWrapper(Cour c1, Cour c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	public ModifierCourWrapper(String c1Json, String c2Json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        try {
            this.c1 = mapper.readValue(c1Json, Cour.class);
            this.c2 = mapper.readValue(c2Json, Cour.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}
	public Cour getC1() {
		return c1;
	}
	public void setC1(Cour c1) {
		this.c1 = c1;
	}
	public Cour getC2() {
		return c2;
	}
	public void setC2(Cour c2) {
		this.c2 = c2;
	}

}
