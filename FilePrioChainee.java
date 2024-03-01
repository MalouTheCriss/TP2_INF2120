public class FilePrioChainee<T extends ITachePrio> implements IFilePrio<T> {

    private Maillon<T> elements;
    private int taille;

    FilePrioChainee (){
        this.elements = null;
        this.taille = 0;

    }

    /**
     * Enfile un nouvel élément sous forme de maillon à la file. Le place juste avant
     * le prochain avec la même info ou à la fin de la file
     *
     * @param element l'element a enfiler dans cette file de priorite.
     */
    @Override
    public void enfiler(ITachePrio element) {
        if(element == null){
            throw new NullPointerException();
        }
        if(taille == 0){
            elements = new Maillon<T>(((T)element), null);
        }
        else{
            placerMaillonDansFile(new Maillon<T>((T)element));
        }
        taille++;
    }

    /**
     * Cherche à travers les maillons le prochain dont l'info contient une valeur plus petite que
     * celle contenue dans l'info du nouveau. Puis, place le nouveau maillon à cet endroit dans la
     * chaine. Si toutes les infos des maillons ont un ordre de priorité plus grand, alors le nouveau
     * est ajouté à la fin.
     *
     * @param m maillon a placer dans la file
     */
    private void placerMaillonDansFile(Maillon<T> m) {
        Maillon <T> precedent = elements;


        while(
                precedent.getSuivant() != null &&
                precedent.getSuivant().getInfo().getPriorite() >= m.getInfo().getPriorite()
        ){
            precedent = precedent.getSuivant();
        }

        //Si precedent = dernier maillon
        if(precedent.getSuivant() == null){
            //Devient dernier maillon
            if(precedent.getInfo().getPriorite() >= m.getInfo().getPriorite()){
                precedent.setSuivant(m);
            }
            //Devient avant dernier maillon
            else {
                m.setSuivant(precedent);
            }
        }
        //Maillon quelquonque
        else{
            m.setSuivant(precedent.getSuivant());
            precedent.setSuivant(m);
        }
    }

    /**
     *  Defile l'element le plus prioritaire (premier arrivee de la plus grande
     *  priorite) de cette file de priorite.
     *
     * @return l'élément défilé
     * @throws FileVideException
     */
    @Override
    public T defiler() throws FileVideException {
        if(taille == 0){
            throw new FileVideException();
        }

        elements = elements.getSuivant();
        taille--;

        return elements.getInfo();
    }

    /**
     * Defile l'element le plus prioritaire de la priorite donnee en parametre.
     * Si aucun element de la priorite donnee n'existe dans cette file de priorite,
     * la methode retourne null et cette file de priorite n'est pas modifiee.
     *
     * @param priorite la priorite de l'element a defiler.
     * @return l'objet defilé ou null
     * @throws FileVideException
     */
    @Override
    public T defiler(int priorite) throws FileVideException {
        Maillon<T> precedent;
        Maillon<T> defile;
        T info;

        if (taille == 0){
            throw new FileVideException();
        }

        if(taille == 1 && elements.getInfo().getPriorite() == priorite){
            defile = elements;
            elements = null;
        }
        else{
            precedent = trouverMaillonPrecedent(priorite);

            if(precedent != null){
                defile = precedent.getSuivant();
                if(precedent.getSuivant().getSuivant() == null){
                    precedent.setSuivant(null);
                }
                else {
                    precedent.setSuivant(precedent.getSuivant().getSuivant());
                }
            }
            else {
                defile = null;
            }
        }

        if(defile != null){
            info = defile.getInfo();
            taille --;
        }
        else{
            info = null;
        }

        return info;
    }

    /**
     * Trouve le maillon précédent du maillon avec une priorité
     * égale à celle passée en paramètre. Si aucun maillon n'a la priorité recherchée,
     * alors null est renvoyé. S'il n'y a qu'un maillon dans la chaîne, alors null est
     * renvoyé puisqu'il ne peut y avoir de précédent.
     *
     * @param priorite priorité du maillon qu'on veut defiler
     * @return Le maillon précédent le premier avec la priorité passée en paramètre
     *         ou null s'il n'y a aucun maillon avec cette prioritée.
     */
    private Maillon<T> trouverMaillonPrecedent(int priorite) {
        Maillon<T> m = elements;

        if(taille == 1 ||
                (taille == 2 && m.getSuivant().getInfo().getPriorite() != priorite)){
            m = null;
        }
        else{
            while (
                    m.getSuivant().getInfo().getPriorite() != priorite &&
                            m.getSuivant().getSuivant() != null) {
                m = m.getSuivant();
            }
            //Retourner null si aucun maillon n'a la priorité cherchée
            if(m.getSuivant().getInfo().getPriorite() != priorite){
                m = null;
            }
        }

        return m;
    }

    /**
     * Defile tous les elements de la priorite donnee. Si aucun element de cette
     * priorite n'existe dans cette file de priorite, celle-ci n'est pas modifiee.
     * La methode retourne une file de priorite contenant tous les elements
     * defiles, dans le meme ordre que lorsqu'ils se trouvaient dans cette file
     * de priorite. Si aucun element n'est defile, la file retournee est vide.
     *
     * @param priorite la priorite des elements a defiler de cette file de
     *                 priorite.
     * @return une file avec les éléments défilés ou null si aucun n'élément n'avait
     * la priorité cherchée.
     * @throws FileVideException
     */
    @Override
    public IFilePrio<T> defilerTous(int priorite) throws FileVideException {
        FilePrioChainee<T> file = new FilePrioChainee<>();
        Maillon<T> precedent;

        if(taille == 0){
            throw new FileVideException();
        }

        if(taille == 1 && elements.getInfo().getPriorite() == priorite){
            file.enfiler(elements.getInfo());
            elements = null;
        }
        else{
            precedent = trouverMaillonPrecedent(priorite);

            if(precedent != null){
                while (precedent.getSuivant().getSuivant() != null &&
                        precedent.getSuivant().getSuivant().getInfo().getPriorite() == priorite){
                    file.enfiler(precedent.getSuivant().getInfo());
                    precedent.setSuivant(precedent.getSuivant().getSuivant());
                    taille--;
                }
            }
            else{
                file = null;
            }
        }


        return file;
    }

    @Override
    public boolean prioriteExiste(int priorite) {
        return false;
    }

    @Override
    public boolean estVide() {
        return false;
    }

    @Override
    public int taille() {
        return 0;
    }

    @Override
    public int taille(int priorite) {
        return 0;
    }

    @Override
    public T premier() throws FileVideException {
        return null;
    }

    @Override
    public T premier(int priorite) throws FileVideException {
        return null;
    }

    @Override
    public void vider() {

    }

    @Override
    public IFilePrio<T> sousFilePrio(int priorite) {
        return null;
    }

    @Override
    public boolean contient(T elem) {
        return false;
    }

    @Override
    public void normaliser() {

    }

    @Override
    public void eliminerDoublons() {

    }

    @Override
    public int prioriteMax() throws FileVideException {
        return 0;
    }

    @Override
    public int prioriteMin() throws FileVideException {
        return 0;
    }

    @Override
    public IFilePrio<T> copie() {
        return null;
    }

    /**
     * Construit une representation sous forme de chaine de caracteres de cette
     * file de priorite.
     * @return une representation sous forme de chaine de caracteres de cette
     *         file de priorite.
     */
    @Override
    public String toString() {
        String s = "tete [ ";
        Maillon<T> tmp = elements;
        if (tmp == null) {
            s = s + " ] fin";
        } else {
            while (tmp != null) {
                s = s + tmp.getInfo() + ", ";
                tmp = tmp.getSuivant();
            }
            s = s.substring(0, s.length() -2) + " ] fin";
        }
        return s;
    }

}
