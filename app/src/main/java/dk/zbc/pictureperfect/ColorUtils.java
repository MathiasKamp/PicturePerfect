package dk.zbc.pictureperfect;

import java.util.List;

import dk.zbc.pictureperfect.Models.Color;

public class ColorUtils {

    /**
     * this method is used to sort a list of color by their population (count of pixels in the given rgb value) using bubble sort method
     * @param colors       : list of colors to be sorted
     * @return             : a sorted list of colors
     */
    public static List<Color> sortColorList(List<dk.zbc.pictureperfect.Models.Color> colors) {

        dk.zbc.pictureperfect.Models.Color tmpColor;

        boolean isSorted = false;

        if (colors.size() > 0) {

            while (!isSorted) {

                isSorted = true;

                for (int i = 0; i < colors.size() - 1; i++) {

                    if (colors.get(i).compareTo(colors.get(i + 1)) > 0) {
                        tmpColor = colors.get(i);
                        colors.set(i, colors.get(i + 1));
                        colors.set(i + 1, tmpColor);
                        isSorted = false;
                    }
                }
            }
        }

        return colors;
    }

    /**
     * This method is used to return the top 5 colors the total amount of colors
     * @param colors    : total list of colors
     * @return          : a list of colors with 5 color objects in it
     */
    public static List<Color> returnTopFiveOfList(List<dk.zbc.pictureperfect.Models.Color> colors) {

        if (colors.size() > 5) {
            return colors.subList(colors.size() - 5, colors.size());
        }

        return colors;

    }


}
