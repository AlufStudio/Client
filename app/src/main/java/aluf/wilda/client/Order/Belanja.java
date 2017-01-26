package aluf.wilda.client.Order;

import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import aluf.wilda.client.Server.Config;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import aluf.wilda.client.R;
import aluf.wilda.client.Server.ConfigCRUD;
import aluf.wilda.client.Server.RequestHandler;

public class Belanja extends AppCompatActivity implements ListView.OnItemClickListener {
    // Definisikan ListView
    private ListView listView;
    // Variabel untuk format String JSON
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_belanja);
        // Inisialiasi ListView
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        //   Method GetJSON
        getJSON();
    }
    private void TampilData(){
        // Data dalam bentuk Array kemudian akan kita ubah menjadi JSON Object
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(ConfigCRUD.TAG_JSON_ARRAY);
            // FOR untuk ambil data
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                // TAG_ID dan TAG_NAME adalah variabel yang ada di Class Config.java,
                String id = jo.getString(ConfigCRUD.TAG_ID);
                String nama = jo.getString(ConfigCRUD.TAG_NAME);
                String harga = jo.getString(ConfigCRUD.TAG_HARGA);
               // String foto = jo.getString(ConfigCRUD.TAG_FOTO);

                HashMap<String,String> mahasiswa = new HashMap<>();
                mahasiswa.put(ConfigCRUD.TAG_ID,id);
                mahasiswa.put(ConfigCRUD.TAG_NAME,nama);
                mahasiswa.put(ConfigCRUD.TAG_HARGA,harga);
                //mahasiswa.put(ConfigCRUD.TAG_FOTO,foto);
                list.add(mahasiswa);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Tampilkan datanya dalam Layout Lihat Data
        ListAdapter adapter = new SimpleAdapter(
                Belanja.this, list, R.layout.activity_belanja,
                new String[]{ConfigCRUD.TAG_ID,ConfigCRUD.TAG_NAME, ConfigCRUD.TAG_HARGA},
                new int[]{R.id.idMakanan, R.id.namaMakanan,R.id.hargaMakanan});
        // Tampilkan dalam bentuk ListView
        listView.setAdapter(adapter);
    }

    // Methode ambil data JSON yang kita definisikan dalam bentuk AsyncTask
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Belanja.this,"Pengambilan Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                // Panggil method tampil data
                TampilData();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                // Proses nya sesuai alamat URL letak script PHP yang kita set di Class Config.java
                String s = rh.sendGetRequest(ConfigCRUD.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
