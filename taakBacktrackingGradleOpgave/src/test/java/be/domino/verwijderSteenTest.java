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

        ArrayList<Steen> ketting = new ArrayList<>();
        ketting.add(new Steen(1,2,Color.RED));
        ketting.add(new Steen(2,3,Color.YELLOW));
        ketting.add(new Steen(3,4,Color.GREEN));

        var removedTile = new Steen(3,4,Color.GREEN);

        ArrayList<Steen> backtrackList = new ArrayList<>();

        // Act

        var originalSize = ketting.size();
        Algoritme.verwijderSteen(ketting, backtrackList, removedTile);
        var sizeNow = ketting.size();

        // Assert

        assertFalse(backtrackList.isEmpty());
        assertTrue(originalSize > sizeNow);

    }

    @Test
    public void Algoritme_verwijderSteen_doesObjectToBeRemovedExists_expectsFalse () {
        // Arrange

        ArrayList<Steen> ketting = new ArrayList<>();
        ketting.add(new Steen(1,2,Color.RED));
        ketting.add(new Steen(2,3,Color.YELLOW));
        ketting.add(new Steen(3,4,Color.GREEN));

        var removedTile = new Steen(4,3,Color.GREEN);

        ArrayList<Steen> backtrackList = new ArrayList<>();

        // Act

        var originalSize = ketting.size();
        Algoritme.verwijderSteen(ketting, backtrackList, removedTile);
        var sizeNow = ketting.size();

        // Assert

        assertTrue(backtrackList.isEmpty());
        assertFalse(originalSize > sizeNow);

    }
}
