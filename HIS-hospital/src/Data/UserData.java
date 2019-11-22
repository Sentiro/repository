package Data;

import Data.Others.FileUse;
import Data.User.Administrator;
import Data.User.DoctorG;
import Data.User.DoctorM;
import Data.User.User;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    List<User> users=new ArrayList<>();
    private static UserData instance;

    private UserData() {
        //初始化
        //挂号医生
        User u1=new DoctorG(1111,"1111","cxk"); users.add(u1);
        //门诊医生
        ArrayList<String> doctorM= FileUse.readFile("DoctorM.txt");
        for(String a:doctorM){
            DoctorM d= JSONObject.parseObject(a,DoctorM.class);
            users.add(d);
        }
        //药房医生
        ArrayList<String> administrator= FileUse.readFile("Administrator.txt");
        for(String a:administrator){
            Administrator d= JSONObject.parseObject(a,Administrator.class);
            users.add(d);
        }

    }

    public static UserData getInstance(){
        if(instance==null){
            instance=new UserData();
        }
        return instance;
    }


    public List<User> getUsers() {
        return users;
    }
}
