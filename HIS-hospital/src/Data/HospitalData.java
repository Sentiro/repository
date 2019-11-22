package Data;

import Data.Others.FileUse;
import Data.Hospital.Department;
import Data.Hospital.Medicine;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HospitalData {
    private ArrayList<Department> departments=new ArrayList<>();
    private List<Medicine> medicines=new ArrayList<Medicine>();
    private static HospitalData instance;//单例 只能有一个hospital


    private HospitalData() {
        /*//信息初始化
        Doctor d1=new Doctor("d1");
        Doctor d2=new Doctor("d2");
        Doctor d3=new Doctor("d3");
        Doctor d4=new Doctor("d4");
        Department department1=new Department();
        department1.setName("内科");
        ArrayList<Doctor> dos1=new ArrayList<>();
        dos1.add(d1);dos1.add(d2);
        department1.setDoctors(dos1);
        Department department2=new Department();
        department2.setName("外科");
        ArrayList<Doctor> dos2=new ArrayList<>();
        dos2.add(d3);dos2.add(d4);
        department2.setDoctors(dos2);
        departments.add(department1);
        departments.add(department2);*/
       ArrayList<String> departments2=FileUse.readFile("department.txt");
       for(String a:departments2){
           Department d=JSONObject.parseObject(a,Department.class);
           departments.add(d);
       }
       System.out.println(departments);
         ArrayList<String> medicines2=FileUse.readFile("medicine.txt");
         for(String a:medicines2){
             Medicine m =JSONObject.parseObject(a,Medicine.class);//将建json String转换为Person对象
             medicines.add(m);
         }
        //System.out.println(medicines);*/
    }
    public static HospitalData getInstance(){
        if(instance==null){
            instance=new HospitalData();
        }
        return instance;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    //根据ID返回药物 如果没有返回null
    public Medicine IDReturn(String ID){
        for(Medicine a:medicines){
            if(ID.equals(a.getID())){
                return a;
            }
        }
        return null;
    }
    //根据拼音助记码返回药物
    public Medicine PinyinReturn(String Pinyin){
        for(Medicine a:medicines){
            if(Pinyin.equals(a.getPinyin())){
                return a;
            }
        }
        return null;
    }

}
