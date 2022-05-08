package dk.zbc.pictureperfect.PictureHandlingThread;

import android.graphics.Bitmap;

import androidx.palette.graphics.Palette;

import java.util.ArrayList;
import java.util.List;

import dk.zbc.pictureperfect.Contracts.PictureHandlingContract;
import dk.zbc.pictureperfect.Models.Color;

public class HandlePicturePixels implements Runnable {


    private final Bitmap bitmapToAnalyse;
    private final PictureHandlingContract.PicturePixelHandler picturePixelHandler;


    public HandlePicturePixels(Bitmap bitmapToAnalyse, PictureHandlingContract.PicturePixelHandler picturePixelHandler) {
        this.bitmapToAnalyse = bitmapToAnalyse;
        this.picturePixelHandler = picturePixelHandler;

    }

    @Override
    public void run() {

        picturePixelHandler.onPictureAnalysed(getColorsFromBitMap(bitmapToAnalyse));
    }

    /**
     * this method is using the Palette library to create Platte.Swatch objects of each color represented in a bitmap
     *
     * @param bitmap : the bitmap of an image
     * @return : list of color objects with all the colors represented in the bitmap
     */
    private List<Color> getColorsFromBitMap(Bitmap bitmap) {

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

        return colors;
    }
}
