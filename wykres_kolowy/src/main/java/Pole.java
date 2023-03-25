import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Pole extends JFrame{
    static ArrayList<Wycinek> lista_wyc = new ArrayList<Wycinek>();
    static ArrayList<Color> kolory = new ArrayList<>();
    static ArrayList<Integer> katy = new ArrayList<>();
    static DefaultListModel<Integer> index = new DefaultListModel<>();
     static Wykres wykres;
    int w_pamieci=0;
    Pole() {
        try {

            Random rand = new Random();
            for (int i = 0; i < 360; i++) {
                kolory.add(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            }

            //RAMKA
            super.setTitle("Wykres kolowy");
            super.setLayout(null);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            super.setSize(new Dimension(460, 600));
            super.setLocationRelativeTo(null);

            Dimension d = new Dimension(70, 30);

//WPROWADZANIE
            JTextArea wprowadzanie;
            wprowadzanie = new JTextArea(1, 20);
            wprowadzanie.setBounds(30, 35, 200, 20);

//SCROLL

            JScrollPane scroll;
            DefaultListModel<Integer> listab;
            listab = new DefaultListModel<>();
            JList<Integer> lista = new JList<Integer>(listab);
            scroll = new JScrollPane(lista);
            scroll.setBounds(270, 15, 100, 100);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            //DODAJ
            JButton dodaj = new JButton("Dodaj");
            dodaj.setFont(new Font("Arial", Font.PLAIN, 10));
            dodaj.setSize(d);
            dodaj.setBounds(20, 60, 70, 30);
            dodaj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == dodaj) {
                        if (!wprowadzanie.getText().equals("")) {

                            try {

                                int bufor = Integer.parseInt(wprowadzanie.getText());
                                if (bufor > 0) {
                                    listab.add(listab.size(), bufor);

                                    katy = Katy.x(listab);
                                    index.add(index.size(), index.size() + w_pamieci);
                                    rysuj();
                                    System.out.println("Dodano element do wykresu na index : " + (index.size() - 1) + "  wartosc: " + bufor);

                                } else {
                                    System.out.println("Nie przeprowadzono operacji - bledna wartosc w polu wprowadzania");
                                }
                                // wprowadzanie.setText("");
                            } catch (NumberFormatException x) {
                                System.out.println("To nie jest liczba!\nSprawdz czy podana wartosc spelnia wymogi liczby Integer");
                            }
                        }

                    }
                }

            });


//USUN
            JButton usun = new JButton("Usun");
            usun.setFont(new Font("Arial", Font.PLAIN, 10));
            usun.setSize(d);
            usun.setBounds(100, 60, 70, 30);

            usun.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == usun) {
                        int b = lista.getSelectedIndex();
                        listab.remove(b);
                        katy = Katy.x(listab);
                        index.remove(b);
                        w_pamieci++;
                        rysuj();
                        System.out.println("Usunieto element z wykresu o indexie: " + b);
                    }
                }
            });

            //EDYTUJ
            JButton edytuj = new JButton("Edytuj");
            edytuj.setFont(new Font("Arial", Font.PLAIN, 10));
            edytuj.setSize(d);
            edytuj.setBounds(180, 60, 70, 30);

            edytuj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int b = lista.getSelectedIndex();
                    int pop;
                    if (e.getSource() == edytuj) {
                        try {

                            int bufor = Integer.parseInt(wprowadzanie.getText());
                            if (bufor > 0) {
                                listab.add(b + 1, bufor);
                                pop = listab.get(b);
                                listab.remove(b);

                                katy = Katy.x(listab);
                                rysuj();
                                System.out.println("Edytowano element na wykresie o indexie: " + b + " z " + pop + " na " + bufor);
                            } else {
                                System.out.println("Nie przeprowadzono operacji - bledna wartosc w polu wprowadzania");
                            }
                            wprowadzanie.setText("");
                        } catch (NumberFormatException x) {
                            System.out.println("To nie jest liczba!\n Sprawdz czy podana wartosc spelnia wymogi liczby Integer");
                        }

                    }
                }
            });


            super.add(dodaj);
            super.add(usun);
            super.add(edytuj);
            super.add(wprowadzanie);
            super.add(scroll);
            super.setVisible(true);


        }
        catch(Exception e){
            System.out.println("Cos poszlo nie tak\nNie mozna wprowadzic wiecej niz 360 wycinkow, prosze nie byc zachlannym");
        }
    }
    public static void aktualizacja_listy(){
       lista_wyc=new ArrayList<>();
       for(int i=0;i<katy.size();i++){
           lista_wyc.add(new Wycinek(katy.get(i),kolory.get(index.get(i))));
       }

    }

    public void rysuj(){
        wykres=new Wykres(lista_wyc);
        super.add(wykres);
        wykres.repaint();
    }


}
