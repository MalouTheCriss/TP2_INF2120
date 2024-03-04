import java.nio.charset.MalformedInputException;

public class FilePrioChainee<T extends ITachePrio> implements IFilePrio<T> {

    /*

    TODO ! : CHANGER LES LOOP AVEC JUSTE P = NULL puisque p = p.getSuivant fini par donner null






     */


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
        boolean existe;

        if (trouverPremierMaillon(priorite) == null) {
            existe = false;
        }
        else{
            existe = true;
        }

        return existe;
    }

    @Override
    public boolean estVide() {
        return taille == 0;
    }

    @Override
    public int taille() {
        return taille;
    }

    @Override
    public int taille(int priorite) {
        int taillePrio = 0;
        Maillon <T> m = trouverPremierMaillon(priorite);

        while (m.getSuivant() != null &&
                m.getSuivant().getInfo().getPriorite() == priorite){
            m.setSuivant(m.getSuivant());
            taillePrio++;
        }

        return taillePrio;
    }

    @Override
    public T premier() throws FileVideException {
        if (taille == 0){
            throw new FileVideException();
        }

        return elements.getInfo();
    }

    @Override
    public T premier(int priorite) throws FileVideException {
        if(taille == 0){
            throw new FileVideException();
        }

        return trouverPremierMaillon(priorite).getInfo();
    }

    @Override
    public void vider() {
        elements = null;
        taille = 0;

    }

    @Override
    public IFilePrio<T> sousFilePrio(int priorite) {
        FilePrioChainee<T> file = new FilePrioChainee<T>();
        Maillon<T> m = trouverPremierMaillon(priorite);

        if(m != null){
            while (m.getSuivant() != null &&
                    m.getInfo().getPriorite() == priorite) {
                file.enfiler(m.getInfo());
                m.setSuivant(m.getSuivant());
            }
        }

        return file;
    }

    @Override
    public boolean contient(T elem) {
        boolean contient;
        Maillon<T> m = elements;

        if(taille != 0){
            while (m.getSuivant() != null && !m.getInfo().equals(elem)){
                m.setSuivant(m.getSuivant());
            }
            if(m.getInfo().equals(elem)){
                contient = true;
            }
            else{
                contient = false;
            }
        }
        else{
            contient = false;
        }


        return contient;
    }

    @Override
    public void normaliser() {
        int temp;
        Maillon<T> m = trouverDernierMaillon();

        if (taille > 1) {
            for (int i = 0; i < taille; i++) {
                temp = m.getInfo().getPriorite();
                do {
                    m.getInfo().setPriorite(i);
                    enfiler(m.getInfo());
                    trouverAvantDernierMaillon().setSuivant(null);
                }while (m.getInfo().getPriorite() == temp);
            }
        }
    }


    @Override
    public void eliminerDoublons() {
        FilePrioChainee temp = new FilePrioChainee();

        for (int i = 0; i < taille; i++) {
            if(!temp.contient(elements.getInfo())){
                temp.enfiler(elements.getInfo());
            }
            elements.setSuivant(elements.getSuivant());
        }

        elements = temp.elements;
        taille = temp.taille;

    }

    @Override
    public int prioriteMax() throws FileVideException {
        return elements.getInfo().getPriorite();
    }

    @Override
    public int prioriteMin() throws FileVideException {
        return trouverDernierMaillon().getInfo().getPriorite();
    }

    @Override
    public IFilePrio<T> copie() {
        FilePrioChainee<T> nouvelleFile = new FilePrioChainee<T>();
        Maillon<T> m = elements;

        for (int i = 0; i < taille; i++) {
            nouvelleFile.enfiler(m.getInfo());
            m = m.getSuivant();
        }

        return nouvelleFile;
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
     * Cherche à travers les maillons le prochain dont l'info contient une valeur plus petite que
     * celle contenue dans l'info du nouveau. Puis, place le nouveau maillon à cet endroit dans la
     * chaine. Si toutes les infos des maillons ont un ordre de priorité plus grand, alors le nouveau
     * est ajouté à la fin.
     *
     * @param m maillon a placer dans la file
     */
    private void placerMaillonDansFile(Maillon<T> m) {
        Maillon <T> precedent;


        precedent = trouverDernierMaillon(m.getInfo().getPriorite());

        //Si precedent == dernier maillon
        if(precedent == null){
            trouverDernierMaillon().setSuivant(m);
        }
        //Maillon quelquonque
        else{
            m.setSuivant(precedent.getSuivant());
            precedent.setSuivant(m);
        }
    }

    /**
     * Retourne le premier maillon avec la priorité demandée ou null si aucun maillon n'a
     * cette priorité.
     *
     * @param priorite priorité recherchée
     * @return premier maillon avec la priorité passée en paramètre
     */
    private Maillon<T> trouverPremierMaillon(int priorite) {
        Maillon <T> m = elements;

        while(
                m.getSuivant() != null &&
                        m.getSuivant().getInfo().getPriorite() > priorite
        ){
            m = m.getSuivant();
        }
        if(m.getInfo().getPriorite() != priorite){
            m = null;
        }

        return m;
    }

    /**
     * Retourne le dernier maillon de la chaîne
     *
     * @return dernier maillon de la chaîne
     */
    private Maillon<T> trouverDernierMaillon() {
        Maillon <T> m = elements;

        while (m.getSuivant() != null){
            m.setSuivant(m.getSuivant());
        }

        return m;
    }

    /**
     * Retourne le dernier maillon avec la priorité demandée ou null si aucun maillon n'a
     * cette priorité.
     *
     * @param priorite priorité recherchée
     * @return dernier maillon avec la priorité passée en paramètre
     */
    private Maillon<T> trouverDernierMaillon(int priorite) {
        Maillon <T> m = elements;

        while(
                m.getSuivant() != null &&
                        m.getSuivant().getInfo().getPriorite() >= priorite
        ){
            m = m.getSuivant();
        }

        if(m.getInfo().getPriorite() != priorite){
            m = null;
        }

        return m;
    }

    /**
     * Retourne l'avant dernier maillon ou null si la taille est de moins de deux.
     *
     * @return l'avant dernier maillon ou null si la taille est de moins de deux.
     */
    private Maillon<T> trouverAvantDernierMaillon() {
        Maillon<T> m;

        if(taille < 2){
            m = null;
        }
        else{
            m = elements;
            while (m.getSuivant().getSuivant() != null){
                m.setSuivant(m.getSuivant());
            }
        }

        return m;
    }
}
