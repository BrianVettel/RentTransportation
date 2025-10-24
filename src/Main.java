import java.util.Scanner;
import java.util.ArrayList; // Import ArrayList

/**
 * Class Main menjalankan simulasi program penyewaan kendaraan.
 * REVISI: Ditambahkan Main Menu untuk Sewa dan Pengembalian.
 */
public class Main {

    // Dipindahkan ke static agar bisa dipakai helper method
    private static final Scanner input = new Scanner(System.in);
    private static final RentalSystem rentalSystem = new RentalSystem();
    private static Customer customer;

    public static void main(String[] args) {

        // 1. Setup Kendaraan (Sudah benar)
        setupVehicles();

        // 2. Setup Pelanggan
        System.out.print("Masukkan nama pelanggan: ");
        String nama = input.nextLine();
        customer = new Customer(nama);

        // --- REVISI: MAIN MENU ---
        int menuPilihan;
        while (true) {
            System.out.println("--- MAIN MENU ---");
            System.out.println("Pelanggan: " + customer.getName());
            System.out.println("1. Sewa Kendaraan");
            System.out.println("2. Kembalikan Kendaraan");
            System.out.println("3. Lihat Kendaraan Saya");
            System.out.println("0. Selesai & Cetak Invoice");
            System.out.print("Pilihan Anda: ");

            if (!input.hasNextInt()) {
                System.out.println("Input harus berupa angka.\n");
                input.next(); // skip input salah
                continue;
            }
            menuPilihan = input.nextInt();

            if (menuPilihan == 1) {
                // Panggil method untuk sewa (kode lama Anda)
                prosesSewa();
            } else if (menuPilihan == 2) {
                // Panggil method BARU untuk pengembalian
                prosesPengembalian();
            } else if (menuPilihan == 3) {
                customer.showRentedVehicles();
            } else if (menuPilihan == 0) {
                break; // Keluar dari loop utama
            } else {
                System.out.println("Pilihan menu tidak valid.");
            }
        }

        // --- Selesai Revisi Menu ---


        // 8. Demonstrasi Passing by Value (Tidak perlu diubah)
        customer.tryChangeNameByValue();
        System.out.println("\nNama setelah tryChangeNameByValue (tidak berubah): " + customer.getName());

        // 9. Demonstrasi Passing by Reference (Tidak perlu diubah)
        customer.changeNameByReference(customer, "Pelanggan VIP");
        System.out.println("Nama setelah changeNameByReference (berubah): " + customer.getName());

        // 10. Tampilkan kendaraan yang disewa (opsional, karena ada di menu 3)
        // customer.showRentedVehicles();

        // 11. Tampilkan invoice akhir (Sekarang menggunakan RentalUtils baru)
        RentalUtils.printInvoice(customer);

        // 12. Tampilkan total kendaraan (Tidak perlu diubah)
        rentalSystem.showTotalCreated();

        // 13. Tutup scanner
        System.out.println("Terima kasih telah menggunakan layanan sewa kami.");
        input.close();
    }

    /**
     * Helper method untuk mendaftarkan kendaraan (kode dari Main lama)
     */
    private static void setupVehicles() {
        rentalSystem.addVehicle(new Mobil("Toyota Avanza", 300000, 3));
        rentalSystem.addVehicle(new Mobil("Honda Brio", 250000, 2));
        rentalSystem.addVehicle(new Motor("Yamaha NMAX", 150000, 4));
        rentalSystem.addVehicle(new Motor("Honda Vario", 120000, 5));
        rentalSystem.addVehicle(new Truk("Isuzu ELF", 500000, 1));
        rentalSystem.addVehicle(new Mobil("Ferrari 499p Le Mans", 10000000, 1));
        rentalSystem.addVehicle(new Mobil("Ferrari SF-25", 100000000, 1));
        rentalSystem.addVehicle(new Motor("Ducati GP-25", 9000000, 1));
    }

    /**
     * Helper method untuk proses sewa (kode loop lama Anda)
     */
    private static void prosesSewa() {
        // Tampilkan kendaraan yang tersedia
        rentalSystem.showAvailableVehicles();

        System.out.print("Pilih nomor kendaraan untuk disewa (0 untuk batal): ");
        if (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka.\n");
            input.next(); // skip input salah
            return;
        }
        int pilihan = input.nextInt();

        if (pilihan == 0) return; // Batal sewa

        Rentable kendaraan = rentalSystem.getVehicle(pilihan - 1);

        if (kendaraan != null) {
            System.out.print("Berapa hari disewa? ");
            int durasi = input.nextInt();

            if (durasi <= 0 || durasi > 30) {
                System.out.println("Durasi harus antara 1 - 30 hari.");
                return;
            }

            // Panggilan ini sekarang menjalankan logika real-time
            rentalSystem.processRental(customer, kendaraan, durasi);
        }
    }

    /**
     * REVISI: Method BARU untuk proses pengembalian kendaraan.
     */
    private static void prosesPengembalian() {
        System.out.println("\n--- Pilih Kendaraan yang Akan Dikembalikan ---");

        ArrayList<RentalRecord> history = customer.getRentalHistory();
        if (history.isEmpty()) {
            System.out.println("Anda belum menyewa kendaraan apapun.");
            return;
        }

        // Buat daftar sementara kendaraan YANG BELUM KEMBALI
        ArrayList<RentalRecord> toReturnList = new ArrayList<>();
        int counter = 1;

        for (RentalRecord record : history) {
            if (!record.isReturned()) {
                System.out.println(counter + ". " + record.getVehicleBrand() +
                        " (Disewa tgl: " + record.getRentDate() + ")");
                toReturnList.add(record);
                counter++;
            }
        }

        if (toReturnList.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang sedang Anda sewa.");
            return;
        }

        System.out.print("Pilih nomor kendaraan untuk dikembalikan (0 untuk batal): ");
        if (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka.\n");
            input.next();
            return;
        }
        int pilihan = input.nextInt();

        if (pilihan == 0) return; // Batal

        if (pilihan > 0 && pilihan <= toReturnList.size()) {
            // Ambil record yang dipilih
            RentalRecord recordToReturn = toReturnList.get(pilihan - 1);

            // Tandai sudah kembali (menggunakan LocalDate.now())
            recordToReturn.returnVehicle();

            // Kembalikan stok ke sistem
            recordToReturn.getVehicle().returnVehicle();

            System.out.println(recordToReturn.getVehicleBrand() +
                    " berhasil dikembalikan pada " + recordToReturn.getReturnDate());
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
}