package at.jku.se.diary.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 *
 * this class is needed to marshal and unmarshal a date from and to the xml file
 * @author Team E
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * to unmarshal a String-Date to a LocalDate type
     * @param v date from the xml
     * @return the unmarshalled date of type LocalDate
     * @throws Exception
     */
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * to marshal a LocalDate to a String type
     * @param v date from a DiaryEntry Object
     * @return the marshalled date of type String
     * @throws Exception
     */
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
