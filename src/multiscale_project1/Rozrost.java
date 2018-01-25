/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiscale_project1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.lang.Math.sqrt;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author Michal
 */
public class Rozrost {

    public int tab[][];
    public boolean[][] granice;
    int temp[][];
    int tablica[];
    Boolean koniec;
    int procent_granice;
    int t [];

    Rozrost() {
        this.koniec = false;
        tab = new int[400][400];
        temp = new int[400][400];
        granice = new boolean [400][400];
        tablica = new int[8];
        procent_granice = 0;
        t  = new int[100];
    }

    public void dodajZarodek(int x, int y, int id) {
        tab[x][y] = id;
    }

    void wyczysc() {
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                tab[i][j] = 0;
                granice[i][j]=false;
                temp[i][j] = 0;
            }
        }
    }

        void wyczysc2() {
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                //tab[i][j] = 0;
                granice[i][j]=false;
                temp[i][j] = 0;
            }
        }
    }
        
    void zeruj() {
        for (int i = 0; i < 8; i++) {
            tablica[i] = 0;
        }
    }

    void moor(Boolean bc, int can) {
        int x1, x2, y1, y2;
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();
                   

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;
                    if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                        tablica[licznik++] = tab[x1][y1];
                    }
                    if (x1 >= 0 && tab[x1][j] > 0) {
                        tablica[licznik++] = tab[x1][j];
                    }
                    if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                        tablica[licznik++] = tab[x1][y2];
                    }
                    if (y1 <= 399 && tab[i][y1] > 0) {
                        tablica[licznik++] = tab[i][y1];
                    }
                    if (y2 >= 0 && tab[i][y2] > 0) {
                        tablica[licznik++] = tab[i][y2];
                    }
                    if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                        tablica[licznik++] = tab[x2][y1];
                    }
                    if (x2 <= 399 && tab[x2][j] > 0) {
                        tablica[licznik++] = tab[x2][j];
                    }
                    if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                        tablica[licznik++] = tab[x2][y2];
                    }

                    int t2 = najwiecej(tablica, licznik);
                    boolean ca = true;
                    for (int c=0; c<can; c++)
                    {
                        if (t[c] == t2 ) ca=false;
                    }
                    if (t2!=500 && ca && t2!=450)
                        temp[i][j] = t2;
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }

    }
    
    void extendedmoor(Boolean bc, int prob) {
        int x1, x2, y1, y2;
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;
                    if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                        tablica[licznik++] = tab[x1][y1];
                    }
                    if (x1 >= 0 && tab[x1][j] > 0) {
                        tablica[licznik++] = tab[x1][j];
                    }
                    if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                        tablica[licznik++] = tab[x1][y2];
                    }
                    if (y1 <= 399 && tab[i][y1] > 0) {
                        tablica[licznik++] = tab[i][y1];
                    }
                    if (y2 >= 0 && tab[i][y2] > 0) {
                        tablica[licznik++] = tab[i][y2];
                    }
                    if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                        tablica[licznik++] = tab[x2][y1];
                    }
                    if (x2 <= 399 && tab[x2][j] > 0) {
                        tablica[licznik++] = tab[x2][j];
                    }
                    if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                        tablica[licznik++] = tab[x2][y2];
                    }

                    int t = najwiecej(tablica, licznik);
                    int ile_t = najwiecej_ilosc(tablica, licznik);
                    if (t != 500) {
                        if (ile_t >= 5) {
                            temp[i][j] = t;
                        } else {
                            zeruj();
                            licznik = 0;
                            if (y1 <= 399 && tab[i][y1] > 0) {
                                tablica[licznik++] = tab[i][y1];
                            }
                            if (y2 >= 0 && tab[i][y2] > 0) {
                                tablica[licznik++] = tab[i][y2];
                            }

                            if (x2 <= 399 && tab[x2][j] > 0) {
                                tablica[licznik++] = tab[x2][j];
                            }
                            if (x1 >= 0 && tab[x1][j] > 0) {
                                tablica[licznik++] = tab[x1][j];
                            }
                            t = najwiecej(tablica, licznik);
                            ile_t = najwiecej_ilosc(tablica, licznik);
                            if (t != 500) {
                                if (ile_t >= 3) {
                                    temp[i][j] = t;
                                } else {
                                    zeruj();
                                    licznik = 0;
                                    if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                                        tablica[licznik++] = tab[x1][y1];
                                    }
                                    if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                                        tablica[licznik++] = tab[x1][y2];
                                    }
                                    if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                                        tablica[licznik++] = tab[x2][y1];
                                    }
                                    if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                                        tablica[licznik++] = tab[x2][y2];
                                    }
                                    t = najwiecej(tablica, licznik);
                                    ile_t = najwiecej_ilosc(tablica, licznik);
                                    if (t != 500) {
                                        if (ile_t >= 3) {
                                            temp[i][j] = t;
                                        } else {
                                            zeruj();
                                            licznik = 0;
                                            if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                                                tablica[licznik++] = tab[x1][y1];
                                            }
                                            if (x1 >= 0 && tab[x1][j] > 0) {
                                                tablica[licznik++] = tab[x1][j];
                                            }
                                            if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                                                tablica[licznik++] = tab[x1][y2];
                                            }
                                            if (y1 <= 399 && tab[i][y1] > 0) {
                                                tablica[licznik++] = tab[i][y1];
                                            }
                                            if (y2 >= 0 && tab[i][y2] > 0) {
                                                tablica[licznik++] = tab[i][y2];
                                            }
                                            if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                                                tablica[licznik++] = tab[x2][y1];
                                            }
                                            if (x2 <= 399 && tab[x2][j] > 0) {
                                                tablica[licznik++] = tab[x2][j];
                                            }
                                            if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                                                tablica[licznik++] = tab[x2][y2];
                                            }
                                            t = najwiecej(tablica, licznik);
                                            ile_t = najwiecej_ilosc(tablica, licznik);
                                            if (t != 500) {
                                                Random r = new Random();
                                                if (r.nextInt(100)>prob)
                                                    temp[i][j] = t;
                                                    //System.out.println(r.nextInt(100)+" "+prob);
                                            }

                                        }

                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }

    }


    void neuman(Boolean bc) {
        int x1, x2, y1, y2;
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;

                    if (x1 >= 0 && tab[x1][j] > 0) {
                        tablica[licznik++] = tab[x1][j];
                    }
                    if (y1 <= 399 && tab[i][y1] > 0) {
                        tablica[licznik++] = tab[i][y1];
                    }
                    if (y2 >= 0 && tab[i][y2] > 0) {
                        tablica[licznik++] = tab[i][y2];
                    }
                    if (x2 <= 399 && tab[x2][j] > 0) {
                        tablica[licznik++] = tab[x2][j];
                    }

                    temp[i][j] = najwiecej(tablica, licznik);
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }
    }

    void hexagp(Boolean bc) {
        int x1, x2, y1, y2;
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;

                    if (x1 >= 0 && tab[x1][j] > 0) {
                        tablica[licznik++] = tab[x1][j];
                    }
                    if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                        tablica[licznik++] = tab[x1][y2];
                    }
                    if (y1 <= 399 && tab[i][y1] > 0) {
                        tablica[licznik++] = tab[i][y1];
                    }
                    if (y2 >= 0 && tab[i][y2] > 0) {
                        tablica[licznik++] = tab[i][y2];
                    }
                    if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                        tablica[licznik++] = tab[x2][y1];
                    }
                    if (x2 <= 399 && tab[x2][j] > 0) {
                        tablica[licznik++] = tab[x2][j];
                    }

                    temp[i][j] = najwiecej(tablica, licznik);
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }
    }

    void hexagl(Boolean bc) {
        int x1, x2, y1, y2;
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;
                    if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                        tablica[licznik++] = tab[x1][y1];
                    }
                    if (x1 >= 0 && tab[x1][j] > 0) {
                        tablica[licznik++] = tab[x1][j];
                    }

                    if (y1 <= 399 && tab[i][y1] > 0) {
                        tablica[licznik++] = tab[i][y1];
                    }
                    if (y2 >= 0 && tab[i][y2] > 0) {
                        tablica[licznik++] = tab[i][y2];
                    }

                    if (x2 <= 399 && tab[x2][j] > 0) {
                        tablica[licznik++] = tab[x2][j];
                    }
                    if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                        tablica[licznik++] = tab[x2][y2];
                    }

                    temp[i][j] = najwiecej(tablica, licznik);
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }
    }

    void hexaglos(Boolean bc) {
        int x1, x2, y1, y2;
        Random r = new Random();
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;

                    if (r.nextInt() % 2 == 0) {
                        if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                            tablica[licznik++] = tab[x1][y1];
                        }
                        if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                            tablica[licznik++] = tab[x2][y2];
                        }
                    } else {

                        if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                            tablica[licznik++] = tab[x2][y1];
                        }
                        if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                            tablica[licznik++] = tab[x1][y2];
                        }
                    }

                    if (x1 >= 0 && tab[x1][j] > 0) {
                        tablica[licznik++] = tab[x1][j];
                    }

                    if (y1 <= 399 && tab[i][y1] > 0) {
                        tablica[licznik++] = tab[i][y1];
                    }
                    if (y2 >= 0 && tab[i][y2] > 0) {
                        tablica[licznik++] = tab[i][y2];
                    }
                    if (x2 <= 399 && tab[x2][j] > 0) {
                        tablica[licznik++] = tab[x2][j];
                    }

                    temp[i][j] = najwiecej(tablica, licznik);
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }
    }

    void penta(Boolean bc) {
        int x1, x2, y1, y2;
        koniec = true;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    zeruj();

                    if (bc) {
                        x1 = i - 1;
                        if (x1 < 0) {
                            x1 = 399;
                        }
                        x2 = i + 1;
                        if (x2 > 399) {
                            x2 = 0;
                        }
                        y1 = j + 1;
                        if (y1 > 399) {
                            y1 = 0;
                        }
                        y2 = j - 1;
                        if (y2 < 0) {
                            y2 = 399;
                        }
                    } else {
                        x1 = i - 1;
                        x2 = i + 1;
                        y1 = j + 1;
                        y2 = j - 1;
                    }

                    int licznik = 0;
                    Random r = new Random();
                    if (r.nextInt() % 4 == 0) {

                        if (x1 >= 0 && tab[x1][j] > 0) {
                            tablica[licznik++] = tab[x1][j];
                        }
                        if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                            tablica[licznik++] = tab[x1][y2];
                        }

                        if (y2 >= 0 && tab[i][y2] > 0) {
                            tablica[licznik++] = tab[i][y2];
                        }

                        if (x2 <= 399 && tab[x2][j] > 0) {
                            tablica[licznik++] = tab[x2][j];
                        }
                        if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                            tablica[licznik++] = tab[x2][y2];
                        }
                    } else if (r.nextInt() % 4 == 1) {
                        if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                            tablica[licznik++] = tab[x1][y1];
                        }
                        if (x1 >= 0 && tab[x1][j] > 0) {
                            tablica[licznik++] = tab[x1][j];
                        }

                        if (y1 <= 399 && tab[i][y1] > 0) {
                            tablica[licznik++] = tab[i][y1];
                        }

                        if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                            tablica[licznik++] = tab[x2][y1];
                        }
                        if (x2 <= 399 && tab[x2][j] > 0) {
                            tablica[licznik++] = tab[x2][j];
                        }

                    } else if (r.nextInt() % 4 == 2) {

                        if (y1 <= 399 && tab[i][y1] > 0) {
                            tablica[licznik++] = tab[i][y1];
                        }
                        if (y2 >= 0 && tab[i][y2] > 0) {
                            tablica[licznik++] = tab[i][y2];
                        }
                        if (y1 <= 399 && x2 <= 399 && tab[x2][y1] > 0) {
                            tablica[licznik++] = tab[x2][y1];
                        }
                        if (x2 <= 399 && tab[x2][j] > 0) {
                            tablica[licznik++] = tab[x2][j];
                        }
                        if (x2 <= 399 && y2 >= 0 && tab[x2][y2] > 0) {
                            tablica[licznik++] = tab[x2][y2];
                        }
                    } else {
                        if (x1 >= 0 && y1 <= 399 && tab[x1][y1] > 0) {
                            tablica[licznik++] = tab[x1][y1];
                        }
                        if (x1 >= 0 && tab[x1][j] > 0) {
                            tablica[licznik++] = tab[x1][j];
                        }
                        if (x1 >= 0 && y2 >= 0 && tab[x1][y2] > 0) {
                            tablica[licznik++] = tab[x1][y2];
                        }
                        if (y1 <= 399 && tab[i][y1] > 0) {
                            tablica[licznik++] = tab[i][y1];
                        }
                        if (y2 >= 0 && tab[i][y2] > 0) {
                            tablica[licznik++] = tab[i][y2];
                        }

                    }

                    temp[i][j] = najwiecej(tablica, licznik);
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }

    }

    void los(Boolean bc, int promien) {
        int x1 = 0, y1 = 0;
        koniec = true;
        List<Integer> lista = new ArrayList<Integer>();
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] == 0) {
                    koniec = false;
                    lista.clear();

                    for (int r = -promien; r < promien * 2; r++) {
                        for (int r2 = -promien; r2 < promien * 2; r2++) {
                            if (bc) {
                                x1 = i + r;
                                if (x1 < 0) {
                                    x1 += 400;
                                }
                                if (x1 > 399) {
                                    x1 -= 400;
                                }

                                y1 = j + r2;
                                if (y1 > 399) {
                                    y1 -= 400;
                                }
                                if (y1 < 0) {
                                    y1 += 400;
                                }
                            } else {
                                x1 = i + r;
                                y1 = j + r2;
                                if (x1 < 0 || x1 > 399) {
                                    continue;
                                }
                                if (y1 < 0 || y1 > 399) {
                                    continue;
                                }

                            }

                            if (sqrt((i - x1) ^ 2 + (j - y1) ^ 2) <= promien) {
                                if (tab[x1][y1] > 0) {
                                    lista.add(tab[x1][y1]);
                                }
                            }
                        }

                    }

                    temp[i][j] = najw(lista);
                }
            }
        }
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (temp[i][j] > 0) {
                    tab[i][j] = temp[i][j];
                }

            }
        }

    }

    private int najwiecej(int[] lista, int licznik) {
        int i, j, L, W, maxL, maxW = 0;
        maxL = 0;
        for (i = 0; i < licznik; i++) {
            W = lista[i];
            L = 0;
            for (j = 0; j < licznik; j++) {
                if (lista[j] == W && W>0) {
                    L++;
                }
            }
            if (L > maxL) {
                maxL = L;
                maxW = W;
            }

        }//System.out.println("Najwicej: "+maxW);
        return maxW;
    }
    
    
        private int najwiecej_ilosc(int[] lista, int licznik) {
        int i, j, L, W, maxL, maxW = 0;
        maxL = 0;
        for (i = 0; i < licznik; i++) {
            W = lista[i];
            L = 0;
            for (j = 0; j < licznik; j++) {
                if (lista[j] == W) {
                    L++;
                }
            }
            if (L > maxL) {
                maxL = L;
                maxW = W;
            }

        }//System.out.println("Najwicej: "+maxW);
        return maxL;
    }

    private int najw(List<Integer> lista) {
        int i, j, L, W, maxL, maxW = 0;
        maxL = 0;
        for (i = 0; i < lista.size(); i++) {
            W = lista.get(i);
            L = 0;
            for (j = 0; j < lista.size(); j++) {
                if (lista.get(j) == W) {
                    L++;
                }
            }
            if (L > maxL) {
                maxL = L;
                maxW = W;
            }

        }//System.out.println("Najwicej: "+maxW);
        return maxW;

    }

    void los2(Boolean bc) {
        Random r = new Random();
        int los = r.nextInt(6);
        if (los % 6 == 0) {
            this.hexagl(bc);
        }
        if (los % 6 == 1) {
            this.hexaglos(bc);
        }
        if (los % 6 == 2) {
            this.hexagp(bc);
        }
        if (los % 6 == 3) {
            this.moor(bc,0);
        }
        if (los % 6 == 4) {
            this.neuman(bc);
        }
        if (los % 6 == 5) {
            this.penta(bc);
        }

    }

    void exportToFile(File file) throws FileNotFoundException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
        
            for (int i=0; i<400; i++)
            {
                for (int j=0; j<400; j++)
                {
                    writer.write(tab[i][j]+"\n");
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }

    void importFromFile(File file) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        for (int i=0; i<400; i++)
            {
                for (int j=0; j<400; j++)
                {
                    tab[i][j]=Integer.parseInt(bufferedReader.readLine());
                }
            }
           
        bufferedReader.close();

    }
  
    public void exportToBmp(File file) {
        BufferedImage bImg = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        Graphics g = bImg.createGraphics();

        g.clearRect(0, 0, 400, 400);
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] > 0) {
                    g.setColor(Color.getHSBColor(tab[i][j] * 20 % 255 + 1, tab[i][j] % 255 * 50 + 1, tab[i][j] % 255 * 37 + 1));
                    g.fillRect(i, j, 1, 1);
                } else {
                    g.setColor(Color.black);
                    g.fillRect(i, j, 1, 1);
                }

            }
        }

        if (file.exists() == false) {
            try {

                ImageIO.write(bImg, "bmp", file);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    
    
    public void ImportFromBmp(File file) {
        BufferedImage bImg = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        Graphics g = bImg.createGraphics();

        g.clearRect(0, 0, 400, 400);
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (tab[i][j] > 0) {
                    g.setColor(Color.getHSBColor(tab[i][j] * 20 % 255 + 1, tab[i][j] % 255 * 50 + 1, tab[i][j] % 255 * 37 + 1));
                    g.fillRect(i, j, 1, 1);
                } else {
                    g.setColor(Color.black);
                    g.fillRect(i, j, 1, 1);
                }

            }
        }

        if (file.exists() == false) {
            try {
                ImageIO.read(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
 
    public void addInclusionsBefore(int type, int radius) {
        if (type == 0) {
            for (int i = 0; i < 5; i++) {
                Random generator = new Random();
                int x = generator.nextInt(399);
                int y = generator.nextInt(399);

                for (int j = -radius; j < radius; j++) {
                    for (int k = -radius; k < radius; k++) {
                        if (x + j >= 0 && x + j < 400) {
                            if (y + k >= 0 && y + k < 400) {
                                if(tab[x + j][y + k] == 0)
                                    tab[x + j][y + k] = 500;
                            }
                        }
                    }
                }
            }
        }
        if (type == 1) {
            for (int i = 0; i < 5; i++) {
                Random generator = new Random();
                int x = generator.nextInt(399);
                int y = generator.nextInt(399);

                for (int j = -radius; j < radius; j++) {
                    for (int k = -radius; k < radius; k++) {
                        if (x + j >= 0 && x + j < 400) {
                            if (y + k >= 0 && y + k < 400) {
                                if (sqrt(j*j + k*k)<= radius)
                                {
                                    if(tab[x + j][y + k] == 0)
                                    tab[x + j][y + k] = 500;
                                }
                            }
                                
                            }
                        }
                    }
                }
            }
    }

    
    
    
    public void addInclusionsAfter(int type, int radius) {
        znajdz_granice(true);
        List<Point> lista_granic = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (granice[i][j]) {
                    lista_granic.add(new Point(i, j));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            Random generator = new Random();
            int l = generator.nextInt(lista_granic.size());

            int x = lista_granic.get(l).x;
            int y = lista_granic.get(l).y;
            if (type == 0) {
                for (int j = -radius; j < radius; j++) {
                    for (int k = -radius; k < radius; k++) {
                        if (x + j >= 0 && x + j < 400) {
                            if (y + k >= 0 && y + k < 400) {
                                    tab[x + j][y + k] = 500;
                                    System.out.println(x+j+","+y+k);  
                            }
                        }
                    }
                }
            }
            if (type == 1) {
                for (int j = -radius; j < radius; j++) {
                    for (int k = -radius; k < radius; k++) {
                        if (x + j >= 0 && x + j < 400) {
                            if (y + k >= 0 && y + k < 400) {
                                if (sqrt(j * j + k * k) <= radius) {
                                        tab[x + j][y + k] = 500;
                                }
                            }

                        }
                    }
                }
            }

        }
    }


  

    
    
    
     void znajdz_granice(Boolean bc)
    {
        this.procent_granice=0;
        int x1, x2, y1, y2;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (bc) {
                    x1 = i - 1;
                    if (x1 < 0) {
                        x1 = 399;
                    }
                    x2 = i + 1;
                    if (x2 > 399) {
                        x2 = 0;
                    }
                    y1 = j + 1;
                    if (y1 > 399) {
                        y1 = 0;
                    }
                    y2 = j - 1;
                    if (y2 < 0) {
                        y2 = 399;
                    }
                } else {
                    x1 = i - 1;
                    x2 = i + 1;
                    y1 = j + 1;
                    y2 = j - 1;
                }
                 
                if (x1<0 || x2>399 || y1>399 || y2 <0) continue;
                else if (tab[x1][y1] != tab[i][j]) granice[i][j] = true;
                else if (tab[i][y1]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x2][y1] != tab[i][j]) granice[i][j] = true;
                else if (tab[x1][j]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x2][j]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x1][y2] != tab[i][j]) granice[i][j] = true;
                else if (tab[i][y2]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x2][y2] != tab[i][j]) granice[i][j] = true;
                
                if (granice[i][j]) this.procent_granice++;
            }
        }
    }
     
     
      void znajdz_granice(Boolean bc, int szer)
    {
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                granice[i][j]=false;
            }
        }
    
        int x1, x2, y1, y2;
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (bc) {
                    x1 = i - 1;
                    if (x1 < 0) {
                        x1 = 399;
                    }
                    x2 = i + 1;
                    if (x2 > 399) {
                        x2 = 0;
                    }
                    y1 = j + 1;
                    if (y1 > 399) {
                        y1 = 0;
                    }
                    y2 = j - 1;
                    if (y2 < 0) {
                        y2 = 399;
                    }
                } else {
                    x1 = i - 1;
                    x2 = i + 1;
                    y1 = j + 1;
                    y2 = j - 1;
                }
                 
                if (x1<0 || x2>399 || y1>399 || y2 <0) continue;
                else if (tab[x1][y1] != tab[i][j]) granice[i][j] = true;
                else if (tab[i][y1]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x2][y1] != tab[i][j]) granice[i][j] = true;
                else if (tab[x1][j]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x2][j]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x1][y2] != tab[i][j]) granice[i][j] = true;
                else if (tab[i][y2]  != tab[i][j]) granice[i][j] = true;
                else if (tab[x2][y2] != tab[i][j]) granice[i][j] = true;
            }
        }
        
        boolean[][] granice2 = new boolean [400][400];

        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                for (int x=-szer; x<szer; x++)
                {
                    for (int y=-szer; y<szer; y++)
                    {
                        if ((i+x)>=0 && (i+x)<400 && (j+y)>=0 && (j+y)<400)
                        {
                           
                            if (granice[i][j]) {
                                granice2[i+x][j+y]=true;
                                granice2[i][j]=true;
                            } 
                        }
                    }
                }
            }
        }
        granice=granice2;
        
    }


    void selectngrains(int n, int i0) {
        Random generator = new Random();
        
        for (int i=0; i<n; i++)
        {
            t[i]= generator.nextInt(i0);
        }
        boolean usunac=true;
        for (int i=0;i <400; i++)
        {
            for (int j=0; j<400; j++)
            {
                usunac=true;
                for (int k=0; k<n; k++)
                    if (tab[i][j] == t[k]) usunac=false;
                if (usunac) tab[i][j]=0;
            }
        }
        wyczysc2();
        
    }
    
    void dualphase()
    {
        for (int i=0; i<400; i++)
            for (int j=0; j<400; j++)
            {
                if (tab[i][j] >0) tab[i][j]=450;
            }
        wyczysc2();
    }
     
  

}
