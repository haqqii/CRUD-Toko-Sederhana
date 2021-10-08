package com.example.toko;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Updatedata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnProses, btnKeluar;
    EditText etNamaPelanggan, etNamaBarang, etJmlBarang, etHarga, etJmlUang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);

        dbHelper = new DataHelper(this);
        //EditText
        etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        etNamaBarang = findViewById(R.id.etNamaBarang);
        etJmlBarang = findViewById(R.id.etJmlBarang);
        etHarga = findViewById(R.id.etHarga);
        etJmlUang = findViewById(R.id.etJmlUang);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE namaPembeli = '" +
                getIntent().getStringExtra("namaPembeli") + "'",null);
        cursor.moveToFirst();
        String idPesanan="" ;
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            idPesanan = (cursor.getString(0).toString());
            etNamaPelanggan.setText(cursor.getString(1).toString());
            etNamaBarang.setText(cursor.getString(2).toString());
            etJmlBarang.setText(cursor.getString(3).toString());
            etHarga.setText(cursor.getString(4).toString());
            etJmlUang.setText(cursor.getString(5).toString());
        }
        btnSimpan = (Button) findViewById(R.id.Simpan);
        btnKeluar = (Button) findViewById(R.id.btnKeluar);
        // daftarkan even onClick pada btnSimpan
        String finalIdPesanan = idPesanan;
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update data set namaPembeli='"+
                        etNamaPelanggan.getText().toString() +"', namaBarang='" +
                        etNamaBarang.getText().toString()+"', jumlahBarang='"+
                        etJmlBarang.getText().toString() +"', harga='" +
                        etHarga.getText().toString() + "', uangBayar='" +
                        etJmlUang.getText().toString()+"' where id = '" + finalIdPesanan.toString() +"'");
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
}
