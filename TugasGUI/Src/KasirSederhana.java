
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KasirSederhana extends JFrame implements ActionListener {

    BorderLayout LayoutUtama = new BorderLayout(10, 10);
    GridLayout LayoutForm = new GridLayout(3, 2, 8, 8);
    FlowLayout LayoutTombol = new FlowLayout(FlowLayout.CENTER, 10, 8);

    JPanel panelHeader = new JPanel();
    JPanel panelUtama = new JPanel();
    JPanel panelForm = new JPanel();
    JPanel panelStruk = new JPanel();
    JPanel panelTombol = new JPanel();
    JPanel panelPembayaran = new JPanel();

    JLabel labelJudul = new JLabel("KASIR MINI MARKET");
    JLabel labelProduk = new JLabel("Pilih Produk:");
    JLabel labelJumlah = new JLabel("Jumlah:");
    JLabel labelHarga = new JLabel("Harga Satuan:");
    JLabel labelTotal = new JLabel("Total: Rp0");
    JLabel labelBayar = new JLabel("Uang Bayar:");
    JLabel labelKembalian = new JLabel("Kembalian: Rp0");
    JLabel nilaiHarga = new JLabel("Rp5.000");

    String[] daftarProduk = {
        "Buku Tulis",
        "Pulpen",
        "Pensil",
        "Penghapus",
        "Penggaris"
    };

    int[] daftarHarga = {
        5000, 3000, 2500, 2000, 4000
    };

    JComboBox<String> comboProduk =
        new JComboBox<>(daftarProduk);

    JTextField fieldJumlah = new JTextField();
    JTextField fieldBayar = new JTextField();

    JTextArea areaStruk = new JTextArea(15, 25);

    JButton tombolTambah = new JButton("Tambah Barang");
    JButton tombolBayar = new JButton("Bayar");
    JButton tombolReset = new JButton("Reset");
    JButton tombolKeluar = new JButton("Keluar");

    int total = 0;

    public KasirSederhana() {

        super("Aplikasi Kasir Mini Market");

        panelHeader.setBackground(new Color(35, 55, 80));
        labelJudul.setForeground(Color.WHITE);
        labelJudul.setFont(new Font("Arial", Font.BOLD, 22));
        panelHeader.add(labelJudul);

        panelForm.setLayout(LayoutForm);
        panelForm.setBorder(
            BorderFactory.createTitledBorder("Input Produk")
        );

        panelForm.add(labelProduk);
        panelForm.add(comboProduk);

        panelForm.add(labelJumlah);
        panelForm.add(fieldJumlah);

        panelForm.add(labelHarga);
        panelForm.add(nilaiHarga);

        panelStruk.setLayout(new BorderLayout(5, 5));
        panelStruk.setBorder(
            BorderFactory.createTitledBorder("Struk Belanja")
        );

        areaStruk.setEditable(false);
        areaStruk.setFont(
            new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scrollStruk = new JScrollPane(areaStruk);

        panelStruk.add(scrollStruk, BorderLayout.CENTER);
        panelStruk.add(labelTotal, BorderLayout.SOUTH);

        panelUtama.setLayout(new GridLayout(1, 2, 15, 10));
        panelUtama.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelUtama.add(panelForm);
        panelUtama.add(panelStruk);

        panelPembayaran.setLayout(new GridLayout(2, 2, 8, 8));
        panelPembayaran.setBorder(
            BorderFactory.createTitledBorder("Pembayaran")
        );

        panelPembayaran.add(labelBayar);
        panelPembayaran.add(fieldBayar);
        panelPembayaran.add(labelKembalian);
        panelPembayaran.add(new JLabel(""));

        panelTombol.setLayout(LayoutTombol);

        panelTombol.add(tombolTambah);
        panelTombol.add(tombolBayar);
        panelTombol.add(tombolReset);
        panelTombol.add(tombolKeluar);

        comboProduk.addActionListener(this);
        tombolTambah.addActionListener(this);
        tombolBayar.addActionListener(this);
        tombolReset.addActionListener(this);
        tombolKeluar.addActionListener(this);

        areaStruk.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                setTitle("Kasir Mini Market - Struk Dipilih");
            }
        });

        setLayout(LayoutUtama);

        add(panelHeader, BorderLayout.NORTH);
        add(panelUtama, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new BorderLayout());

        panelBawah.add(panelPembayaran, BorderLayout.CENTER);
        panelBawah.add(panelTombol, BorderLayout.SOUTH);

        add(panelBawah, BorderLayout.SOUTH);

        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent action) {

        if (action.getSource() == comboProduk) {

            int index = comboProduk.getSelectedIndex();

            nilaiHarga.setText("Rp" + daftarHarga[index]);

        } else if (action.getSource() == tombolTambah) {

            try {

                int index = comboProduk.getSelectedIndex();
                int jumlah = Integer.parseInt(fieldJumlah.getText());

                if (jumlah <= 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Jumlah harus lebih dari 0!"
                    );
                    return;
                }

                int harga = daftarHarga[index];
                int subtotal = harga * jumlah;

                total += subtotal;

                areaStruk.append(
                    daftarProduk[index] +
                    " x " + jumlah +
                    " = Rp" + subtotal + "\n"
                );

                labelTotal.setText("Total: Rp" + total);

                fieldJumlah.setText("");

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Masukkan jumlah barang dengan benar!"
                );
            }

        } else if (action.getSource() == tombolBayar) {

            try {

                if (total == 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Belum ada barang yang dibeli!"
                    );
                    return;
                }

                int uangBayar = Integer.parseInt(fieldBayar.getText());

                if (uangBayar < total) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Uang pembayaran tidak mencukupi!"
                    );
                    return;
                }

                int kembalian = uangBayar - total;

                labelKembalian.setText(
                    "Kembalian: Rp" + kembalian
                );

                areaStruk.append(
                    "\nTotal Bayar : Rp" + total +
                    "\nUang Bayar  : Rp" + uangBayar +
                    "\nKembalian   : Rp" + kembalian +
                    "\n--------------------------" +
                    "\nTERIMA KASIH"
                );

                tombolBayar.setEnabled(false);
                tombolTambah.setEnabled(false);

                JOptionPane.showMessageDialog(
                    this,
                    "Transaksi berhasil!\nKembalian: Rp" + kembalian
                );

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Masukkan nominal pembayaran dengan benar!"
                );
            }

        } else if (action.getSource() == tombolReset) {

            total = 0;

            areaStruk.setText("");
            fieldJumlah.setText("");
            fieldBayar.setText("");

            labelTotal.setText("Total: Rp0");
            labelKembalian.setText("Kembalian: Rp0");

            tombolBayar.setEnabled(true);
            tombolTambah.setEnabled(true);

            setTitle("Aplikasi Kasir Mini Market");

        } else if (action.getSource() == tombolKeluar) {

            int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin keluar?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
            );

            if (pilihan == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
