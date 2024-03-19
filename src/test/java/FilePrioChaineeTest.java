import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @Test
    void defiler(){

    }

    @Test
    void testDefiler() {
    }

    @Test
    void defilerTous() {
    }

    @Test
    void prioriteExiste() {
    }

    @Test
    void estVide() {
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