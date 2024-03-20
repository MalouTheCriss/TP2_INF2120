import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilePrioChaineeTest {

    @Test
    void enfilerNull() {
        FilePrioChainee<TachePrio> file = new FilePrioChainee();
        assertThrows(NullPointerException.class, () -> file.enfiler(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void enfilerN(int nbTaches){
        FilePrioChainee<TachePrio> file = creerTaches(nbTaches);
        assertEquals(nbTaches, file.taille());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void enfilerString (int nbTaches) {
        TachePrio[] tab = creerTachesTableau(nbTaches);
        FilePrioChainee<TachePrio> file = creerTaches(tab);

        assertEquals(tabTachesToString(tab), file.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void enfilerPlusieursString (int nbTaches) {
        TachePrio[] tab = creerTachesTableau(nbTaches, 3);
        FilePrioChainee<TachePrio> file = creerTaches(tab);

        assertEquals(tabTachesToString(tab), file.toString());
    }

    @Test
    void defilerException() {
        FilePrioChainee<TachePrio> file = new FilePrioChainee<>();

        assertThrows(FileVideException.class, () -> file.defiler());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    void defilerDansFile(int nbTaches){
        TachePrio[] tab = creerTachesTableau(nbTaches);
        FilePrioChainee file = creerTaches(tab);
        boolean valid = true;
        int i = 0;

        while (valid != false && i < file.taille()) {
            try {
                if(!tab[i].equals(file.defiler())){
                    valid = false;
                }
            }
            catch (FileVideException e) {

            }
        }

        assertTrue(valid);

    }

    @Test
    void testDefilerPrioFileVideException() {
        FilePrioChainee<TachePrio> file = new FilePrioChainee<>();

        assertThrows(FileVideException.class, () -> file.defiler());
    }

    @Test
    void testPrio() {
        FilePrioChainee file = new FilePrioChainee();
        TachePrio[] tab = new TachePrio[9];
        tab [0] = new TachePrio(0);
        tab [1] = new TachePrio(0);
        tab [2] = new TachePrio(1);
        tab [3] = new TachePrio(1);
        tab [4] = new TachePrio(1);
        tab [5] = new TachePrio(2);
        tab [6] = new TachePrio(2);
        tab [7] = new TachePrio(2);
        tab [8] = new TachePrio(2);
        boolean valid = true;

        for (int i = 0; i < tab.length; i++) {
            file.enfiler(tab[i]);
        }

        try {if(!file.defiler(0).equals(tab[0])){valid = false;}}
        catch (FileVideException e){}

        try {if(!file.defiler(1).equals(tab[2])){valid = false;}}
        catch (FileVideException e){}

        try {if(!file.defiler(0).equals(tab[1])){valid = false;}}
        catch (FileVideException e){}
        try {if(file.defiler(0) != null){valid = false;}}
        catch (FileVideException e){}
        try {if(!file.defiler(2).equals(tab[6])){valid = false;}}
        catch (FileVideException e){}
        try {if(!file.defiler(2).equals(tab[7])){valid = false;}}
        catch (FileVideException e){}

        assertTrue(valid);

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void defilerTous(int nbTaches) {
        TachePrio[] tab = creerTachesTableau(nbTaches, 3);
        FilePrioChainee file = creerTaches(tab);
        FilePrioChainee retire = new FilePrioChainee();
        FilePrioChainee expected = new FilePrioChainee();
        boolean valid = true;

        if(file.taille() > 3){
            try {
                retire = (FilePrioChainee) file.defilerTous(1);
                expected.enfiler(tab[3]);
                expected.enfiler(tab[4]);
                expected.enfiler(tab[5]);
            }
            catch (FileVideException e){}

            while (!retire.estVide()) {
                try {
                    if (!retire.defiler().equals(expected.defiler())){
                        valid = false;
                    }
                }
                catch (FileVideException e){

                }
            }
        }
        else if (file.taille() == 3) {
            try{
                file.defilerTous(0);
            }
            catch (FileVideException e){}

        }

        assertTrue(valid);

    }

    @Test
    void defilerTousFileVideExcption () {
        FilePrioChainee file = new FilePrioChainee();

        assertThrows(FileVideException.class, () -> file.defilerTous(0));

    }

    @Test
    void prioriteExiste() {
    }

    @Test
    void estVide() {
        FilePrioChainee file = new FilePrioChainee();

        assertEquals(file.taille()==0, file.estVide());


    }
    @Test
    void estPasVide() {
        FilePrioChainee file = new FilePrioChainee();
        file.enfiler(new TachePrio(1));

        assertEquals(file.taille()!=0, file.estVide());
    }

    @Test
    void taille() {
    }

    @Test
    void testTaille() {
    }

    @Test
    void premier() {
    }

    @Test
    void testPremier() {
    }

    @Test
    void vider() {
    }

    @Test
    void sousFilePrio() {
    }

    @Test
    void contient() {
    }

    @Test
    void normaliser() {
    }

    @Test
    void eliminerDoublons() {
    }

    @Test
    void prioriteMax() {
    }

    @Test
    void prioriteMin() {
    }

    @Test
    void copie() {
    }


    private FilePrioChainee<TachePrio> creerTaches(int nbTaches) {
        FilePrioChainee<TachePrio> file = new FilePrioChainee<TachePrio>();

        for (int i = 0; i < nbTaches; i++) {
            file.enfiler(new TachePrio(i));
        }

        return file;

    }
    private FilePrioChainee<TachePrio> creerTaches(int nbGroupesDeTaches, int nbTachesParPrio) {
        FilePrioChainee<TachePrio> file = new FilePrioChainee<TachePrio>();

        for (int i = 0; i < nbGroupesDeTaches; i++) {
            for (int j = 0; j < nbTachesParPrio; j++) {
                file.enfiler(new TachePrio(i));
            }
        }

        return file;

    }

    private TachePrio[] creerTachesTableau(int nbTaches) {
        TachePrio[] tab = new TachePrio[nbTaches];

        for (int i = nbTaches-1; i > -1; i--) {
            tab[i] = new TachePrio(i);
        }

        return tab;

    }
    private TachePrio[] creerTachesTableau(int nbGroupesDeTaches, int nbTachesParPrio) {
        TachePrio[] tab = new TachePrio[nbGroupesDeTaches * nbTachesParPrio];

        for (int i = nbGroupesDeTaches-1; i > -1 ; i--) {

            int prio = i;
            for (int j = nbTachesParPrio-1; j > -1; j--) {
                tab [tab.length - 1 - (i * nbTachesParPrio + j)] = new TachePrio(i);
            }
        }

        return tab;

    }

    private FilePrioChainee<TachePrio> creerTaches(TachePrio[] tab) {
        FilePrioChainee<TachePrio> file = new FilePrioChainee<TachePrio>();

        for (int i = 0; i < tab.length; i++) {

            int prio = i;
            file.enfiler(tab[i]);
        }

        return file;

    }

    public String tabTachesToString(TachePrio[] tab) {
        String s = "tete [ ";

        if (tab.length == 0) {
            s = s + " ] fin";
        }
        else{
            for (int i = 0; i < tab.length; i++) {
                s = s + tab[i] + ", ";
            }

            s = s.substring(0, s.length() -2) + " ] fin";
        }

        return s;
    }

}