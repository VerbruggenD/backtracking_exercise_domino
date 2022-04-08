package be.domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author r0711421
 */
public class Algoritme {
    public Optional<List<Steen>> maakKetting(ArrayList<Steen> todo) {
        // conversie tussen ArrayList en array voor als je dat nodig zou hebben
        Steen[] todoArray = new Steen[todo.size()];
        for (int i=0; i<todo.size(); i++) {
            todoArray[i] = todo.get(i);
        }
        return Optional.of(todo);
    }
}

