package be.domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author r0711421
 */

public class Algoritme {

    public static final boolean HEAD = true;
    public static final boolean TAIL = false;

    public Optional<List<Steen>> maakKetting(ArrayList<Steen> todo) {
        // conversie tussen ArrayList en array voor als je dat nodig zou hebben
        Steen[] todoArray = new Steen[todo.size()];
        for (int i=0; i<todo.size(); i++) {
            todoArray[i] = todo.get(i);
        }
        return Optional.of(todo);
    }

    public static boolean checkEyesAndFlip(Steen steen1, Steen steen2, boolean headOrTail) {    // bij true mag steentje geplaatst worden
        if (headOrTail) {    // true --> head                                                   // checkColor() word eerder uitgevoerd
            if (steen1.getOgen2() == steen2.getOgen1()) {
                return true;    // ogen 1 is juist, geen flip, wel een match
            }
            else {
                if (steen1.getOgen2() == steen2.getOgen2()) {
                    steen2.flip();
                    return true;    // ogen 2 is juist, wel een flip, ook een match
                } else {
                    return false;   // geen ogen juist, geen flip, geen match
                }
            }
        }
        else {  // false --> tail   !!! necessary for adding tiles to the beginning end
                // !!! Only may be used if chain is not being closed --> otherwise its not allowed to flip the tiles
            if (steen1.getOgen1() == steen2.getOgen2()) {
                return true;
            }
            else {
                if (steen1.getOgen1()== steen2.getOgen1()) {
                    steen2.flip();
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    public static boolean checkColor(Steen steen1, Steen steen2) {      // bij true mag checkEyesAndFlip() uitgevoerd worden
        if (steen1.getKleur()==steen2.getKleur()) {
            return false;   // kleur is gelijk, geen match
        }
        else {
            return true;    // kleuren ongelijk, wel een match
        }
    }

    public static boolean checkEyesClosing(Steen steen1, Steen steen2) {    // steen 1 = head, steen 2 = tail
        if (steen1.getOgen2()==steen2.getOgen1()) return true;
        else return false;
    }

    public static Steen searchNextTile(ArrayList<Steen> list, Steen steen) {
        for (Steen tempSteen : list) {
            if (checkColor(steen, tempSteen)) {
                if (checkEyesAndFlip(steen, tempSteen, HEAD)) {
                    list.remove(tempSteen);
                    return tempSteen;
                }
            }
        }
        return null;
    }

    public static void plaatsSteen(ArrayList<Steen> sourceList, ArrayList<Steen> destinationList) {
        throw new UnsupportedOperationException();
    }
}

