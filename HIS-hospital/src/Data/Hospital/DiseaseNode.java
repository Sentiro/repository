package Data.Hospital;

import java.util.Vector;

public class DiseaseNode {
    private String ID;
   private String name;
   private String ParentID;
    Vector<DiseaseNode> sub_diseases;
    Vector<String> patients;

    public DiseaseNode(String name) {
        this.name = name;
    }

    public DiseaseNode() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public Vector<DiseaseNode> getSub_diseases() {
        return sub_diseases;
    }

    public void setSub_diseases(Vector<DiseaseNode> sub_diseases) {
        this.sub_diseases = sub_diseases;
    }

    public Vector<String> getPatients() {
        return patients;
    }

    public void setPatients(Vector<String> patients) {
        this.patients = patients;
    }
}
