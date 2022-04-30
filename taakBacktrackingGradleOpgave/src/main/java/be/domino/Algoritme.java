package be.domino;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author r0711421
 */

public class Algoritme {

    public Optional<ArrayList<ArrayList<Steen>>> maakKetting(ArrayList<Steen> todo) {
        ArrayList<Steen> chainList = new ArrayList<>();     // working chain arrayList to find answers
        var prevPlacedTiles = make2DArrayList(todo.size()); // 2D ArrayList to keep track of tried tiles in that step
        ArrayList<ArrayList<Steen>> outputGroup = new ArrayList<>();    // 2D ArrayList to return all possible answers

        return solve(todo, chainList, prevPlacedTiles, outputGroup);
    }

    public Optional<ArrayList<ArrayList<Steen>>> solve(ArrayList<Steen> todo, ArrayList<Steen> chainList, ArrayList<ArrayList<Steen>> prevPlacedTiles, ArrayList<ArrayList<Steen>> outputGroup) {
        if (todo.isEmpty()) {       // check todo is leeg
                                    // ja, geen tiles meer om te plaatsen
                                    // check of kring sluit
            if (checkChainClosing(chainList.get(chainList.size() - 1), chainList.get(0))) {     // is het een oplossing
                writeChain(chainList, outputGroup);
                //System.out.println("oplossing " + chainList);
            }
            if (!backtrack(todo, chainList, prevPlacedTiles)) {     // sowieso backtracken
                System.out.println("fout");
                throw new ArithmeticException();
            }
            //else System.out.println("backtrack");

        } else {    // todo niet leeg

            if (chainList.size()>1) {       // is het een oplossing
                if (checkChainClosing(chainList.get(chainList.size() - 1), chainList.get(0))) {
                    writeChain(chainList, outputGroup);
                    //System.out.println("oplossing " + chainList);
                }
            }

            var geplaatsteSteen = placeTile(todo, chainList, prevPlacedTiles);
            if (geplaatsteSteen.isPresent()) { // check of source leeg is
                // source is niet leeg
                if (geplaatsteSteen.get()) {
                    // plaatsen gelukt --> ga verder

                    //System.out.println("gelukt " + chainList);
                } else {
                    // plaatsen mislukt --> backtrack
                    // als eerste tile niet gezet kan worden --> gedaan
                    if (chainList.size()==0) {
                        if (outputGroup.size() == 0) return Optional.empty();
                        else return Optional.of(outputGroup);
                    }

                    if (!backtrack(todo, chainList, prevPlacedTiles)) {
                        System.out.println("fout");
                        throw new ArithmeticException();
                    }
                    //else System.out.println("backtrack");
                }
                return solve(todo, chainList, prevPlacedTiles, outputGroup);
            } else { // source is leeg                // komt niet voor aangezien hier al op gecheckt was
                System.out.println("fout");
                throw new ArithmeticException();
            }
        }
        return solve(todo, chainList, prevPlacedTiles, outputGroup);
    }

    public static boolean checkEyesAndFlip(Steen tile1, Steen tile2) {    // bij true mag steentje geplaatst worden
        // checkColor() word eerder uitgevoerd
        if (tile1.getOgen2() == tile2.getOgen1()) {
            return true;    // ogen 1 is juist, geen flip, wel een match
        } else {
            if (tile1.getOgen2() == tile2.getOgen2()) {
                tile2.flip();
                return true;    // ogen 2 is juist, wel een flip, ook een match
            } else {
                return false;   // geen ogen juist, geen flip, geen match
            }
        }
    }

    public static boolean checkColor(Steen tile1, Steen tile2) {      // bij true mag checkEyesAndFlip() uitgevoerd worden
        // kleuren ongelijk, wel een match
        return tile1.getKleur() != tile2.getKleur();   // kleur is gelijk, geen match
    }

    public static boolean checkEyesClosing(Steen end, Steen begin) {
        return end.getOgen2() == begin.getOgen1();
    }

