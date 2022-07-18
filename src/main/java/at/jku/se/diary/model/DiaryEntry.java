/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * this class represents a DiaryEntry
 * @author Team E
 *
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

    /**
     * standard constructor needed to save data into the xml file
     */
    public DiaryEntry(){
    }

    /**
     * constructor to create a DiaryEntry Object
     * @param id for identifying a DiaryEntry
     * @param date for the DiaryEntry
     * @param title for the DiaryEntry
     * @param address for the location of a DiaryEntry
     * @param diaryText for describing a DiaryEntry
     * @param infos list for more detailed and structured information of a DiaryEntry
     */
    public DiaryEntry(int id, LocalDate date, String title, String address, String diaryText, ArrayList<StructInformation> infos) {
        setId(id);
        setDate(date);
        setTitle(title);
        setAddress(address);
        setDiaryText(diaryText);
        setStructuredInfo(infos);
        String defaultPic = "defaultPic.png";
        picture1 = defaultPic;
        picture2 = defaultPic;
        picture3 = defaultPic;

    }

    /**
     * to set the name of the first picture
     * @param nameOfPic which should be set
     */
    public void setPicture1(String nameOfPic){
       this.picture1 = nameOfPic;
    }

    /**
     * to set the name of the second picture
     * @param nameOfPic which should be set
     */
    public void setPicture2(String nameOfPic){
        this.picture2 = nameOfPic;
    }

    /**
     * to set the name of the third picture
     * @param nameOfPic which should be set
     */
    public void setPicture3(String nameOfPic){
        this.picture3 = nameOfPic;
    }

    /**
     * to set the list of structured information of a DiaryEntry
     * @param structuredInfo which should be set
     */
    public void setStructuredInfo(ArrayList<StructInformation> structuredInfo) {
        this.structuredInfo = structuredInfo;
    }

    /**
     * to set the id of a DiaryEntry
     * @param id which should be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * to set the date of a DiaryEntry, if no date is selected the current date is set
     * @param date which should be set
     */
    public void setDate(LocalDate date){
        if(date != null){
            this.date = date;
        }else{
            this.date = LocalDate.now();
        }
    }

    /**
     * to set the location of a DiaryEntry
     * @param address which should be set
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * to set the title of a DiaryEntry
     * @param title which should be set
     */
    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }

    /**
     * to set the text of a DiaryEntry
     * @param diaryText
     */
    public void setDiaryText(String diaryText){
        if(diaryText != null) {
            this.diaryText = diaryText;
        }
    }

    /**
     * @return the name of the first picture
     */
    public String getPicture1(){
        return picture1;
    }

    /**
     * @return the name of the second picture
     */
    public String getPicture2(){
        return picture2;
    }

    /**
     * @return the name of the third picture
     */
    public String getPicture3(){
        return picture3;
    }

    /**
     * @return the list of structured information
     */
    public ArrayList<StructInformation> getStructuredInfo() {
            return structuredInfo;
    }

    /**
     * @return the id of the DiaryEntry
     */
    public int getId() {
        return id;
    }

    /**
     * @return the date of the DiaryEntry
     */
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the title of a DiaryEntry
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the address of a DiaryEntry
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return return the text of a DiaryEntry
     */
    public String getDiaryText() {
        return diaryText;
    }

    /**
     * takes the selected picture from the home-directory of the
     * user and save it to the pictures-folder with a specific name
     * @param fileImg is the file path to the selected picture
     * @param id of the DiaryEntry
     * @return the name of the saved picture
     */
    public String saveImageToFile(String fileImg, String id){
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(new Image(fileImg), null);
        try{
            ImageIO.write(bufferedImage, "jpg", new File("src\\pictures\\image" + id + ".jpg"));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "image" + id + ".jpg";
    }
}
