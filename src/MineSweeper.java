import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    int elemanSayisi;

    int selectedRow;
    int selectedCol;
    int mineCount = 0;
    int rcCount;
    String[][] dizi = new String[row][col];
    String[][] dizi2 = new String[row][col];
    Scanner input = new Scanner(System.in);

    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.dizi = new String[row][col];
        this.dizi2 = new String[row][col];
    }


    void diziYazdir() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.dizi[i][j] = "-";
            }
        }
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.dizi[i][j]);
            }
            System.out.println();
        }
    }

    void mayinliDizi() {
        elemanSayisi = (row * col) / 4;
        Math.floor(elemanSayisi);
        Random random = new Random();
        for (int i = 0; i < elemanSayisi; i++) {
            int a = random.nextInt(row);
            int b = random.nextInt(col);
            dizi[a][b] = "*";
        }

        for (String[] i : dizi) {
            for (String j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    void change() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dizi2[i][j] = dizi[i][j];
            }
        }
    }

    boolean isCorrect() {
        if ((selectedRow >= row || selectedRow < 0) || (selectedCol >= col || selectedCol < 0)) {
            return false;
        } else {
            return true;
        }
    }

    boolean isWin() {
        if (dizi[selectedRow][selectedCol].equals("*")) {
            System.out.println("GAME OVER!");
            return false;
        } else {
            return true;
        }
    }

    void selected() {
        rcCount = (row * col) - elemanSayisi;
        System.out.println("Satır seçiniz: ");
        selectedRow = input.nextInt();
        System.out.println("Sütun seçiniz: ");
        selectedCol = input.nextInt();
        if (!isCorrect()) {
            while (!isCorrect()) {
                System.out.println("Yanlış seçim yaptınız tekrar seçiniz.");
                System.out.println("Satır seçiniz: ");
                selectedRow = input.nextInt();
                System.out.println("Sütun seçiniz: ");
                selectedCol = input.nextInt();
            }
        }
        rcCount--;
        while (isWin()) {
            if (rcCount == 0) {
                System.out.println("Tebrikler oyunu kazandınız");
                break;
            }
            mineCount();
            while (!isCorrect()) {
                System.out.println("Yanlış seçim yaptınız tekrar seçiniz.");
                System.out.println("Satır seçiniz: ");
                selectedRow = input.nextInt();
                System.out.println("Sütun seçiniz: ");
                selectedCol = input.nextInt();
            }
            if (isCorrect()) {
                System.out.println("Satır seçiniz: ");
                selectedRow = input.nextInt();
                System.out.println("Sütun seçiniz: ");
                selectedCol = input.nextInt();
                rcCount--;
            }
        }

    }

    void mineCount() {
        mineCount = 0;
        for (int i = selectedRow - 1; i <= selectedRow + 1; i++) {
            for (int j = selectedCol - 1; j <= selectedCol + 1; j++) {
                if (i < 0 || j < 0 || i >= row || j >= col) {
                    continue;
                } else {
                    if (dizi[i][j].equals("*")) {
                        mineCount++;
                    }
                }
            }
        }
        while (isWin() && isCorrect()) {
            dizi2[selectedRow][selectedCol] = String.valueOf(mineCount);
            for (String[] i : dizi2) {
                for (String j : i) {
                    System.out.print(j);
                }
                System.out.println();
            }
            break;
        }
    }

    void run() {
        System.out.println("Mayın Tarlası oyununa hoşgeldiniz.");
        diziYazdir();
        System.out.println("======================");
        mayinliDizi();
        change();
        System.out.println("======================");
        selected();
        System.out.println();
    }
}
