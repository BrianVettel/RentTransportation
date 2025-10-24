// REVISI TOTAL UNTUK REAL-TIME
import java.time.LocalDate;
import java.time.temporal.ChronoUnit; // Import untuk menghitung selisih hari
import java.util.ArrayList;

public class RentalUtils {
    // Konstanta
    // DEADLINE_DAYS tidak lagi dipakai, denda dihitung dari jatuh tempo
    private static final int DENDA_PER_HARI = 25000;

    /**
     * REVISI: Menghitung biaya berdasarkan durasi sewa aktual (real-time).
     * @param durationInDays durasi aktual dalam hari (long)
     * @param pricePerDay harga sewa
     */
    public static double calculateCost(long durationInDays, double pricePerDay) {
        // Jika kembali di hari yang sama, tetap dihitung 1 hari
        long actualDuration = (durationInDays == 0) ? 1 : durationInDays;
        return pricePerDay * actualDuration;
    }

    /**
     * Menghitung diskon 10% jika total biaya melebihi 1 juta.
     * (Logika ini tidak perlu diubah)
     */
    public static double calculateDiscount(double total) {
        return (total > 1000000) ? total * 0.10 : 0;
    }

    /**
     * REVISI: Menghitung denda keterlambatan berdasarkan tanggal real-time.
     * @param dueDate Tanggal jatuh tempo
     * @param returnDate Tanggal pengembalian
     */
    public static int calculatePenalty(LocalDate dueDate, LocalDate returnDate) {
        // Cek apakah tanggal kembali LEBIH DARI tanggal jatuh tempo
        if (returnDate.isAfter(dueDate)) {
            // Hitung selisih hari keterlambatan
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return (int) daysLate * DENDA_PER_HARI;
        }
        return 0; // Tidak ada denda
    }

    /**
     * REVISI: Menampilkan invoice akhir kepada pelanggan secara lengkap.
     * Membaca data dari ArrayList<RentalRecord>.
     */
    public static void printInvoice(Customer customer) {
        System.out.println("\n===============================");
        System.out.println("         INVOICE SEWA          ");
        System.out.println("===============================");
        System.out.println("Nama Pelanggan : " + customer.getName());

        // Ambil data riwayat sewa (bukan array lama)
        ArrayList<RentalRecord> history = customer.getRentalHistory();

        double subtotal = 0;
        int totalDenda = 0;
        int itemsProcessed = 0;

        for (RentalRecord record : history) {

            // PENTING: Hanya cetak invoice untuk kendaraan
            // yang SUDAH DIKEMBALIKAN.
            if (!record.isReturned()) {
                continue; // Lewati kendaraan yang masih disewa
            }

            itemsProcessed++;
            Rentable v = record.getVehicle();

            // Hitung durasi sewa aktual (Bukan lagi dari input user)
            long durasiAktual = ChronoUnit.DAYS.between(record.getRentDate(), record.getReturnDate());
            // Jika kembali di hari yg sama, hitung 1 hari
            durasiAktual = (durasiAktual == 0) ? 1 : durasiAktual;

            double biaya = calculateCost(durasiAktual, v.getPricePerDay());
            int denda = calculatePenalty(record.getDueDate(), record.getReturnDate());

            subtotal += biaya;
            totalDenda += denda;

            String brand = record.getVehicleBrand();
            String type = v.getClass().getSimpleName();

            System.out.println((itemsProcessed) + ". " + type + " - " + brand);
            System.out.println("   Disewa    : " + record.getRentDate() + " s/d " + record.getReturnDate() + " ("+ durasiAktual +" hari)");
            System.out.println("   Jatuh Tempo: " + record.getDueDate());
            System.out.println("   Biaya     : Rp" + biaya + (denda > 0 ? " | Denda: Rp" + denda : ""));
        }

        if (itemsProcessed == 0) {
            System.out.println("\nTidak ada kendaraan yang telah dikembalikan.");
        }

        double diskon = calculateDiscount(subtotal);
        double totalBayar = subtotal - diskon + totalDenda;

        // Menampilkan ringkasan biaya
        System.out.println("-------------------------------");
        System.out.println("Subtotal      : Rp" + subtotal);
        System.out.println("Diskon        : Rp" + diskon);
        System.out.println("Denda Total   : Rp" + totalDenda);
        System.out.println("Total Bayar   : Rp" + totalBayar);
        System.out.println("===============================\n");
    }
}