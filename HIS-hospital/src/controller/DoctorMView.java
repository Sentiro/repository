package controller;

import Data.Others.FileUse;
import Data.Hospital.Doctor;
import Data.Hospital.Medicine;
import Data.HospitalData;
import Data.Others.item;
import Data.Patient.OneRecord;
import Data.Patient.Record;
import Data.RecordData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import logic.LogicDoctorM;
import logic.Login;

import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorMView implements Initializable {
    LogicDoctorM doctorM=LogicDoctorM.getInstance();
    Doctor doctor;
    @FXML
    private TextField zhusu;

    @FXML
    private TableColumn<Medicine, String> numbers;

    @FXML
    private TextArea history;

    @FXML
    private TableColumn<Medicine, String> form;

    @FXML
    private Button delete;

    @FXML
    private ComboBox<String> TypeBox;

    @FXML
    private TableView<Medicine> medicineTable;

    @FXML
    private TableColumn<Medicine, String > usage;

    @FXML
    private TableColumn<Medicine,String > times;

    @FXML
    private ComboBox<OneRecord> PatientBox;

    @FXML
    private TableColumn<Medicine, Integer> price;

    @FXML
    private TableColumn<Medicine, String> name;

    @FXML
    private TableColumn<Medicine, Integer> id;

    @FXML
    private ComboBox<Medicine> MedicineBox;

    @FXML
    private TextField chufang;

    Login logicLogin=Login.getInstance();
    //新建一个List存储选中药物的信息
    ObservableList<Medicine> tempMedicine = FXCollections.observableArrayList(new ArrayList<Medicine>());
    public DoctorMView() throws IOException {

    }

    @Override
        public void initialize(URL location, ResourceBundle resources) {

        //根据登录信息确定医生
        int ID = LoginC.ID;
        doctor = doctorM.getDoctorThroughID(ID);
        System.out.println(ID);
        System.out.println(doctor);
        MedicineBox.setEditable(true);
        //设置patientBox的内容
        try {
            ArrayList<OneRecord> myRecords = LogicDoctorM.getInstance().myPatientRecords(doctor);
            System.out.println(myRecords);
            ObservableList oneRecords = FXCollections.observableArrayList(myRecords);
            PatientBox.setItems(oneRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置TypeBox的内容
        ArrayList<String> s = new ArrayList<>();
        s.add("中药");
        s.add("西药");
        TypeBox.setItems(FXCollections.observableArrayList(s));

        //对TypeBox进行监听
//        TypeBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (oldValue == true && newValue == false) {
//                addMedicine();
//            }
//        });
        TypeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                addMedicine();
            }
        });
        //对MedicineBox内嵌的textField进行监听 value改变即调用更新方法
       // MedicineBox.getEditor().textProperty().addListener( (observable, oldValue, newValue) -> updateMedicine());
        MedicineBox.getEditor().textProperty().addListener(new ChangeListener<String>() {
                                                 @Override
                                                 public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                                     if(MedicineBox.getEditor().getText().length()<10){
                                                         updateMedicine();
                                                     }else if(MedicineBox.getEditor().getText().length()==0){
                                                         addMedicine();
                                                     }
                                                 }
                                             });
//        MedicineBox.getEditor().focusedProperty().addListener(){
//                System.out.println("11111");
//                updateMedicine();
//        });
    }
