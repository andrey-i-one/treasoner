package ontologycomparer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class reads the taxonomy of the concepts in ontology.
 * @author Andrey Grigoryev
 */

public class Taxonomy {

    private ArrayList<String> al = new ArrayList<String>();
    private HashMap<String, Integer> hm = new HashMap<String, Integer>();
    private HashSet<Pair> axs = new HashSet<Pair>();
    private HashSet<Pair> eee = new HashSet<Pair>();
    
    private int find(String s) {
        if(hm.containsKey(s)) return hm.get(s);
        hm.put(s, al.size());
        al.add(s);
        return al.size() - 1;
    }
    
    /**
     * Method adds SubClassOf Axiom
     * @param sub Define subconcept.
     * @param sup Define superconcept.
     */
    public void addSubClass(String sub, String sup) {
        int sb = find(sub);
        int sp = find(sup);
        Pair p = new Pair(sb, sp);
        axs.add(p);
    }

    /**
     * Method adds Equivalent Classes Axiom
     * @param cl1 Defines one class.
     * @param cl2 Defines equivalent class.
     */
    public void addEqClass(String cl1, String cl2) {
        int c1 = find(cl1);
        int c2 = find(cl2);
        Pair p1 = new Pair(c1, c2);
        Pair p2 = new Pair(c2, c1);
        eee.add(p1);
        eee.add(p2);
    }
    
    private boolean isExists(String sub, String sup) {
        int sb = find(sub);
        int sp = find(sup);
        return axs.contains(new Pair(sb, sp));
    }
    
    private boolean isExistsEqual(String c1, String c2) {
        int sb = find(c1);
        int sp = find(c2);
        return eee.contains(new Pair(sb, sp));        
    }
    
    private ArrayList<StringPair> getAllAxioms() {
        ArrayList<StringPair> ret = new ArrayList<StringPair>();
        for(Pair p: axs) {
            StringPair tmp = new StringPair(al.get(p.l), al.get(p.r));
            ret.add(tmp);
        }
        return ret;
    }
    
    private ArrayList<StringPair> getAllEqualAxioms() {
        ArrayList<StringPair> ret = new ArrayList<StringPair>();
        for(Pair p: eee) {
            StringPair tmp = new StringPair(al.get(p.l), al.get(p.r));
            ret.add(tmp);
        }
        return ret;
    }
     
    /**
     * Method compares current taxonomy with the given.
     * @param t Define another taxonomy.
     */
    public boolean compareTo(Taxonomy t)
    {
        boolean res = true;
        System.out.println((t.getAllAxioms().size() + t.getAllEqualAxioms().size()) + " " + (getAllAxioms().size() + getAllEqualAxioms().size()));
        ArrayList<StringPair> a = t.getAllAxioms();
        for(int i = 0; i < a.size(); i++) {
            //System.out.println(a.get(i).left + " [= " + a.get(i).right);
            if(!axs.contains(new Pair(find(a.get(i).left), find(a.get(i).right)))) {
                System.out.println("ERROR: can't find " + a.get(i).left + " [= " + a.get(i).right + " in reference taxonomy");
                res = false;
            }
        }
        
        ArrayList<StringPair> b = t.getAllEqualAxioms();
        for(int i = 0; i < b.size(); i++) {
            //System.out.println(a.get(i).left + " == " + a.get(i).right);
            if(!eee.contains(new Pair(find(b.get(i).left), find(b.get(i).right)))) {
                System.out.println("ERROR: can't find " + b.get(i).left + " == " + b.get(i).right + " in reference taxonomy");
                res = false;
            }
        }
        
        a = getAllAxioms();
        System.out.println("");
        for(int i = 0; i < a.size(); i++) {
            //System.out.println(a.get(i).left + " [= " + a.get(i).right);
            if(!t.isExists(a.get(i).left, a.get(i).right)) {
                System.out.println("ERROR: can't find " + a.get(i).left + " [= " + a.get(i).right + " in my taxonomy");
                res = false;
            }
        }
        
        b = getAllEqualAxioms();
        System.out.println("");
        for(int i = 0; i < b.size(); i++) {
            //System.out.println(a.get(i).left + " [= " + a.get(i).right);
            if(!t.isExistsEqual(b.get(i).left, b.get(i).right))
            {
                System.out.println("ERROR: can't find " + b.get(i).left + " == " + b.get(i).right + " in my taxonomy");
                res = false;
            }
        }
        return res;
    }
}

class Pair {
    public int l, r;
    public Pair(int _l, int _r) {
        l = _l;
        r = _r;
    }
    
    @Override
    public int hashCode() {
        return l * 107 + r;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.l != other.l) {
            return false;
        }
        if (this.r != other.r) {
            return false;
        }
        return true;
    }
}

class StringPair {
    public String left = "", right = "";
    public StringPair(String l, String r) {
        left = l;
        right = r;
    }
}