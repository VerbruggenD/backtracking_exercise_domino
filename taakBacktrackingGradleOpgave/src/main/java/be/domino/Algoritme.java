package be.domino;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author r0711421
 */

public class Algoritme {

    public Optional<ArrayList<Steen>> maakKetting(ArrayList<Steen> todo, ArrayList<Steen> destList, ArrayList<Steen> backtrackList) {
        if (todo.isEmpty()) {
            // check kring gesloten
            // als kring gesloten en bactrackList is leeg
            if (checkEyesClosing(destList.get(destList.size()-1), destList.get(0)) && backtrackList.isEmpty() ) return Optional.of(destList);
                // return destList

            // else
                // geen oplossing
                // backtrack
            //
            return Optional.empty();
        }
        else {
            var geplaatsteSteen = plaatsSteen(todo, destList);
            if (geplaatsteSteen.isPresent()) { // check of source leeg is
                                        // source is niet leeg
                if (geplaatsteSteen.get()) {
                    // plaatsen gelukt --> ga verder
                    if (!backtrackList.isEmpty()) {         // om gebacktrackte stenen niet meer te kunnen kiezen tijdens de zoek functie
                        for (Steen huidigeSteen: backtrackList) { // na plaatsen moeten deze terug in de todo geplaatst worden
                            todo.add(huidigeSteen);
                        }
                        backtrackList.clear();
                    }
                    System.out.println("gelukt");
                } else {
                    // plaatsen mislukt --> backtrack
                    if (destList.size() > 1) {
                        System.out.println("backtrack");
                        var backtrackSteen = destList.get(destList.size() - 1);
                        verwijderSteen(destList, backtrackList, backtrackSteen);
                    }
                    else return Optional.empty();
                }
                return maakKetting(todo, destList, backtrackList);
            }
            else { // source is leeg
                System.out.println("gedaan");
                return Optional.of(destList);
            }
        }
    }

    public static boolean checkEyesAndFlip(Steen steen1, Steen steen2) {    // bij true mag steentje geplaatst worden
                                                                // checkColor() word eerder uitgevoerd
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

    public static boolean checkColor(Steen steen1, Steen steen2) {      // bij true mag checkEyesAndFlip() uitgevoerd worden
        // kleuren ongelijk, wel een match
        return steen1.getKleur() != steen2.getKleur();   // kleur is gelijk, geen match
    }

    public static boolean checkEyesClosing(Steen steen1, Steen steen2) {    // steen 1 = head, steen 2 = tail
        return steen1.getOgen2() == steen2.getOgen1();
    }

    public static Optional<Steen> searchNextTile(ArrayList<Steen> list, Steen steen) {
        for (Steen tempSteen : list) {
            if (checkColor(steen, tempSteen)) {
                if (checkEyesAndFlip(steen, tempSteen)) {
                    list.remove(tempSteen);
                    return Optional.of(tempSteen);
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<Boolean> plaatsSteen(ArrayList<Steen> sourceList, ArrayList<Steen> destinationList) {
        if (!sourceList.isEmpty()) {
            if(destinationList.isEmpty()) {
                var eersteSteen = sourceList.get(0);
                destinationList.add(eersteSteen);
                sourceList.remove(eersteSteen);
                return Optional.of(true);
            }
            else {
                var einde = destinationList.get(destinationList.size() - 1);
                var gevondenSteen = searchNextTile(sourceList, einde);
                if (gevondenSteen.isEmpty()) return Optional.of(false);
                else {
                    destinationList.add(gevondenSteen.get());
                    return Optional.of(true);    // kunnen plaatsen
                }
            }
        }
        else return Optional.empty();  // niet kunnen plaatsen --> lege source
    }

    public static boolean verwijderSteen (ArrayList<Steen> origineleLijst, ArrayList<Steen> backtrackList, Steen verwijderdeSteen) {
        if (origineleLijst.contains(verwijderdeSteen)) {
            origineleLijst.remove(verwijderdeSteen);
            backtrackList.add(verwijderdeSteen);
            return true;
        }
        else return false;
    }
}

