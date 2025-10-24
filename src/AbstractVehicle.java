public abstract class AbstractVehicle implements Rentable {
    // Variabel instance
    protected String type;            // Jenis kendaraan: Mobil, Motor, Truk
    protected String brand;           // Merek kendaraan
    protected double pricePerDay;     // Harga sewa per hari
    protected int stock;              // Jumlah unit kendaraan yang tersedia

    // Static variable untuk menghitung total kendaraan
    protected static int totalVehicles = 0;

    // --- Constructor Default ---
    public AbstractVehicle() {
        this("Unknown", "Unknown", 0.0, 0); // panggil constructor berparameter
    }

    // --- Constructor dengan Parameter ---
    public AbstractVehicle(String type, String brand, double pricePerDay, int stock) {
        this.type = type;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.stock = stock;
        totalVehicles++;
    }

    // --- Getter ---
    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getStock() {
        return stock;
    }

    // --- Setter ---
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // --- Implementasi Method dari Interface Rentable ---

    // Menampilkan informasi kendaraan
    @Override
    public void displayInfo() {
        System.out.println(type + " - " + brand + " | Rp" + pricePerDay + "/hari | Stok: " + stock);
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
    public abstract int getCapacity();

}