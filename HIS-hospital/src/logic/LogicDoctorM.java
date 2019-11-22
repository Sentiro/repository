package logic;

import Data.Hospital.Department;
import Data.Hospital.Doctor;
import Data.Hospital.Medicine;
import Data.HospitalData;
import Data.Patient.OneRecord;
import Data.Patient.Patient;
import Data.Patient.Record;
import Data.RecordData;
import Data.User.DoctorM;
import Data.User.User;
import Data.UserData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LogicDoctorM {
    HospitalData hospitalData = HospitalData.getInstance();
    UserData userData = UserData.getInstance();
    RecordData recordData = RecordData.getInstance();
    private static LogicDoctorM instance;

    public LogicDoctorM() throws IOException {
    }

    public static LogicDoctorM getInstance() throws IOException {
        if (instance == null) {
            instance = new LogicDoctorM();
        }
        return instance;
    }

    //通过登录账号确定医生
    public Doctor getDoctorThroughID(int ID) {
        String name = null;
        //遍历用户里DoctorM的信息 得到对应的名字
        for (User user : userData.getUsers()) {
            if (user instanceof DoctorM && user.getID() == ID) {
                name = user.getName();
            }
        }
        //遍历HospitalData里的医生 返回名字一致的医生
        for (Department d : hospitalData.getDepartments()) {
            for (Doctor doctor : d.getDoctors()) {
                if (doctor.getName().equals(name)) {
                    return doctor;
                }
            }
        }
        return null;
    }

    //通过医生确定该医生待管理的病人 返回一个ArrayList<OneRecord> 即病人的一次看病记录
    public ArrayList<OneRecord> myPatientRecords(Doctor doctor) {
        ArrayList<OneRecord> result = new ArrayList<>();
        //遍历所有的病历本
        for (Integer i : recordData.getRecords().keySet()) {
            //遍历病历本里所有的看病记录
            for (OneRecord oneRecord : recordData.getRecords().get(i).getMyRecord()) {
                System.out.println(oneRecord);
                //如果这一次记录里医生的名字和传入的参数医生的名字一样 并且已经缴费 并且还没确诊 就add
                if (oneRecord.getRegister().getDoctor().equals(doctor.getName()) && oneRecord.getRegister().isStatus() == false && oneRecord.getEnd() == false) {
                    result.add(oneRecord);
                    System.out.println(oneRecord);
                }
            }
        }
        return result;
    }

    //通过医生确定该医生可管理的病人 返回一个ArrayList<Patient> 病人的集合
   /* public ArrayList<Patient> myPatients(Doctor doctor) {
        ArrayList<Patient> result = new ArrayList<>();
        for (Integer i : recordData.getRecords().keySet()) {

        }
    }*/

    //通过得到可编辑comboBox的TextField的值，匹配到相应代号，拼音编码的药品，返回一个ArrayList
    public List<Medicine> renewMedicine(String target,String type) {
        List<Medicine> medicines = hospitalData.getMedicines();
        List<Medicine> finalMedicines = new ArrayList<>();
        int length = target.length();
        String pattern = ".*" + target + ".*";
        for (Medicine m : medicines) {
            if (Pattern.matches(pattern, m.getID()) || Pattern.matches(pattern, m.getPinyin()) || Pattern.matches(pattern, m.getName())) {
                if(type.equals(m.getType())){
                    finalMedicines.add(m);
                }
            }
        }
        return finalMedicines;
    }
}
