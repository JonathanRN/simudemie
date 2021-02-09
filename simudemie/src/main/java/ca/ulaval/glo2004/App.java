package ca.ulaval.glo2004;


import org.apache.commons.math3.distribution.BinomialDistribution;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;


public class App {
    //Exemple de creation d'une fenetre et d'un bouton avec swing. Lorsque vous allez creer votre propre GUI
    // Vous n'aurez pas besoin d'ecrire tout ce code, il sera genere automatiquement par intellij ou netbeans
    // Par contre vous aurez a creer les actions listener pour vos boutons et etc.
    public static void main(String[] args) {
        
        Random rnd = new Random (System.currentTimeMillis());
        double seuilTol = 0.0001;
        
        BinomialDistribution binomial = new BinomialDistribution(100, 0.05);

        
        ArrayList<Double> probabilites = new ArrayList<>();
        ArrayList<Integer> nombreSucces = new ArrayList<>();
        
        int x = 5;
        double prob = binomial.probability(x);
        while(prob > seuilTol && x >= 0){
            probabilites.add(prob);
            nombreSucces.add(x);
            x -= 1;
            prob = binomial.probability(x);
        }
        
        System.out.println(probabilites);
        System.out.println(nombreSucces);
        
        double randomNumber = 0.0;
        double current = 0.0; //treshold
        int success = 0;
        
        for (int k = 0; k < 10; k++){
            randomNumber = rnd.nextDouble();
            current = 0.0; //treshold
            success = 0;
            for(int i = 0; i < probabilites.size(); i++){
                current += probabilites.get(0);
                if(randomNumber < current){
                    success = nombreSucces.get(i);
                    break;
                }
            }
            System.out.println(success);
        }
        
        
        /*
        // Creation du main window
        JFrame frame = new JFrame();

        // Exemple tres simple de comment ajouter un evenement lors de la fermeture de l'application avec le X
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Creation d'un bouton
        JButton button = new JButton("click");

        // On place le bouton a un endroit dans la fenetre
        button.setBounds(130, 100, 100, 40);

        // On lui ajouter un action listener qui declanche une methode qui possede qui possede le code devant etre
        // executer lorsque le bouton est clique
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        // On ajoute le bouton a notre fenetre principal
        frame.add(button);

        // Ici on ne fait que changer les propriete de la fenetre
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        // Simple commande permettant d'ecrire sur la console
        System.out.println("hello world");
        */
    }
}

