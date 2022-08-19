package cmsc256;

public class MyBook implements Comparable<MyBook> {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;

    public MyBook() {
        this.title = "None Given";
        this.authorFirstName = "None Given";
        this.authorLastName = "None Given";
        this.ISBN10 = "0000000000";
        this.ISBN13 = "0000000000000";
    }

    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13 ){
        setTitle(title);
        setAuthorFirstName(authorFirstName);
        setAuthorLastName(authorLastName);
        setISBN10(ISBN10);
        setISBN13(ISBN13);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null) {
            throw new IllegalArgumentException();
        }

        this.title = title;
    }

    public void setAuthorFirstName(String authorFirstName) throws IllegalArgumentException {
        if (authorFirstName == null) {
            throw new IllegalArgumentException();
        }

        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) throws IllegalArgumentException {
        if (authorLastName == null) {
            throw new IllegalArgumentException();
        }

        this.authorLastName = authorLastName;
    }

    public void setISBN10(String ISBN10) throws IllegalArgumentException, NumberFormatException {
        if (ISBN10 == null) {
            throw new IllegalArgumentException();
        }

        Long.parseLong(ISBN10);

        this.ISBN10 = ISBN10;

        if (ISBN10.length() != 10) {
            throw new IllegalArgumentException();
        }
    }

    public void setISBN13(String ISBN13) throws IllegalArgumentException, NumberFormatException {
        if (ISBN13 == null) {
            throw new IllegalArgumentException();
        }

        Long.parseLong(ISBN13);

        this.ISBN13 = ISBN13;

        if (ISBN13.length() != 13) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" + "Author: " + getAuthorFirstName() + " " + getAuthorLastName() + "\n" + "ISBN10: " + getISBN10() + "\n" + "ISBN13: " + getISBN13();
    }

    @Override
    public boolean equals(Object otherBook) {
        if(!(otherBook instanceof MyBook)) {
            return false;
        }

        MyBook other = (MyBook)otherBook;

        if(this == other) {
            return true;
        }

        if(this.ISBN10 == other.getISBN10()) {
            return true;
        }
        else if(this.ISBN13 == other.getISBN13()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(MyBook o) {
        int result = authorLastName.compareTo(o.getAuthorLastName());

        if(result == 0) {
            result = authorFirstName.compareTo(o.getAuthorFirstName());
        }

        if(result == 0) {
            result = title.compareTo(o.getTitle());
        }

        return result;
    }
}