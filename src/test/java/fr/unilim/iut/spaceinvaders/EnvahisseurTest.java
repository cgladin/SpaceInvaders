package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class EnvahisseurTest {

	private SpaceInvaders spaceinvaders;
	@Before
	    public void initialisation() {
		    spaceinvaders = new SpaceInvaders(15, 10);
	  }
	
	 @Test
	  public void  test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {

		  SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
		  spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1), new Position(7,1), 1);

	       assertEquals("" +
	       "...............\n" + 
	       ".......EEE.....\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());

	  }
   @Test
	public void test_UnNouveauEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {
		
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(15,9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(-1,9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(14,10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1),new Position(14,-1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}	
	}
   @Test
	public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2),new Position(7,9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		
		
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4),new Position(7,1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}	
	}
   
   @Test
   public void test_EnvahisseurAvanceAutomatiquement_VersLaDroite() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(7,1), 3);

	   spaceinvaders.deplacerEnvahisseur();
	   
      assertEquals("" + 
      "...............\n" + 
      "..........EEE..\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
  }

@Test
   public void test_EnvahisseurAvanceAutomatiquement_VersLaGauche() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(7,1), 3);
	   spaceinvaders.deplacerEnvahisseurVersLaGauche();
      assertEquals("" + 
      "...............\n" + 
      "....EEE........\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
  }

@Test
public void test_Envahisseur_ArriveAuBoutDeLaLigneVersLaDroite_PuisChangeDirectionVersLaGauche() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(11,1), 3);
	   
	   spaceinvaders.deplacerEnvahisseur();
	   
      assertEquals("" + 
      "...............\n" + 
      "............EEE\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
      
      
      assertFalse(spaceinvaders.recupererUnEnvahisseur().SeDeplaceVersLaDroite());
  }

@Test
public void test_Envahisseur_ArriveAuBoutDeLaLigneVersLaGauche_PuisChangeDirectionVersLaDroite() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(1,1), 3);
	   
	   spaceinvaders.recupererUnEnvahisseur().changerDirectionHoziontale();
	   spaceinvaders.deplacerEnvahisseur();
	   
	   assertEquals("" + 
     "...............\n" + 
     "EEE............\n" +
     "...............\n" + 
     "...............\n" + 
     "...............\n" + 
     "...............\n" + 
     "...............\n" + 
     "...............\n" + 
     "...............\n" + 
     "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     
     
     assertTrue(spaceinvaders.recupererUnEnvahisseur().SeDeplaceVersLaDroite());
}
   
 
}
