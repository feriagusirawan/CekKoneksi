package com.koneksi.root.cekkoneksi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNetworkAvailable()){
            //Create an alertDialog
            AlertDialog.Builder checkBuilder = new AlertDialog.Builder(MainActivity.this);
//            checkBuilder.setIcon(R.drawable.error);
            checkBuilder.setTitle("Peringatan!");
            checkBuilder.setMessage("Periksa koneksi internet anda!");

            //Builder retry button
            checkBuilder.setPositiveButton("Ulangi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Restart the activity
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            checkBuilder.setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alert = checkBuilder.create();
            alert.show();
        }
        else {
            if (isNetworkAvailable()){
                Thread thread = new Thread(){
                    public void run(){
                        try {
                            sleep(4500);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        finally {
                            startActivity(new Intent(MainActivity.this, WebsiteActivity.class));
                            finish();
                        }
                    }
                };
                thread.start();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo           = connectivityManager.getActiveNetworkInfo();
        return  activeNetworkInfo != null;
    }
}