package Data.Others;

import Data.Hospital.Department;
import Data.Patient.Record;
import com.alibaba.fastjson.JSONObject;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileUse {


//    public void readFile(File file) throws IOException, BiffException {
//        // 创建输入流，读取Excel
//        FileInputStream is = new FileInputStream(file);
//        // jxl提供的Workbook类
//        Workbook wb = Workbook.getWorkbook(is);
//        // 只有一个sheet,直接处理
//        //创建一个Sheet对象
//        Sheet sheet = wb.getSheet(0);
//        // 得到所有的行数
//        int rows = sheet.getRows();
//        // 所有的数据
//        List<List<String>> allData = new ArrayList<List<String>>();
//        // 越过第一行 它是列名称
//        for (int j = 1; j < rows; j++) {
//
//            List<String> oneData = new ArrayList<String>();
//            // 得到每一行的单元格的数据
//            Cell[] cells = sheet.getRow(j);
//            for (int k = 0; k < cells.length; k++) {
//
//                oneData.add(cells[k].getContents().trim());
//            }
//            // 存储每一条数据
//            allData.add(oneData);
//            // 打印出每一条数据
//            //System.out.println(oneData);
//
//        }
//
//    }

    /*
    读取json文件中的内容 传入文件名 返回一个ArrayList<String>
     */
    public static ArrayList<String> readFile(String fileName){
        ArrayList<String> result=new ArrayList<>();
        File file=new File(fileName);
        try {
            FileReader reader=new FileReader(file);
            BufferedReader bf=new BufferedReader(reader);
            String s;
            while ((s=bf.readLine())!=null){
                result.add(s);
            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public ArrayList getObjects(Object object,ArrayList<String > strings){
        ArrayList result=null;
        for(String a:strings){
            Object o1=JSONObject.parseObject(a, object.getClass());
            result.add(o1);
        }
        return result;
    }

    //序列化读写对象
    static public void  writeFileO(String name,Object a) throws IOException {
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //如果传入的是List
        if(a instanceof List){
            List list=(List) a;
            try {
                for (Object m:list) {
                    oos.writeObject(m);
                }
                fos.flush();
                fos.close();
                oos.close();
            } catch (NullPointerException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(a instanceof HashMap){
            try {
                for (Object o : ((Map) a).keySet()) {
                    oos.writeObject(((Map) a).get(o));
                    System.out.println(o);
                }
                fos.flush();
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        //反序列化 读取records
        public static Map readFileO(String name) throws IOException {
         HashMap<Integer, Record> result=new HashMap<>();
            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object m;
            //读取对象

            try {
                while ((m = ois.readObject()) != null) {
                    Record r = (Record)m;
                    int ID = r.getID();
                    result.put(ID,r);
                    System.out.println(r);
                    }
                    //得到当前时间

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {

            }
            fis.close();
            ois.close();
             return result;
        }



}
