package aluf.wilda.client.Server;

/**
 * Created by wilda on 25/05/16.
 */
public class ConfigCRUD {

    public static final String URL_ADD="http://192.168.43.251/crud/creat.php";
    public static final String URL_GET_ALL = "http://192.168.43.251/client/lihatdata.php";
    public static final String URL_GET_ID="http://192.168.43.251/crud/get_data.php?id=";
    // Link untuk Update data
    public static final String URL_UPDATE_EMP="http://192.168.43.251/crud/update.php";
    // Link Untuk Hapus Data
    public static final String URL_DELETE_EMP="http://192.168.43.251/crud/delete.php";

    // Filed yang digunakan untuk dikirimkan ke Database, sesuaikan saja dengan Field di Tabel Mahasiswa
    public static final String KEY_EMP_ID = "id_menu";
    public static final String KEY_EMP_NAMA = "nm_makanan";
    public static final String KEY_EMP_HARGA = "harga";
    public static final String KEY_EMP_FOTO= "nm_gambar";

    // Tags Format JSON
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nama";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_FOTO= "foto";

    public static final String EMP_ID = "emp_id";

}