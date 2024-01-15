
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;


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



    static Deque <queue_parse_class> commandDeque = new ArrayDeque<>();
    static FileConverter globalFileConverter = new FileConverter();



    static void startConversion() throws JAXBException, IOException {


        for (queue_parse_class qpc: commandDeque
             ) {
        MyFile fileFormat = qpc.getFile();

        switch (qpc.mode){
            case 1: {
                globalFileConverter.ConvertFrom(fileFormat);
                break;
            }

            case 2:{
                globalFileConverter.ConvertInto(fileFormat);
                break;
            }
        }


        }
    }

    public static void main(String[] args) throws IOException, JAXBException {

        String helpData = "\n####################\n" +
                "h - display help data\n" +
                "d - show queue\n" +
                "f - get current file name\n" +
                "x - remove last queue command\n" +
                "c - compute\n" +
                "z - zip\n" +
                "j - json\n" +
                "m - xml\n" +
                "1 - convert from\n" +
                "2 - convert into\n" +
                "s - start conversion\n" +
                "q - end work\n####################\n";
        System.out.println(helpData);

        System.out.print("filename = ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();

        fileName = "MainTest.txt";


        globalFileConverter.setFileName(fileName);
        //add commands to queue

        scanner.nextLine();
        boolean stopAll = false;
        while(!stopAll){

            System.out.print("next command: ");
            String input = scanner.nextLine();

            if(input.matches("[a-z]")){
                switch (input.charAt(0)){
                    case 'h':{
                        System.out.println(helpData);
                        break;
                    }

                    case 's':{
                        startConversion();
                        break;
                    }

                    case 'x':{
                        if(!commandDeque.isEmpty()) {
                            commandDeque.removeLast();
                        }
                        else{
                            System.out.println("Error: command deque is already empty");
                        }
                        break;
                    }

                    case 'c':{
                        globalFileConverter.compute();
                    }

                    case 'f':{
                        System.out.println(globalFileConverter.fileName);
                        break;
                    }

                    case 'd':{
                        System.out.println("");
                        if (commandDeque.isEmpty()){
                            System.out.println("command queue is empty!");
                        }
                        else{
                            for (queue_parse_class d : commandDeque
                            ) {
                                if (d.getMode() == 1) {
                                    System.out.print("\nconvert from ");
                                } else if (d.getMode() == 2) {
                                    System.out.print("\nconvert into ");
                                }

                                System.out.print(d.getFile().getClass().getName());
                            }
                            System.out.println("");
                        }
                        break;
                    }

                    case 'q':{
                        stopAll = true;
                        break;
                    }
                }

            } else if (input.matches("[12] [jzm]")) {
                queue_parse_class a = new queue_parse_class();

                a.setMode(Integer.parseInt(input.substring(0, 1)));

                switch (input.charAt(2)) {

                    case 'z': {
                        a.setFile(new MyZipFile());
                        break;
                    }

                    case 'j': {
                        a.setFile(new MyJSONFile());
                        break;
                    }

                    case 'm':{
                        a.setFile(new MyXMLFile());
                        break;
                    }


                }

                commandDeque.addLast(a);
            }
            else {
                System.out.println("invalid character");
            }


        }








    }



    }