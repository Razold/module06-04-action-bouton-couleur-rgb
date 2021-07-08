package fr.eni.main;

import javax.swing.SwingUtilities;

import fr.eni.ihm.CouleurIhm;


public class AppelFenetre {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				CouleurIhm cihm= new CouleurIhm();
				cihm.setVisible(true);//nécessaire pour que l'interface graphique soit visible!!!
			}
		
		});
	}

}
