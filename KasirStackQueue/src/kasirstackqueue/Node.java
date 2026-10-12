package kasirstackqueue;

// Node: menyimpan data satu pelanggan/transaksi + pointer ke node berikutnya
public class Node {
    String kode;   // nomor antrian
    String nama;   // nama pelanggan
    long total;    // total belanja
    Node next;

    public Node(String kode, String nama, long total) {
        this.kode = kode;
        this.nama = nama;
        this.total = total;
        this.next = null;
    }
}