    public static boolean checkChainClosing(Steen end, Steen begin) {
        return checkColor(end, begin) && checkEyesClosing(end, begin);
    }

    public static Optional<Steen> searchNextTile(ArrayList<Steen> list, Optional<Steen> tile, ArrayList<Steen> prevPlacedColumn) {
        if (tile.isPresent()) {
            for (Steen tempSteen : list) {
                if (checkColor(tile.get(), tempSteen)) {
                    if (checkEyesAndFlip(tile.get(), tempSteen)) {
                        if (!checkPrevPlacedTiles(tempSteen, prevPlacedColumn)) {
                            list.remove(tempSteen);
                            return Optional.of(tempSteen);
                        }
                    }
                }
            }
        }
        else {
            // eertste nog niet geplaatste steen plaatsen
            for (Steen tempSteen : list) {
                if (!checkPrevPlacedTiles(tempSteen, prevPlacedColumn)) {
                    list.remove(tempSteen);
                    return Optional.of(tempSteen);
                }
            }
        }
        return Optional.empty();
    }

    public static ArrayList<ArrayList<Steen>> make2DArrayList(int size) {
        ArrayList<ArrayList<Steen>> array2D = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array2D.add(new ArrayList<>());
        }
        return array2D;
    }

    public static boolean checkPrevPlacedTiles(Steen tileToBeChecked, ArrayList<Steen> prevPlacedColumn) {
        if (!prevPlacedColumn.isEmpty()) {
            for (Steen tempSteen : prevPlacedColumn) {
                if (tileToBeChecked.equals(tempSteen)) return true;
            }
        }
        return false;
    }

    public static Optional<ArrayList<ArrayList<Steen>>> writePrevPlacedTile (Steen tile, ArrayList<ArrayList<Steen>> prevPlaced, int column) {
        if (prevPlaced.get(column) == null) {
            return Optional.empty();
        }
        prevPlaced.get(column).add(tile);
        return Optional.of(prevPlaced);
    }

    public static Optional<Boolean> placeTile(ArrayList<Steen> sourceList, ArrayList<Steen> destinationList, ArrayList<ArrayList<Steen>> prevPlaced) {
        if (!sourceList.isEmpty()) {
            Optional<Steen> end = Optional.empty();
            var index = 0;

            if (!destinationList.isEmpty()) {        // niet eerste steen plaatsen
                index = destinationList.size();
                end = Optional.of(destinationList.get(index-1));
            }

            var foundTile = searchNextTile(sourceList, end, prevPlaced.get(index));
            if (foundTile.isEmpty()) return Optional.of(false);     // niet kunnen plaatsen --> geen match
            else {
                destinationList.add(foundTile.get());
                writePrevPlacedTile(foundTile.get(), prevPlaced, index);     // prevPlaced update
                return Optional.of(true);    // kunnen plaatsen
            }
        } else return Optional.empty();  // fout! --> lege source of bug
    }

    public static boolean removeTile(ArrayList<Steen> source, ArrayList<Steen> dest, Steen removedTile) {
        if (dest.contains(removedTile)) {
            dest.remove(removedTile);
            source.add(removedTile);
            return true;
        } else return false;
    }

    public static boolean backtrack(ArrayList<Steen> source, ArrayList<Steen> dest, ArrayList<ArrayList<Steen>> prevPlacedTiles) {
        if (dest.size() > 0) {
            var index = (dest.size()-1);
            //System.out.println("backtrack");

            if (!(dest.size() == prevPlacedTiles.size())) {
                prevPlacedTiles.get(index+1).clear();
            }

            var backtrackSteen = dest.get(index);
            if (removeTile(source, dest, backtrackSteen)) return true;
        }
        return false;
    }

    public static void writeChain(ArrayList<Steen> solution, ArrayList<ArrayList<Steen>> outputGroup) {
        if (!outputGroup.contains(solution)) {
            ArrayList<Steen> copy = new ArrayList<>();
            for (Steen tile : solution) {
                copy.add(tile);
            }
            outputGroup.add(copy);
        }
    }
}

