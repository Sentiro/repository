package Data;

import Data.Others.FileUse;
import Data.Patient.OneRecord;
import Data.Patient.Patient;
import Data.Patient.Record;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordData {

    private HashMap<Integer,Record> records=new HashMap<>();//所有病历本
    private static RecordData instance;

    private RecordData() throws IOException {
        //String name, int age, String gender, String ageType, LocalDate birthDate, String ID, String address
        Record r1=new Record();
        Patient p1=new Patient("111111","yyc",19,"女","岁", LocalDate.of(2000,6,9),"sssssssss");
        r1.setOwner(p1);
        r1.setID(1234);
        List<OneRecord> myRecord=new ArrayList<>();
        /*OneRecord o1=new OneRecord();
        Register
        o1.setType("西医");o1.setRegister();
        myRecord.add(new OneRecord())
        r1.setMyRecord(new OneRecord())*/
      //  records.put(1234,r1);
       /* ArrayList<String> records2=F.readFile("Records.txt");
        System.out.println(records2.toString());
        System.out.println("!!!!!!!");*/

      /*  for(String a:records2){
            Record d= JSONObject.parseObject(a,Record.class);
            records.put(d.getID(),d);
        }
        System.out.println(records);*/
     records= (HashMap<Integer, Record>) FileUse.readFileO("Records.txt");
    }
    public static RecordData getInstance() throws IOException {
        if(instance==null){
            instance=new RecordData();
        }
        return instance;
    }

    public HashMap<Integer, Record> getRecords() {
        return records;
    }

    //通过病历号返回Record
    public Record getOneRecord(int ID){
        for(Integer a:records.keySet()){
            if(a==ID){
                return records.get(a);
            }
        }
        return null;
    }
}
