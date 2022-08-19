package cmsc256;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;
import bridges.base.SLelement;


import java.util.ArrayList;
import java.util.NoSuchElementException;

/***********************************************************************************************************************************************************************************
 * SongList.java
 ************************************************************************************************************************************************************************************
 * project three - SongList
 ************************************************************************************************************************************************************************************
 * Project description
 * Songlist with linked list that has the list methods to use on the Songs from the api bridge
 * Kevin Phung
 * 3/18/2022
 * CMSC-256
 * Spring 2022
 *********************************************************************************************************************************/


public class SongList implements cmsc256.List<bridges.data_src_dependent.Song> {

    // implementing the list
    // instance variable of head, tail, current and a int of listsize
    private   SLelement<Song> head;
    private   SLelement<Song> tail;
    private   SLelement<Song> curr;
    private int listSize;

    // constructors
    public SongList(){
        clear();
        //credentials for bridge
        Bridges bridges = new Bridges(3, "kevinphung01", "777680704448");
        DataSource ds = bridges.getDataSource();

        // List of song to be stored
        ArrayList<Song> songData = null;

        // trying to connect to the bridge and get data
        try {
            songData = ds.getSongData();
        }
        catch (Exception e) {
            System.out.println("Unable to connect to Bridges.");
        }
        // append all the song data to populate the SLelement<Song>
        if (songData != null) {
            for (int i = 0; i < songData.size(); i++) {
                append(songData.get(i));
            }
        }

    }



    //ignore list size
    public SongList(int listSize) {
        this();
    }

    // remove all element and start anew
    public void clear(){
        curr = tail = new SLelement<>((SLelement<Song>) null); // Create trailer
        head = new  SLelement<Song>(tail);        // Create header
        listSize = 0;
    }

    // Insert "it" at current position
    @Override
    public boolean insert(Song it) {
        curr.setNext(new SLelement<>(curr.getValue(), curr.getNext())); // making a new SLelement for current in next
        curr.setValue(it); // setting that "it" to the current
        if (tail == curr) {
            tail = curr.getNext();  // New tail
        }
        listSize++;
        return true;
    }

    // Append "it" to list
    @Override
    public boolean append(Song it) {
        tail.setNext(new SLelement<Song>((SLelement<Song>) null)); // making a new SLelement for tail in next
        tail.setValue(it); // setting that "it" to the tail
        tail = tail.getNext();
        listSize++;
        return true;
    }

    // remove and return the current element
    @Override
    public Song remove()  {
        if (curr == tail) {// Nothing to remove
            return null;
        }

        Song it = curr.getValue();                  // Remember value
        curr.setValue(curr.getNext().getValue()); // Pull forward the next element
        if (curr.getNext() == tail) {
            tail = curr;   // Removed last, move tail
        }
        curr.setNext(curr.getNext().getNext());       // Point around unneeded link
        listSize--;                             // Decrement element count
        return it;                              // Return value
    }


    // moving to the start
    @Override
    public void moveToStart() {
        curr = head.getNext(); // Set curr at list start
    }

    // moving to the end
    @Override
    public void moveToEnd() {
        curr = tail; // Set curr at list end
    }

    // Move curr one step left; no change if now at front
    @Override
    public void prev() {
        if (head.getNext() == curr) {
            return; // No previous element
        }
        SLelement<Song> temp = head;
        // March down list until we find the previous element
        while (temp.getNext() != curr) {
            temp = temp.getNext();
        }
        curr = temp;
    }

    // Move curr one step right; no change if now at end
    @Override
    public void next() {
        if (curr != tail) {
            curr = curr.getNext();
        }
    }

    // returning the length
    @Override
    public int length() {
        return listSize;
    }

    // Return the position of the current element
    @Override
    public int currPos() {
        SLelement<Song> temp = head.getNext(); // temp would have the head
        int i;
        // make sure if it not in the head
        for (i=0; curr != temp; i++) {
            temp = temp.getNext();
        }
        return i;
    }

    // Move down list to "pos" position
    @Override
    public boolean moveToPos(int pos) {

        // position should be higher and lower than the listsize (false if not)
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }

        curr = head.getNext();
        for(int i=0; i<pos; i++) {
            curr = curr.getNext();
        }
        return true;
    }

    // Return true if current position is at end of the list
    @Override
    public boolean isAtEnd() {
        return curr == tail;
    }

    // Return current element value. Note that null gets returned if curr is at the tail
    @Override
    public Song getValue() throws NoSuchElementException {
        if (curr == tail) // No current element
        {
            return null;
        }

        return curr.getValue();
    }


    // method of getting song by the artists
    public String getSongsByArtist(String artist){

        String info = "";

        // this would go through all the song element in listSize
        for(int i = 0; i < listSize; i++) {
            // printing the artist that and getting the value in current
            if (curr.getValue().getArtist() == artist) {
                info = "Title: " + curr.getValue().getSongTitle() + " Artist: " + curr.getValue().getArtist() + " Album: " + curr.getValue().getAlbumTitle();
            }

            // would not have any song by the artist named
            else {
                info = "There are no songs by " + artist + " in this playlist";
            }
        }

        return info;
    }




}