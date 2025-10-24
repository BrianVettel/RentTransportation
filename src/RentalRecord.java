import java.time.LocalDate;
/**
 * Class ini berfungsi untuk menyimpan data riwayat penyewaan.
 * Menggantikan penggunaan dua array terpisah di Customer.
 * Menyimpan tanggal sewa, tanggal jatuh tempo, dan tanggal kembali.
 */
public class RentalRecord {
    private final Rentable vehicle;
    private final LocalDate rentDate;     // Tanggal sewa (real-time)
    private final LocalDate dueDate;      // Tanggal jatuh tempo
    private LocalDate returnDate;   // Tanggal pengembalian (diisi nanti)
    private boolean isReturned;

    /**
     * Constructor untuk membuat catatan sewa baru.
     * @param vehicle Kendaraan yang disewa
     * @param rentDate Tanggal sewa (dari LocalDate.now() di RentalSystem)
     * @param durationInDays Durasi sewa (dari input user)
     */
    public RentalRecord(Rentable vehicle, LocalDate rentDate, int durationInDays) {
        this.vehicle = vehicle;
        this.rentDate = rentDate;
        // Menghitung tanggal jatuh tempo secara otomatis
        this.dueDate = rentDate.plusDays(durationInDays);
        this.isReturned = false;
    }

    // --- Getters ---
    public Rentable getVehicle() {
        return vehicle;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    /**
     * Method untuk menandai kendaraan telah dikembalikan.
     * Menggunakan tanggal "hari ini" secara real-time.
     */
    public void returnVehicle() {
        this.returnDate = LocalDate.now(); // Tanggal real-time saat ini
        this.isReturned = true;
    }

    /**
     * Helper method untuk mengambil brand kendaraan dengan aman.
     */
    public String getVehicleBrand() {
        if (vehicle instanceof AbstractVehicle) {
            return ((AbstractVehicle) vehicle).getBrand();
        }
        return "Kendaraan Tidak Dikenal";
    }
}