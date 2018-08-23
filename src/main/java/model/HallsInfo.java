package model;

import model.enums.Place;

import java.util.ArrayList;

/**
 * Created by gaara on 3/28/17.
 */
public class HallsInfo {
    private int rowcount;
    private int checkboxs;
    private ArrayList<ArrayList<Boolean>> matrOfchecks;


    public HallsInfo(int rowcount, int checkboxs, ArrayList<ArrayList<Boolean>> matrOfchecks) {
        this.rowcount = rowcount;
        this.checkboxs = checkboxs;
        this.matrOfchecks = matrOfchecks;
    }

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getCheckboxs() {
        return checkboxs;
    }

    public void setCheckboxs(int checkboxs) {
        this.checkboxs = checkboxs;
    }

    public ArrayList<ArrayList<Boolean>> getMatrOfchecks() {
        return matrOfchecks;
    }

    public void setMatrOfchecks(ArrayList<ArrayList<Boolean>> matrOfchecks) {
        this.matrOfchecks = matrOfchecks;
    }

    public ArrayList<ArrayList<Place>> getPlaces()
    {
        ArrayList<ArrayList<Place>> row = new ArrayList<>();

        for (ArrayList<Boolean> elm :matrOfchecks)
        {
            ArrayList<Place> col = new ArrayList<>();
            for (int i = 0; i < elm.size(); i++) {
                if (elm.get(i))
                    col.add(Place.Empty);
                else
                    col.add(Place.NotExist);
            }
            row.add(col);
        }
        return row;
    }
}
