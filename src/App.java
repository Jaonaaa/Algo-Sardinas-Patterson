import java.util.*;

public class App {

    public static void main(String[] args) {

        List<String> mots = new ArrayList<>();
        mots.add("000");
        mots.add("010");
        mots.add("011");
        mots.add("01001");

        boolean estUnCode = new Sardine().isCode(mots);
        if (estUnCode) {
            System.out.println("C'est un code.");
        }
        // throwed if not a code

        // aléa
        // boolean isCode = new Sardine().alea(mots);

    }

}
