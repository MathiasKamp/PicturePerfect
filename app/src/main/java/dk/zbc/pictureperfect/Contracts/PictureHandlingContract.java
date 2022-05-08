package dk.zbc.pictureperfect.Contracts;

import android.graphics.Bitmap;

import java.util.List;

import dk.zbc.pictureperfect.Models.Color;

public interface PictureHandlingContract {
    /**
     * this interface is used to pass data from HandlePicture thread to the MainActivityPresenter
     */
    interface Presenter {
        /**
         * this method is used to pass a list of color to the MainActivityPresenter
         * @param topFiveColors : a list of color with the top 5 populated colors
         */
        void onCheckBitMap(List<Color> topFiveColors);
    }


    interface PicturePixelHandler{

        void onPictureAnalysed(List<Color> colorsInBitmap);
    }
}