//更新药品实现即时搜索
    public void updateMedicine(){
           String target=MedicineBox.getEditor().getText();
            List<Medicine> newMedicines=doctorM.renewMedicine(target,TypeBox.getValue());
            MedicineBox.getItems().removeAll();
            MedicineBox.setItems(FXCollections.observableArrayList(newMedicines));

    }

    //根据TypeBox的选择情况 添加MedicineBox的内容
    public void addMedicine(){
        ArrayList<Medicine> m1=new ArrayList<>();
        ArrayList<Medicine> m2=new ArrayList<>();
        List<Medicine> m= HospitalData.getInstance().getMedicines();
        for(Medicine a:m){
            if(a.getType().equals("西药")){
                m1.add(a);
            }else m2.add(a);
        }
        if(TypeBox.getValue().equals("西药")){
            MedicineBox.setItems(FXCollections.observableArrayList(m1));
        }else MedicineBox.setItems(FXCollections.observableArrayList(m2));
    }

    //设置药品表格里的内容
    //网表格里添加药品,将表格与Medicine类进行绑定，获取药品信息转化为相应格式写入表格
    //同时将表格中相应控件设置为可编辑，回车后保存信息
    public void showMedicine(){
        medicineTable.setEditable(true);
     //   System.out.println(MedicineBox.getValue().getUsage()+1);
        //todo 记得把override写完
        MedicineBox.setConverter(new StringConverter<Medicine>() {
            @Override
            public String toString(Medicine m) {
                if(m!=null){
                    return m.getName()+" "+m.getForm()+" "+m.getID()+" "+m.getPinyin()+" "+m.getType()+" "+m.getPrice();
                }else
                    return null;
            }

            @Override
            public Medicine fromString(String string) {
                String[] results=string.split(" ");
                Medicine m=new Medicine();
                m.setName(results[0]);
                m.setForm(results[1]);
                m.setID(results[2]);
                m.setPinyin(results[3]);
                m.setType(results[4]);
                m.setPrice(Float.parseFloat(results[5]));
                return m;
            }
        });
        Medicine m=MedicineBox.getConverter().fromString(MedicineBox.getEditor().getText());
        tempMedicine.add(m);
        //设置每一列的信息
        //设置MedicineBox的格式
        //普通单元格的绑定
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));//药品编号
        form.setCellFactory(TextFieldTableCell.forTableColumn());
        form.setCellValueFactory(new PropertyValueFactory<>("form"));//病历号药品规格
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        //可修改的单元格绑定
        usage.setCellValueFactory(new PropertyValueFactory<>("usage"));//用法
        usage.setCellFactory(TextFieldTableCell.forTableColumn());
        usage.setOnEditCommit(
                (TableColumn.CellEditEvent<Medicine, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setUsage(t.getNewValue())
        );
        times.setCellValueFactory(new PropertyValueFactory<>("useTimes"));//频次
        times.setCellFactory(TextFieldTableCell.forTableColumn());
        times.setOnEditCommit(
                (TableColumn.CellEditEvent<Medicine, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setUseTimes(t.getNewValue())
        );
        numbers.setCellValueFactory(new PropertyValueFactory<>("numbers"));//数量
        numbers.setCellFactory(TextFieldTableCell.forTableColumn());
        numbers.setOnEditCommit(
                (TableColumn.CellEditEvent<Medicine, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumbers(t.getNewValue())
        );


        medicineTable.setItems(tempMedicine);
        addMedicine();
    }

    //添加这次诊断信息到OneRecord
    public void addDetails() throws IOException {
        //根据药品表格内的选择情况建立药品集合
        ArrayList<Medicine> medicineItems=new ArrayList<>();
        for(int i=0;i<medicineTable.getItems().size();i++){
            Medicine tempMedicine=medicineTable.getItems().get(i);
            medicineItems.add(tempMedicine);
        }
        //如果药品数量小于5 进行后续操作
        if (medicineItems.size()<5){
            for(int i=0;i<medicineItems.size();i++){
                medicineItems.get(i).setMedicineStatus("已开立");
            }
            //根据选择框确定具体的OneRecord
            OneRecord temp = PatientBox.getValue();
            RecordData recordData=RecordData.getInstance();
            for(int a:recordData.getRecords().keySet()){
                Record tempRecord=recordData.getRecords().get(a);
                System.out.println(tempRecord);
                for(OneRecord oneRecord:tempRecord.getMyRecord()){
                    if(oneRecord.getDate().equals(temp.getDate())){
                        oneRecord.setTime(LocalDateTime.now());
                        oneRecord.setType(TypeBox.getValue());
                        oneRecord.setHistory(history.getText());
                        oneRecord.setZhusu(zhusu.getText());
                        oneRecord.setMedicines(medicineItems);
                        oneRecord.setEnd(true);
                        oneRecord.getRegister().setStatus(true);
                    }
                }
            }
            //System.out.println(temp.getMedicines());
            FileUse.writeFileO("Records.txt",recordData.getRecords());
            System.out.println("duwenjjian ");
            FileUse.readFileO("Records.txt");
            System.out.println("添加成功");
            logicLogin.showSuccessfulPage();
        }else{
            logicLogin.showFailPage("/view/WarningM.fxml");
        }

    }

    public void removeMedicine(){
        int selectedIndex=medicineTable.getSelectionModel().getFocusedIndex();
        medicineTable.getItems().remove(selectedIndex);
    }

}
