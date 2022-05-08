package dk.zbc.pictureperfect;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import dk.zbc.pictureperfect.Contracts.PictureHandlingContract;
import dk.zbc.pictureperfect.Models.Color;
import dk.zbc.pictureperfect.PictureHandlingThread.HandlePicturePixels;

public class PicturePixelHandler implements PictureHandlingContract.PicturePixelHandler {

    private final PictureHandlingContract.Presenter presenter;

    private final List<Color> colors = new ArrayList<>();

    private final Bitmap bitmapToAnalyse;

    private ArrayList<Thread> threads;


    public void getTopFiveColors() {

        startPictureAnalysingThreads();

        List<Color> colors = ColorUtils.sortColorList(this.colors);

        presenter.onCheckBitMap(ColorUtils.returnTopFiveOfList(colors));
    }


    public PicturePixelHandler(Bitmap bitmapToAnalyse, PictureHandlingContract.Presenter presenter){

        this.bitmapToAnalyse = bitmapToAnalyse;
        this.presenter = presenter;
        initPicturePixelThreads();
        getTopFiveColors();
    }

    public void initPicturePixelThreads() {

        Bitmap topBitmap = Bitmap.createBitmap(bitmapToAnalyse, 0, 0, bitmapToAnalyse.getWidth(), (bitmapToAnalyse.getHeight() / 2));
        Bitmap lowerBitmap = Bitmap.createBitmap(bitmapToAnalyse, 0, (bitmapToAnalyse.getHeight() / 2), bitmapToAnalyse.getWidth(), (bitmapToAnalyse.getHeight() / 2));


        threads = new ArrayList<>();
        threads.add( new Thread(new HandlePicturePixels(topBitmap, this)));
        threads.add( new Thread(new HandlePicturePixels(lowerBitmap,this )));

    }

    public void startPictureAnalysingThreads(){
        for (Thread thread:threads) {
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onPictureAnalysed(List<Color> colorsInBitmap) {

        mergeColorLists(colorsInBitmap);
    }

    public void mergeColorLists(List<Color> colorsInBitmap){

        this.colors.addAll(colorsInBitmap);
    }
}
