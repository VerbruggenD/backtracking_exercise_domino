package be.domino;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author r0711421
 * @author Dieter
 * Uren: 14 uur  -> don 14 april    21:32           // regelmatig updaten
 */
public class Main {

    private static String defaultInput = "12R 16R 23G 34B";

    public static void main(String[] args) {
        String input;
        if (args.length == 0) input = defaultInput;
        else input = readFileAsString(args[0]);

        ArrayList<Steen> stenen = readStones(input);
        if (stenen.size() > 1) stenen.get(1).flip();

        Algoritme rekenaar = new Algoritme();
        var oplossing = rekenaar.maakKetting(stenen);
        if (oplossing.isEmpty()) {
            System.out.println("geen oplossing");
        }
        else {
            for (ArrayList<Steen> column : oplossing.get() ) {
                System.out.println(column);
            }
        }
    }

    public static String readFileAsString(String fileName)
    {
        try {
            String data = "";
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        }
        catch (Exception e) {
            return defaultInput;
        }
    }

    public static ArrayList<Steen> readStones(String txt) {
        ArrayList<Steen> stenen = new ArrayList<>();
        for (int i=0; i < txt.length(); i=i+4) {
            stenen.add(parseStone(txt.substring(i, i+3)));
        }
        return stenen;
    }

    public static Steen parseStone(String txt) {
        try {
            int ogen1 = Integer.valueOf(txt.substring(0,1));
            int ogen2 = Integer.valueOf(txt.substring(1,2));
            char kleur = txt.charAt(2);
            return new Steen(ogen1, ogen2, kleur);
        }
        catch (Exception e) {
            return new Steen(9);
        }
    }

}
