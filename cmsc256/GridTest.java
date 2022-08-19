package cmsc256;

public class GridTest {
    public static void main(String[] args) {

        Grid grid1 = new Grid();

        grid1.isRowMatching(3);
        System.out.println(grid1.hasDiagonalMatch());

    }
}
