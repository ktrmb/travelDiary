package at.jku.se.diary.model;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class DiaryDB {

    /**
     * Writes the current diary-Object into the XML
     * @param diary
     * @param file
     * @throws JAXBException
     */
    public void writeDiary(Diary diary, File file) throws JAXBException{
        diary.setCurrentEntry(null);
        JAXBContext jc = JAXBContext.newInstance(Diary.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(diary, file);
    }

    /**
     * Reads the data from the XML
     * @param file
     * @return file, diary class
     * @throws JAXBException
     */
    public Diary readDiary(File file) throws JAXBException{
        return JAXB.unmarshal(file, Diary.class);
    }
}
