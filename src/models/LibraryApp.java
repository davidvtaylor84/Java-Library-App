package models;

public class LibraryApp {

    public static void main(String[] args) {

        Library library = new Library();

        Book book1 = new Book("Red Mars", "Robinson", "Kim Stanley", "9788445000090", "Gollanz", false, 2, "Red Mars is the first novel of the Mars trilogy, published to great acclaim in 1992. It tells of the beginnings of the colonization of Mars, the beginnings of the efforts to terraform it and the tensions resulting from corporate forces from Earth exerting their influence on the developing Martian culture.");
        Book book2 = new Book("Lies Inc.", "Dick", "Philip K.", "9788445000897", "HarperCollins", false, 1, "A masterwork by Philip K. Dick, this is the final, expanded version of the novella The Unteleported Man, which Dick worked on shortly before his death. In Lies, Inc., fans of the science fiction legend will immediately recognize his hallmark themes of life in a security state, conspiracy, and the blurring of reality and illusion.");
        Book book3 = new Book("Dhalgren", "Delany", "Samuel", "9788445005843", "Verso", false, 3, "Dhalgren is a 1975 science fiction novel by American writer Samuel R. Delany. It features an extended trip to and through Bellona, a fictional city in the American Midwest cut off from the rest of the world by an unknown catastrophe.");
        Book book4 = new Book("The Thing Itself", "Roberts", "Adam", "9788445006754", "Compass", false, 1, "Two men while away the days in an Antarctic research station. Tensions between them build as they argue over a love-letter one of them has received. One is practical and open. The other surly, superior and obsessed with reading one book - by the philosopher Kant.");
        Book book5 = new Book("Solaris", "Lem", "Stanislaw", "9788445007635", "Compass", false, 2, "Solaris is a 1961 science fiction novel by Polish writer Stanisław Lem. It follows a crew of scientists on a research station as they attempt to understand an extraterrestrial intelligence, which takes the form of a vast ocean on the titular alien planet. The novel is one of Lem's best-known works.");
        Book book6 = new Book("Annihilation", "Vandermeer", "Jeff", "9788445007644", "pusssyfoot publishing", false, 1, "It is the first in a series of three books called the Southern Reach Trilogy. The book describes a team of four women (a biologist, an anthropologist, a psychologist, and a surveyor) who set out into an area known as Area X. The area is abandoned and cut off from the rest of civilization.");

        Staff philip = new Staff("Philip", "Jose Farmer", "ID7685", "youbetrippin86");
        Staff jessie = new Staff("Jessie", "Salamander", "ID8768", "clipthecutz");

        Member donovan = new Member("Donovan", "Cleary", "21/02/1984", "123 Fake Street, Edinburgh", "EH7 8HY", "ghosthorse@gmail.com", "13006838", "Customer has difficulty walking. May need assistance getting to toilet on first floor.");
        Member geena = new Member("Geena", "Davis", "23/07/1978", "42 Hellmouth Way, Glasgow", "GY7 8KJ", "meandyouandhimandher@yahoo.co.uk", "13449087", "It really is THAT Geena Davis from the movies.");

        book1.setDueBack("2023-01-22");
        book2.setDueBack("2022-12-10");

        book1.setCheckedOut(true);
        book2.setCheckedOut(true);

        book1.addLoanHistory("2023-01-22", geena);
        book2.addLoanHistory("2022-12-10", donovan);

        geena.addCheckedOutBooks(book1);
        donovan.addCheckedOutBooks(book2);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);

        library.addStaffMember(philip);
        library.addStaffMember(jessie);

        library.addMember(donovan);
        library.addMember(geena);

        if(library.login()){
            library.optionPage();
        } else {
            library.login();
        }
    }
}
