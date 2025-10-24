import java.util.ArrayList;
import java.time.LocalDate; // <-- 1. IMPORT BARU yang wajib ada

/**
 * RentalSystem menyimpan dan mengelola semua kendaraan dalam sistem penyewaan.
 * Kendaraan bertipe Rentable (interface), memungkinkan polymorphism.
 */
public class RentalSystem {
    // Menggunakan ArrayList untuk menyimpan kendaraan secara fleksibel
    private ArrayList<Rentable> vehicles;

    // Constructor (Tidak berubah)
    public RentalSystem() {
        vehicles = new ArrayList<>();
    }

    /**
     * Menampilkan semua kendaraan yang tersedia (stok > 0).
     * (Tidak perlu diubah)
     */
    public void showAvailableVehicles() {
        System.out.println("\n--- Daftar Kendaraan Tersedia ---");
        boolean adaStok = false;

        for (int i = 0; i < vehicles.size(); i++) {
            Rentable r = vehicles.get(i);
            if (!r.isAvailable()) {
                continue;
            }
            System.out.print((i + 1) + ". ");
            r.displayInfo();
            adaStok = true;
        }

        if (!adaStok) {
            System.out.println("Maaf, semua kendaraan saat ini sedang disewa.");
        }
    }

    /**
     * Mengembalikan kendaraan berdasarkan indeks pilihan user.
     * (Tidak perlu diubah)
     */
    public Rentable getVehicle(int index) {
        if (index >= 0 && index < vehicles.size() && vehicles.get(index).isAvailable()) {
            return vehicles.get(index);
        } else if (index >= 0 && index < vehicles.size()) {
            System.out.println("Stok kendaraan tersebut habis.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }
        return null;
    }

    /**
     * Menampilkan semua kendaraan, baik yang tersedia maupun tidak.
     * (Tidak perlu diubah)
     */
    public void showAllVehicles() {
        System.out.println("\n--- Semua Kendaraan dalam Sistem ---");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.print((i + 1) + ". ");
            vehicles.get(i).displayInfo();
        }
    }

    /**
     * Menambahkan kendaraan baru ke sistem secara dinamis.
     * (Tidak perlu diubah)
     */
    public void addVehicle(Rentable vehicle) {
        vehicles.add(vehicle);
    }

    /**
     * REVISI REAL-TIME: Method LOGIKA UTAMA untuk memproses penyewaan.
     * Sekarang membuat objek RentalRecord menggunakan tanggal real-time.
     *
     * @param customer Pelanggan yang menyewa
     * @param vehicle Kendaraan yang disewa
     * @param duration Durasi sewa
     * @return true jika berhasil, false jika gagal
     */
    public boolean processRental(Customer customer, Rentable vehicle, int duration) {
        // 1. Coba sewa kendaraan (Update stok)
        if (vehicle.rent()) {

            // --- REVISI UTAMA ADA DI SINI ---

            // 2. Ambil tanggal hari ini (Real-Time)
            LocalDate rentDate = LocalDate.now();

            // 3. Buat catatan sewa (RentalRecord)
            RentalRecord record = new RentalRecord(vehicle, rentDate, duration);

            // 4. Panggil method BARU di Customer (addRentalRecord)
            //    Bukan lagi addRentedVehicle(vehicle, duration)
            customer.addRentalRecord(record);

            // --- SELESAI REVISI ---

            // 5. Berikan pesan sukses yang lebih informatif
            System.out.println(vehicle.getClass().getSimpleName() +
                    " berhasil disewa oleh " + customer.getName() +
                    "\n  Mulai tanggal: " + record.getRentDate() +
                    "\n  Jatuh tempo : " + record.getDueDate());
            return true;
        } else {
            // Gagal sewa (pesan error sudah dicetak oleh vehicle.rent())
            return false;
        }
    }


    // Getter untuk semua kendaraan (Tidak berubah)
    public ArrayList<Rentable> getVehicles() {
        return vehicles;
    }

    // Menampilkan total kendaraan (static method) (Tidak berubah)
    public void showTotalCreated() {
        System.out.println("Total kendaraan yang pernah dibuat: " + AbstractVehicle.getTotalVehicles());
    }
}