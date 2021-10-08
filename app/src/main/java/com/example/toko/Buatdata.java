package com.example.toko;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buatdata extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnProses, btnKeluar;
    EditText etNamaPelanggan, etNamaBarang, etJmlBarang, etHarga, etJmlUang;
    TextView tvNamaPembeli, tvNamaBarang, tvJmlBarang, tvHarga, tvUangBayar,
            tvTotal, tvKembalian, tvBonus, tvKeterangan , tvTotalBayar;
    String namaPelanggan, namaBarang, jumlahBarang, hargaBarang, uangBayar;
    double jmlBarang, hrgBarang, uangByr, total, kembalian, transaksi = 0.0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buatdata);

        dbHelper = new DataHelper(this);
        //EditText
        etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        etNamaBarang = findViewById(R.id.etNamaBarang);
        etJmlBarang = findViewById(R.id.etJmlBarang);
        etHarga = findViewById(R.id.etHarga);
        etJmlUang = findViewById(R.id.etJmlUang);

        tvBonus = findViewById(R.id.tvBonus);
        tvKeterangan = findViewById(R.id.tvKeterangan);

        tvNamaPembeli = findViewById(R.id.tvNamaPembeli);
        tvNamaBarang = findViewById(R.id.tvNamaBarang);
        tvJmlBarang = findViewById(R.id.tvJmlBarang);
        tvHarga = findViewById(R.id.tvHarga);
        tvTotal = findViewById(R.id.tvTotal);
        tvUangBayar = findViewById(R.id.tvUangBayar);
        tvKembalian = findViewById(R.id.tvKembalian);
        tvTotalBayar = findViewById(R.id.tvTotalBayar);

        //Button
        btnProses = findViewById(R.id.btnProses);
        btnSimpan = findViewById(R.id.Simpan);
        btnKeluar = findViewById(R.id.btnKeluar);

        //Proses
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            namaPelanggan = etNamaPelanggan.getText().toString().trim();
            namaBarang = etNamaBarang.getText().toString().trim();
            jumlahBarang = etJmlBarang.getText().toString().trim();
            hargaBarang = etHarga.getText().toString().trim();
            uangBayar = etJmlUang.getText().toString().trim();

            jmlBarang = Double.parseDouble(jumlahBarang);
            hrgBarang = Double.parseDouble(hargaBarang);
            uangByr = Double.parseDouble(uangBayar);
            total = (jmlBarang * hrgBarang);
            transaksi = (transaksi + total);


            //tvTotal.setText("Total " + total);
            if (total >= 200000) {
                tvBonus.setText("Bonus : HardDisk 1TB");
            } else if (total >= 50000) {
                tvBonus.setText("Bonus : Keyboard Gaming");
            } else if (total >= 40000) {
                tvBonus.setText("Bonus : Mouse Gaming");
            } else {
                tvBonus.setText("Bonus : Tidak ada bonus!");
            }

            kembalian = (uangByr - transaksi);

            if (uangByr < total) {
                tvKeterangan.setText("Keterangan : Uang bayar kurang Rp. " + (-kembalian));
                tvKembalian.setText("Uang Kembalian : Rp. 0");
            } else {
                tvKeterangan.setText("Keterangan : Tunggu kembalian");
                tvKembalian.setText("Uang Kembalian : Rp. " + kembalian);
            }


            //ADD Tabel
            tvNamaBarang.setText(tvNamaBarang.getText()+"\n"+namaBarang);
            tvHarga.setText(tvHarga.getText()+"\n"+hargaBarang );
            tvJmlBarang.setText(tvJmlBarang.getText()+"\n"+jmlBarang );
            tvTotal.setText(tvTotal.getText()+"\n" + total);

            //Total Bayar
            tvNamaPembeli.setText("Nama Pembeli : " + namaPelanggan);
            tvTotalBayar.setText("Total Bayar : " + transaksi);
            tvUangBayar.setText("Uang Bayar : " + uangByr);
            tvKembalian.setText("Kembalian : " + kembalian);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into data(namaPembeli, namaBarang, jumlahBarang, harga, uangBayar, TotalBayar, Kembalian, Bonus, Keterangan) values('" +
                        etNamaPelanggan.getText().toString() + "','" +
                        etNamaBarang.getText().toString() + "','" +
                        etJmlBarang.getText().toString() + "','" +
                        etHarga.getText().toString() + "','" +
                        etJmlUang.getText().toString() + "','" +
                        Double.toString(total) + "','" +
                        Double.toString(kembalian) + "','" +
                        tvBonus.getText().toString() + "','" +
                        tvKeterangan.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });



            btnKeluar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
        }
    });
}}