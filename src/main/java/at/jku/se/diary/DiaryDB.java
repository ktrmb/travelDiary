package at.jku.se.diary;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class DiaryDB {

    //Methode um in die XML zu schreiben
    public void writeDiary(Diary diary, File file) throws JAXBException{
        JAXBContext jc = JAXBContext.newInstance(Diary.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(diary, file);
    }
    //Methode um Daten aus der XML auszulesen
    public Diary readDiary(File file) throws JAXBException{
        return JAXB.unmarshal(file, Diary.class);
    }
}
