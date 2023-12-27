import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
public class My_Json_XML_Element {
    String contents;

    String type;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public My_Json_XML_Element(String contents) {
        this.contents = contents;
    }

    public My_Json_XML_Element(){
        contents=null;
    }

    @XmlAttribute
    public String getType() {
        return type;

    }

    public void setType(String type) {
        this.type = type;
    }
}



