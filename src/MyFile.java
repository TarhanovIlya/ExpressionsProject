import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.*;

interface MyFile {
    abstract void ConvertFrom(String fileName) throws IOException;
    abstract void ConvertInto(String fileName) throws IOException;

}






class MyZipFile implements MyFile{

    byte[] myBytes = new byte[1024];


    @Override
    public void ConvertFrom(String fileName) throws IOException {


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
    }

    @Override
    public void ConvertInto(String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName+".zip");
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
    }

}





class MyJSONFile implements  MyFile{


    @Override
    public void ConvertFrom(String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();


        List<MyJsonElement> myJsonElementList = new ArrayList<>();
        myJsonElementList = mapper.readValue(new File(fileName), new TypeReference<List<MyJsonElement>>(){});



        String newFileContent = "";
        for (int i=0;i<myJsonElementList.size();i++){
            newFileContent += myJsonElementList.get(i).getContents();
        }

        String newFileName = fileName.substring(0,fileName.lastIndexOf('.'));

        FileOutputStream outputStream = new FileOutputStream(new File(newFileName));
        outputStream.write(newFileContent.getBytes(StandardCharsets.UTF_8));



    }


    //TODO add deletion of file after conversion
    @Override
    public void ConvertInto(String fileName) throws IOException {

        FileInputStream inputStream = new FileInputStream(fileName);

        String fileContents = new String(inputStream.readAllBytes());






        ExpressionExtractor extractor = new ExpressionExtractor(fileContents);

        List<MyJsonElement> myJsonElementList = new ArrayList<>();

        if(extractor.isValid()){
            int index_first =0;
            do {
                    String text = fileContents.substring(index_first,extractor.GetStart());
                    String equation = fileContents.substring(extractor.GetStart(),extractor.GetEnd());

                    myJsonElementList.add(new MyJsonElement(text));
                    myJsonElementList.add(new MyJsonElement(equation));

                    index_first=extractor.GetEnd();
            }while(extractor.GoToNext());
            myJsonElementList.add((new MyJsonElement(fileContents.substring(index_first,fileContents.length()))));
        }
        else{
            myJsonElementList.add(new MyJsonElement(fileContents));
        }





        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(fileName+".json"),myJsonElementList);

    }
}