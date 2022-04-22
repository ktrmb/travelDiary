/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author reinhold
 */
public class DiaryEntry {

    final int id;
    private LocalDate date;
    private String title;
    private String address;
    private String diaryText;
    private ArrayList<Image> pictures;


    public DiaryEntry(String title, int id) {
        this.id = id;
        setTitle(title);
    }
    public DiaryEntry(int id, LocalDate date, String title, String address, String diaryText) {
        this.id = id;
        setDate(date);
        setTitle(title);
        this.address = address; //weil optional
        setDiaryText(diaryText);
        pictures = new ArrayList<>();

    }
    public ArrayList<Image> getPictures(){
        return pictures;
    }

    public int getId() {
        return id;
    }
    public void addPicture(Image pic){
        pictures.add(pic);
    }

    public void setDate(LocalDate date){
        if(date != null){
            this.date = date;
        }
    }
    public void setTitle(String title) {
        if (title != null && title.length() > 0)
            this.title= title;
    }
    public void setDiaryText(String diaryText){
        if(diaryText != null && diaryText.length() > 0)
            this.diaryText = diaryText;
    }

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

    //dient nur zum Testen
    public void outPut(){
        System.out.println("Datum: " + getDate().toString() + " Title: " + getTitle().toString() + " Adresse: " + getAddress().toString() + " Text: " + getDiaryText().toString() + "pics: " + getPictures().toString());
    }


}
