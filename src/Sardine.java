import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class Sardine {

    List<List<String>> suites = new ArrayList<List<String>>();

    // ALEA
    public boolean alea(List<String> mots) {

        for (int i = 0; i < mots.size(); i++) {
            Boolean isCode_ = isCode(removeOne(mots, i));
            if (isCode_) {
                System.out.println("Remove one " + i);
                return true;
            }
        }

        for (int i = 0; i < mots.size(); i++) {
            for (int j = i; j < mots.size(); j++) {
                Boolean isCode_ = isCode(removeTwo(mots, i, j));
                if (isCode_) {
                    System.out.println("Remove two " + i + " " + j);
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> removeOne(List<String> mots, int index) {
        List<String> news = new Vector<String>();
        for (int i = 0; i < mots.size(); i++) {
            if (i == index)
                continue;
            news.add(mots.get(i));
        }
        return news;
    }

    public List<String> removeTwo(List<String> mots, int index, int two) {
        List<String> news = new Vector<>();
        for (int i = 0; i < mots.size(); i++) {
            if (i == index || two == i)
                continue;
            news.add(mots.get(i));
        }
        return news;
    }

    public boolean isCode(List<String> mots) {
        List<String> L1 = getL1(mots);
        getSuite(mots, L1);
        return true;

    }

    public List<String> getSuite(List<String> L, List<String> Ln) {
        //
        int counter = 0;
        return getLn(L, Ln, counter);
    }

    public List<String> getLn(List<String> L, List<String> Ln, int counter) {

        List<String> L_Left = getDivision(Ln, L);
        List<String> L_Right = getDivision(L, Ln);
        List<String> newLn = union(L_Left, L_Right);
        // showList(newLn);
        this.suites.add(newLn);
        Boolean isCode_ = isPeriodique();
        counter++;
        if (counter == 100) {
            throw new RuntimeException("Counter Stop");
        }
        if (isCode_)
            return newLn;
        else {
            newLn = getLn(L, newLn, counter);
            return newLn;
        }
    }

    public void showList(List<String> L) {
        for (String string : L) {
            System.out.print(string + " _ ");
        }
        System.out.println();
    }

    public Boolean isPeriodique() {
        for (int o = 0; o < this.suites.size(); o++) {
            List<String> L3 = this.suites.get(o);
            for (int i = o; i < this.suites.size(); i++) {
                if (i == o)
                    continue;
                Boolean isPeriodique = compareListsContent(L3, this.suites.get(i));
                if (isPeriodique)
                    return true;
            }
        }
        return false;
    }

    public boolean compareListsContent(List<String> list1, List<String> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public List<String> getDivision(List<String> numerateur, List<String> denominateur) {
        List<String> newL = new ArrayList<String>();
        for (String deno : denominateur) {
            for (String nume : numerateur) {
                String residus = getResidusAndThrow(deno, nume);
                if (residus != null)
                    newL.add(residus);
            }
        }
        return newL;
    }

    public List<String> union(List<String> one, List<String> two) {
        List<String> newL = new ArrayList<String>();
        newL.addAll(one);
        newL.addAll(two);
        return newL;
    }

    public List<String> getL1(List<String> mots) {

        List<String> l1 = new ArrayList<String>();
        for (String mot : mots) {
            for (String mot2 : mots) {
                String residus = getResidus(mot, mot2);
                if (residus != null)
                    l1.add(residus);
            }
        }
        return l1;
    }

    public String getResidus(String prefix, String text) {
        Boolean isPrefix = text.startsWith(prefix);
        if (isPrefix) {
            String residus = text.substring(prefix.length());
            if (residus.equals(""))
                return null;
            else
                return residus;
        } else
            return null;

    }

    public String getResidusAndThrow(String prefix, String text) {
        Boolean isPrefix = text.startsWith(prefix);
        if (isPrefix) {
            String residus = text.substring(prefix.length());

            if (residus.equals(""))
                throw new RuntimeException("Mot vide on prefix " + prefix + " and text " + text);
            else
                return residus;
        } else
            return null;

    }

}
