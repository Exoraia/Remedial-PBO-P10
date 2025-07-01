package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
 

public class PadelRentalForm extends JFrame {
    private JTextField tfNama, tfNoHP, tfTanggal, tfJamMulai, tfJamSelesai;
    private DefaultTableModel tableModel;   
    private JButton btnSubmit, btnEdit, btnDel;
    private JComboBox<String> cbLapangan;
    private JTable rentTable;
    private List<Rent> rents;

    List<Rent> rentList = new ArrayList<>()
    rentList.add(new Rent(1, "Tes", "Tes", "Tes", "Tes", "Tes", "Tes"));

    public PadelRentalForm() {
        setTitle("Form Sewa Lapangan Padel");
        setSize(600, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Kelola Customer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nama
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nama:"), gbc);

        tfNama = new JTextField();
        gbc.gridx = 1;
        formPanel.add(tfNama, gbc);

        // No HP
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("No HP:"), gbc);

        tfNoHP = new JTextField();
        gbc.gridx = 1;
        formPanel.add(tfNoHP, gbc);

        // Tanggal
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Tanggal Sewa:"), gbc);


        tfTanggal = new JTextField("yyyy-mm-dd");
        gbc.gridx = 1;
        formPanel.add(tfTanggal, gbc);
        
        // Jam Mulai
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Jam Mulai:"), gbc);
        
        tfJamMulai = new JTextField("08:00");
        gbc.gridx = 1;
        formPanel.add(tfJamMulai, gbc);

        // Jam Selesai
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Jam Selesai:"), gbc);

        tfJamSelesai = new JTextField("09:00");
        gbc.gridx = 1;
        formPanel.add(tfJamSelesai, gbc);
        
        // Lapangan
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Lapangan:"), gbc);

        cbLapangan = new JComboBox<>(new String[]{"Lapangan 1", "Lapangan 2", "Lapangan 3", "Lapangan 4"});
        gbc.gridx = 1; 
        formPanel.add(cbLapangan, gbc);

        // Simpan
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        btnSubmit = new JButton("Simpan");
        formPanel.add(btnSubmit, gbc);

        // Edit
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        btnSubmit = new JButton("Simpan");
        formPanel.add(btnSubmit, gbc);

        // Delete
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        btnSubmit = new JButton("Simpan");
        formPanel.add(btnSubmit, gbc);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(formPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Nomor HP", "Tanggal", "Jam Mulai", "Jam Selesai", "Lapangan"}, 0);
        rentTable = new JTable(tableModel);
        loadRentData(rents);
 
        // Actions
        btnSubmit.addActionListener(e -> {
            String nama = tfNama.getText();
            String nohp = tfNoHP.getText();
            String tanggal = tfTanggal.getText();
            String jammulai = tfJamMulai.getText();
            String jamselesai = tfJamSelesai.getText();
            String lapangan = (String) cbLapangan.getSelectedItem();

            if (nama.isEmpty() || nohp.isEmpty() || tanggal.isEmpty() || jammulai.isEmpty() || jamselesai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedRow = rentTable.getSelectedRow();
            if (selectedRow != -1) {
                Rent rent = rents.get(selectedRow);
                rent.setNama(nama);
                rent.setNohp(nohp);
                rent.setTanggal(tanggal);
                rent.setJamMulai(jammulai);
                rent.setJamSelesai(jamselesai);
                rent.setLapangan(lapangan);
                
                tableModel.setValueAt(nama, selectedRow, 1);
                tableModel.setValueAt(nohp, selectedRow, 2);
                tableModel.setValueAt(tanggal, selectedRow, 3);
                tableModel.setValueAt(jammulai, selectedRow, 4);
                tableModel.setValueAt(jamselesai, selectedRow, 5);
                tableModel.setValueAt(lapangan, selectedRow, 5);

                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
            } else {
                int newId = rents.size() + 1;
                Rent newRent = new Rent(newId, nama, nohp, tanggal, jammulai, jamselesai, lapangan);
                rents.add(newRent);

                tableModel.addRow(new Object[]{
                    newRent.getId(),
                    newRent.getNama(),
                    newRent.getNohp(),
                    newRent.getTanggal(),
                    newRent.getJamMulai(),
                    newRent.getJamSelesai(),
                    newRent.getLapangan()
                });

                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan.");
            }
            clearForm();
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = rentTable.getSelectedRow();
            if (selectedRow != -1) {
                tfNama.setText(rentTable.getValueAt(selectedRow, 1).toString());
                tfNoHP.setText(rentTable.getValueAt(selectedRow, 2).toString());
                tfTanggal.setText(rentTable.getValueAt(selectedRow, 3).toString());
                tfJamMulai.setText(rentTable.getValueAt(selectedRow, 4).toString());
                tfJamSelesai.setText(rentTable.getValueAt(selectedRow, 5).toString());
                cbLapangan.setSelectedItem(rentTable.getValueAt(selectedRow, 6).toString());
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnDel.addActionListener(e -> {
            int selectedRow = rentTable.getSelectedRow();
            if (selectedRow != -1) {
                rents.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void loadRentData(List<Rent> rentList) {
        if (rentList == null) {
            rentList = new ArrayList<>();
        }
        for (Rent rent : rentList) {
            tableModel.addRow(new Object[]{
                rent.getId(),
                rent.getNama(),
                rent.getNohp(),
                rent.getTanggal(),
                rent.getJamMulai(),
                rent.getJamSelesai(),
                rent.getLapangan()
            });
        }
    }

    private void clearForm() {
        tfNama.setText("");
        tfNoHP.setText("");
        tfTanggal.setText("");
        tfJamMulai.setText("");
        tfJamSelesai.setText("");
        cbLapangan.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PadelRentalForm().setVisible(true));
    }
}
