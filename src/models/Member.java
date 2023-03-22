package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Member {

    private String firstName;
    private String surname;
    private String DOB;
    private String address;
    private String postcode;
    private String email;
    private String libraryID;
    private List<Book> checkedOutBooks;
    private String notes;
    private double fines;


    public Member(String firstName, String surname, String DOB, String address, String postcode, String email, String libraryID, String notes) {
        this.firstName = firstName;
        this.surname = surname;
        this.DOB = DOB;
        this.address = address;
        this.postcode = postcode;
        this.email = email;
        this.libraryID = libraryID;
        this.checkedOutBooks = new ArrayList<>();
        this.notes = notes;
        this.fines = 0.00;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(String libraryID) {
        this.libraryID = libraryID;
    }

    public void addCheckedOutBooks(Book book) {
        this.checkedOutBooks.add(book);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String toString(){
        return "\nID: "+libraryID+" \nNAME: "+firstName+" "+surname+"\nADDRESS: "+address+", "+postcode+"\nEMAIL: "+email+"\nFINES: £"+fines+"\nNOTES: "+notes+"\n\nCHECKED-OUT TITLES:\n"+listOfCheckedOutBooks()+"\n";
    }

    public String listOfCheckedOutBooks(){
        StringBuilder list = new StringBuilder();
        if(checkedOutBooks.isEmpty()){
            return "NO BOOKS CHECKED OUT";
        }
        for (Book checkedOutBook : checkedOutBooks) {
            list.append(checkedOutBook.getIsbn() + " | " + checkedOutBook.getTitle() + " | "+checkedOutBook.getAuthorSurname()+" | Due back on " + checkedOutBook.getDueBack() + "\n");
        }
        return list.toString();
    }
}
