package dk.zbc.pictureperfect.Contract;

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

    /**
     * this method is used by the HandlePicture. it is used by the MainActivityPresenter to pass data.
     */
    interface PictureHandler{

        /**
         * this method is used to pass the provided bitmap to the HandlePicture thread.
         * @param bitmap    : provided bitmap by MainActivity
         */
        void setBitMap(Bitmap bitmap);

        /**
         * this method is used to start the HandlePicture Thread.
         */
        void runThread();
    }
}
