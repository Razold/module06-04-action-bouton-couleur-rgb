package fr.eni.ihm;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.TypeConstraintException;


public class CouleurIhm extends JFrame {
	private JPanel panneauPrincipal;
	private JLabel label;
	private JTextField textFieldRouge;
	private JTextField textFieldVert;
	private JTextField textFieldBleu;
	
	private JButton bRouge;
	private JButton bVert;
	private JButton bBleu;
	private JButton bRgb;
	

	
	public CouleurIhm() {
		super("couleurs");
	
		gestionComposants();//On initialise les différents paramètres
		
		this.setSize(400,150);//Autre maniere de faire une fenêtre rectangulaire en interface graphique
		this.setLocationRelativeTo(null);//position la fenêtre dans l'écran mettre null la positionne au centre
		//this.setResizable(false);//Interdire le redimensionnement de la fenêtre par l'utilisateur
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//On supprime l'instance de la fenêtre 	
	}
	
	private void gestionComposants() {
		//Création des différents éléments dont le panneau dans lequel tout les autres éléments seront intégré
		panneauPrincipal = new JPanel();
		panneauPrincipal.setBackground(Color.black);
		
		label = new JLabel("");
		bRouge = new JButton("Rouge");
		bVert = new JButton("Vert");
		bBleu = new JButton("Bleu");
		textFieldRouge = new JTextField(10);//Champ de saisie avec longueur de 10
		textFieldVert = new JTextField(10);//Champ de saisie avec longueur de 10
		textFieldBleu = new JTextField(10);//Champ de saisie avec longueur de 10
		textFieldRouge.setText("0");//Champ de saisie avec longueur de 10
		textFieldVert.setText("0");//Champ de saisie avec longueur de 10
		textFieldBleu.setText("0");//Champ de saisie avec longueur de 10


		bRgb = new JButton("RGB");
		this.getContentPane().add(panneauPrincipal);//Renvoyer le panneau courant pour ensuite rajouter dessus le panneau créé
		
		//ajout des différents éléments au panneau
		
		panneauPrincipal.add(bRouge);
		panneauPrincipal.add(bVert);
		panneauPrincipal.add(bBleu);
		panneauPrincipal.add(textFieldRouge);
		panneauPrincipal.add(textFieldVert);
		panneauPrincipal.add(textFieldBleu);
		panneauPrincipal.add(bRgb);
		panneauPrincipal.add(label);
		bRouge.addActionListener(new BoutonRougeActionListener());//détecte un clique sur le bouton nommé Rouge
		bVert.addActionListener(new BoutonVertActionListener());//détecte un clique sur le bouton nommé Vert
		bBleu.addActionListener(new BoutonBleuActionListener());//détecte un clique sur le bouton nommé Bleu

		bRgb.addActionListener(new BoutonRgbActionListener());//détecte un clique sur le bouton nommé Bleu


	}
	
	class BoutonRougeActionListener implements ActionListener{//Permet de définir les action à effectueur en fonction de l'action sur le bouton

		@Override
		public void actionPerformed(ActionEvent e) {	
			//e.getSource() permet en le comparant avec un des bouton que c'est bien celui là qui est cliqué.Cela sert si l'on veut regrouper les 3 fonctions BoutonCouleurActionListener en une seule
			panneauPrincipal.setBackground(Color.red);// si l'on mettait le nom de la couleur en majuscule Color.RED ça ne changerait rien, on peut donc l'écrire  à la place si on le souhaite.
			textFieldRouge.setText("255");
			textFieldVert.setText("0");
			textFieldBleu.setText("0");
			if(label.getText() =="mauvaise valeur"){	
				label.setForeground(Color.black);
			}
			// par convention on utilise les majuscules pour les constance
		}											  //La différence entre Color.maCouleurEnMinuscule et Color.maCouleurEnMajuscule est que les majuscules le mettent directement alors que les minuscules pointe vers l'équivalent en majuscules(elles font référence à la version en majuscules)
	}
	
