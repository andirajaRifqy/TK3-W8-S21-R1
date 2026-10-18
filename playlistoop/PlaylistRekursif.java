package playlistoop;

import java.util.Arrays;

/*
 * ============================================================
 * TUGAS KELOMPOK 3 - WEEK 8
 * ALGORITHMIC ANALYSIS, MATHEMATICAL INDUCTION,
 * AND RECURSIVE FUNCTIONS
 *
 * Nama Anggota:
 * 1. DYNOTAMA PARAYU INDRAWASTRA - 2902824861
 * 2. RIZKY HAIDAR ZAKY - 2902825744
 * 3. M. RIFQY ANDIRAJA DJAMIL - 2902825302
 * 4. GOFAR CHAIRULLOH ANNAM - 2902853363
 * Kelas   : COSC6142036
 * ============================================================
 */
public class PlaylistRekursif {

    // Menyimpan array objek Lagu yang dianalisis.
    private Lagu[] playlist;

    public PlaylistRekursif(Lagu[] playlist) {
        this.playlist = playlist;
    }

    public Lagu[] getPlaylist() {
        return playlist;
    }

    /*
     * Tujuan       : Menghitung total durasi seluruh lagu secara rekursif.
     * Base case    : n == 0, tidak ada lagu lagi yang dijumlahkan.
     * Recursive    : durasi lagu ke-(n-1) + totalDurasi untuk n-1 lagu.
     * Kompleksitas : O(n), karena setiap lagu diproses satu kali.
     */
    public static double totalDurasi(Lagu[] list, int n) {
        if (n == 0) {
            return 0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /*
     * Tujuan       : Menampilkan lagu dari indeks terakhir ke indeks pertama.
     * Base case    : index < 0, seluruh lagu sudah ditampilkan.
     * Recursive    : tampilkan satu lagu lalu panggil index - 1.
     * Kompleksitas : O(n), karena setiap lagu diproses satu kali.
     */
    public static void tampilkanMundur(Lagu[] list, int index) {
        if (index < 0) {
            return;
        }

        System.out.println((index + 1) + ". " + list[index].getJudul());
        tampilkanMundur(list, index - 1);
    }

    /*
     * Tujuan       : Mencari nilai durasi terpanjang secara rekursif.
     * Base case    : index == 0, durasi lagu pertama menjadi nilai awal.
     * Recursive    : bandingkan durasi pada index dengan hasil dari index - 1.
     * Kompleksitas : O(n), karena satu perbandingan dilakukan per lagu.
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int index) {
        if (index == 0) {
            return list[0].getDurasi();
        }

        double durasiSebelumnya = cariDurasiTerpanjang(list, index - 1);
        return Math.max(list[index].getDurasi(), durasiSebelumnya);
    }

    /*
     * Fungsi pendukung untuk mendapatkan indeks lagu terpanjang agar
     * judul dan artis dapat ditampilkan. Fungsi ini tidak digunakan
     * sebagai pengganti pengukuran cariDurasiTerpanjang().
     * Kompleksitas : O(n).
     */
    public static int cariIndexTerpanjang(Lagu[] list, int index) {
        if (index == 0) {
            return 0;
        }

        int indexSebelumnya = cariIndexTerpanjang(list, index - 1);
        if (list[index].getDurasi() > list[indexSebelumnya].getDurasi()) {
            return index;
        }
        return indexSebelumnya;
    }

    /*
     * Menjalankan satu skenario pengujian dengan jumlah lagu tertentu.
     * Waktu diukur menggunakan System.nanoTime() lalu dikonversi ke ms.
     */
    public static void ujiPlaylist(Lagu[] semuaLagu, int jumlahLagu) {
        Lagu[] dataUji = Arrays.copyOf(semuaLagu, jumlahLagu);

        System.out.println("========================================");
        System.out.println("PENGUJIAN DENGAN " + jumlahLagu + " LAGU");
        System.out.println("========================================");

        long mulaiTotal = System.nanoTime();
        double total = totalDurasi(dataUji, dataUji.length);
        long selesaiTotal = System.nanoTime();

        long mulaiTerpanjang = System.nanoTime();
        double durasiTerpanjang = cariDurasiTerpanjang(dataUji, dataUji.length - 1);
        long selesaiTerpanjang = System.nanoTime();

        // Dipanggil terpisah untuk memperoleh judul/artis lagu terpanjang.
        int indexTerpanjang = cariIndexTerpanjang(dataUji, dataUji.length - 1);

        System.out.println("Daftar lagu secara terbalik:");
        long mulaiMundur = System.nanoTime();
        tampilkanMundur(dataUji, dataUji.length - 1);
        long selesaiMundur = System.nanoTime();

        System.out.printf("%nTotal durasi: %.2f menit%n", total);
        System.out.println("Lagu terpanjang: \""
                + dataUji[indexTerpanjang].getJudul() + "\" - "
                + dataUji[indexTerpanjang].getArtis() + " ("
                + String.format("%.2f", durasiTerpanjang) + " menit)");

        System.out.println("\nWaktu eksekusi:");
        System.out.printf("totalDurasi(): %.4f ms%n",
                (selesaiTotal - mulaiTotal) / 1_000_000.0);
        System.out.printf("tampilkanMundur(): %.4f ms%n",
                (selesaiMundur - mulaiMundur) / 1_000_000.0);
        System.out.printf("cariDurasiTerpanjang(): %.4f ms%n%n",
                (selesaiTerpanjang - mulaiTerpanjang) / 1_000_000.0);
    }

    public static void main(String[] args) {

        // Sepuluh data lagu untuk eksperimen n = 3, 5, dan 10.
        Lagu[] semuaLagu = {
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Fix You", "Coldplay", 4.18),
            new Lagu("Yellow", "Coldplay", 4.27),
            new Lagu("Viva La Vida", "Coldplay", 4.52),
            new Lagu("Photograph", "Ed Sheeran", 4.18),
            new Lagu("A Sky Full of Stars", "Coldplay", 4.28),
            new Lagu("Thinking Out Loud", "Ed Sheeran", 4.41),
            new Lagu("The Scientist", "Coldplay", 5.09),
            new Lagu("Adventure of a Lifetime", "Coldplay", 4.22)
        };

        PlaylistRekursif analisis = new PlaylistRekursif(semuaLagu);

        System.out.println("=== EKSPERIMEN ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Tugas Kelompok 3 - Week 8\n");

        // Pengujian sesuai instruksi tugas: 3, 5, dan 10 lagu.
        ujiPlaylist(analisis.getPlaylist(), 3);
        ujiPlaylist(analisis.getPlaylist(), 5);
        ujiPlaylist(analisis.getPlaylist(), 10);

        System.out.println("=== PENGUJIAN SELESAI ===");
    }
}
