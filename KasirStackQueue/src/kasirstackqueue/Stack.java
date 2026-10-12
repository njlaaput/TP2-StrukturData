package kasirstackqueue;

// Stack (LIFO): riwayat transaksi. Data terbaru selalu di atas (top)
public class Stack {
    private Node top;
    private int size;

    // push: simpan transaksi di atas stack
    public void push(Node data) {
        data.next = top; // node baru menunjuk ke top lama
        top = data;      // node baru jadi top
        size++;
    }

    // display: tampilkan transaksi dari terbaru (top) ke terlama
    public void display() {
        if (top == null) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("Riwayat Transaksi (terbaru -> terlama):");
        long grandTotal = 0;
        for (Node cur = top; cur != null; cur = cur.next) {
            System.out.println("- " + cur.kode + " | " + cur.nama + " | Rp " + cur.total);
            grandTotal += cur.total;
        }
        System.out.println("Jumlah transaksi: " + size + " | Total pendapatan: Rp " + grandTotal);
    }
}