/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary;

import javafx.scene.image.Image;
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
    private ArrayList<Image> pictures;
    private ArrayList<StructInformation> structuredInfo;

    //Standardkonstruktor notwendig für XML Umwandlung!
    public DiaryEntry(){
    }

    public DiaryEntry(int id, LocalDate date, String title, String address, String diaryText, ArrayList<StructInformation> infos) {
        setId(id);
        setDate(date);
        setTitle(title);
        setAddress(address);
        setDiaryText(diaryText);
        pictures = new ArrayList<>();
        structuredInfo = infos;

    }

    //Setter: --------------------------------------------------------------------------------------------

    public void setStructuredInfo(ArrayList<StructInformation> structuredInfo) {
        this.structuredInfo = structuredInfo;
    }
    public void setId(int id){
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
        if (title != null)
            this.title= title;
    }

    public void setDiaryText(String diaryText){
        if(diaryText != null)
            this.diaryText = diaryText;
    }

    public void addPicture(Image pic){
        pictures.add(pic);
    }

    //getter:-------------------------------------------------------------------------------------------------------
    public ArrayList<Image> getPictures(){
        return pictures;
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
    public void outPut(){
        System.out.println("ID: " + getId() + " "  + getTitle() + " Adresse: " + getAddress() + " Text: " + getDiaryText()  + "structuredInfos: " + getStructuredInfo().size());
        for(Image pic:getPictures()){
            System.out.println(pic.getUrl());
        }
    }
    public String toString(){
        String output = ("ID: " + getId() + " "  + getTitle() + " Adresse: " + getAddress() + " Text: " + getDiaryText()  + "structuredInfos: " + getStructuredInfo().size());

        for(Image pic:getPictures()){
            output += (" Bild: " + pic.getUrl());
        }
        return output;
    }



}
