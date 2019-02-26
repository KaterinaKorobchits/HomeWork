
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    private static ArrayList<MyClass> listOfMyClasses = new ArrayList<>();
    private static ArrayList<Person> listOfPersons = new ArrayList<>();
    static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder builder = null;
    static TransformerFactory transformerFactory = TransformerFactory.newInstance();

    public static void main(String[] args) {
        firstPart();
        secondPartOne();
        secondPartTwo();
    }

    public static void firstPart() {
        listOfMyClasses.clear();
        listOfPersons.clear();
        try {
            readXMLThroughURlConnectionToFile("https://goo.gl/AZnd2V", "note2.txt");
            readXMLThroughURlConnectionToFile("https://goo.gl/tFpBDV", "note1.txt");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MySAXParsFirst handler1 = new MySAXParsFirst();
            MySAXParsSecond handler2 = new MySAXParsSecond();
            parser.parse("note1.txt", handler1);
            parser.parse("note2.txt", handler2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(listOfMyClasses.toString());
        for(Person person: listOfPersons)
            System.out.println(person.toString());
    }

    public static void readXMLThroughURlConnectionToFile(String urlAddress, String fileName) throws IOException {
        URL url = new URL(urlAddress);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             FileWriter write = new FileWriter(fileName)) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
            write.write(stringBuilder.toString().replaceAll("  ", ""));
        }
    }

    private static class MySAXParsFirst extends DefaultHandler {

        MyClass myClassLast;
        MyMethod myMethodLast;

        @Override
        public void startDocument() {
            System.out.println("Start document");
        }


        @Override
        public void endDocument() {
            System.out.println("End document");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("class") && attributes.getValue("name") != null)
                myClassLast = new MyClass(attributes.getValue("name"));
            if (qName.equals("method") && attributes.getValue("name") != null)
                myMethodLast = new MyMethod(attributes.getValue("name"));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("class"))
                listOfMyClasses.add(myClassLast);
            if (qName.equals("method"))
                myClassLast.addMethod(myMethodLast);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            myMethodLast.setValue(new String(ch, start, length));
        }
    }

    private static class MySAXParsSecond extends DefaultHandler {

        Person person;
        Student studentLast;
        Professor professorLast;
        ServicePersonal servicePersonalLasct;

        @Override
        public void startDocument() {
            System.out.println("Start document");
        }


        @Override
        public void endDocument() {
            System.out.println("End document");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("student") && attributes.getLength() != 0)
                for (int i = 0; i < attributes.getLength(); i++) {
                    switch (attributes.getQName(i)) {
                        case "name":
                            studentLast = new Student(attributes.getValue(i));
                            break;
                        case "course":
                            studentLast.setCourse(Integer.parseInt(attributes.getValue(i)));
                            break;
                        case "specialization":
                            studentLast.setSpecialization(attributes.getValue(i));
                            break;
                        default:
                            break;
                    }
                }
            if (qName.equals("professor") && attributes.getLength() != 0)
                for (int i = 0; i < attributes.getLength(); i++) {
                    switch (attributes.getQName(i)) {
                        case "name":
                            professorLast = new Professor(attributes.getValue(i));
                            break;
                        case "experience":
                            professorLast.setExperience(attributes.getValue(i));
                            break;
                        case "discipline":
                            professorLast.setDiscipline(attributes.getValue(i));
                            break;
                        default:
                            break;
                    }
                }
            if (qName.equals("member") && attributes.getLength() != 0)
                for (int i = 0; i < attributes.getLength(); i++) {
                    switch (attributes.getQName(i)) {
                        case "name":
                            servicePersonalLasct = new ServicePersonal(attributes.getValue(i));
                            break;
                        case "position":
                            servicePersonalLasct.setPosition(attributes.getValue(i));
                            break;
                        default:
                            break;
                    }
                }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("student"))
                listOfPersons.add(studentLast);
            if (qName.equals("professor"))
                listOfPersons.add(professorLast);
            if (qName.equals("member"))
                listOfPersons.add(servicePersonalLasct);
        }
    }

    public static void secondPartOne() {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement("application");
        doc.appendChild(rootElement);
        for (int i = 0; i < listOfMyClasses.size(); i++) {
            MyClass myClass = listOfMyClasses.get(i);
            Element classElement = doc.createElement("class");
            rootElement.appendChild(classElement);
            Attr attr = doc.createAttribute("name");
            attr.setValue(myClass.name);
            classElement.setAttributeNode(attr);
            for (int k = 0; k < myClass.listMethod.size(); k++) {
                MyMethod myMethod = myClass.listMethod.get(k);
                Element methodElement = doc.createElement("method");
                classElement.appendChild(methodElement);
                Attr attr1 = doc.createAttribute("name");
                attr1.setValue(myMethod.name);
                methodElement.setAttributeNode(attr1);
                if(myMethod.value != null) {
                    methodElement.appendChild(doc.createTextNode(myMethod.value));
                }

            }
        }
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("file1.xml"));
            //StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void secondPartTwo() {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement("database");
        doc.appendChild(rootElement);
        Element students = doc.createElement("students");
        rootElement.appendChild(students);
        Element professors = doc.createElement("professors");
        rootElement.appendChild(professors);
        Element service = doc.createElement("service");
        rootElement.appendChild(service);
        for (int i = 0; i < listOfPersons.size(); i++) {
            if(listOfPersons.get(i) instanceof Student) {
                Student st = (Student)listOfPersons.get(i);
                Element student = doc.createElement("student");
                students.appendChild(student);
                Attr name = doc.createAttribute("name");
                Attr course = doc.createAttribute("course");
                Attr specialization = doc.createAttribute("specialization");
                name.setValue(st.name);
                course.setValue(st.getCourse()+"");
                specialization.setValue(st.getSpecialization());
                student.setAttributeNode(name);
                student.setAttributeNode(course);
                student.setAttributeNode(specialization);
            }
            if(listOfPersons.get(i) instanceof Professor) {
                Professor pr = (Professor) listOfPersons.get(i);
                Element professor = doc.createElement("professor");
                professors.appendChild(professor);
                Attr name = doc.createAttribute("name");
                Attr experience = doc.createAttribute("experience");
                Attr discipline = doc.createAttribute("discipline");
                name.setValue(pr.name);
                experience.setValue(pr.getExperience());
                discipline.setValue(pr.getDiscipline());
                professor.setAttributeNode(name);
                professor.setAttributeNode(experience);
                professor.setAttributeNode(discipline);
            }
            if(listOfPersons.get(i) instanceof ServicePersonal) {
                ServicePersonal sp = (ServicePersonal) listOfPersons.get(i);
                System.out.println(sp.toString());
                Element member = doc.createElement("member");
                service.appendChild(member);
                Attr name = doc.createAttribute("name");
                Attr position = doc.createAttribute("position");
                name.setValue(sp.name);
                position.setValue(sp.getPosition());
                member.setAttributeNode(name);
                member.setAttributeNode(position);
            }
        }
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("file2.xml"));
            //StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
