package control;

import model.CompactDisc;

/**
 * Created by Jean-Pierre on 21.10.2016.
 */
public class CDCollectionHandler {

    private CompactDisc[][] allCDs;

    /**
     * Die Anzahl an Platzgrößen gibt die Anzahl an CD-Ständern vor - hier 4.
     * Die CD-Ständer an sich sind so groß wie die jeweilige Platzgröße.
     *
     * @param amounts - Platzgrößen der einzelnen CD-Ständer.
     */
    public CDCollectionHandler(int[] amounts) {
        allCDs = new CompactDisc[amounts.length][];
        for (int i = 0; i < amounts.length; i++) {
            allCDs[i] = new CompactDisc[amounts[i]];
        }

    }

    /**
     * @param box    - Gewählter CD-Ständer
     * @param place  - Gewählter Platz
     * @param artist - Künstername/Bandname
     * @param title  - Albumtitel
     * @return - true, falls ein Platz frei war und die CD hinzugefügt werden konnte, sonst false.
     */
    public boolean addNewCD(int box, int place, String artist, String title) {
        while(place >= allCDs[box].length){
            CompactDisc[] newArr = new CompactDisc[allCDs[box].length+1];
            int i = 0;
            for (CompactDisc cd :
                    allCDs[box]) {
                newArr[i]=cd;
                i++;
            }
            allCDs[box] = newArr;
        }
        if (allCDs[box][place] == null) {
            allCDs[box][place] = new CompactDisc(artist, title);
            return true;
        }
        return false;
    }

    /**
     * Diese Methode dient dazu, die Daten einer bestimmten Position im zweidimensionalem Array auszugeben.
     *
     * @param box   - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @return - Entweder ein String-Array mit "Künstler" - "Titel" oder mit "Empty" - "Empty".
     */
    public String[] getInfo(int box, int place) {
        String[] output = new String[2];
        output[0] = allCDs[box][place].getArtist();
        if (output[0] == null) output[0] = "empty";
        output[1] = allCDs[box][place].getArtist();
        if (output[1] == null) output[1] = "empty";
        return output;
    }

    /**
     * Diese Methode dient dem Entfernen einer CD.
     *
     * @param box   - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @return - true, falls eine vorhandene CD entfernt wurde, false, falls keine Cd zum entfernen vorhanden war.
     */
    public boolean releaseCD(int box, int place) {
        allCDs[box][place] = null;
        return false;
    }

    /**
     * Diese Methode dient dazu, die enthaltenen Daten aufzubereiten und als String-Array auszugeben.
     *
     * @param index - CD-Ständer, um den es sich handelt.
     * @return Ein Array, das abwechselnd den jeweiligen Künstler und den jeweiligen Albumtitel enthält. Leere Plätze werden mit "Empty" gefüllt.
     */
    public String[] getAllCDsFrom(int index) {
        String[] output = new String[allCDs[index].length * 2];
        for (int i = 0; i < allCDs[index].length; i++) {
            if (allCDs[index][i] == null) {
                output[i * 2] = "empty - ";
                output[i * 2 + 1] = "empty";
            } else {
                output[i * 2] = allCDs[index][i].getArtist();
                output[i * 2 + 1] = allCDs[index][i].getTitle();
            }
        }

        return output;
    }

    /**
     * Diese Methode dient dazu, einen CD-Ständer zu komprimieren. Dabei rücken spätere CDs einfach auf. Die vorhandene Sortierung bleibt erhalten.
     *
     * @param box - Gewählter CD-Ständer
     */
    public void pack(int box) {
        int count = 0;
        for (CompactDisc cd :
                allCDs[box]) {
            if (cd != null)count++;
        }
        CompactDisc[] newCds = new CompactDisc[count];
        int i = 0;
        for (CompactDisc cd :
                allCDs[box]) {
            if(cd != null){
                newCds[i] = cd;
                i++;
            }
        }
        allCDs[box] = newCds;
        System.out.println("packed");
    }

