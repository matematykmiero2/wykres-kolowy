import javax.swing.DefaultListModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Katy {


    public static ArrayList<Integer> x (DefaultListModel<Integer> lista){
        ArrayList<Integer> buf=new ArrayList<>();
        ArrayList<Integer> buf2=new ArrayList<>();
        int roznica=0;
        if(lista.size()==1){
            buf.add(360);
        }

        else if(lista.size()>1){
            float suma=0;
            for(int i=0;i<lista.size();i++){
                suma+=lista.get(i);
            }
            float wielkosc=360/suma;
            for(int i=0;i<lista.size();i++){
                int w= (int)Math.round(wielkosc*lista.get(i));
                buf2.add(w);

            }

roznica = roznica(buf2);

            if(roznica!=0){
                System.out.println("Różnica od 360 stopni: "+roznica);
            }
            wyrownanie(buf2);


buf=buf2;
        }

        return buf;


    }
    public static int roznica( ArrayList<Integer> x){
        int r=0;

        for(int i=0;i<x.size();i++){
            r+=x.get(i);
        }
        return 360-r;
    }

    public static void wyrownanie(ArrayList<Integer> x){
        int r = roznica(x);
        int rozmiar = x.size()-1;
        Random los = new Random();
        int i=0;
        int wylosowana=0;

        while( r!=0 && i<=rozmiar){
            wylosowana=los.nextInt(rozmiar);
            if(r>0){
                x.set(wylosowana,x.get(wylosowana)+1);
            }
           else if(r<0){
                x.set(wylosowana,x.get(wylosowana)-1);
            }
            System.out.println(r);
            r = roznica(x);
            i++;
        }

        System.out.println("Różnica od 360 stopni: "+r);
    }
}
