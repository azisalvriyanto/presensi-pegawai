package azisalvriyanto.uinsunankalijaga.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("lahir_tanggal")
    @Expose
    private String lahirTanggal;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLahirTanggal() {
        return lahirTanggal;
    }

    public void setLahirTanggal(String lahirTanggal) {
        this.lahirTanggal = lahirTanggal;
    }
}
