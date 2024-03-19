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
        FilePrioChainee<ITachePrio> file = new FilePrioChainee();
        assertThrows(NullPointerException.class, () -> file.enfiler(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void enfilerN(int nbTaches){
        FilePrioChainee<ITachePrio> file = creerTaches(nbTaches);
        assertEquals(nbTaches, file.taille());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void enfilerString (int nbTaches) {
        ITachePrio[] tab = creerTachesTableau(nbTaches);
        FilePrioChainee<ITachePrio> file = creerTaches(tab);

        assertEquals(tabTachesToString(tab), file.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 5, 10})
    void enfilerPlusieursString (int nbTaches) {
        ITachePrio[] tab = creerTachesTableau(nbTaches, 3);
        FilePrioChainee<ITachePrio> file = creerTaches(tab);

        assertEquals(tabTachesToString(tab), file.toString());
    }

    @Test
    void defiler() {
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


    private FilePrioChainee<ITachePrio> creerTaches(int nbTaches) {
        FilePrioChainee<ITachePrio> file = new FilePrioChainee<ITachePrio>();

        for (int i = 0; i < nbTaches; i++) {

            int prio = i;
            file.enfiler(new ITachePrio() {
                int priorite = prio;
                @Override
                public int getPriorite() {
                    return priorite;
                }

                @Override
                public void setPriorite(int priorite) {
                    this.priorite = priorite;
                }
            });
        }

        return file;

    }
    private FilePrioChainee<ITachePrio> creerTaches(int nbGroupesDeTaches, int nbTachesParPrio) {
        FilePrioChainee<ITachePrio> file = new FilePrioChainee<ITachePrio>();

        for (int i = 0; i < nbGroupesDeTaches; i++) {

            int prio = i;
            for (int j = 0; j < nbTachesParPrio; j++) {
                file.enfiler(new ITachePrio() {
                    int priorite = prio;
                    @Override
                    public int getPriorite() {
                        return priorite;
                    }

                    @Override
                    public void setPriorite(int priorite) {
                        this.priorite = priorite;
                    }
                });
            }
        }

        return file;

    }

    private ITachePrio[] creerTachesTableau(int nbTaches) {
        ITachePrio[] tab = new ITachePrio[nbTaches];

        for (int i = nbTaches-1; i > -1; i--) {

            int prio = i;
            tab [tab.length - i - 1] = (new ITachePrio() {
                int priorite = prio;
                @Override
                public int getPriorite() {
                    return priorite;
                }

                @Override
                public void setPriorite(int priorite) {
                    this.priorite = priorite;
                }
            });
        }

        return tab;

    }
    private ITachePrio[] creerTachesTableau(int nbGroupesDeTaches, int nbTachesParPrio) {
        ITachePrio[] tab = new ITachePrio[nbGroupesDeTaches * nbTachesParPrio];

        for (int i = nbGroupesDeTaches-1; i > -1 ; i--) {

            int prio = i;
            for (int j = nbTachesParPrio-1; j > -1; j--) {
                tab [tab.length - 1 - (i * nbTachesParPrio + j)] = (new ITachePrio() {
                    int priorite = prio;
                    @Override
                    public int getPriorite() {
                        return priorite;
                    }

                    @Override
                    public void setPriorite(int priorite) {
                        this.priorite = priorite;
                    }
                });
            }
        }

        return tab;

    }

    private FilePrioChainee<ITachePrio> creerTaches(ITachePrio[] tab) {
        FilePrioChainee<ITachePrio> file = new FilePrioChainee<ITachePrio>();

        for (int i = 0; i < tab.length; i++) {

            int prio = i;
            file.enfiler(tab[i]);
        }

        return file;

    }

    public String tabTachesToString(ITachePrio[] tab) {
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