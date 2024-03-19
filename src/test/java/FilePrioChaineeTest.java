import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilePrioChaineeTest {

    @Test
    void toString (){
        FilePrioChainee<ITachePrio> file = new FilePrioChainee();
        for (int i = 0; i < 5; i++) {
            final int prioloop = i;
            file.enfiler(new ITachePrio() {
                private int prio = prioloop;
                @Override
                public int getPriorite() {
                    return prio;
                }

                @Override
                public void setPriorite(int priorite) {
                    prio = priorite;
                }
            });
        }

        System.out.printf(file.toString());
    }

    @Test
    void enfilerNull() {
        FilePrioChainee<ITachePrio> file = new FilePrioChainee();
        assertThrows(NullPointerException.class, () -> file.enfiler(null));
        file.enfiler(new ITachePrio() {
            @Override
            public int getPriorite() {
                return 0;
            }

            @Override
            public void setPriorite(int priorite) {

            }
        });

        assertEquals(1, file.taille());
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
}