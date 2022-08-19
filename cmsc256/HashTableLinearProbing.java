package cmsc256;
import java.util.Iterator;

public class HashTableLinearProbing<K,V> extends HashTableOpenAddressing <K, V>{
    public int linearProbe(int index, K keyIn) {
        boolean found = false;
        int removedStateIndex = -1; // Index of first removed location
        if(table[index] == null)    // The hash index is available
            return index;
        while (!found && table[index] != null) {
            if (table[index].isIn()) {
                if (keyIn.equals(table[index].getKey())) {
                    found = true; // Key found
                }
                else                     // Follow probe sequence
                    index = (index + 1) % table.length;
            }
            else {                              // Skip entries that were removed
                // Save index of first location in removed state
                if (removedStateIndex == -1) {
                    removedStateIndex = index;
                }
                index = (index + 1) % table.length;
            }
        }

        if (found || (removedStateIndex == -1) ) {
            return index; // Index of either key or null
        }
        else
            return removedStateIndex; // Index of an available location

    }


    @Override
    public Object put(Object key, Object value) {
        if (key == null || value == null){
            throw new IllegalArgumentException("nulls not allowed");
        }

        Object values = null;
        Entry entry = new Entry(key,value);
        int index = super.getHashIndex((K) key);
        index = linearProbe(index, (K)key);

        if (table[index]==null || table[index].isRemoved()){
            table[index]= entry;
            numEntries++;
        }
        else {
            values = (V)table[index].getValue();
            table[index]= entry;
        }

        if (isFull()){
            enlargeHashTable();
        }
        return (V)values;

    }

    @Override
    public Object remove(Object key) {
       Object removedvalue = null;
       Boolean isFound = false;

        //calculating the index
        int index = super.getHashIndex((K) key);

        //finding the location of the key using linear probing
        index = linearProbe(index, (K)key);

        //if the key is found, then flag entry as removed and return the value
        while (!isFound && table[index]!=null){
            if (table[index].isIn()){
                if(key == table[index].getKey()){
                    isFound= true;
                    removedvalue = table[index].getValue();
                    table[index].setToRemoved();
                    numEntries--;
                }
            }
        }
        // if the key is not found it should return null
        return removedvalue;
    }

    @Override
    public Object getValue(Object key) {
        int index = super.getHashIndex((K)key);
        index = linearProbe(index, (K)key);
        Entry<K,V> element = table[index];
        if ((element != null ) && element.isIn()){
            return element.getValue();
        }
        else
            return null;
    }

    @Override
    public boolean contains(Object key) {
        boolean found = false;
        int index = getHashIndex((K)key);
        index = linearProbe(index, (K)key);
        //
        while (!found && table[index]!=null){
            if (table[index].isIn()){
                if(key == table[index].getKey()){
                    found = true; //key is found
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        HashTableLinearProbing<String, Integer> purchases = new HashTableLinearProbing<>();

        String[] names = {"Pax", "Eleven", "Angel", "Abigail", "Jack"};
        purchases.put(names[0], 654);
        purchases.put(names[1], 341);
        purchases.put(names[2], 70);
        purchases.put(names[3], 867);
        purchases.put(names[4], 5309);

        System.out.println("Contents with linear probing:\n" + purchases.toString());

        System.out.println("Replaced old value was " + purchases.put(names[1], 170));
        System.out.println("Contents after changing Eleven to 170:\n" + purchases.toString());

        System.out.println("Calling getValue() on Pax, Eleven, & Angel:");
        System.out.println("\tPax: " + purchases.getValue(names[0]));
        System.out.println("\tEleven: " + purchases.getValue(names[1]));
        System.out.println("\tAngel: " + purchases.getValue(names[2]));

        purchases.remove(names[0]);
        purchases.remove(names[2]);
        System.out.println("Contents after removing Pax & Angel:\n" + purchases);

        purchases.put("Gino", 348);
        System.out.println("Contents after adding Gino:\n" + purchases);

        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while (keyIter.hasNext())
            System.out.println("Key-" + keyIter.next() + " : Value-" + valueIter.next());
    }
}


