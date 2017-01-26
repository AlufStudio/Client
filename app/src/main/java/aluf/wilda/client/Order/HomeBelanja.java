package aluf.wilda.client.Order;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import aluf.wilda.client.R;

public class HomeBelanja extends AppCompatActivity implements View.OnClickListener {
    Button btnSayur, btnBumbu,btnDaging,btnLauk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_belanja);
        //button
        btnSayur = (Button)findViewById(R.id.btnSayur);
        btnDaging = (Button)findViewById(R.id.btnDaging);
        btnBumbu = (Button)findViewById(R.id.btnBumbu);
        btnLauk=(Button)findViewById(R.id.btnLauk);

        btnSayur.setOnClickListener(this);
        btnDaging.setOnClickListener(this);
        btnBumbu.setOnClickListener(this);
        btnLauk.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSayur:
                startActivity(new Intent(this,Belanja.class));
                break;
            case R.id.btnDaging:
                break;
            case R.id.btnBumbu:
                break;
            case R.id.btnLauk:
                break;
        }

    }
}
