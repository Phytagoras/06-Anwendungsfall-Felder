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
     * @param amounts - Platzgrößen der einzelnen CD-Ständer.
     */
    public CDCollectionHandler(int[] amounts){
        //TODO: 01 - Konstruktor fertigstellen.

    }

    /**
     *
     * @param box - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @param artist - Künstername/Bandname
     * @param title - Albumtitel
     * @return - true, falls ein Platz frei war und die CD hinzugefügt werden konnte, sonst false.
     */
    public boolean addNewCD(int box, int place, String artist, String title){
        //TODO: 02 - Hinzufügen einer CD
        return false;
    }

    /**
     * Diese Methode dient dazu, die Daten einer bestimmten Position im zweidimensionalem Array auszugeben.
     * @param box - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @return - Entweder ein String-Array mit "Künstler" - "Titel" oder mit "Empty" - "Empty".
     */
    public String[] getInfo(int box, int place){
        String[] output = new String[2];
        //TODO: 03 - Informationen zu einer bestimmen CD
        return output;
    }

    /**
     * Diese Methode dient dem Entfernen einer CD.
     * @param box - Gewählter CD-Ständer
     * @param place - Gewählter Platz
     * @return - true, falls eine vorhandene CD entfernt wurde, false, falls keine Cd zum entfernen vorhanden war.
     */
    public boolean releaseCD(int box, int place){
        //TODO: 04 - Entfernen einer bestimmten CD
        return false;
    }

    /**
     * Diese Methode dient dazu, die enthaltenen Daten aufzubereiten und als String-Array auszugeben.
     * @param index - CD-Ständer, um den es sich handelt.
     * @return Ein Array, das abwechselnd den jeweiligen Künstler und den jeweiligen Albumtitel enthält. Leere Plätze werden mit "Empty" gefüllt.
     */
    public String[] getAllCDsFrom(int index){
        //TODO: 05 - Vollständige Informationsausgabe aller CDs - Nach Fertigstellung im MainPanelHandler Zeile 165-167 entkommentieren
        return null;
    }

    /**
     * Diese Methode dient dazu, einen CD-Ständer zu komprimieren. Dabei rücken spätere CDs einfach auf. Die vorhandene Sortierung bleibt erhalten.
     * @param box - Gewählter CD-Ständer
     */
    public void pack(int box){
        //TODO: 06 - Komprimieren eines CD-Ständers, von unten nach oben
    }

    /**
     * Diese Methode dient dazu, einen CD-Ständer zu sortieren nach Artist+Title. Gleichzeitig wird der CD-Ständer komprimiert.
     * @param box - Gewählter CD-Ständer
     */
    public void sort(int box){
        //TODO: 07 - Sortieren eines CD-Ständers
    }
}