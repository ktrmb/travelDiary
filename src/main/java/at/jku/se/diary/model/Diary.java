package at.jku.se.diary.model;

import at.jku.se.diary.HelloFX;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * this class represents the whole diary
 * @author Team E
 *
 */
@XmlRootElement(name="myDiary")
public class Diary {
    private ArrayList<DiaryEntry> entryList;
    private DiaryDB diaryDB;
    private File diaryFile;
    private ArrayList<String> categories;
    private DiaryEntry currentEntry;
    private String diaryFilePath;

    /**
     * Constructor to crate a new diary
     * @throws JAXBException
     */
    public Diary() throws JAXBException {
        entryList = new ArrayList<>();
        diaryDB = HelloFX.diaryDB;
        diaryFile = HelloFX.diaryFile;
        categories = new ArrayList<>();
        currentEntry = null;
        diaryFilePath = "diary.xml";
    }

    /**
     * @return the entryList of DiaryEntry objects
     */
    @XmlElement
    public ArrayList<DiaryEntry> getEntryList() {
        return entryList;
    }

    /**
     * @return the list of categories
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * to set the entryList
     * @param entryList of DiaryEntry Objects
     */
    public void setEntryList(ArrayList<DiaryEntry> entryList) {
        this.entryList = entryList;
    }

    /**
     * to set the list of categories
     * @param categories list
     */
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    /**
     * to add a new category to the arraylist and to save it in the xml file
     * @param category to be added
     * @throws JAXBException
     */
    public void addNewCategory(String category) throws JAXBException {
        categories.add(category);
        saveDiary();
    }

    /**
     * to add a new DiaryEntry to the entryList and to save it in the xml file
     * @param newEntry to be added
     * @throws JAXBException
     */
    public void addNewEntry(DiaryEntry newEntry) throws JAXBException {
        entryList.add(newEntry);
        saveDiary();
    }

    /**
     * to save the current status of the Diary-Object to the xml file
     * @throws JAXBException
     */
    public void saveDiary() throws JAXBException {
        if(this.diaryDB !=null){
            diaryDB.writeDiary(this, diaryFile);
        }
        else{
            return;
        }
    }

    /**
     * @return the current DiaryEntry object
     */
    public DiaryEntry getCurrentEntry() {
        return currentEntry;
    }

    /**
     * to set the current DiaryEntry objekt
     * @param currentEntry to be set
     */
    public void setCurrentEntry(DiaryEntry currentEntry) {
        this.currentEntry = currentEntry;
    }

    /**
     * to create a new id that does not yet exist
     * @return the new id
     */
    public int createID(){
        int id;
        if(this.getEntryList().isEmpty()){
            id = 1;
        }else{
            int max = 0;
            for(DiaryEntry e : this.getEntryList()){
                int currentID = e.getId();
                if(currentID > max){
                    max = currentID;
                }
            }
            id = max+1;
        }
        return id;
    }

    /**
     * to create a new DiaryEntry object and to call the method for adding it to the arrayList
     * @param id of the new DiaryEntry
     * @param date of the new DiaryEntry
     * @param title of the new DiaryEntry
     * @param address of the new DiaryEntry
     * @param diaryText of the new DiaryEntry
     * @param pic1 of the new DiaryEntry
     * @param pic2 of the new DiaryEntry
     * @param pic3 of the new DiaryEntry
     * @param structInfo of the new DiaryEntry
     * @throws JAXBException
     */
    public void createNewEntry(int id, LocalDate date, String title, String address,
                               String diaryText, String pic1, String pic2,
                               String pic3, ArrayList<StructInformation> structInfo) throws JAXBException {
        if(getCurrentEntry() != null) {
            structInfo = getCurrentEntry().getStructuredInfo();
            setCurrentEntry(null);
        }
        DiaryEntry newEntry = new DiaryEntry(id, date, title, address, diaryText, structInfo);

        String defaultPic = "png";
        if(!pic1.contains(defaultPic)){
            newEntry.setPicture1(newEntry.saveImageToFile(pic1, (String.valueOf(newEntry.getId())+"_1")));
        }
        if(!pic2.contains(defaultPic)){
            newEntry.setPicture2(newEntry.saveImageToFile(pic2, (String.valueOf(newEntry.getId())+"_2")));
        }
        if(!pic3.contains(defaultPic)){
            newEntry.setPicture3(newEntry.saveImageToFile(pic3, (String.valueOf(newEntry.getId())+"_3")));
        }
        addNewEntry(newEntry);
    }

    /**
     * to open a new window with the user home directory to select a picture in jpg-format
     * @param stage to open the user home directory
     * @return the file-path of the selected picture
     */
    public File addPic(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }

    /**
     * to search the StructuredInformation of an DiaryEntry for a specific category
     * @param entry to be searched
     * @param category by which the filter is applied
     * @return a boolean value, depending on whether the DiaryEntry contains the selected category or not
     */
    public boolean filterCategories(DiaryEntry entry, String category){
        if(entry.getStructuredInfo() != null){
            for(StructInformation s : entry.getStructuredInfo()){
                if(s.getCategory().equals(category)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * to search the StructuredInformation of an DiaryEntry for a specific text
     * @param entry to be searched
     * @param value text to be searched for
     * @return a boolean value, depending on whether the DiaryEntry contains the text value or not
     */
    public boolean filterStructInfoText(DiaryEntry entry, String value){
        if(entry.getStructuredInfo() != null){
            for(StructInformation s : entry.getStructuredInfo()){
                if(s.getStructuredText().toLowerCase().contains(value.toLowerCase())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * to search the StructuredInformation of an DiaryEntry for a specific star-rating
     * @param entry to be searched
     * @param rating to be searched for
     * @return a boolean value, depending on whether the DiaryEntry contains the selected star-rating or not
     */
    public boolean filterStars(DiaryEntry entry, String rating){
        if(entry.getStructuredInfo() != null){
            for(StructInformation s : entry.getStructuredInfo()){
                if(String.valueOf(s.getStars()).equals(rating)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * save set backup file bath to variable
     * @param path to be saved
     * @throws JAXBException
     */
    public void setDiaryFilePath (String path) throws JAXBException {
        this.diaryFilePath = path;
    }

    /**
     * get saved backup file path
     * @return backup file path
     * @throws JAXBException
     */
    public String getDiaryFilePath () throws JAXBException {
        return diaryFilePath;
    }
}
