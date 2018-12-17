package azisalvriyanto.uinsunankalijaga.Api;

import android.widget.EditText;

import azisalvriyanto.uinsunankalijaga.Model.ModelMasuk;
import azisalvriyanto.uinsunankalijaga.Model.ModelPengguna;
import azisalvriyanto.uinsunankalijaga.Model.ModelPenggunaPerbaharui;
import azisalvriyanto.uinsunankalijaga.Model.ModelRiwayatList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("aa_riwayat.php")
    Call<ModelRiwayatList> riwayat(@Field("nip") String username);

    @FormUrlEncoded
    @POST("masuk.php")
    Call<ModelMasuk> masuk(
            @Field("nip") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("pengguna.php")
    Call<ModelPengguna> pengguna(@Field("nip") String username);

    @FormUrlEncoded
    @POST("pengguna_perbaharui.php")
    Call<ModelPenggunaPerbaharui> pengguna_perbaharui(
            @Field("nip") String username,
            @Field("nama") String nama,
            @Field("lahir_tempat") String lahir_tempat,
            @Field("lahir_tanggal") String lahir_tanggal,
            @Field("jkelamin") String jkelamin,
            @Field("fakultas") String fakultas,
            @Field("golongan") String golongan,
            @Field("email") String email
    );
}
