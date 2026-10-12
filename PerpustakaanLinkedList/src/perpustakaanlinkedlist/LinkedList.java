package perpustakaanlinkedlist;

// LinkedList: Single Linked List untuk daftar buku perpustakaan
public class LinkedList {
    private Node head; // node pertama
    private Node tail; // node terakhir (agar tambah di akhir cepat)
    private int size;  // jumlah buku

    // Tambah buku di AKHIR daftar (Push)
    public void push(String kode, String judul, String penulis) {
        Node baru = new Node(kode, judul, penulis);
        if (head == null) {          // daftar masih kosong
            head = tail = baru;
        } else {                     // sambungkan ke node terakhir
            tail.next = baru;
            tail = baru;
        }
        size++;
    }

    // Hapus buku TERAKHIR dari daftar (Pop)
    public Node pop() {
        if (head == null) return null;     // daftar kosong

        Node dihapus = tail;
        if (head == tail) {                // hanya ada 1 node
            head = tail = null;
        } else {                           // cari node sebelum tail
            Node cur = head;
            while (cur.next != tail) {
                cur = cur.next;
            }
            cur.next = null;               // putus sambungan ke tail lama
            tail = cur;                    // node ini jadi tail baru
        }
        size--;
        return dihapus;
    }

    // Cari buku berdasarkan kodeBuku (linear search)
    public Node cari(String kode) {
        Node cur = head;
        while (cur != null) {
            if (cur.kodeBuku.equalsIgnoreCase(kode)) return cur;
            cur = cur.next;
        }
        return null; // tidak ditemukan
    }

    // Tampilkan semua buku sesuai urutan input
    public void tampil() {
        if (head == null) {
            System.out.println("Daftar buku masih kosong.");
            return;
        }
        System.out.println("Daftar Buku:");
        Node cur = head;
        while (cur != null) {
            System.out.println("Kode: " + cur.kodeBuku + " | Judul: " + cur.judul
                    + " | Penulis: " + cur.penulis);
            cur = cur.next;
        }
        System.out.println("Total Buku: " + size);
    }

    public int getSize() { return size; }
}