	class BoutonVertActionListener implements ActionListener{//Permet de définir les action à effectueur en fonction de l'action sur le bouton

		@Override
		public void actionPerformed(ActionEvent e) {	
			textFieldRouge.setText("0");
			textFieldVert.setText("255");
			textFieldBleu.setText("0");
			panneauPrincipal.setBackground(Color.green);
			if(label.getText() =="bonnes valeurs"){	
				label.setForeground(Color.black);
			}
			
		}	
	}
	
	class BoutonBleuActionListener implements ActionListener{//Permet de définir les action à effectueur en fonction de l'action sur le bouton

		@Override
		public void actionPerformed(ActionEvent e) {			
			panneauPrincipal.setBackground(Color.blue);
			textFieldRouge.setText("0");
			textFieldVert.setText("0");
			textFieldBleu.setText("255");
		}	
	}
	
	class BoutonRgbActionListener implements ActionListener{//Permet de définir les action à effectueur en fonction de l'action sur le bouton

		@Override
		public void actionPerformed(ActionEvent e) {

			
			try {
				int rgbRouge = 0;
				int rgbVert = 0;
				int rgbBleu = 0;
				rgbRouge=Integer.parseInt(textFieldRouge.getText().trim());//Intensite de Rouge de 0 à 255
				rgbVert=Integer.parseInt(textFieldVert.getText().trim());//Intensite de Vert de 0 à 255
				rgbBleu=Integer.parseInt(textFieldBleu.getText().trim());//Intensite de Bleu de 0 à 255
				if(rgbRouge >= 0 && rgbRouge <= 255 && rgbVert >= 0 && rgbVert <= 255 && rgbBleu >= 0 && rgbBleu <= 255) {
					panneauPrincipal.setBackground(new Color(rgbRouge,rgbVert, rgbBleu));
					label.setText("bonnes valeurs");
					if(panneauPrincipal.getBackground() == Color.green ||panneauPrincipal.getBackground()== Color.GREEN) {	
						label.setForeground(Color.black);
					}else {
						label.setForeground(Color.green);						
					}

				}else {
					throw (new Exception());
				}
			}catch(Exception erreurSaisie) {	//Le programme ne remarque l'erreur que si la valeur saisie n'est pas un int ou si l'un des champs n'est pas saisie et met en noir soit 0,0,0 i il pense que les valeurs saisies sont bonnes
				label.setText("mauvaise valeur");
				if(panneauPrincipal.getBackground() == Color.red ||panneauPrincipal.getBackground()== Color.RED) {		
					label.setForeground(Color.black);					
				}else {
					label.setForeground(Color.red);					
				}
				//3 exceptions possibles
				//Exception  pour saisie vide
				//Exception pour saisie pas de type entier NumerFormatException
				//Exception non entrée dans le if mais dans le else
				
				if(erreurSaisie instanceof NumberFormatException) {
					if(erreurSaisie instanceof NullPointerException) {
						System.out.println("La saisie n'est pas faite.");						
					}else {
						System.out.println("L'une des valeurs n'est pas un entier.");
					}

				}else {
					System.out.println("L'une des valeurs n'est pas comprise entre 0 et 255.");					
				}

			}	
			//e.getSource() permet en le comparant avec un des bouton que c'est bien celui là qui est cliqué.Cela sert si l'on veut regrouper les 3 fonctions BoutonCouleurActionListener en une seule
			// si l'on mettait le nom de la couleur en majuscule Color.RED ça ne changerait rien, on peut donc l'écrire  à la place si on le souhaite.
													  // par convention on utilise les majuscules pour les constance
		}											  //La différence entre Color.maCouleurEnMinuscule et Color.maCouleurEnMajuscule est que les majuscules le mettent directement alors que les minuscules pointe vers l'équivalent en majuscules(elles font référence à la version en majuscules)
	}
}
