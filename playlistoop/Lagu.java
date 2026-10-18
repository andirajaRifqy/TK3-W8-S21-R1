package playlistoop;

/*
 * Class Lagu digunakan untuk merepresentasikan sebuah lagu.
 *
 * Class ini menerapkan konsep enkapsulasi dengan membuat
 * seluruh atribut bersifat private dan menyediakan getter
 * dan setter untuk mengakses atribut tersebut.
 */
public class Lagu {

    // Atribut lagu dibuat private sebagai penerapan enkapsulasi
    private String judul;
    private String artis;
    private double durasi;

    /*
     * Constructor digunakan untuk membuat objek Lagu
     * dengan mengisi judul, artis, dan durasi.
     */
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter untuk mengambil nilai judul
    public String getJudul() {
        return judul;
    }

    // Setter untuk mengubah nilai judul
    public void setJudul(String judul) {
        this.judul = judul;
    }

    // Getter untuk mengambil nilai artis
    public String getArtis() {
        return artis;
    }

    // Setter untuk mengubah nilai artis
    public void setArtis(String artis) {
        this.artis = artis;
    }

    // Getter untuk mengambil nilai durasi
    public double getDurasi() {
        return durasi;
    }

    // Setter untuk mengubah nilai durasi
    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    /*
     * Method tampilkanInfo() digunakan untuk menampilkan
     * informasi lengkap dari sebuah lagu.
     */
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.printf("Durasi : %.2f menit%n", durasi);
    }
}
