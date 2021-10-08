package com.example.toko;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lihatdata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnProses, btnKeluar;
    TextView etNamaPelanggan, etNamaBarang, etJmlBarang, etHarga, etJmlUang,
            tvNamaPembeli, tvNamaBarang, tvJmlBarang, tvHarga, tvUangBayar,
            tvTotal, tvKembalian, tvBonus, tvKeterangan , tvTotalBayar;
    String namaPelanggan, namaBarang, jumlahBarang, hargaBarang, uangBayar;
    Double jmlBarang, hrgBarang, uangByr, total, kembalian, transaksi = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdata);
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
        btnSimpan = findViewById(R.id.btnsimpan);
        btnKeluar = findViewById(R.id.btnKeluar);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE namaPembeli = '" +
                getIntent().getStringExtra("namaPembeli") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            etNamaPelanggan.setText(cursor.getString(1).toString());
            etNamaBarang.setText(cursor.getString(2).toString());
            etJmlBarang.setText(cursor.getString(3).toString());
            etHarga.setText(cursor.getString(4).toString());
            etJmlUang.setText(cursor.getString(5).toString());
            tvKembalian.setText(tvKembalian.getText()+cursor.getString(7).toString());
            tvBonus.setText(cursor.getString(8).toString());
            tvKeterangan.setText(cursor.getString(9).toString());
            tvNamaBarang.setText(tvNamaBarang.getText()+"\n"+cursor.getString(2));
            tvHarga.setText(tvHarga.getText()+"\n"+cursor.getString(4) );
            tvJmlBarang.setText(tvJmlBarang.getText()+"\n"+cursor.getString(3) );
            tvTotal.setText(tvTotal.getText()+"\n" +cursor.getString(6) );
            tvTotalBayar.setText(tvTotalBayar.getText()+cursor.getString(6).toString());
            tvUangBayar.setText(tvUangBayar.getText()+cursor.getString(5).toString());
        }
        btnKeluar =findViewById(R.id.btnKeluar);
        btnKeluar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}