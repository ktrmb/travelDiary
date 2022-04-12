/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary;

import java.time.LocalDate;

/**
 *
 * @author reinhold
 */
public class DiaryEntry {

    private LocalDate date;
    private String title;
    private String address;
    private String diaryText;

    public DiaryEntry(String title) {
        setTitle(title);
    }
    public DiaryEntry(LocalDate date, String title, String address, String diaryText) {
        setDate(date);
        setTitle(title);
        this.address = address; //weil optional
        setDiaryText(diaryText);
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
        System.out.println("Datum: " + getDate().toString() + " Title: " + getTitle().toString() + " Adresse: " + getAddress().toString() + " Text: " + getDiaryText().toString());
    }

}
