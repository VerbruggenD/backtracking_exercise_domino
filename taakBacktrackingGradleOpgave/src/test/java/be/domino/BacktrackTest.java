package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackTest {

    @Test
    public void Algoritme_backtrack_kettingSluiten_expectsTrue() {
        // Arrange
        ArrayList<Steen> source = new ArrayList<>();

        var prevPlacedTiles = Algoritme.make2DArrayList(4);

        ArrayList<Steen> dest = new ArrayList<>();
        dest.add(new Steen(1,2, Color.RED));
        dest.add(new Steen(2,3,Color.YELLOW));
        dest.add(new Steen(3,1,Color.blue));
        dest.add(new Steen(1,5,Color.GREEN));

        // Act

        var result = Algoritme.backtrack(source, dest, prevPlacedTiles);

        // Assert

        assertTrue(Algoritme.checkChainClosing(dest.get(dest.size()-1), dest.get(0)));
    }

    @Test
    public void Algoritme_backtrack_kettingNietSluiten_expectsFalse_doeBacktrack() {
        // Arrange
        ArrayList<Steen> source = new ArrayList<>();

        var prevPlacedTiles = Algoritme.make2DArrayList(4);

        ArrayList<Steen> dest = new ArrayList<>();
        dest.add(new Steen(1,2, Color.RED));
        dest.add(new Steen(2,3,Color.YELLOW));
        dest.add(new Steen(3,2,Color.blue));

        var originalSize = dest.size();

        // Act

        var result = Algoritme.backtrack(source, dest, prevPlacedTiles);
        var sizeNow = dest.size();

        // Assert

        assertFalse(Algoritme.checkChainClosing(dest.get(dest.size()-1), dest.get(0)));
        assertFalse(originalSize<sizeNow);
    }

    @Test
    public void Algoritme_backtrack_nieuwBegin_expectsSame() {
        // Arrange
        var steen = new Steen(3,4,Color.BLUE);

        ArrayList<Steen> source = new ArrayList<>();
        source.add(steen);

        var prevPlacedTiles = Algoritme.make2DArrayList(4);

        ArrayList<Steen> dest = new ArrayList<>();
        dest.add(new Steen(1,2, Color.RED));

        // Act

        var result = Algoritme.backtrack(source, dest, prevPlacedTiles);
        Algoritme.plaatsSteen(source, dest, prevPlacedTiles);

        // Assert

        assertSame(dest.get(0), steen);
    }

    @Test
    public void Algoritme_backtrack_prevPlacedUpdated_expectsTrue() {
        // Arrange

        ArrayList<Steen> source = new ArrayList<>();
        ArrayList<Steen> dest = new ArrayList<>();

        dest.add(new Steen(1,2,Color.RED));
        dest.add(new Steen(2,3,Color.YELLOW));
        dest.add(new Steen(3,4,Color.BLUE));

        var prevPlacedTiles = Algoritme.make2DArrayList(4);
        prevPlacedTiles.get(2).add(new Steen(1,2,Color.RED));
        prevPlacedTiles.get(2).add((new Steen(3,4,Color.GREEN)));

        // Act

        var result1 = Algoritme.backtrack(source, dest, prevPlacedTiles);
        Algoritme.backtrack(source, dest, prevPlacedTiles);

        // Assert

        assertTrue(result1);
        assertTrue(prevPlacedTiles.get(2).isEmpty());

    }
}
