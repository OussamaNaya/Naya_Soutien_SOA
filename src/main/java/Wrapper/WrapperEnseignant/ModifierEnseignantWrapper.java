package Wrapper.WrapperEnseignant;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Cour;
import Models.Enseignant;

public class ModifierEnseignantWrapper {
	@JsonProperty("e1")
	Enseignant e1;
	@JsonProperty("e2")
	Enseignant e2;
	/**
	 * 
	 */
	public ModifierEnseignantWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param e1
	 * @param e2
	 */
	public ModifierEnseignantWrapper(Enseignant e1, Enseignant e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	public ModifierEnseignantWrapper(String e1Json, String e2Json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
            this.e1 = mapper.readValue(e1Json, Enseignant.class);
            this.e2 = mapper.readValue(e2Json, Enseignant.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public Enseignant getE1() {
		return e1;
	}
	public void setE1(Enseignant e1) {
		this.e1 = e1;
	}
	public Enseignant getE2() {
		return e2;
	}
	public void setE2(Enseignant e2) {
		this.e2 = e2;
	}
	
}
