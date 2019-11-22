package Data.Hospital;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable {
    private String name;
    private ArrayList<Doctor> doctors=new ArrayList();


    public Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return name;
    }
}
