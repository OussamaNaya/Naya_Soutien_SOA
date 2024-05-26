package Wrapper.WrapperEtudiant;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Etudiant;

public class EtudiantWrapper {
    @JsonProperty("e1")
    private Etudiant e1;
    
    @JsonProperty("e2")
    private Etudiant e2;
    

	public EtudiantWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EtudiantWrapper(Etudiant e1, Etudiant e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

	public EtudiantWrapper(String e1Json, String e2Json) throws IOException {
        // Convertir les chaînes JSON en objets Etudiant
        // Utiliser ObjectMapper pour désérialiser les chaînes JSON en objets Etudiant
        // Assurez-vous d'importer ObjectMapper de com.fasterxml.jackson.databind.ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.e1 = mapper.readValue(e1Json, Etudiant.class);
            this.e2 = mapper.readValue(e2Json, Etudiant.class);
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

    public Etudiant getE2() {
        return e2;
    }

    public void setE2(Etudiant e2) {
        this.e2 = e2;
    }
}