package com.jianpan.imagedecoder;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jianpan.imagedecoder.library.ImageDecoder;
import com.jianpan.imagedecoder.library.ImageSize;
import com.jianpan.imagedecoder.library.ImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "123.jpg";
    String outPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "new.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageDecoder decoder = new ImageDecoder();
        final ImageSize imageSize = new ImageSize(400, 800);
        final File file = new File(imagePath);
        if (file.exists()) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Bitmap bitmap = decoder.decode(file, imageSize);
                        ImageUtils.compressBmpToFile(bitmap, new File(outPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
