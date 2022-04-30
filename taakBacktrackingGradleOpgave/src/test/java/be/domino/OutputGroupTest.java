package be.domino;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutputGroupTest {

    @Test
    public void Algoritme_writeChain_oneSolution_to2DArrayList_testDeepCopy_outputGroupEmpty_expectsFalse() {
        // Arrange
        ArrayList<Steen> chain = new ArrayList<>();
        chain.add(new Steen(1,2, Color.RED));
        chain.add(new Steen(2,3,Color.BLUE));
        chain.add(new Steen(3,4,Color.GREEN));
        chain.add(new Steen(4,1,Color.YELLOW));

        ArrayList<ArrayList<Steen>> outputGroup = new ArrayList<>();
        // Act
        Algoritme.writeChain(chain, outputGroup);

        chain.clear();
        // Assert
        assertFalse(outputGroup.isEmpty());
    }

    @Test
    public void Algoritme_writeChain_twoSolutions_to2DArrayList_testDeepCopy_solutionsNotChanging_expectsTrue() {
        // Arrange
        ArrayList<Steen> chain = new ArrayList<>();
        chain.add(new Steen(1,2, Color.RED));
        chain.add(new Steen(2,3,Color.BLUE));
        chain.add(new Steen(3,4,Color.GREEN));
        chain.add(new Steen(4,1,Color.YELLOW));

        ArrayList<ArrayList<Steen>> outputGroup = new ArrayList<>();
        // Act
        Algoritme.writeChain(chain, outputGroup);

        chain.clear();

        chain.add(new Steen(1,4, Color.RED));
        chain.add(new Steen(4,3,Color.BLUE));
        chain.add(new Steen(3,2,Color.GREEN));
        chain.add(new Steen(2,1,Color.YELLOW));

        Algoritme.writeChain(chain, outputGroup);
        // Assert
        assertTrue(outputGroup.get(0) != outputGroup.get(1));
    }

    @Test
    public void Algoritme_writeChain_2sameSolutions_shouldOnlyAddOnce_expectsTrue() {
        // Arrange
        ArrayList<Steen> chain = new ArrayList<>();
        chain.add(new Steen(1,2, Color.RED));
        chain.add(new Steen(2,3,Color.BLUE));
        chain.add(new Steen(3,4,Color.GREEN));
        chain.add(new Steen(4,1,Color.YELLOW));

        ArrayList<ArrayList<Steen>> outputGroup = new ArrayList<>();
        // Act
        Algoritme.writeChain(chain, outputGroup);

        chain.clear();

        chain.add(new Steen(1,2, Color.RED));
        chain.add(new Steen(2,3,Color.BLUE));
        chain.add(new Steen(3,4,Color.GREEN));
        chain.add(new Steen(4,1,Color.YELLOW));

        Algoritme.writeChain(chain, outputGroup);
        // Assert
        assertTrue(outputGroup.size() == 1);
    }
}
