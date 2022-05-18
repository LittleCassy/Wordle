/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordle;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author LittleCassy
 */
public class LogicalClass {

    public static ArrayList<String> wordChar = new ArrayList<>();
    public static Tablero gameInstance;
    public static String word;

    public static ArrayList<ArrayList<JTextPane>> allTrys = new ArrayList<>();

    public static int currentTry = 0;

    public static void preLoad() {
        int aux = new Random().nextInt(FileShenanigans.words.size());
        String[] aux_Str = FileShenanigans.words.get(aux).split("");
        for (int i = 0; i < aux_Str.length; i++) {
            wordChar.add(aux_Str[i]);
        }
        word = FileShenanigans.words.get(aux);
        Init();
    }

    public static void Init() {

        gameInstance = new Tablero();
        gameInstance.setVisible(true);

        for (int i = 0; i < 6; i++) {
            JPanel aux_Pn = new JPanel();
            aux_Pn.setLayout(new GridLayout(1, 5));
            gameInstance.Tablero.add(aux_Pn);
            allTrys.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                JTextPane aux_Btn = new JTextPane();
                aux_Pn.add(aux_Btn);
                aux_Btn.setFont(new java.awt.Font("Segoe UI", 1, 24));
                aux_Btn.setEditable(false);
                allTrys.get(i).add(aux_Btn);
            }
        }
    }

    public static void CheckGreen() {
        String[] aux = gameInstance.TextFieldPalabra.getText().toUpperCase().split("");
        ArrayList<String> enteredWord = new ArrayList<>();
        
        for (int i = 0; i < aux.length; i++) {
            enteredWord.add(aux[i]);
        }
        
        ArrayList<String> wordAux = new ArrayList<>(wordChar);
        int aux_Victory = 0;

        for (int i = 0; i < 5; i++) {
            allTrys.get(currentTry).get(i).setText(enteredWord.get(i));
            if (enteredWord.get(i).equals(wordChar.get(i))) {
                allTrys.get(currentTry).get(i).setBackground(Color.green);
                wordAux.set(i, "");
                enteredWord.set(i, "");
                aux_Victory++;
            }
        }

        if (aux_Victory == 5) {
            System.out.print("Victoria");
            gameInstance.TextFieldPalabra.setEnabled(false);
            gameInstance.BotonEnviar.setEnabled(false);
            gameInstance.TableroVictoria.setSize(400,250);
            gameInstance.TableroVictoria.setVisible(true);
        } else if(currentTry<=5){
            CheckYellow(wordAux, enteredWord);
        }
    }

    public static void CheckYellow(ArrayList<String> palabraBuena, ArrayList<String> palabraEntrada) {
        int[] repeats = {0,0,0,0,0};

        for (int i = 0; i < palabraBuena.size(); i++) {
            for (int j = palabraBuena.size() - 1; j >= i; j--) {
                if(palabraBuena.get(i).equals(palabraBuena.get(j)) && !palabraBuena.get(i).equals("")){
                    repeats[i]++;
                    System.out.println(palabraBuena.get(i));
                }
            }
        }

        for (int i = 0; i < palabraEntrada.size(); i++) {
            if (repeats[i]!= 0 && palabraBuena.contains(palabraEntrada.get(i))) {
                allTrys.get(currentTry).get(i).setBackground(Color.yellow);
                repeats[i]--;
            }
        }
        
        currentTry++;
        
        if(currentTry==6){
            gameInstance.TextFieldPalabra.setEnabled(false);
            gameInstance.BotonEnviar.setEnabled(false);
            Component[] aux = gameInstance.tableroDerrota.getContentPane().getComponents();
            for (int i = 0; i < aux.length; i++) {
                if(aux[i].getClass() == JLabel.class){
                    JLabel label = (JLabel) aux[i];
                    label.setText("La palabra es: " + word);
                }
            }
            gameInstance.tableroDerrota.setSize(400,250);
            gameInstance.tableroDerrota.setVisible(true);
        }
    }
}
