package at.jku.se.diary.model;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 *
 * this class contains methods to read and write the xml file of Diary-data
 * @author Team E
 *
 */
public class DiaryDB {

    /**
     * writes the current diary-Object into the XML
     * @param diary object which should be saved
     * @param file in which the diary should be saved
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
     * reads the data from the XML
     * @param file from which the data is read
     * @return the unmarshaled file and the Diary.class
     * @throws JAXBException
     */
    public Diary readDiary(File file) throws JAXBException{
        return JAXB.unmarshal(file, Diary.class);
    }
}
