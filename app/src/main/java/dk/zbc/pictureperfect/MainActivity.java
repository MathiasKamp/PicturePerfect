package dk.zbc.pictureperfect;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dk.zbc.pictureperfect.Adapters.ColorAdapter;
import dk.zbc.pictureperfect.Contract.MainActivityContract;
import dk.zbc.pictureperfect.Models.Color;
import dk.zbc.pictureperfect.Presenter.MainActivityPresenter;

/**
 * this class represents the MainActivity.
 */

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {


    private ListView lv_DominantColors;
    private ImageView iv_takenPicture;
    private MainActivityContract.Presenter presenter;
    ActivityResultLauncher<Intent> activityResultLauncher;

    /**
     * This method is instantiating the elements on activity_main.xml.
     * it has a callback activityResult which gets invoked when the take picture button is called and the user has chosen the picture
     * @param savedInstanceState : a Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_TakePicture = findViewById(R.id.Btn_TakePicture);
        lv_DominantColors = findViewById(R.id.lv_DomniantColors);
        iv_takenPicture = findViewById(R.id.Iv_takenPicture);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Bundle bundle = result.getData().getExtras();

                        Bitmap bitmap = (Bitmap) bundle.get("data");

                        iv_takenPicture.setImageBitmap(bitmap);

                        presenter.doCheckPicture(bitmap);
                    }
                }
        );
        presenter = new MainActivityPresenter(this);

        btn_TakePicture.setOnClickListener(view -> startCameraIntent());
    }

    /**
     * this method is used to start an intent of MediaStore.ACTION_IMAGE_CAPTURE
     */
    private void startCameraIntent() {

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        activityResultLauncher.launch(takePicture);

    }

    /**
     * this method is used to collect a list of colors from the MainActivityPresenter
     * and put the list in the listview via the ColorAdapter
     * @param topFiveColors : list of colors
     */
    @Override
    public void onSuccess(List<Color> topFiveColors) {

        ColorAdapter colorAdapter = new ColorAdapter(this, 0, topFiveColors);

        lv_DominantColors.setAdapter(colorAdapter);

    }

    /**
     * this method is used to show the error message to the user via a Toast.
     * @param message   : error message
     */
    @Override
    public void onError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}