package com.example.animalworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.animalworld.Interface.mobileAdInterface.mobileAdInterface;
import com.example.animalworld.MobileAdGeneric.MobileInterstitialAd;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, mobileAdInterface {

    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,home;
    TextToSpeech tts;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, this);
        img1 = (ImageView) findViewById(R.id.im1);
        img2 = (ImageView) findViewById(R.id.im2);
        img3 = (ImageView) findViewById(R.id.im3);
        img4 = (ImageView) findViewById(R.id.im4);
        img5 = (ImageView) findViewById(R.id.im5);
        img6 = (ImageView) findViewById(R.id.im6);
        img7 = (ImageView) findViewById(R.id.im7);
        img8 = (ImageView) findViewById(R.id.im8);
        img9 = (ImageView) findViewById(R.id.im9);
        img10 = (ImageView) findViewById(R.id.im10);
        img11 = (ImageView) findViewById(R.id.im11);
        img12 = (ImageView) findViewById(R.id.im12);
        home = (ImageView) findViewById(R.id.logout);

        firebaseDatabase = FirebaseDatabase.getInstance();
        appName = firebaseDatabase.getReference().child("Apps");

        String packageName = MainActivity.this.getPackageName().toString();
        Log.d("TAG", "onCreate: "+packageName);

        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(MainActivity.this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mobileAdInterface mobileAdInterface = this;
        home.bringToFront();

        appName.push().orderByChild("id").equalTo(packageName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!=null){

                    HashMap hashMap = new HashMap();
                    hashMap.put("Banner_id","123");
                    hashMap.put("interstitial_id","4321");
                    hashMap.put("id",packageName);

                    appName.push().setValue(hashMap);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.UpdateDialogStyle));
                /*  builder.setTitle(R.string.app_name);*/

                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MobileInterstitialAd mobileInterstitialAd = new MobileInterstitialAd(MainActivity.this,mobileAdInterface,MainActivity.this);
                                if(mobileInterstitialAd.loadAd()!=null){
                                    finish();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Cow");
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Horse");
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Lion");
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Camel");
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Giraffe");
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Bull");
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Sheep");
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Snake");
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Deer");
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Elephant");
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Rabbit");
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut("Zebra");
            }
        });



    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(MainActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
            } else {
                img1.setEnabled(true);
                img2.setEnabled(true);
                img3.setEnabled(true);
                img4.setEnabled(true);
                img5.setEnabled(true);
                img6.setEnabled(true);
                img7.setEnabled(true);
                img8.setEnabled(true);
                img9.setEnabled(true);
                img10.setEnabled(true);
                img11.setEnabled(true);
                img12.setEnabled(true);
                speakOut("");
            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void speakOut(String text)
    {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onAdLoaded(InterstitialAd ad, Activity activity) {
        ad.show(activity);
        ad.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                finish();
            }

            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                System.exit(1);
            }
        });
    }

    @Override
    public void onAdFailedToLoad(String error, Activity activity) {

        finish();
    }
}
