
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

interface MyFile {
    abstract String ConvertFrom(String fileName) throws IOException, JAXBException;
    abstract String ConvertInto(String fileName) throws IOException, JAXBException;

}






class MyZipFile implements MyFile{



    @Override
    public String ConvertFrom(String fileName) throws IOException {


            byte[] buffer = new byte[1024];
            File zipFile = new File(fileName);
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {

                FileOutputStream fos = new FileOutputStream(zipEntry.getName());
                int len;
                while((len = zis.read(buffer))>0){
                    fos.write(buffer,0,len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            //dont leave garbage files
            zipFile.delete();

            return fileName.substring(0,fileName.lastIndexOf('.'));
    }

    @Override
    public String ConvertInto(String fileName) throws IOException {

        String newFileName=fileName+".zip";

        FileOutputStream fos = new FileOutputStream(newFileName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(fileName);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();



        fileToZip.delete();

        return newFileName;
    }


}





class MyJSONFile implements  MyFile{


    @Override
    public String ConvertFrom(String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();


        List<My_Json_XML_Element> myJsonXMLElementList = new ArrayList<>();
        File fileToUnJson =new File(fileName);
        myJsonXMLElementList = mapper.readValue(fileToUnJson, new TypeReference<List<My_Json_XML_Element>>(){});



        String newFileContent = "";
        for (int i = 0; i< myJsonXMLElementList.size(); i++){
            newFileContent += myJsonXMLElementList.get(i).getContents();
        }

        String newFileName = fileName.substring(0,fileName.lastIndexOf('.'));

        FileOutputStream outputStream = new FileOutputStream(new File(newFileName));
        outputStream.write(newFileContent.getBytes(StandardCharsets.UTF_8));


        fileToUnJson.delete();

        return newFileName;
    }


    //TODO add deletion of file after conversion
    @Override
    public String ConvertInto(String fileName) throws IOException {

        File fileToJson = new File(fileName);
        FileInputStream inputStream = new FileInputStream(fileToJson);

        String fileContents = new String(inputStream.readAllBytes());






        ExpressionExtractor extractor = new ExpressionExtractor(fileContents);

        List<My_Json_XML_Element> myJsonXMLElementList = new ArrayList<>();

        if(extractor.isValid()){
            int index_first =0;
            do {
                    String text = fileContents.substring(index_first,extractor.GetStart());
                    String equation = fileContents.substring(extractor.GetStart(),extractor.GetEnd());

                    My_Json_XML_Element a = new My_Json_XML_Element(text);
                    a.type = "text";
                    myJsonXMLElementList.add(a);
                    My_Json_XML_Element b = new My_Json_XML_Element(equation);
                    b.type = "equation";
                    myJsonXMLElementList.add(b);

                    index_first=extractor.GetEnd();
            }while(extractor.GoToNext());
            My_Json_XML_Element temp = new My_Json_XML_Element(fileContents.substring(index_first,fileContents.length()));
            temp.type = "text";
            myJsonXMLElementList.add(temp);
        }
        else{
            My_Json_XML_Element a =new My_Json_XML_Element(fileContents);
            a.type="content";
            myJsonXMLElementList.add(a);

        }





        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(fileName+".json"), myJsonXMLElementList);

        fileToJson.delete();
        return  fileName+".json";
    }
}




class MyXMLFile implements  MyFile{

    @Override
    public String ConvertFrom(String fileName) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(MyXML_JSON_EL_list.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        MyXML_JSON_EL_list elList = new MyXML_JSON_EL_list();

        File XMLfile = new File(fileName);

        elList =(MyXML_JSON_EL_list) unmarshaller.unmarshal(XMLfile);





        String newFileName = fileName.substring(0,fileName.lastIndexOf('.'));

        String newFileContent = "";
        for (int i = 0; i< elList.list.size(); i++){
            newFileContent += elList.list.get(i).getContents();
        }

        File newFile = new File(newFileName);
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(newFileContent.getBytes(StandardCharsets.UTF_8));

        XMLfile.delete();

        return  newFileName;
    }

    @Override
    public String ConvertInto(String fileName) throws IOException, JAXBException {
        File fileToXMl = new File(fileName);
        FileInputStream inputStream = new FileInputStream(fileToXMl);

        String fileContents = new String(inputStream.readAllBytes());






        ExpressionExtractor extractor = new ExpressionExtractor(fileContents);

        List<My_Json_XML_Element> myXMLElementList = new ArrayList<>();

        if(extractor.isValid()){
            int index_first =0;
            do {
                String text = fileContents.substring(index_first,extractor.GetStart());
                String equation = fileContents.substring(extractor.GetStart(),extractor.GetEnd());

                My_Json_XML_Element a = new My_Json_XML_Element(text);
                a.type = "text";
                myXMLElementList.add(a);
                My_Json_XML_Element b = new My_Json_XML_Element(equation);
                b.type = "equation";
                myXMLElementList.add(b);

                index_first=extractor.GetEnd();
            }while(extractor.GoToNext());
            My_Json_XML_Element temp = new My_Json_XML_Element(fileContents.substring(index_first,fileContents.length()));
            temp.type = "text";
            myXMLElementList.add(temp);
        }
        else{
            My_Json_XML_Element a =new My_Json_XML_Element(fileContents);
            a.type="content";
            myXMLElementList.add(a);

        }

        JAXBContext context = JAXBContext.newInstance(MyXML_JSON_EL_list.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(new MyXML_JSON_EL_list(myXMLElementList),new File(fileName+".xml"));




        //fileToXMl.delete();
        return  fileName+".xml";
    }
}