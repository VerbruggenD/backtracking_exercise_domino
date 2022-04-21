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
        ArrayList<Steen> list = readStones("12R 21G 32R 21P");
        Steen referenceSteen = new Steen(list.get(2));
        boolean found = false;

        // Act

        var resultaat = Algoritme.searchNextTile(list, begin);
        if (resultaat.get().equals(referenceSteen)) found = true;

        // Assert

        assertTrue(found);
        assertFalse((resultaat.get()).isFlipped());
    }

    @Test
    public void Algoritme_searchNextTile_4Items_find3th_containsRemovedTile_expectsFalse() {
        // Arrange

        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> list = readStones("12R 21G 32R 21P");

        // Act

        var resultaat = Algoritme.searchNextTile(list, begin);

        // Assert

        assertFalse(list.contains(resultaat.get()));
    }

    @Test
    public void Algoritme_searchNextTile_4Items_find3th_contains2thTile_expectsTrue() {
        // Arrange

        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> list = readStones("12R 21G 32R 21P");
        Steen steen2 = new Steen(list.get(1));

        // Act

        var resultaat = Algoritme.searchNextTile(list, begin);

        // Assert

        assertTrue(list.contains(steen2));
    }

    @Test
    public void Algoritme_searchNextTile_4Items_find3th_flipped() {
        // Arrange

        Steen begin = new Steen(4,3,Color.YELLOW);
        ArrayList<Steen> list = readStones("12R 21G 23R 21P");

        boolean found = false;

        // Act

        Steen steenFlipped = new Steen(list.get(2));
        var resultaat = Algoritme.searchNextTile(list, begin);
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
        ArrayList<Steen> list = readStones("12R 21G 32R 21P");

        boolean found = false;

        // Act

        var resultaat = Algoritme.searchNextTile(list, begin);
        if (!resultaat.isEmpty()) found = true;

        // Assert

        assertFalse(found);
    }

    @Test
    public void Algoritme_searchNextTile_4Items_findNothingColor() {
        // Arrange

        Steen begin = new Steen(1,3,Color.RED);
        ArrayList<Steen> list = readStones("12R 21G 32R 21P");

        boolean found = false;

        // Act

        var resultaat = Algoritme.searchNextTile(list, begin);
        if (!resultaat.isEmpty()) found = true;

        // Assert

        assertFalse(found);
    }

}
