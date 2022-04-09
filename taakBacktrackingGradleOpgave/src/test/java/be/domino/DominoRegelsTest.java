package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DominoRegelsTest {
    @Test
    public void Algoritme_checkOgen_2DezelfdeOgen() {  // enkel naar de ogen kijken
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(2,3,Color.RED);
        // Act
        var resultaat = Algoritme.checkOgen(steen1, steen2);
        // Assert
        assertTrue(resultaat);
    }

    @Test
    public void Algoritme_checkOgen_2VerschillendeOgen() {
        // Arrange
        var steen1 = new Steen(1,2, Color.YELLOW);
        var steen2 = new Steen(3,4,Color.RED);
        // Act
        var resultaat = Algoritme.checkOgen(steen1, steen2);
        // Assert
        assertFalse(resultaat);
    }
}
