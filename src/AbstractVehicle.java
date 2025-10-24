public abstract class AbstractVehicle implements Rentable {
    // Variabel instance
    protected String type;            // Jenis kendaraan: Mobil, Motor, Truk
    protected String brand;           // Merek kendaraan
    protected double pricePerDay;     // Harga sewa per hari
    protected int stock;              // Jumlah unit kendaraan yang tersedia

    // Static variable untuk menghitung total kendaraan
    protected static int totalVehicles = 0;

    // --- Constructor Default ---
    // REVISI: Constructor ini dihapus karena tidak pernah digunakan.
    //
    // --- Constructor dengan Parameter ---
    // (Ini adalah constructor satu-satunya yang digunakan oleh subclass)
    public AbstractVehicle(String type, String brand, double pricePerDay, int stock) {
        this.type = type;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.stock = stock;
        totalVehicles++;
    }

    // --- Getter ---
    // REVISI: Method getType()
    // dan getStock() dihapus
    // karena tidak pernah dipanggil dari luar class.

    public String getBrand() {
        return brand;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    // --- Setter ---
    // REVISI: Method setPricePerDay()
    // dan setStock() dihapus
    // karena tidak pernah digunakan.

    // --- Implementasi Method dari Interface Rentable ---

    // Menampilkan informasi kendaraan
    @Override
    public void displayInfo() {
        // REVISI: Method getCapacity()
        // sekarang dipanggil di sini agar terpakai.
        System.out.println(type + " - " + brand +
                " | Kapasitas: " + getCapacity() + " org" +
                " | Rp" + pricePerDay + "/hari | Stok: " + stock);
    }

    // Mengecek apakah kendaraan tersedia
    @Override
    public boolean isAvailable() {
        return stock > 0;
    }

    /**
     * REVISI: Menggantikan reduceStock().
     * Mencoba menyewa kendaraan dan mengurangi stok jika berhasil.
     * @return true jika stok berhasil dikurangi, false jika stok habis.
     */
    @Override
    public boolean rent() {
        if (isAvailable()) {
            stock--;
            return true; // Berhasil disewa
        } else {
            System.out.println("Maaf, stok " + brand + " habis.");
            return false; // Gagal disewa
        }
    }

    /**
     * REVISI: Method baru untuk menambah stok saat kendaraan dikembalikan.
     */
    @Override
    public void returnVehicle() {
        stock++;
    }

    // --- Static Method: Menampilkan total kendaraan yang pernah dibuat ---
    public static int getTotalVehicles() {
        return totalVehicles;
    }

    // Method abstract ini tetap ada untuk diimplementasikan oleh subclass
    // dan sekarang sudah dipanggil di displayInfo()
    public abstract int getCapacity();

}