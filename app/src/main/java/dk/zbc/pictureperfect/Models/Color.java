package dk.zbc.pictureperfect.Models;

import androidx.annotation.NonNull;

/**
 * this class represents a argb color object.
 * with an aplha, red, green, blue and population property.
 */

public class Color {

    private int red;
    private int green;
    private int blue;
    private int alpha;
    private int population;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }




    /**
     * the constructor of Color.
     * At creation of a Color object every property is being set.
     * @param red   : double value of red
     * @param green : double value of green
     * @param blue  : double value of blue
     * @param alpha : double value of alpha
     * @param population : double value of the population (amount of pixels which has this color)
     */

    public Color(int red, int green, int blue, int alpha, int population){
        this.alpha = alpha;
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.population = population;
    }


    /**
     * this method is used to override the toString method.
     *
     * @return : string value of the objects properties
     */
    @NonNull
    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }

    /**
     * this method is used to get the population of the color.
     * @return : double value of population
     */
    public double getPopulation() {
        return population;
    }


    /**
     * this method is used to compare the value between two color objects.
     * @param color : the color object that you want to compare with
     * @return      : integer value -1 if this object has less population than the compared object.
     *              : 1 if this object has greater population than the compared object.
     */
    public int compareTo(Color color) {

        int result = 0;

        if (this.population < color.getPopulation()) {

            result = -1;
        }

        if (this.population > color.getPopulation()) {
            result = 1;
        }

        return result;

    }

    public String getHexadecimalCodeOfRgb(){

        return String.format("#%02x%02x%02x", getRed(), getGreen() ,getBlue());
    }
}