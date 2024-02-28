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
            placerMaillonDansFile(new Maillon<T>((T) element));
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
     * Défile l'élément en tête de liste et le retourne
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
     * Defile la première instance de T avec une priorite égale à celle passée en paramètre
     * et la retourne. S'il n'y en a pas dans la file, retourne null.
     *
     * @param priorite la priorite de l'element a defiler.
     * @return l'objet defilé ou null
     * @throws FileVideException
     */
    @Override
    public T defiler(int priorite) throws FileVideException {
        Maillon<T> precedent;
        Maillon<T> defile;

        if (taille == 0){
            throw new FileVideException();
        }

        precedent = trouverMaillonPrecedent(priorite);

        //Le dernier maillon n'a pas la bonne priorité <=> c'est l'avant dernier
        if(precedent.getInfo().getPriorite() != priorite){
            //Verif si dernier à la bonne priorité
            if(precedent.getSuivant().getInfo().getPriorite() == priorite){
                defile = precedent.getSuivant();
                precedent.setSuivant(null);
            }
            //Si aucun maillon n'a la priorité recherchée
            else {
                defile = null;
            }
        }
        else{
            defile = precedent.getSuivant();
            precedent.setSuivant(precedent.getSuivant().getSuivant());
        }

        return defile.getInfo();
    }

    /**
     * Trouve le maillon precedent du premiere maillon avec une priorité
     * égale à celle passée en paramètre.
     *
     * @param priorite
     * @return Le maillon précédent
     */
    private Maillon<T> trouverMaillonPrecedent(int priorite) {
        Maillon<T> m = elements;

        if(taille == 2){

        }

        if(m.getInfo().getPriorite() != priorite){
            if(m.getSuivant() != null){
                while (
                        m.getSuivant().getInfo().getPriorite() != priorite &&
                                m.getSuivant().getSuivant() != null) {
                    m = m.getSuivant();

                }
            }
            else{
                m = null;
            }

        }

        return m;
    }

    @Override
    public IFilePrio<T> defilerTous(int priorite) throws FileVideException {
        FilePrioChainee<T> file;
        Maillon<T> precedent;

        if(taille == 0){
            throw new FileVideException();
        }

        precedent = trouverMaillonPrecedent(priorite);

        while (precedent.getSuivant().getSuivant() != null){

        }

        return null;
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

}
