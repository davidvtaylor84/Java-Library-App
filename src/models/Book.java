package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {

    private String title;
    private String authorSurname;
    private String authorFirstname;
    private String isbn;
    private String publisher;
    private boolean checkedOut;
    private HashMap<String, Member> loanHistory;
    private String summary;

    private String dueBack;

    public Book(String title, String authorSurname, String authorFirstname, String isbn, String publisher, boolean checkedOut, String summary) {
        this.title = title;
        this.authorSurname = authorSurname;
        this.authorFirstname = authorFirstname;
        this.isbn = isbn;
        this.publisher = publisher;
        this.checkedOut = checkedOut;
        this.summary = summary;
        this.loanHistory = new HashMap<>();
        this.dueBack = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public HashMap<String, Member> getLoanHistory() {
        return loanHistory;
    }

    public void setLoanHistory(HashMap<String, Member> loanHistory) {
        this.loanHistory = loanHistory;
    }

    public void addLoanHistory(String date, Member member){
        loanHistory.put(date, member);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDueBack() {
        return dueBack;
    }

    public void setDueBack(String dueBack) {
        this.dueBack = dueBack;
    }

    public String toString(){
        return "\nISBN: "+isbn+"\nTITLE: "+title+"\nAUTHOR: "+authorSurname+", "+authorFirstname+"\nPUBLISHER: "+publisher+"\nSUMMARY: "+summary+"\nCHECKED OUT: "+checkedOut+"\nDUE BACK DATE: "+dueBack+"\n\nLOAN HISTORY:\n"+loanHistory()+"\n";
    }

    public String loanHistory(){
        if(loanHistory.isEmpty()){
            return "Book has not yet been checked out";
        }
        for (Map.Entry<String, Member> entry : loanHistory.entrySet()) {
            String key = entry.getKey();
            Member member = entry.getValue();
            return "Due Date: "+key+"\nMember ID: "+member.getLibraryID()+"\nMember Name: "+member.getSurname()+", "+member.getFirstName()+"\nemail: "+member.getEmail()+"\nFines: £"+member.getFines()+"\n";
        }
        return null;
    }
}
