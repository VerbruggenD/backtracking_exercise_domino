package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

import static be.domino.Main.readStones;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchNextTileTest {

    @Test
    public void Algoritme_searchNextTile_4Items_find3th() {
        // Arrange
        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");
        Steen referenceSteen = new Steen(source.get(2));
        boolean found = false;

        var column = 1;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(column).add(new Steen(1,2,Color.RED));
        prevPlacedTiles.get(column).add(new Steen(3,1,Color.YELLOW));       // gaat zoeken voor plaats 2 in de ketting
        // Act
        var resultaat = Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        if (resultaat.get().equals(referenceSteen)) found = true;
        // Assert
        assertTrue(found);
        assertFalse((resultaat.get()).isFlipped());
    }

    @Test
    public void Algoritme_searchNextTile_4Items_find3th_containsRemovedTile_expectsFalse() {
        // Arrange
        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");

        var prevPlacedTiles = Algoritme.make2DArrayList(4);

        var column = 1;
        // Act
        var resultaat = Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        // Assert
        assertFalse(source.contains(resultaat.get()));
    }

    @Test
    public void Algoritme_searchNextTile_4Items_find3th_contains2thTile_expectsTrue() {
        // Arrange
        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");
        Steen steen2 = new Steen(source.get(1));

        var column = 1;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(column).add(new Steen(1,2,Color.RED));
        prevPlacedTiles.get(column).add(new Steen(3,1,Color.YELLOW));
        // Act
        Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        // Assert
        assertTrue(source.contains(steen2));
    }

    @Test
    public void Algoritme_searchNextTile_4Items_find3th_flipped() {
        // Arrange
        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 23R 21P");

        boolean found = false;

        var column = 1;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(column).add(new Steen(1,2,Color.RED));
        prevPlacedTiles.get(column).add(new Steen(3,1,Color.YELLOW));
        // Act
        Steen steenFlipped = new Steen(source.get(2));
        var resultaat = Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        steenFlipped.flip();
        if (resultaat.get().equals(steenFlipped)) found = true;
        // Assert
        assertTrue(found);
        assertTrue(resultaat.get().isFlipped());
    }

    @Test
    public void Algoritme_searchNextTile_4Items_findNothingEyes() {
        // Arrange
        Steen begin = new Steen(4,4,Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");

        boolean found = false;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);

        var column = 1;
        // Act
        var resultaat = Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        if (!resultaat.isEmpty()) found = true;
        // Assert
        assertFalse(found);
    }

    @Test
    public void Algoritme_searchNextTile_4Items_findNothingColor() {
        // Arrange
        Steen begin = new Steen(1,3,Color.RED);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");

        boolean found = false;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);

        var column = 1;
        // Act
        var resultaat = Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        if (!resultaat.isEmpty()) found = true;
        // Assert
        assertFalse(found);
    }

    @Test
    public void Algoritme_searchNextTile_4items_findNothing_alreadyTried_expectFalse() {
        // Arrange
        Steen begin = new Steen(1,3,Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");

        var column = 1;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(column).add(new Steen(3,2,Color.RED));
        // Act
        var resultaat = Algoritme.searchNextTile(source, Optional.of(begin), prevPlacedTiles.get(column));
        // Assert
        assertFalse(resultaat.isPresent());
    }

    @Test
    public void Algoritme_searchNextTile_4items_newFirstTile_finds2th_expectTrue() {
        // Arrange
        Steen begin = new Steen(1, 3, Color.YELLOW);
        ArrayList<Steen> source = readStones("12R 21G 32R 21P");

        var steen = new Steen(2, 1, Color.GREEN);

        var column = 0;

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(column).add(new Steen(1, 2, Color.RED));
        // Act
        var result = Algoritme.searchNextTile(source, Optional.empty(), prevPlacedTiles.get(column));
        // Assert
        assertTrue(steen.equals(result.get()));
    }
}
