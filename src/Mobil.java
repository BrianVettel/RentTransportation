public class Mobil extends AbstractVehicle {

    // Constructor dengan parameter
    public Mobil(String brand, double pricePerDay, int stock) {
        // Memanggil constructor dari AbstractVehicle
        super("Mobil", brand, pricePerDay, stock);
    }

    /**
     * REVISI: Implementasi method abstract 'getCapacity()'
     * yang diwajibkan oleh AbstractVehicle.
     */
    @Override
    public int getCapacity() {
        // Contoh implementasi kapasitas untuk mobil
        return 5;
    }
}