    /**
     * Diese Methode dient dazu, einen CD-Ständer zu sortieren nach Artist+Title. Gleichzeitig wird der CD-Ständer komprimiert.
     *
     * @param box - Gewählter CD-Ständer
     */
    public void sort(int box) {
        pack(box);
        raddixSortUltraRecursiveTitle(box, getLongestName(box));
        raddixSortUltraRecursiveArtist(box,  getLongestArtist(box));

    }

    private void raddixSortUltraRecursiveArtist(int box, int depth) {
        if (depth> 0){
            int[] counting = new int[27];
            for (CompactDisc cd :
                    allCDs[box]) {
                if(cd.getArtist().length()<depth){
                    counting[0]++;
                }else{
                    int charValue = cd.getArtist().charAt(depth-1);
                    if(charValue == 32 || charValue == 46) counting[0]++;
                    else {
                        if(charValue < 97){
                            charValue += 32;
                        }
                        charValue -= 96;
                        counting[charValue]++;

                    }

                }

            }
            for(int i = 1; i < 27; i++){
                counting[i] = counting[i] + counting[i-1];
            }
            for(int i = 26; i > 0; i--){
                counting[i] = counting[i-1];
            }
            counting[0] = 0;
            CompactDisc[] newSorted = new CompactDisc[allCDs[box].length];

            for (CompactDisc cd :
                    allCDs[box]) {
                if(cd.getArtist().length()<depth){
                    newSorted[counting[0]] = cd;
                    counting[0]++;
                }else{
                    int charValue = cd.getArtist().charAt(depth -1);
                    if(charValue == 32 || charValue == 46) {
                        newSorted[counting[0]] = cd;
                        counting[0]++;
                    }
                    else {
                        if(charValue < 97){
                            charValue += 32;
                        }
                        charValue -= 96;
                        newSorted[counting[charValue]] = cd;
                        counting[charValue]++;

                    }

                }

            }
            allCDs[box] = newSorted;
            raddixSortUltraRecursiveArtist(box, depth - 1);
        }
    }


    private int getLongestName(int box) {
        int longest = 0;
        for (CompactDisc cd :
                allCDs[box]) {
            if (cd != null){
                if(cd.getTitle().length() > longest)longest = cd.getTitle().length();
            }
        }

        return longest;
    }
    private int getLongestArtist(int box) {
        int longest = 0;
        for (CompactDisc cd :
                allCDs[box]) {
            if (cd != null){
                if(cd.getArtist().length() > longest)longest = cd.getArtist().length();
            }
        }

        return longest;
    }

    private void raddixSortUltraRecursiveTitle(int box, int depth) {
        if (depth> 0){
            int[] counting = new int[27];
            for (CompactDisc cd :
                    allCDs[box]) {
                if(cd.getTitle().length()<depth){
                    counting[0]++;
                }else{
                    int charValue = cd.getTitle().charAt(depth-1);
                    if(charValue == 32 || charValue == 46) counting[0]++;
                    else {
                        if(charValue < 97){
                            charValue += 32;
                        }
                        charValue -= 96;
                        counting[charValue]++;

                    }

                }

            }
            for(int i = 1; i < 27; i++){
                counting[i] = counting[i] + counting[i-1];
            }
            for(int i = 26; i > 0; i--){
                counting[i] = counting[i-1];
            }
            counting[0] = 0;
            CompactDisc[] newSorted = new CompactDisc[allCDs[box].length];

            for (CompactDisc cd :
                    allCDs[box]) {
                if(cd.getTitle().length()<depth){
                    newSorted[counting[0]] = cd;
                    counting[0]++;
                }else{
                    int charValue = cd.getTitle().charAt(depth -1);
                    if(charValue == 32 || charValue == 46) {
                        newSorted[counting[0]] = cd;
                        counting[0]++;
                    }
                    else {
                        if(charValue < 97){
                            charValue += 32;
                        }
                        charValue -= 96;
                        newSorted[counting[charValue]] = cd;
                        counting[charValue]++;

                    }

                }

            }
            allCDs[box] = newSorted;
            raddixSortUltraRecursiveTitle(box, depth - 1);
        }
    }
}
