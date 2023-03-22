package models;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Library {


    private List<Book> bookCollection;
    private List<Staff> libraryStaff;
    private List<Member> members;

    public Library() {
        this.bookCollection = new ArrayList<>();
        this.libraryStaff = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    public void addBook(Book book){
        bookCollection.add(book);
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void addStaffMember(Staff staff){
        libraryStaff.add(staff);
    }

    public void setBookCollection(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    public List<Staff> getLibraryStaff() {
        return libraryStaff;
    }

    public void setLibraryStaff(List<Staff> libraryStaff) {
        this.libraryStaff = libraryStaff;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public boolean login(){
        boolean loginSuccess = false;
        while (!loginSuccess){
            Scanner scanner = new Scanner(System.in);
            System.out.print("STAFF ID:");
            String inputID = scanner.nextLine();
            System.out.print("PASSWORD:");
            String inputPassword = scanner.nextLine();
            System.out.println("");
            for(Staff staff : libraryStaff){
                if(staff.getStaffID().equals(inputID) && staff.getStaffPassword().equals(inputPassword)){
                    loginSuccess=true;
                    break;
                }
            }
            if(!loginSuccess){
                System.out.println("Incorrect LOGIN or PASSWORD. Please try again...");
                System.out.println("");
            }
        }
        return loginSuccess;
    }

    public void optionPage(){
        System.out.println("");
        System.out.println("FAR SKY 11 SCIENCE-FICTION LIBRARY");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Checkout book");
        System.out.println("2. Book collection");
        System.out.println("3. Add New Member");
        System.out.println("4. Staff List");
        System.out.println("5. Return books");
        System.out.println("6. Logout");
        System.out.println("----------------------------------------------------");
        System.out.print("Please select a number from the above options:");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                checkoutBook();
                break;
            case 2:
                bookCollection();
                break;
            case 3:
                addNewMember();
                break;
            case 4:
                staffList();
                break;
            case 5:
                returnBooks();
                break;
            case 6:
                login();
                break;
            default:
                System.out.println("Please select a number on the list of options");
                optionPage();
        }
    }

    private void returnBooks() {
    }

    private void staffList() {
    }

    private void addNewMember() {
    }

    private void checkoutBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Input Member ID:");
        String nameOrId = scanner.nextLine();
        if(nameOrId.equals("?")){
            memberSearch();
            System.out.print("Input id: ");
            String memberSelection = scanner.nextLine();
            if(memberSelection.equals("q")){
                optionPage();
            }
        for (Member member : members) {
            if (memberSelection.equals(member.getLibraryID()) || nameOrId.equals(member.getLibraryID())) {
                System.out.println(member);
                while (true) {
                    System.out.println(" ");
                    System.out.print("Checkout Book (y/n): ");
                    String checkoutYorN = scanner.nextLine();
                    if (checkoutYorN.equals("y")) {
                        System.out.print("Enter ISBN or Q to quit: ");
                        String isbn = scanner.nextLine();
                        if (isbn.equals("Q")) {
                            optionPage();
                            break;
                        }
                        for (Book book : bookCollection) {
                            if (isbn.equals(book.getIsbn())) {
                                if (!book.isCheckedOut()) {
                                    LocalDate today = LocalDate.now();
                                    LocalDate twoWeekLoan = today.plusDays(14);
                                    DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
                                    member.addCheckedOutBooks(book);
                                    book.setDueBack(twoWeekLoan.format(dtf));
                                    book.setCheckedOut(true);
                                    book.addLoanHistory(today.format(dtf), member);
                                    System.out.println("");
                                    System.out.println(member);
                                } else {
                                    System.out.println("Book is already checked out");
                                }
                            }
                        }
                    } else {
                        System.out.println("ISBN not recognised");
                        optionPage();
                        break;
                    }
                }
            } else {
                System.out.println("Member ID not recognised");
            }
        }}
    }

    public void memberSearch(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Enter Member's surname:");
        String inputtedSurname = scanner.nextLine();
        int count = 0;
        for(int i = 0; i<members.size(); i++){
            String surname = members.get(i).getSurname();
            if(inputtedSurname.equals(surname)){
                System.out.println(members.get(i).toString());
                count++;
            }
        }
        if(count==0){
            System.out.println("Name not recognised");
        }
    }

    public void bookCollection(){
        System.out.println("");
        System.out.println("COLLECTION");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Search Titles");
        System.out.println("2. Add Book to Collection");
        System.out.println("3. List All Titles");
        System.out.println("4. List All Overdue Books");
        System.out.println("5. Delete Book");
        System.out.println("6. Back");
        System.out.println("----------------------------------------------------");
        System.out.print("Please select a number from the above options:");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                checkoutBook();
                break;
            case 2:
                addBookToCollection();
                break;
            case 3:
                listAll();
                break;
            case 4:
                staffList();
                break;
            case 5:
                deleteBook();
                break;
            case 6:
                optionPage();
                break;
            default:
                System.out.println("Please select a number on the list of options");
                optionPage();
        }
    }

    public void addBookToCollection(){
        System.out.println("");
        System.out.println("ADD BOOK TO COLLECTION");
        System.out.println("------------------------");
        System.out.println("Enter details below");
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("TITLE: ");
        String title = scanner.nextLine();
        System.out.print("AUTHOR SURNAME: ");
        String surname = scanner.nextLine();
        System.out.print("AUTHOR FIRSTNAME: ");
        String firstname = scanner.nextLine();
        System.out.print("PUBLISHER: ");
        String publisher = scanner.nextLine();
        System.out.print("SUMMARY: ");
        String summary = scanner.nextLine();
        System.out.print("NO OF COPIES: ");
        int noOfCopies = scanner.nextInt();
        boolean checkedOut = false;
        bookCollection.add(new Book(title, surname, firstname, isbn, publisher, checkedOut, noOfCopies, summary));
        System.out.println("");
        System.out.println(bookCollection.get(bookCollection.size()-1));
        System.out.print("Are all these details correct (y/n): ");
        String correct = scanner.nextLine();
        if(correct.equals("y")){
            bookCollection();
        } else {
            bookCollection.remove(bookCollection.get(bookCollection.size()-1));
            addBookToCollection();
        }
    }

    public void listAll(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.print("LIST ALL BOOKS BY (1)AUTHOR, (2)TITLE OR (3)PUBLISHER OR (q) TO GO BACK: ");
        String input = scanner.nextLine();
        if(input.equals("1")){
            listAllTitlesByAuthorSurname();
        }else if(input.equals("2")) {
            listTitlesAlphabetically();
        } else if(input.equals("3")){
            listByPublisher();
        } else{
            bookCollection();
        }
    }

    public void listAllTitlesByAuthorSurname(){
        List<Book> sortedByAuthor = bookCollection.stream()
                .sorted(Comparator.comparing(Book::getAuthorSurname))
                .collect(Collectors.toList());
        sortedByAuthor.forEach(System.out::println);
        listAll();
    }

    public void listTitlesAlphabetically(){
        List<Book> listTitles = bookCollection.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
        listTitles.forEach(System.out::println);
        listAll();
    }

    public void listByPublisher(){
        List<Book> listOfBooks = bookCollection.stream()
                .sorted(Comparator.comparing(Book::getPublisher))
                .collect(Collectors.toList());
        listOfBooks.forEach(System.out::println);
        listAll();
    }

    public void deleteBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("DELETE BOOK");
        System.out.println("--------------");
        System.out.print("Enter title or ISBN to be deleted or q to go back: ");
        String input = scanner.nextLine();
        for(Book book : bookCollection){
            if(input.equals(book.getIsbn()) || input.equals(book.getTitle())){
                System.out.println(book);
                System.out.println("");
                System.out.print("Are you sure you want to delete this title? (y/n)");
                String confirm = scanner.nextLine();
                if(confirm.equals("y")){
                    bookCollection.remove(book);
                    System.out.println("");
                    System.out.println("DELETION CONFIRMED");
                } else {
                    bookCollection();
                }
            } else if (
                input.equals("q")
            ) {
                bookCollection();
            } else {
                System.out.println("Input not recognised");
            }
        }
    }


//            System.out.println("4. Add Staff Login");
//        System.out.println("Search titles");
//        System.out.println("Delete books");
//        System.out.println("Update book");
//        System.out.println("Display all titles");
//        System.out.println("List overdue books");
}
