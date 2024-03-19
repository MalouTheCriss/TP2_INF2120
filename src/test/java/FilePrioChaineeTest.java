import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FilePrioChaineeTest {

    @Test
    void enfilerNull() {
        FilePrioChainee<ITachePrio> file = new FilePrioChainee();
        assertThrows(NullPointerException.class, () -> file.enfiler(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    void enfilerN(int nbTaches){
        FilePrioChainee<ITachePrio> file = creeTachesSimples(nbTaches);
        assertEquals(nbTaches, file.taille());
    }

    @Test
    void enfilerPrintString () {

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


    private FilePrioChainee<ITachePrio> creeTachesSimples (int nbTaches) {
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
    private FilePrioChainee<ITachePrio> creeTachesN (int nbGroupesDeTaches, int nbTachesParPrio) {
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

    private ITachePrio[] creeTachesSimplesTableau (int nbTaches) {
        ITachePrio[] tab = new ITachePrio[nbTaches];

        for (int i = 0; i < nbTaches; i++) {

            int prio = i;
            tab [i] = (new ITachePrio() {
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
    private ITachePrio[] creeTachesNTableau (int nbGroupesDeTaches, int nbTachesParPrio) {
        ITachePrio[] tab = new ITachePrio[nbGroupesDeTaches * nbTachesParPrio];

        for (int i = 0; i < nbGroupesDeTaches; i++) {

            int prio = i;
            for (int j = 0; j < nbTachesParPrio; j++) {
                tab [i * nbTachesParPrio + j] = (new ITachePrio() {
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



}