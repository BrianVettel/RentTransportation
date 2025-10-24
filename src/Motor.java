public class Motor extends AbstractVehicle {

    // Constructor dengan parameter
    public Motor(String brand, double pricePerDay, int stock) {
        // Memanggil constructor dari AbstractVehicle
        super("Motor", brand, pricePerDay, stock);
    }

    // REVISI: Constructor ganda/kosong dihapus agar rapi.
    // public Motor(String brand, double pricePerDay) {}

    /**
     * REVISI: Implementasi method abstract 'getCapacity()'.
     * Kita beri kapasitas yang logis (misal: 2 orang).
     */
    @Override
    public int getCapacity() {
        return 2;
    }
}