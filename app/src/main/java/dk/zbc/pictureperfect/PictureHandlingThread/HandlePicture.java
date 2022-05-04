package dk.zbc.pictureperfect.PictureHandlingThread;

import android.graphics.Bitmap;

import androidx.palette.graphics.Palette;
import java.util.ArrayList;
import java.util.List;

import dk.zbc.pictureperfect.Contract.PictureHandlingContract;
import dk.zbc.pictureperfect.Models.Color;

/**
 * This class represents a thread that handles a bitmap and retrieves the top 5 colors of the bitmap
 */

public class HandlePicture implements Runnable, PictureHandlingContract.PictureHandler {

    private Bitmap bitmap;

    private PictureHandlingContract.Presenter presenter;

    public HandlePicture(PictureHandlingContract.Presenter presenter){
        this.presenter = presenter;

    }

    /**
     * this method is the run method of thread.
     * in here i collect the color list from the bitmap
     * get the top 5 populated colors and returns them via the presenter interface
     */
    @Override
    public void run() {

        List<Color> colorsFromBitmap = getColorsFromBitMap(bitmap);

        List<Color> getTopFive = getTopFivePopulatedColor(colorsFromBitmap);

        presenter.onCheckBitMap(getTopFive);

    }

    /**
     * This method is used to return the top 5 colors the total amount of colors
     * @param colors    : total list of colors
     * @return          : a list of colors with 5 color objects in it
     */
    public List<Color> getTopFivePopulatedColor(List<dk.zbc.pictureperfect.Models.Color> colors) {

        if (colors.size() > 5) {
            return colors.subList(colors.size() - 5, colors.size());
        }

        return colors;

    }

    /**
     * this method is used to sort a list of color by their population (count of pixels in the given rgb value) using bubble sort method
     * @param colors       : list of colors to be sorted
     * @return             : a sorted list of colors
     */
    private List<dk.zbc.pictureperfect.Models.Color> sortColorList(List<dk.zbc.pictureperfect.Models.Color> colors) {

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
     * this method is using the Palette library to create Platte.Swatch objects of each color represented in a bitmap
     * @param bitmap    : the bitmap of an image
     * @return          : list of color objects with all the colors represented in the bitmap
     */
    private List<dk.zbc.pictureperfect.Models.Color> getColorsFromBitMap(Bitmap bitmap) {

        // get swatch (group of colors)
        List<Palette.Swatch> swatches = Palette.from(bitmap).generate().getSwatches();

        ArrayList<Color> colors = new ArrayList<>();

        if (swatches.size() > 0) {

            for (Palette.Swatch swatch : swatches) {

                int rgb = swatch.getRgb();

                dk.zbc.pictureperfect.Models.Color tmpColor = new dk.zbc.pictureperfect.Models.Color(android.graphics.Color.red(rgb), android.graphics.Color.green(rgb), android.graphics.Color.blue(rgb), android.graphics.Color.alpha(rgb), swatch.getPopulation());

                System.out.println(swatch.getTitleTextColor());
                colors.add(tmpColor);
            }
        }

        return sortColorList(colors);
    }

    /**
     * interface method from invoked by MainActivityPresenter setting the provided bitmap
     * @param bitmap    : provided bitmap from MainActivityPresenter
     */
    @Override
    public void setBitMap(Bitmap bitmap) {

        this.bitmap = bitmap;
    }

    /**
     * interface invoked by MainActivityPresenter used to start the thread.
     */
    @Override
    public void runThread() {

        run();
    }
}
