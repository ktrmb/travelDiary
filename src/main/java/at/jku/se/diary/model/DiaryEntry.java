/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author reinhold
 */
public class DiaryEntry {

    private int id;
    private LocalDate date;
    private String title;
    private String address;
    private String diaryText;
    private String picture1;
    private String picture2;
    private String picture3;
    private ArrayList<StructInformation> structuredInfo;

    //Standardkonstruktor notwendig für XML Umwandlung!
    public DiaryEntry(){
    }

    public DiaryEntry(int id, LocalDate date, String title, String address, String diaryText, ArrayList<StructInformation> infos) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.address = address;
        this.diaryText = diaryText;
        structuredInfo = infos;
        String defaultPic = "defaultPic.png";
        picture1 = defaultPic;
        picture2 = defaultPic;
        picture3 = defaultPic;

    }

    //Setter: --------------------------------------------------------------------------------------------
    public void setPicture1(String nameOfPic){
       this.picture1 = nameOfPic;
    }
    public void setPicture2(String nameOfPic){
        this.picture2 = nameOfPic;
    }
    public void setPicture3(String nameOfPic){
        this.picture3 = nameOfPic;
    }


    public void setStructuredInfo(ArrayList<StructInformation> structuredInfo) {
        this.structuredInfo = structuredInfo;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDate(LocalDate date){
        if(date != null){
            this.date = date;
        }
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }

    public void setDiaryText(String diaryText){
        if(diaryText != null) {
            this.diaryText = diaryText;
        }
    }

    //getter:-------------------------------------------------------------------------------------------------------
    public String getPicture1(){
        return picture1;
    }
    public String getPicture2(){
        return picture2;
    }
    public String getPicture3(){
        return picture3;
    }

    public ArrayList<StructInformation> getStructuredInfo() {
        return structuredInfo;
    }

    public int getId() {
        return id;
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getDiaryText() {
        return diaryText;
    }

    //dient nur zum Testen - wieder weglöschen!!----------------------------------------------------------------------------------
/*    public void outPut(){
        System.out.println("ID: " + getId() + " "  + getTitle() + " Adresse: " + getAddress() + " Text: " + getDiaryText()  + "structuredInfos: " + getStructuredInfo().size());
        System.out.println("Name Pic1:" + getPicture1() + " Name Pic2: " + getPicture2() + " Name Pic3: " + getPicture3());

    }*/
}
