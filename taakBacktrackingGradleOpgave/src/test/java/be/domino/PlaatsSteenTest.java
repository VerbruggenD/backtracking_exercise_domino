package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlaatsSteenTest {

    @Test
    public void Algoritme_plaatsSteen_testItemAdded() {
        // Arrange

        ArrayList<Steen> sourceList = new ArrayList<>();
        sourceList.add(new Steen(1,2, Color.YELLOW));
        sourceList.add(new Steen(3,4,Color.RED));

        ArrayList<Steen> destinList = new ArrayList<>();
        destinList.add(new Steen(1,1,Color.BLUE));

        Steen steen = new Steen(sourceList.get(0));

        var prevPlaced = Algoritme.make2DArrayList(4);

        // Act

        var result = Algoritme.plaatsSteen(sourceList,destinList, prevPlaced);

        var sizeDest = destinList.size();

        // Assert

        assertTrue(result.get());
        assertTrue(sizeDest == 2);
        assertTrue(destinList.contains(steen));
        //assertTrue(steen.equals(placedSteen));
    }

    @Test
    public void Algoritme_plaatsSteen_testItemRemoved() {
        // Arrange

        ArrayList<Steen> sourceList = new ArrayList<>();
        sourceList.add(new Steen(1,2, Color.YELLOW));
        sourceList.add(new Steen(3,4,Color.RED));

        ArrayList<Steen> destinList = new ArrayList<>();
        destinList.add(new Steen(1,1,Color.BLUE));

        var prevPlaced = Algoritme.make2DArrayList(4);

        Steen steen = new Steen(sourceList.get(0));

        // Act

        var result = Algoritme.plaatsSteen(sourceList,destinList, prevPlaced);
        var sizeSource = sourceList.size();

        // Assert

        assertTrue(result.get());
        assertTrue(sizeSource == 1);
        assertFalse(sourceList.contains(steen));
    }

    @Test
    public void Algoritme_plaatsSteen_emptySourceArray_resultIsEmpty_expectsTrue() {
        // Arrange

        ArrayList<Steen> sourceList = new ArrayList<>();

        ArrayList<Steen> destinList = new ArrayList<>();
        destinList.add(new Steen(1,1,Color.BLUE));

        var prevPlaced = Algoritme.make2DArrayList(4);

        // Act

        var result = Algoritme.plaatsSteen(sourceList,destinList, prevPlaced);

        // Assert

        assertTrue(result.isEmpty());
    }

    @Test
    public void Algoritme_plaatsSteen_nothingPlaced_nextTileNotFound_expectsFalse() {
        // Arrange

        ArrayList<Steen> sourceList = new ArrayList<>();
        sourceList.add(new Steen(3,2, Color.YELLOW));
        sourceList.add(new Steen(3,4,Color.RED));

        ArrayList<Steen> destinList = new ArrayList<>();
        destinList.add(new Steen(1,1,Color.BLUE));

        var prevPlaced = Algoritme.make2DArrayList(4);

        var errorThrown = false;

        // Act

        var originalSize = sourceList.size();
        var result = Algoritme.plaatsSteen(sourceList, destinList, prevPlaced);
        var sizeNow = sourceList.size();

        if (!result.get()) {
            errorThrown = true;
        }

        // Assert

        assertTrue(errorThrown);
        assertTrue(sizeNow == originalSize);
    }

    @Test public void Algoritme_plaatsSteen_firstTile_destinationListEmpty_expectsPossible() {

        // Arrange

        ArrayList<Steen> sourceList = new ArrayList<>();
        sourceList.add(new Steen(3,2, Color.YELLOW));
        sourceList.add(new Steen(3,4,Color.RED));

        ArrayList<Steen> destinListEmpty = new ArrayList<>();

        var prevPlaced = Algoritme.make2DArrayList(4);

        // Act

        var originalSizeDest = destinListEmpty.size();
        var result = Algoritme.plaatsSteen(sourceList, destinListEmpty, prevPlaced);
        var newSizeDest = destinListEmpty.size();

        // Assert

        assertEquals(0, originalSizeDest);
        assertEquals(1, newSizeDest);
        assertTrue(result.get());

    }

    @Test
    public void Algoritme_plaatsSteen_alreadyTriedThisTile_expectsfalse () {
        // Arrange

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(1).add(new Steen(1,2,Color.RED));
        prevPlacedTiles.get(1).add(new Steen(1,3,Color.YELLOW));

        ArrayList<Steen> dest = new ArrayList<>();
        dest.add(new Steen(1,1,Color.BLUE));

        ArrayList<Steen> source = new ArrayList<>();
        source.add(new Steen(1,2,Color.RED));
        source.add(new Steen(1,3,Color.YELLOW));

        // Act

        var result = Algoritme.plaatsSteen(source, dest, prevPlacedTiles);

        // Assert

        assertFalse(result.get());
    }
}
