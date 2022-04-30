package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class verwijderSteenTest {

    @Test
    public void Algoritme_verwijderSteen_doesObjectToBeRemovedExists_expectsTrue () {
        // Arrange

        ArrayList<Steen> dest = new ArrayList<>();
        dest.add(new Steen(1,2,Color.RED));
        dest.add(new Steen(2,3,Color.YELLOW));
        dest.add(new Steen(3,4,Color.GREEN));

        var removedTile = new Steen(3,4,Color.GREEN);

        ArrayList<Steen> source = new ArrayList<>();

        // Act

        var originalSize = dest.size();
        Algoritme.verwijderSteen(source, dest, removedTile);
        var sizeNow = dest.size();

        // Assert

        assertFalse(source.isEmpty());
        assertTrue(originalSize > sizeNow);

    }

    @Test
    public void Algoritme_verwijderSteen_doesObjectToBeRemovedExists_expectsFalse () {
        // Arrange

        ArrayList<Steen> dest = new ArrayList<>();
        dest.add(new Steen(1,2,Color.RED));
        dest.add(new Steen(2,3,Color.YELLOW));
        dest.add(new Steen(3,4,Color.GREEN));

        var removedTile = new Steen(4,3,Color.GREEN);

        ArrayList<Steen> source = new ArrayList<>();

        // Act

        var originalSize = dest.size();
        Algoritme.verwijderSteen(dest, source, removedTile);
        var sizeNow = dest.size();

        // Assert

        assertTrue(source.isEmpty());
        assertFalse(originalSize > sizeNow);

    }
}
