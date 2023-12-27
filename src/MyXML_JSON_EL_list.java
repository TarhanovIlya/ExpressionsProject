import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class MyXML_JSON_EL_list {
    List<My_Json_XML_Element> list;

    public MyXML_JSON_EL_list(List<My_Json_XML_Element> list) {
        this.list = list;
    }

    public MyXML_JSON_EL_list() {
        list = null;
    }


    public List<My_Json_XML_Element> getList() {
        return list;
    }

    public void setList(List<My_Json_XML_Element> list) {
        this.list = list;
    }
}
