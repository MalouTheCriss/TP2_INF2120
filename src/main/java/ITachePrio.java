
/**
 * Interface fournissant des methodes pour consulter et modifier une valeur
 * de type int representant la priorite d'un objet.
 * 
 * @author Melanie Lord
 * @version H24
 */
public interface ITachePrio {
   /**
    * Permet d'obtenir la priorite de cet objet.
    * @return la priorite de cet objet.
    */
   int getPriorite();
   
   /**
    * Permet de modifier la priorite de cet objet par la valeur passee 
    * en parametre.
    * @param priorite la nouvelle priorite de cet objet.
    */
   void setPriorite(int priorite);
}
