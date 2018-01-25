/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiscale_project1;

import java.util.Random;

/**
 *
 * @author Michał
 */
public class Rekrystalizacja {
    public Rozrost ziarna;
    public Rozrost zrek;
    public boolean[][] granice;
    public double [][] ro;
    public double [][] ro2;
    public boolean[][] czy_zrek;
    Boolean bc=true;
    Boolean koniec;
    int krok;
    int id;
    double A =  86710969050178.5, 
           B = 9.41268203527779, //stale jakies do ro z excela
           ro_kryt = 4.215840000000/(400*400);  //400- wymiar przestrzeni automatow

    int metoda;
    
    int max_id;
    
    Rekrystalizacja(Rozrost r, int id)
    {
        this.krok=0;
        this.koniec = false;
        ziarna = r;
        zrek= new Rozrost();
        granice = new boolean [400][400];
        czy_zrek= new boolean [400][400];
        ro= new double [400][400];   
        ro2= new double [400][400]; 
        max_id=id;
        for (int i=0; i<400; i++)
        {
            for (int j=0; j<400; j++)
            {
                granice[i][j]=false;
                czy_zrek[i][j]=false;
                ro[i][j]=0;
                ro2[i][j]=0;
            }
        }
        
        this.id=id;
        
    }
    
    
    void znajdz_granice()
    {
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
                else if (ziarna.tab[x1][y1] != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[i][y1]  != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[x2][y1] != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[x1][j]  != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[x2][j]  != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[x1][y2] != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[i][y2]  != ziarna.tab[i][j]) granice[i][j] = true;
                else if (ziarna.tab[x2][y2] != ziarna.tab[i][j]) granice[i][j] = true;
            }
        }
    }
    
    
    void rekrystalizuj(boolean bc, int jakie, int odl, int ile)
    {
        krok++;
        double t=krok/100;
       // if (krok == 1)
          //  this.znajdz_granice();
        //Random d= new Random();
        Random r= new Random();
        
        
        double suma=0;
        for(int i=0; i<400; i++)
        {
        
        //for (int q=0; q<100;q++ )
            
            
            for(int j=0; j<400; j++)
            {
           // int i=d.nextInt(399);
            //int j=d.nextInt(399);
                double procent=0;
                if(granice[i][j])
                    procent=r.nextInt(6)+12;
                else
                    procent=r.nextInt(3);    ////////////////////////////////////
                procent/=10;
                
                
                double roo=A/B + (1 - (A/B))*Math.exp(-1*B*t);
                this.ro[i][j]=this.ro2[i][j];
               
                double ro_sr=(roo-ro[i][j])/(400*400);
                
                ro2[i][j]+=procent*ro_sr;
                
                
                int x1,x2,y1,y2;
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
                
                if (czy_zrek[i][j]) continue;
                
                int min_odl=odl;       //-------------odleglosc miedzy zarodkami rekrytalizujacymi
                boolean za_blisko=false;
                
              
                for (int a=-min_odl; a<min_odl; a++)
                {
                    for (int b=-min_odl; b<min_odl; b++)
                    {
                        if (i+a >0 && i+a<399 && j+b>0 && j+b<399)
                            if (zrek.tab[i+a][j+b] > 0) za_blisko=true;
                        if(za_blisko) continue;
                    }
                    if(za_blisko) continue;
                }
                if (za_blisko) continue;//-------------
                
                
                
                if (!czy_zrek[i][j] && ro2[i][j] > this.ro_kryt )
                {
                    zrek.dodajZarodek(i, j, id++);
                    czy_zrek[i][j]=true;
                    ro2[i][j]=0;
                }
                
                
            }
        }
        
        if (jakie==0)  zrek.moor(bc,0);        //-------------wybor sasiedztwa
        else if (jakie==1) zrek.neuman(bc);
        else if (jakie==2) zrek.hexagp(bc);
        else if (jakie==3) zrek.hexagl(bc);
        else if (jakie==4) zrek.hexaglos(bc);
        else if (jakie==5) zrek.penta(bc);
        else if (jakie==6) zrek.los2(bc);
        else if (jakie==7) zrek.los(bc,5);  //-------------
      
        this.koniec=zrek.koniec;
         
    }
    
    
}
