package miu.edu.apps;


import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactMgmtApp {
    public static void main(String[] args) {
        Contact contacts [] = Contact.getData();
        printContacts(contacts);

    }

    static void printContacts(Contact[] contacts){
        List<Contact> contactList = Arrays.stream(contacts)
                        .sorted(Comparator.comparing(Contact::getName))
                .collect(Collectors.toList());
        printJSON(contactList);
        printXML(contactList);
        printCSV(contactList);
    }

    public static void printJSON(List<Contact> contacts){
        System.out.println("JSON format:\n"+new Gson().toJson(contacts));
    }

    public static void printCSV(List<Contact> contacts){
        System.out.println("CSV format\n");
        for (Contact contact : contacts) {
            System.out.printf("%s, %s, %s, %s, %s \n", contact.getContactId(),contact.getName(), contact.getDateSupplied(), contact.getQuantityInStock(), contact.getUnitPrice());
        }
    }

    public static void printXML(List<Contact> contacts){
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<Contacts>\n\t");
        for (Contact contact : contacts) {
            xmlBuilder.append("<Contact>\n\t");
            xmlBuilder.append(String.format("<contactId>%s</contactId/>", contact.getContactId()));
            xmlBuilder.append(String.format("<name>%s</name/>", contact.getName()));
            xmlBuilder.append(String.format("<dateSupplied>%s</dateSupplied/>", contact.getDateSupplied()));
            xmlBuilder.append(String.format("<quantityInStock>%s</quantityInStock/>", contact.getQuantityInStock()));
            xmlBuilder.append(String.format("<unitPrice>%s</unitPrice/>\n", contact.getUnitPrice()));
            xmlBuilder.append("</Contact>\n\t");
        }
        xmlBuilder.append("</Contacts>");
        System.out.println("XML format:\n"+xmlBuilder.toString());
    }

}
