package cmsc256;

public class Dog implements Comparable<Dog> {
private String dogName;
private int dogCount;

    public Dog(String dogName, int count){
    this.dogName = dogName;
    this.dogCount = count;
    }

    public String getDogName() {
    return dogName;
    }

    public void setDogName(String dogName) {
    this.dogName = dogName;
    }

    public int getDogCount() {
    return dogCount;
    }

    public void setDogCount(int dogCount) {
    this.dogCount = dogCount;
    }

    @Override
    public int compareTo(Dog o) {
        int result = dogName.compareTo(o.getDogName());

        return result;
    }
}
