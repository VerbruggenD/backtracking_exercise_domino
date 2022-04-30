package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DominoRegelsTest {

    // !!! check functie test voor blokje achteraan toevoegen
    @Test
    public void Algoritme_checkEyes_2SameEyes_unFlipped_expectTrue() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(2,3,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2);
        // Assert
        assertTrue(resultaat);
        assertFalse(steen2.isFlipped());
    }

    @Test
    public void Algoritme_checkEyes_2SameEyes_flipped_expectTrue() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(3,2,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2);
        // Assert
        assertTrue(resultaat);
        assertTrue(steen2.isFlipped());
    }

    @Test
    public void Algoritme_checkEyes_2DifferentEyes_expectFalse() { // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(3,4,Color.RED);
        // Act
        var resultaat = Algoritme.checkEyesAndFlip(steen1, steen2);
        // Assert
        assertFalse(resultaat);
        assertFalse(steen2.isFlipped());
    }

    // check functie test voor kleur

    @Test
    public void Algoritme_checkColor_2SameColors_expectTrue() { // enkel naar de kleur kijken
        // Arrange
        var steen1 = new Steen(1, 2, Color.YELLOW);
        var steen2 = new Steen(2,3,Color.RED);
        // Act
        var resultaat = Algoritme.checkColor(steen1, steen2);
        // Assert
        assertTrue(resultaat);
    }

    @Test
    public void Algoritme_checkColor_2SameColors_expectFalse() { // enkel naar de kleur kijken
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
    public void Algoritme_checkEyesClosing_2SameEyes_expectTrue() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(2,3,Color.RED);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkEyesClosing(steen1, steen2);
        // Assert
        assertTrue(resultaat);
    }

    @Test
    public void Algoritme_checkEyesClosing_2SameEyes_expectFalse() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(3,4,Color.RED);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkEyesClosing(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }

    @Test
    public void Algoritme_checkChainClosing_2SameEyes_differentColor_expectTrue() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(2,4,Color.RED);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkChainClosing(steen1, steen2);
        // Assert
        assertTrue(resultaat);
    }

    @Test
    public void Algoritme_checkChainClosing_2DifferentEyes_differentColor_expectFalse() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(3,4,Color.RED);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkChainClosing(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }

    @Test
    public void Algoritme_checkChainClosing_2SameEyes_sameColor_expectFalse() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(2,4,Color.YELLOW);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkChainClosing(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }

    @Test
    public void Algoritme_checkChainClosing_2DifferentEyes_sameColor_expectFalse() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);  // dit is de head
        var steen2 = new Steen(3,4,Color.YELLOW);  // dit is de tail
        // Act
        var resultaat = Algoritme.checkChainClosing(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }
}
