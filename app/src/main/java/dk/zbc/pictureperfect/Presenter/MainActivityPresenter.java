package dk.zbc.pictureperfect.Presenter;

import android.graphics.Bitmap;
import java.util.List;

import dk.zbc.pictureperfect.Contracts.MainActivityContract;
import dk.zbc.pictureperfect.Contracts.PictureHandlingContract;
import dk.zbc.pictureperfect.Models.Color;
import dk.zbc.pictureperfect.PicturePixelHandler;

/**
 * this method is used as the presenter for MainActivity
 */
public class MainActivityPresenter implements MainActivityContract.Presenter, PictureHandlingContract.Presenter {


    private final MainActivityContract.View view;
    private PictureHandlingContract.PicturePixelHandler picturePixelHandler = null;
    private Bitmap bitmapToAnalyse;

    /**
     * Constructor
     * instantiate the pictureHandler
     * @param view : Interface of view
     */
    public MainActivityPresenter(MainActivityContract.View view) {

        this.view = view;
    }

    /**
     * this method is used by MainActivity to pass the bitmap of the taken image.
     * i call pictureHandler.setBitmap and pictureHandler.runThread in this method.
     * @param bitmap : bitmap of the taken image
     */
    @Override
    public void doCheckPicture(Bitmap bitmap) {

        this.bitmapToAnalyse = bitmap;
        this.picturePixelHandler = new PicturePixelHandler(bitmapToAnalyse, this);

    }

    /**
     * this method is getting invoked by HandlePicture when the bitmap has been processed.
     * @param topFiveColors : a list of color with the top 5 populated colors
     */

    @Override
    public void onCheckBitMap(List<Color> topFiveColors) {

        if (topFiveColors.size() > 0) {

            view.onSuccess(topFiveColors);
        } else {
            view.onError("Something went wrong while retrieving the top 5 color codes of the image");
        }

    }


}
