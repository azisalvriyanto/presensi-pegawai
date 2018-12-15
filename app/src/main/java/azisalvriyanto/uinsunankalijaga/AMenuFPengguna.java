package azisalvriyanto.uinsunankalijaga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import azisalvriyanto.uinsunankalijaga.Api.ApiClient;
import azisalvriyanto.uinsunankalijaga.Api.ApiService;
import azisalvriyanto.uinsunankalijaga.Model.ModelMasuk;
import azisalvriyanto.uinsunankalijaga.Model.ModelPengguna;
import azisalvriyanto.uinsunankalijaga.Model.ModelPenggunaData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class AMenuFPengguna extends Fragment {
    Button b_keluar;

    public AMenuFPengguna() {
        // Required empty public constructor
        //return Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.l_menu_fpengguna, container, false);
        final String username = SaveSharedPreference.getNIP(getActivity().getApplicationContext());

        Retrofit apiClient = ApiClient.getClient();
        ApiService apiService = apiClient.create(ApiService.class);
        Call<ModelPengguna> call = apiService.pengguna(username);
        call.enqueue(new Callback<ModelPengguna>() {
            @Override
            public void onResponse(Call<ModelPengguna> call, Response<ModelPengguna> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getStatus().equals("sukses")) {
                            TextView data_nip_tv = view.findViewById(R.id.l_fakun_tv_nip);
                            TextView data_nama_tv = view.findViewById(R.id.l_fakun_tv_nama);
                            TextView data_lahirtanggal_tv = view.findViewById(R.id.l_fakun_tv_lahirtanggal);

                            ModelPengguna indo = response.body();
                            String data_nip = indo.getData().getNIP();
                            String data_nama = indo.getData().getNama();
                            String data_lahirtanggal = indo.getData().getLahirTanggal();

                            data_nip_tv.setText(data_nip);
                            data_nama_tv.setText(data_nama);
                            data_lahirtanggal_tv.setText(data_lahirtanggal);
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Akun tidak ditemukan.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Response gagal.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Credentials are not valid.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelPengguna> call, Throwable t) {
                Log.e("TAG", "=======onFailure: " + t.toString());
                t.printStackTrace();
            }
        });



        b_keluar = view.findViewById(R.id.l_fakun_b_keluar);
        b_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set LoggedIn status to false
                SaveSharedPreference.setLoggedIn(getActivity().getApplicationContext(), false, "data_nip");
                Intent intent = new Intent(getActivity().getApplicationContext(), AMasuk.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}
