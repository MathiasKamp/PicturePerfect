package dk.zbc.pictureperfect.Contracts;

import android.graphics.Bitmap;

import java.util.List;

import dk.zbc.pictureperfect.Models.Color;

public interface MainActivityContract {

    /**
     * this interface is used by the MainActivity to collect result from who ever implements it.
     */
    interface View {

        /**
         * this method is used to return a list of colors
         * @param topFiveColors : list of colors
         */
        void onSuccess(List<Color> topFiveColors);

        /**
         * this method is used to show the MainActivity what went wrong if onSuccess was not successful
         * @param message   : error message
         */
        void onError(String message);
    }

    /**
     * this interface is used by the MainActivity pass data to the MainActivityPresenter
     * MainActivityPresenter is the class which implements the interface.
     */
    interface Presenter{

        /**
         * this method is used to pass a bitmap from MainActivity to MainActivityPresenter
         * @param bitmap    : bitmap of the taken image
         */
        void doCheckPicture(Bitmap bitmap);
    }
}
