package aluf.wilda.client.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


import aluf.wilda.client.R;
import aluf.wilda.client.Server.Koneksi_SignUp;

/**
 * Created by wilda on 02/05/16.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    // Definisi View dan Button
    private EditText txtnama;
    private EditText txtemail;
    private EditText txtnohp;
    private EditText txtpass;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
// Inisialisasi View dan Button
        txtnama = (EditText) findViewById(R.id.txt_nama);
        txtemail= (EditText) findViewById(R.id.txt_email);
        txtnohp= (EditText) findViewById(R.id.txt_nohp);
        txtpass= (EditText) findViewById(R.id.txt_pass);
        buttonRegister = (Button) findViewById(R.id.save);
        // Event penekanan Button
        buttonRegister.setOnClickListener(this);
    }
    // Method untuk Proses registrasi User
    private void DaftarUser(){
        // Ubah ketipe data String
        final String nama=txtnama.getText().toString().trim();
        final String email=txtemail.getText().toString().trim();
        final String no_hp=txtnohp.getText().toString().trim();
        final String password=txtpass.getText().toString().trim();
// Kirimkan data kserver menggunakan method : POST berdasarkan URL di Class Koneksi.java
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Koneksi_SignUp.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_LONG).show();

                            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(i);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams(){
                // Parameter yang dikirmkan sesuai di Class Koneksi.java
                Map<String,String> params = new HashMap<String, String>();
                params.put(Koneksi_SignUp.KEY_NAMA,nama);
                params.put(Koneksi_SignUp.KEY_EMAIL,email);
                params.put(Koneksi_SignUp.KEY_NO_HP, no_hp);
                params.put(Koneksi_SignUp.KEY_PASS, password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    // Panggil method DaftarUser
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
            DaftarUser();
                finish();
                break;


        }


    }
}
