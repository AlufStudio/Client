package aluf.wilda.client.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import aluf.wilda.client.DaftarOrder.DaftarBelanja;
import aluf.wilda.client.Order.Belanja;
import aluf.wilda.client.R;
import aluf.wilda.client.Server.Koneksi;
import aluf.wilda.client.T_App.Call;
import aluf.wilda.client.T_App.Info;

public class Dashbord extends AppCompatActivity implements View.OnClickListener {
    Button btnBelanja, btnDBelanja,btnInfo,btnCall;
    ImageView imgOrder, imgList, imgInfo,imgCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        TextView textemail;

        //Initializing textview
        // textemail= (TextView) findViewById(R.id.textemail);
        //gambar
        imgOrder = (ImageView) findViewById(R.id.imgOrder);
        imgList = (ImageView)findViewById(R.id.imgListOrder);
        imgInfo = (ImageView)findViewById(R.id.imgInfo);
        imgCall =(ImageView)findViewById(R.id.imgCall);

        imgOrder.setOnClickListener(this);
        imgList.setOnClickListener(this);
        imgInfo.setOnClickListener(this);
        imgCall.setOnClickListener(this);

        //button
        btnBelanja = (Button)findViewById(R.id.btn_belanja);
        btnDBelanja = (Button)findViewById(R.id.btn_daftar_belanja);
        btnInfo = (Button)findViewById(R.id.btn_info);
        btnCall =(Button)findViewById(R.id.btn_call);

        btnBelanja.setOnClickListener(this);
        btnDBelanja.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnInfo.setOnClickListener(this);

        // Fetching nama username user dari shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(Koneksi.EMAIL_SHARED_PREF,"Not Available");

        // Tampilkan nama user login kedalam View
        // textemail.setText("Nama User: " + email);
    }

    //Method untuk proses Logout User
    private void logout(){
        // Munculkan alert dialog apabila user ingin keluar aplikasi
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Untuk logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        // Getting out
                        SharedPreferences preferences = getSharedPreferences(Koneksi.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        // put nilai false untuk login
                        editor.putBoolean(Koneksi.LOGGEDIN_SHARED_PREF, false);

                        // put nilai untuk username
                        editor.putString(Koneksi.EMAIL_SHARED_PREF, "");

                        //Simpan ke haredpreferences

                        editor.commit();

                        // Balik dan tampilkan ke halaman Utama aplikasi jika logout berhasil
                        Intent intent = new Intent(Dashbord.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
// Pilihan jika NO
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        // Tampilkan alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    // Method untuk pembuatan Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu Untuk toolbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            // Panggil method logout ketika menu Logout di Klik
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_belanja:
                startActivity(new Intent(this,Belanja.class));
                break;
            case R.id.btn_daftar_belanja:
                startActivity(new Intent(this, DaftarBelanja.class));
                break;
            case R.id.btn_info:
                startActivity(new Intent(this, Info.class));
                break;
            case R.id.btn_call:
                startActivity(new Intent(this, Call.class));
                break;

            //gambar

            case R.id.imgOrder:
                startActivity(new Intent(this,Belanja.class));
                break;
            case R.id.imgListOrder:
                startActivity(new Intent(this, DaftarBelanja.class));
                break;
            case R.id.imgInfo:
                startActivity(new Intent(this, Info.class));
                break;
            case R.id.imgCall:
                startActivity(new Intent(this, Call.class));
                break;
        }

    }
}
