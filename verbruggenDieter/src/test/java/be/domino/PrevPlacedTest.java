package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrevPlacedTest {

    @Test
    public void Algoritme_writePrevPlacedTile_add2Items_check1e_check2e_expectsSame () {
        // Arrange
        var prevPlacedTiles = Algoritme.make2DArrayList(3);

        Steen steen1 = new Steen(1,2, Color.RED);
        Steen steen2 = new Steen(3,4, Color.GREEN);
        // Act
        Algoritme.writePrevPlacedTile(steen1, prevPlacedTiles, 0);      // steen 1 in rij 0 kolom 0
        Algoritme.writePrevPlacedTile(steen2, prevPlacedTiles, 1);     // steen 1 in rij 0 kolom 1
        // Assert
        assertSame(steen1, prevPlacedTiles.get(0).get(0));
        assertSame(steen2, prevPlacedTiles.get(1).get(0));
    }

    @Test
    public void Algoritme_checkPrevPlacedTiles_noMatch_column0_expectFalse() {
        // Arrange
        Steen steen1 = new Steen(1,2,Color.RED);
        Steen steen2 = new Steen(3,4,Color.YELLOW);

        var prevPlaced = Algoritme.make2DArrayList(4);
        Algoritme.writePrevPlacedTile(steen2, prevPlaced, 0);
        // Act
        var result = Algoritme.checkPrevPlacedTiles(steen1, prevPlaced.get(0));
        // Assert
        //assertTrue(prevPlaced[0][0].size() == 1);
        assertFalse(result);
    }

    @Test
    public void Algoritme_checkPrevPlacedTiles_isAMatch_column0_expectTrue() {
        // Arrange
        Steen steen1 = new Steen(1,2,Color.RED);
        Steen steen2 = new Steen(1,2,Color.RED);

        var prevPlaced = Algoritme.make2DArrayList(4);
        Algoritme.writePrevPlacedTile(steen2, prevPlaced, 0);
        // Act
        var result = Algoritme.checkPrevPlacedTiles(steen1, prevPlaced.get(0));
        // Assert
        //assertTrue(prevPlaced[0][0].size() == 1);
        assertTrue(result);
    }

    @Test
    public void Algoritme_checkPrevPlacedTiles_isAMatch_column1_expectTrue() {
        // Arrange
        Steen steen1 = new Steen(3,4,Color.RED);
        Steen steen2 = new Steen(1,2,Color.RED);

        var prevPlaced = Algoritme.make2DArrayList(4);
        Algoritme.writePrevPlacedTile(steen2, prevPlaced, 0);
        Algoritme.writePrevPlacedTile(steen1, prevPlaced, 1);
        // Act
        var result = Algoritme.checkPrevPlacedTiles(steen1, prevPlaced.get(1));
        // Assert
        //assertTrue(prevPlaced[0][0].size() == 1);
        assertTrue(result);
    }
}
