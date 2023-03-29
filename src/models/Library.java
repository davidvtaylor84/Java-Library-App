package models;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigDecimal;
import java.time.*;
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

    public void addBook(Book book) {
        bookCollection.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addStaffMember(Staff staff) {
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

    public boolean login() {
        boolean loginSuccess = false;
        while (!loginSuccess) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("STAFF ID:");
            String inputID = scanner.nextLine();
            System.out.print("PASSWORD:");
            String inputPassword = scanner.nextLine();
            System.out.println("");
            for (Staff staff : libraryStaff) {
                if (staff.getStaffID().equals(inputID) && staff.getStaffPassword().equals(inputPassword)) {
                    loginSuccess = true;
                    break;
                }
            }
            if (!loginSuccess) {
                System.out.println("Incorrect LOGIN or PASSWORD. Please try again...");
                System.out.println("");
            }
        }
        return loginSuccess;
    }

    public void optionPage() {
        System.out.println("-----------------------------------------------------");
        System.out.println("-                                                   -");
        System.out.println("-         FAR SKY 11 SCIENCE-FICTION LIBRARY        -");
        System.out.println("-                                                   -");
        System.out.println("-----------------------------------------------------");
        System.out.println("1. Checkout Book");
        System.out.println("2. Library Collection");
        System.out.println("3. Add New Member");
        System.out.println("4. Edit Member Details");
        System.out.println("5. Return Books");
        System.out.println("6. List Staff");
        System.out.println("7. Logout");
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
                editMemberDetails();
                break;
            case 5:
                returnBooks();
                break;
            case 6:
                listStaff();
                break;
            case 7:
                login();
                break;
            default:
                System.out.println("Please select a number on the list of options");
                optionPage();
        }
    }

    private void listStaff() {
        System.out.println(" ");
        System.out.println("-------------------");
        System.out.println("STAFF LIST");
        System.out.println("--------------------");
        for (Staff staff : libraryStaff) {
            System.out.println(staff);
        }
        System.out.println(" ");
        System.out.print("To exit, press q: ");
        Scanner scanner = new Scanner(System.in);
        String quit = scanner.nextLine();
        if (quit.equals("q")) {
            optionPage();
        }
    }

    private void editMemberDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Input Member ID:");
        String nameOrId = scanner.nextLine();
        if (nameOrId.equals("?")) {
            memberSearch();
            System.out.print("Input Number Selection: ");
            String memberSelection = scanner.nextLine();
            if (memberSelection.equals("q")) {
                optionPage();
            } else {
                int convertedSelection = Integer.parseInt(memberSelection);
                for (Member member : members) {
                    if (convertedSelection <= members.size() + 1 && convertedSelection > 0) {
                        editMemberDetails2(members.get(convertedSelection - 1).getLibraryID());
                    } else {
                        System.out.println("Member ID not recognised");
                    }
                }
            }
        } else {
            editMemberDetails2(nameOrId);
        }
    }

    private void editMemberDetails2(String nameOrId) {
        for (Member member : members) {
            if (nameOrId.equals(member.getLibraryID())) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("What do you wish to edit or q to quit?");
                    System.out.println("1. Surname, 2. First Name, 3. Address, 4. Postcode, 5. Email, 6. Notes: ");
                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        System.out.print("Surname: ");
                        String surname = scanner.nextLine();
                        member.setSurname(surname);
                        System.out.println(member);
                    } else if (input.equals("2")) {
                        System.out.print("First Name: ");
                        String firstname = scanner.nextLine();
                        member.setFirstName(firstname);
                        System.out.println(member);
                    } else if (input.equals("3")) {
                        System.out.print("Address: ");
                        String address = scanner.nextLine();
                        member.setAddress(address);
                        System.out.println(member);
                    } else if (input.equals("4")) {
                        System.out.print("Postcode: ");
                        String postcode = scanner.nextLine();
                        member.setPostcode(postcode);
                        System.out.println(member);
                    } else if (input.equals("5")) {
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        member.setEmail(email);
                        System.out.println(member);
                    } else if (input.equals("6")) {
                        System.out.print("Notes: ");
                        String notes = scanner.nextLine();
                        member.setNotes(notes);
                        System.out.println(member);
                    } else {
                        optionPage();
                        break;
                    }
                }
            } else {
                System.out.println("ID not recognised");
                optionPage();
            }
        }
    }

    private void returnBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("--------------------");
        System.out.println("RETURN BOOKS");
        System.out.println("--------------------");
        while (true) {
            System.out.print("Enter Book ISBN or q to quit: ");
            String isbn = scanner.nextLine();
            if (isbn.equals("q")) {
                optionPage();
                break;
            }
            boolean check = false;
            for (Book book : bookCollection) {
                if (isbn.equals(book.getIsbn())) {
                    book.setCheckedOut(false);
                    for (Member member : members) {
                        if (member.getCheckedOutBooks().contains(book)) {
                            member.getCheckedOutBooks().remove(book);
                            System.out.println(member);
                            System.out.println("Book Successfully Returned");
                            check = true;
                        }
                    }
                }
            }
            if (!check) {
                System.out.println(" ");
                System.out.println("ISBN not recognised");
            }
        }
    }

    private void staffList() {
    }

    private void addNewMember() {
        System.out.println(" ");
        System.out.println("--------------------");
        System.out.println("ADD NEW MEMBER");
        System.out.println("--------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add new member?(y/n)");
        String input = scanner.nextLine();
        if (input.equals("y")) {
            System.out.println(" ");
            System.out.print("First name: ");
            String firstName = scanner.nextLine();
            System.out.print("Surname: ");
            String surname = scanner.nextLine();
            System.out.print("Date of Birth (format dd/mm/yyyy): ");
            String dob = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Postcode: ");
            String postcode = scanner.nextLine();
            System.out.print("email: ");
            String email = scanner.nextLine();
            System.out.print("Notes: ");
            String notes = scanner.nextLine();
            System.out.println("--------------------------------");
            System.out.print("Is the above information correct?(y/n)");
            String correct = scanner.nextLine();
            if (correct.equals("y")) {
                String libraryId = libraryIdGenerator();
                members.add(new Member(firstName, surname, dob, address, postcode, email, libraryId, notes));
                System.out.println(members.get(members.size() - 1));
                System.out.println(" ");
                System.out.print("Press any key to exit: ");
                String exit = scanner.nextLine();
                if (exit.equals("q")) {
                    optionPage();
                } else {
                    optionPage();
                }
            } else {
                addNewMember();
            }

        } else {
            optionPage();
        }
    }

    private void checkoutBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("---------------------------");
        System.out.println("CHECKOUT TITLE");
        System.out.println("---------------------------");
        System.out.print("Input Member ID:");
        String nameOrId = scanner.nextLine();
        if (nameOrId.equals("?")) {
            memberSearch();
            System.out.print("Input ID Number: ");
            String memberSelection = scanner.nextLine();
            if (memberSelection.equals("q")) {
                optionPage();
            } else {
                boolean check = false;
                for (Member member : members) {
                    if (member.getLibraryID().equals(memberSelection)) {
                        check = true;
                        checkout2(member);
                    }
                }
                if (!check) {
                    System.out.println("Member ID not recognised");
                }
            }
        } else {
            for (Member member : members) {
                if (member.getLibraryID().equals(nameOrId)) {
                    checkout2(member);
                }
            }
        }
    }


    public void checkout2(Member member) {
        Scanner scanner = new Scanner(System.in);
//        for (Member member : members) {
//                if (id.equals(member.getLibraryID())) {
        System.out.println(member);
        if (member.getFines() >= 3.0) {
            payFines(member);
        }
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
                            System.out.println("BOOK IS ALREADY CHECKED OUT");
                            for (Book copy : bookCollection) {
                                if (isbn.equals(copy.getIsbn())) {
                                    System.out.println("Due back on " + copy.getDueBack());
                                    System.out.println("__________________________________");
                                }
                            }
                            checkout2(member);
                        }
                    }
                }
            } else {
                System.out.println("ISBN not recognised");
                optionPage();
                break;
            }
        }
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
                System.out.println("\n"+ members.get(i).toString());
                count++;
            }
        }
        if(count==0){
            System.out.println("Name not recognised");
        }
    }

    public void bookCollection(){
        System.out.println(" ");
        System.out.println("---------------------------------------------------");
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
                searchTitles();
                break;
            case 2:
                addBookToCollection();
                break;
            case 3:
                listAll();
                break;
            case 4:
                overdueBooks();
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
        boolean checkedOut = false;
        System.out.println("");
        System.out.println("ISBN: "+ isbn);
        System.out.println("TITLE: "+title);
        System.out.println("AUTHOR SURNAME: "+surname);
        System.out.println("AUTHOR FIRSTNAME: "+firstname);
        System.out.println("PUBLISHER: "+publisher);
        System.out.println("SUMMARY: "+summary);
        System.out.println(" ");
        System.out.println("Are all these details correct (y/n): ");
        String areTheseCorrect = scanner.nextLine();
        if(areTheseCorrect.equals("y")){
            bookCollection.add(new Book(title, surname, firstname, isbn, publisher, checkedOut, summary));
            System.out.println("Book has been added");
            bookCollection();
        } else {
            System.out.println("Book has not been added");
            bookCollection();
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
        System.out.println(" ");
        System.out.println("----------------");
        System.out.println("DELETE BOOK");
        System.out.println("--------------");
        System.out.print("Enter ISBN to be deleted or q to go back: ");
        String input = scanner.nextLine();
        for(Book book : bookCollection){
            if(input.equals(book.getIsbn())){
                System.out.println(book);
                System.out.println("");
                System.out.print("Are you sure you want to delete this title? (y/n)");
                String confirm = scanner.nextLine();
                if(confirm.equals("y")){
                    bookCollection.remove(book);
                    System.out.println("");
                    System.out.println("DELETION CONFIRMED");
                    bookCollection();
                } else {
                    bookCollection();
                }
            }
        }
        System.out.println("INCORRECT ISBN");
        bookCollection();
    }

    public void searchTitles(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("SEARCH TITLES");
        System.out.println("----------------------");
        System.out.println("1. Search by ISBN");
        System.out.println("2. Search by title");
        System.out.println("3. Search by author");
        System.out.println("4. Search by publisher");
        System.out.print("Please select option number or q to quit: ");
        String option = scanner.nextLine();
        if(option.equals("1")){
            System.out.print("ENTER ISBN: ");
            String isbn = scanner.nextLine();
            isbnSearch(isbn);
        } else if (option.equals("2")) {
            System.out.print("ENTER TITLE: ");
            String name = scanner.nextLine();
            bookTitleSearch(name);
        } else if(option.equals("3")){
            System.out.print("ENTER AUTHOR SURNAME: ");
            String author = scanner.nextLine();
            authorNameSearch(author);
        } else if (option.equals("4")) {
            System.out.println("ENTER PUBLISHER: ");
            String publisher = scanner.nextLine();
            publisherSearch(publisher);
        } else {
            bookCollection();
        }
    }

    public void isbnSearch(String isbn){
        List<Book> isbnList = bookCollection.stream()
                .filter(book -> isbn.equals(book.getIsbn()))
                .collect(Collectors.toList());
        isbnList.forEach(System.out::println);
        searchTitles();
    }

    public void bookTitleSearch(String title){
        List<Book> titleList = bookCollection.stream()
                .filter(book -> title.equals(book.getTitle()))
                .collect(Collectors.toList());
        titleList.forEach(System.out::println);
        searchTitles();
    }

    public void authorNameSearch(String name){
        List<Book> authorList = bookCollection.stream()
                .filter(book -> name.equals(book.getAuthorSurname()))
                .collect(Collectors.toList());
        authorList.forEach(System.out::println);
        searchTitles();
    }

    public void publisherSearch(String publisher){
        List<Book> publisherList = bookCollection.stream()
                .filter(book -> publisher.equals(book.getPublisher()))
                .collect(Collectors.toList());
        publisherList.forEach(System.out::println);
        searchTitles();
    }

    public void overdueBooks() {
        System.out.println("--------------------");
        System.out.println("OVERDUE TITLES");
        System.out.println("--------------------");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate today  = LocalDate.now();
        for(Book book : bookCollection){
            if(book.getDueBack() != null) {
                LocalDate dueDate = LocalDate.parse(book.getDueBack(), formatter);
                if (today.isAfter(dueDate)) {
                    System.out.println("Title: "+book.getTitle()+"\nISBN: "+book.getIsbn());
                    System.out.println(book.loanHistory());
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press any key to exit: ");
        String input = scanner.nextLine();
        if(input.equals("q")){
            bookCollection();
        } else {
            bookCollection();
        }
    }

    public String libraryIdGenerator(){
        Random rnd = new Random();
        int number = rnd.nextInt(99999999);
        return String.format("%08d", number);
    }

    public double fineCalculator(LocalDate dueDate, LocalDate today){
        Period period = Period.between(dueDate, today);
        int days = period.getDays();
        return 1.5 * days;
    }

    public void overdueFine() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate today = LocalDate.now();
        for (Book book : bookCollection) {
            if (book.getDueBack() != null) {
                LocalDate dueDate = LocalDate.parse(book.getDueBack(), formatter);
                if (today.isAfter(dueDate)) {
                    for (Member member : members) {
                        if (member.listOfCheckedOutBooks().contains(book.getIsbn())) {
                            member.setFines(fineCalculator(dueDate, today));
                        }
                    }
                }
            }
        }
    }

    public void payFines(Member member){
        Scanner scanner = new Scanner(System.in);
        System.out.println("CANNOT CHECKOUT BOOK UNLESS OVERDUE FEES ARE UNDER £3");
        System.out.println(" ");
        System.out.print("Pay outstanding fees?(y/n)");
        String input = scanner.nextLine();
        if(input.equals("y")){
            System.out.println(" ");
            System.out.print("Current balance is £"+member.getFines()+". Please enter amount paid today: ");
            double amount = scanner.nextDouble();
            member.setFines(member.getFines()-amount);
            System.out.println("Current balance is now "+member.getFines());
            checkout2(member);
        } else {
            optionPage();
        }
    }

}
