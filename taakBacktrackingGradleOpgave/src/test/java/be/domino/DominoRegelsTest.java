package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DominoRegelsTest {

    // !!! check functie test voor blokje achteraan toevoegen
    @Test
    public void Algoritme_checkOgen_2DezelfdeOgen_unFlipped_achteraan_expectTrue() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(2,3,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2, Algoritme.HEAD);
        // Assert
        assertTrue(resultaat);
        assertFalse(steen2.isFlipped());
    }

    @Test
    public void Algoritme_checkOgen_2DezelfdeOgen_flipped_achteraan_expectTrue() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(3,2,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2, Algoritme.HEAD);
        // Assert
        assertTrue(resultaat);
        assertTrue(steen2.isFlipped());
    }

    @Test
    public void Algoritme_checkOgen_2VerschillendeOgen_achteraan_expectFalse() { // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(3,4,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2, Algoritme.HEAD);
        // Assert
        assertFalse(resultaat);
        assertFalse(steen2.isFlipped());
    }

    // check functie test voor blokje vooraan toevoegen

    @Test
    public void Algoritme_checkOgen_2DezelfdeOgen_unFlipped_vooraan_expectTrue() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(3,2, Color.YELLOW);
        var steen2 = new Steen(1,3,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2, Algoritme.TAIL);
        // Assert
        assertTrue(resultaat);
        assertFalse(steen2.isFlipped());
    }

    @Test
    public void Algoritme_checkOgen_2DezelfdeOgen_flipped_vooraan_expectTrue() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(3,2, Color.YELLOW);
        var steen2 = new Steen(3,1,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2, Algoritme.TAIL);
        // Assert
        assertTrue(resultaat);
        assertTrue(steen2.isFlipped());
    }

    @Test
    public void Algoritme_checkOgen_2VerschillendeOgen_vooraan_expectFalse() { // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(3,4,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2, Algoritme.TAIL);
        // Assert
        assertFalse(resultaat);
        assertFalse(steen2.isFlipped());
    }

    // check functie test voor kleur

    @Test
    public void Algoritme_checkKleur_2VerschillendeKleuren_expectTrue() { // enkel naar de kleur kijken
        // Arrange
        var steen1 = new Steen(1, 2, Color.YELLOW);
        var steen2 = new Steen(2,3,Color.RED);
        // Act
        var resultaat = Algoritme.checkColor(steen1, steen2);
        // Assert
        assertTrue(resultaat);
    }

    @Test
    public void Algoritme_checkKleur_2DezelfdeKleuren_expectFalse() { // enkel naar de kleur kijken
        // Arrange
        var steen1 = new Steen(1, 2, Color.YELLOW);
        var steen2 = new Steen(2,3,Color.YELLOW);
        // Act
        var resultaat = Algoritme.checkColor(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }

    // check functie test voor ketting te sluiten --> enkel naar ogen kijken zonder te flippen

    @Test
    public void Algoritme_checkOgenSluiten_2DezelfdeOgen_expectTrue() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(2,3,Color.RED);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkEyesClosing(steen1, steen2);
        // Assert
        assertTrue(resultaat);
    }

    @Test
    public void Algoritme_checkOgenSluiten_2VerschillendeOgen_expectFalse() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(3,4,Color.RED);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkEyesClosing(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }
}
