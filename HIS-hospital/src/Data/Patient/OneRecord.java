package Data.Patient;

import Data.Hospital.Medicine;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OneRecord  implements Serializable {
    private String type;//中医还是西医
    private List<Medicine> medicines=new ArrayList<>();//开的药
    private LocalDateTime time;//下单时间
    private Register register;//挂号记录
    private Record record;//只是用于方便TableView显示
    private String history;//病史
    private String zhusu;//主诉
    private Boolean end=false;//是否确诊即医生是否已经提交处方诊断
    private Boolean payAll=false;//本次看病是否交全所有药品的费用

    //通过get方法获取下层Register信息department date  帮助填写fx TableView
    public String getDepartment() {
        return register.getDepartment();
    }
    public LocalDateTime getDate(){
        return  register.getDate();
    }
    public String getStatus(){
        if(register.isStatus()==false){
            return "已缴费";
        }else return  "已看诊";
    }

    //通过get方法获取上层Record内部信息 病历号 Patient 内部信息 姓名和 身份证号
    public void  setRecord(Record record){
        this.record= record;
    }
    //身份证号
    public String getID(){
        return record.getOwner().getID();
    }
    //name
    public String getName(){
        return record.getOwner().getName();
    }
    //病历号
    public int getId(){
        return record.getID();
    }

    public Register getRegister() {
        return register;
    }



    public void setRegister(Register register) {
        this.register = register;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getZhusu() {
        return zhusu;
    }

    public void setZhusu(String zhusu) {
        this.zhusu = zhusu;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Boolean getPayAll() {
        return payAll;
    }

    public void setPayAll(Boolean payAll) {
        this.payAll = payAll;
    }

    @Override
    public String toString() {
     /*   return "OneRecord{" +
                "type='" + type + '\'' +
                ", medicines=" + medicines +
                ", time=" + time +
                ", register=" + register +
                '}';*/
        Patient p=record.getOwner();
        return p.getName()+" "+p.getGender()+" "+p.getAge()+" "+p.getAgeType();

    }
    //为了方便ComboBox设置下拉菜单 写一个能打印当前OneRecord所属病历本里面的patient信息的方法
    public String returnPatientDetails(){
        Patient p=record.getOwner();
    return p.getName()+" "+p.getGender()+" "+p.getAge()+" "+p.getAgeType();
    }

}
