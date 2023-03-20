package com.if4a.playerfootball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        etNama= findViewById(R.id.et_nama);
        etNomor= findViewById(R.id.et_nomor);
        etKlub= findViewById(R.id.et_klub);
        btnUbah= findViewById(R.id.btn_ubah);

        Intent varIntent = getIntent();
        String id =varIntent.getStringExtra("varID");
        String Nama =varIntent.getStringExtra("varNama");
        String Nomor =varIntent.getStringExtra("varNomor");
        String Klub =varIntent.getStringExtra("varKlub");

        etNama.setText(Nama);
        etNomor.setText(Nomor);
        etKlub.setText(Klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getKlub;

                getNama = etNama.getText().toString();
                getNomor = etNomor.getText().toString();
                getKlub = etKlub.getText().toString();

                if (Nama.trim().equals(""))
                {
                    etNama.setError("Nama Player Tidak Boleh Kosong!!");
                }
                else if(Nomor.trim().equals(""))
                {
                    etNomor.setError("Nomor Player Tidak Boleh Kosong!!");
                } else if (Klub.trim().equals(""))
                {
                    etKlub.setError("Nama Klub Tidak Boleh Kosong!!");
                }
                else
                {
                    long eks = myDB.ubahPlayer(id, getNama, getNomor, getKlub);
                    if (eks == -1)
                    {
                        Toast.makeText(UbahActivity.this, "Gagal Mengubah Data", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(UbahActivity.this, "Berhasil Mengubah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }
}