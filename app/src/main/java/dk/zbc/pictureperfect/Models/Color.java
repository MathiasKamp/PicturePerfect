package dk.zbc.pictureperfect.Models;

/**
 * this class represents a argb color object.
 * with an aplha, red, green, blue and population property.
 */

public class Color {

    private double red;
    private double green;
    private double blue;
    private double alpha;
    private double population;


    /**
     * the constructor of Color.
     * At creation of a Color object every property is being set.
     * @param red   : double value of red
     * @param green : double value of green
     * @param blue  : double value of blue
     * @param alpha : double value of alpha
     * @param population : double value of the population (amount of pixels which has this color)
     */

    public Color(double red, double green, double blue, double alpha, double population){
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
    public int compareTo(Color color){

        int result = 0;

        if (this.population < color.getPopulation()){

            result = -1;
        }

        if (this.population > color.getPopulation()){
            result = 1;
        }

        return result;
    }

}