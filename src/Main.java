import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row;
        int col;
        System.out.println("Satır ve sütun sayısını girerek oyuna başlayabilirsiniz.");
        System.out.print("Satır sayısı : ");
        row = input.nextInt();
        System.out.print("Sütun sayısı : ");
        col = input.nextInt();
        MineSweeper mine = new MineSweeper(row, col);
        mine.run();

    }
}
