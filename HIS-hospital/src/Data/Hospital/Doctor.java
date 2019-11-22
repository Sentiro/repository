package Data.Hospital;

import Data.Patient.Patient;
import Data.Patient.Record;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor implements Serializable {
    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
