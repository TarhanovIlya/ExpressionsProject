
import  javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static class queue_parse_class{
        private Integer mode;
        private MyFile file;

        public void setMode(int mode) {
            this.mode = mode;
        }

        public void setFile(MyFile file) {
            this.file = file;
        }

        public int getMode() {
            return mode;
        }

        public MyFile getFile() {
            return file;
        }
    }


    public static void main(String[] args) throws IOException, JAXBException {
//
//        String helpData = "\n####################\nh - display help data\nd - show queue\nx - cancel last command\nz - zip\nj - json\n1 - convert from\n2 - convert into\ns - start conversion\nq - end work\n####################\n";
//        System.out.println(helpData);
//
//        //System.out.print("filename = ");
//        Scanner scanner = new Scanner(System.in);
//        //String fileName = scanner.next();
//
//
//        //add commands to queue
//        Deque <queue_parse_class> commandDeque = new ArrayDeque<>();
//
//        boolean stopAll = false;
//        while(!stopAll){
//
//            System.out.print("next command: ");
//            String input = scanner.nextLine();
//
//            if(input.matches("[a-z]")){
//                switch (input.charAt(0)){
//                    case 'h':{
//                        System.out.println(helpData);
//                        break;
//                    }
//
//                    case 's':{
//                        startConversion();
//                        break;
//                    }
//
//                    case 'x':{
//                        if(!commandDeque.isEmpty()) {
//                            commandDeque.removeLast();
//                        }
//                        else{
//                            System.out.println("Error: command deque is already empty");
//                        }
//                        break;
//                    }
//
//                    case 'd':{
//                        System.out.println("");
//                        for (queue_parse_class d: commandDeque
//                             ) {
//                            if(d.getMode() == 1){
//                                System.out.print("\nconvert from ");
//                            }else if(d.getMode() == 2){
//                                System.out.print("\nconvert into ");
//                            }
//
//                            System.out.print(d.getFile().getClass().getName());
//                        }
//                        System.out.println("");
//                        break;
//                    }
//
//                    case 'q':{
//                        stopAll = true;
//                        break;
//                    }
//                }
//
//            } else if (input.matches("[12] [a-z]")) {
//                queue_parse_class a = new queue_parse_class();
//
//                a.setMode(Integer.parseInt(input.substring(0,1)));
//
//                switch (input.charAt(2)){
//
//                    case'z':{
//                        a.setFile(new MyZipFile());
//                        break;
//                    }
//
//                    case'j':{
//                        a.setFile(new MyJSONFile());
//                        break;
//                    }
//                }
//
//                commandDeque.addLast(a);
//            }
//
//
//        }
//
//
//

        FileConverter converter = new FileConverter("testFile.txt.json.xml");
        converter.ConvertFrom(new MyXMLFile());
    }

    static void startConversion(){

    }
}