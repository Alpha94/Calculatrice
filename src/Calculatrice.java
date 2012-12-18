import java.awt.BorderLayout; // placements (nord, sud ...)
import java.awt.Color;	// couleurs
import java.awt.Dimension;	// dimensions
import java.awt.Font;	// police
import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
<<<<<<< HEAD
<<<<<<< HEAD
// commentaire
=======
// test commit
>>>>>>> bf258c9a503423b614daf4684f67c404a361ebc4
=======

>>>>>>> parent of 28045f4... Ajout commentaire
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; // affichage
import javax.swing.JPanel;


public class Calculatrice extends JFrame {

        private JPanel container = new JPanel(); //panneau
        
        String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "=", "C", "+", "-", "*", "/"};
        JButton[] tab_button = new JButton[tab_string.length]; // long tab-1
        
        private JLabel ecran = new JLabel(); // affichage
        private Dimension dim = new Dimension(50, 40); // (Largeur chiffre, hauteur chiffre) 
        private Dimension dim2 = new Dimension(50, 31); // (Largeur symbole, hauteur symbole)
        private double chiffre1;
        private boolean clicOperateur = false, update = false;
        private String operateur = "";
        
        public Calculatrice(){
                
                this.setSize(350, 300); //(Largeur, Hauteur)
                this.setTitle("Calculatrice"); //Nom programme
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                initComposant();
                
                this.setContentPane(container);
                this.setVisible(true);
        }
        
        private void initComposant(){

                Font police = new Font("Calibri", Font.BOLD, 15);
                ecran = new JLabel("0");
                ecran.setFont(police);
                ecran.setHorizontalAlignment(JLabel.CENTER); //Alignement dans le cadre
                ecran.setPreferredSize(new Dimension(220, 20));
                
                JPanel operateur = new JPanel();        
                operateur.setPreferredSize(new Dimension(55, 225));
                JPanel chiffre = new JPanel();
                chiffre.setPreferredSize(new Dimension(165, 225));
                JPanel panEcran = new JPanel();
                panEcran.setPreferredSize(new Dimension(220, 30)); //(Largeur, Hauteur cadre)

                
                for(int i = 0; i < tab_string.length; i++)
                {
                    tab_button[i] = new JButton(tab_string[i]);		// création des boutons
                    tab_button[i].setPreferredSize(dim);
                    
                    switch(i){
	                
                    	case 11 :
                    		tab_button[i].setForeground(Color.red);
                    		tab_button[i].addActionListener(new EgalListener());
                    		chiffre.add(tab_button[i]); // égale
                    		break;
                    	
                    	case 12 :
                    		tab_button[i].setForeground(Color.blue);
	                        tab_button[i].addActionListener(new ResetListener());
	                        tab_button[i].setPreferredSize(dim2);
	                        operateur.add(tab_button[i]); // opérande
                    		break;
                    	    
                    	case 13 :
                    		tab_button[i].setForeground(Color.black);
                    		tab_button[i].addActionListener(new PlusListener());
                    		tab_button[i].setPreferredSize(dim2);
                    		operateur.add(tab_button[i]);
                    		break;
                    	
                    	case 14 :
                    		tab_button[i].setForeground(Color.black);
                    		tab_button[i].addActionListener(new MoinsListener());
                    		tab_button[i].setPreferredSize(dim2);
                    		operateur.add(tab_button[i]);
                    		break;	
                    	
                    	case 15 :	
                    		tab_button[i].setForeground(Color.black);
                    		tab_button[i].addActionListener(new MultiListener());
                    		tab_button[i].setPreferredSize(dim2);
                    		operateur.add(tab_button[i]);
                    		break;
	                    
                    	case 16 :
                    		tab_button[i].setForeground(Color.black);
                    		tab_button[i].addActionListener(new DivListener());
                    		tab_button[i].setPreferredSize(dim2);
                    		operateur.add(tab_button[i]);
                    		break;
                    		
                    	case 17 :
                    		tab_button[i].setForeground(Color.black);
                    		tab_button[i].addActionListener(new DivListener());
                    		tab_button[i].setPreferredSize(dim2);
                    		operateur.add(tab_button[i]);
                    		break;	
                    	                    	
                    	default :
                    		tab_button[i].addActionListener(new ChiffreListener());
                    		chiffre.add(tab_button[i]); // chiffre
                    		break;
                    }
                }
                
                panEcran.add(ecran);
                panEcran.setBorder(BorderFactory.createLineBorder(Color.black)); // bordure affichage
 
                container.add(panEcran, BorderLayout.NORTH); // affichage
                container.add(chiffre, BorderLayout.CENTER); // corps
                container.add(operateur, BorderLayout.EAST); // opérande
        }
        
        
        private void calcul(){
                if(operateur.equals("+"))
                {
                        chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }
                        
                if(operateur.equals("-"))
                {
                        chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }               
                
                if(operateur.equals("*"))
                {
                        chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }       
                        
                if(operateur.equals("/"))
                {
                        try{
                                chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
                                ecran.setText(String.valueOf(chiffre1));
                        } catch (ArithmeticException e){
                                ecran.setText("0");
                        }
                }
        }
        
        class ChiffreListener implements ActionListener{
                public void actionPerformed(ActionEvent e) {
                        //On affiche le chiffre en plus dans le label
                        String str = ((JButton)e.getSource()).getText();
                        
                        if(update)
                        {
                                update = false;
                        }
                        else
                        {
                                if(!ecran.getText().equals("0")) // si écran = 0
                                        str = ecran.getText() + str;
                        }
                        
                        ecran.setText(str);
                }
        }
 
        
        class EgalListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        calcul();
                        update = true;
                        clicOperateur = false;
                }    
        }
        
        
        class PlusListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "+";
                        update = true;
                }
        }
        
        class MoinsListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "-";
                        update = true;
                }
        }
        
        class MultiListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "*";
                        update = true;
                }
        }
        
        class DivListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "/";
                        update = true;
                }
        }
        
        class ResetListener implements ActionListener{ // boutton reset
                public void actionPerformed(ActionEvent arg0) {
                        clicOperateur = false;
                        update = true;
                        chiffre1 = 0;
                        operateur = "";
                        ecran.setText(""); // vide quand clic sur reset
                }
        }
        
}
