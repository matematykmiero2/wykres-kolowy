import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Wykres extends JPanel {
    ArrayList<Wycinek> lista;
    int kat=0;
    Wykres(ArrayList<Wycinek> lista){
        this.setBounds(20,140,400,400);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        setVisible(true);
        Pole.aktualizacja_listy();
    }
    public void paint (Graphics g){
        super.paint(g);
        Graphics2D wykres_kolowy = (Graphics2D) g;
        for(int i=0;i<Pole.lista_wyc.size();i++){
            wykres_kolowy.setPaint(Pole.lista_wyc.get(i).kolor);
            wykres_kolowy.fillArc(10,10,380,380,kat,Pole.lista_wyc.get(i).kat);
            kat+=Pole.lista_wyc.get(i).kat;
        }
    }

}