import java.util.ArrayList; // Import ArrayList

/**
 * Class Customer mewakili pelanggan yang dapat menyewa kendaraan.
 * REVISI: Menggunakan ArrayList<RentalRecord> untuk data real-time.
 */
public class Customer {
    // Variabel instance
    private String name;

    // REVISI: Mengganti dua array (rentedVehicles, rentalDurations)
    // dengan satu ArrayList<RentalRecord>
    private ArrayList<RentalRecord> rentalHistory;

    // (rentedCount tidak diperlukan lagi, kita bisa pakai rentalHistory.size())

    // Constructor default
    public Customer() {
        this("Anonymous");
    }

    // Constructor dengan parameter
    public Customer(String name) {
        this.name = name;
        // REVISI: Inisialisasi ArrayList
        this.rentalHistory = new ArrayList<>();
    }

    // --- Getter dan Setter ---
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * REVISI: Getter untuk riwayat sewa (menggantikan getter array lama)
     */
    public ArrayList<RentalRecord> getRentalHistory() {
        return rentalHistory;
    }

    // (Getter untuk rentedVehicles, rentalDurations, rentedCount dihapus
    // karena variabelnya sudah tidak ada)


    /**
     * REVISI: Mengganti nama dari addRentedVehicle(Rentable, int).
     * Sekarang menerima objek RentalRecord yang sudah berisi
     * tanggal real-time dari RentalSystem.
     */
    public void addRentalRecord(RentalRecord record) {
        // (Batas 5 kendaraan bisa diterapkan jika mau)
        if (rentalHistory.size() < 5) {
            this.rentalHistory.add(record);
            // Pesan sukses dipindahkan ke RentalSystem
        } else {
            System.out.println("Maksimal 5 kendaraan dapat disewa per pelanggan.");
        }
    }

    /**
     * Menampilkan daftar kendaraan yang disewa pelanggan.
     * REVISI: Membaca data dari ArrayList<RentalRecord>.
     */
    public void showRentedVehicles() {
        if (rentalHistory.isEmpty()) {
            System.out.println(name + " belum menyewa kendaraan.");
            return;
        }

        System.out.println("Daftar kendaraan yang disewa oleh " + name + ":");

        // Loop menggunakan enhanced-for loop untuk ArrayList
        for (RentalRecord record : rentalHistory) {
            if (record == null) continue;

            // Kita bisa ambil data langsung dari record
            String brand = record.getVehicleBrand();
            String type = record.getVehicle().getClass().getSimpleName();

            System.out.println("- " + type + " (" + brand + ")");
            System.out.println("  Disewa tgl : " + record.getRentDate());
            System.out.println("  Jatuh tempo: " + record.getDueDate());

            if (record.isReturned()) {
                System.out.println("  Status     : SUDAH KEMBALI (" + record.getReturnDate() + ")");
            } else {
                System.out.println("  Status     : SEDANG DISEWA");
            }
        }
    }

    // --- Method Demonstrasi (Tidak perlu diubah) ---

    /**
     * Demonstrasi: Passing by value (tidak akan mengubah nilai asli).
     */
    public void tryChangeNameByValue(String newName) {
        newName = "Mr./Ms. " + newName; // Tidak berpengaruh ke nama asli
    }

    /**
     * Demonstrasi: Passing by reference (mengubah nama asli).
     */
    public void changeNameByReference(Customer cust, String newName) {
        cust.setName(newName); // Langsung ubah pada objek
    }
}