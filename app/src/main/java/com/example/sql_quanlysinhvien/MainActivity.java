package com.example.sql_quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SinhVienDao sinhVienDao_118;
    ListView list_118;
    ArrayList<SinhVien> arrayList_118;
    SinhVienAdapter adapter_118;
    Button buttnThem_118;
    EditText edtTen_118;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa listview
        list_118 = (ListView)findViewById(R.id.list_sinhvien);
        edtTen_118 = (EditText) findViewById(R.id.editTen_118);
        buttnThem_118 = (Button) findViewById(R.id.btnThem_118);

        buttnThem_118.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sinhVien = edtTen_118.getText().toString().trim();
                if(TextUtils.isEmpty(sinhVien)){
                    Toast.makeText(MainActivity.this, "Nhập tên sinh viên thêm !",Toast.LENGTH_SHORT).show();
                    return;
                }
                sinhVienDao_118.QueryData("INSERT INTO SinhVienTable VALUES (null, '"+ sinhVien +"')");
                //Chay du lieu
                action();
            }
        });

        arrayList_118 = new ArrayList<>();
        adapter_118 = new SinhVienAdapter(this, R.layout.item_sinhvien, arrayList_118);
        list_118.setAdapter(adapter_118);

        //Tao database:
        sinhVienDao_118 = new SinhVienDao(this, "sinhvien.sqlite",null,1);

        //tao bang
        sinhVienDao_118.QueryData("CREATE TABLE IF NOT EXISTS SinhVienTable(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenSinhVien VARCHAR(200))");

        //Them du lieu(Chỉ chạy 1 lần -> tránh lặp lại dữ liệu)
//        sinhVienDao_118.QueryData("INSERT INTO SinhVienTable VALUES (null, 'Lê Thuận Diệu')");
//        sinhVienDao_118.QueryData("INSERT INTO SinhVienTable VALUES (null, 'Nguyễn Tiến An')");
//        sinhVienDao_118.QueryData("INSERT INTO SinhVienTable VALUES (null, 'Lê Thu Thảo Nguyên')");

        //Hien thi
        action();
    }

    public void DiaglogDelete(String ten,final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có đồng ý xóa -- "+ten+" -- không ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sinhVienDao_118.QueryData("DELETE FROM SinhVienTable WHERE Id= '"+id+"'");
                //Chay Data
                action();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    //Sự kiện update
    public  void DiaglogUpdate(String tensinhvien, final int id ){
        Dialog diaglog = new Dialog(this);
        diaglog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaglog.setContentView(R.layout.item_sua);
        EditText edtSua = (EditText)diaglog.findViewById(R.id.editSua_118);
        Button btnSua = (Button)diaglog.findViewById(R.id.btnUpdate_118);
        Button btnHuy = (Button)diaglog.findViewById(R.id.btnCancel_118);

        edtSua.setText(tensinhvien);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaglog.dismiss();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenUpdate = edtSua.getText().toString().trim();
                if (TextUtils.isEmpty(tenUpdate)){
                    Toast.makeText(MainActivity.this,"Chưa nhập !", Toast.LENGTH_SHORT).show();
                    diaglog.dismiss();
                    return;
                }

                sinhVienDao_118.QueryData("UPDATE SinhVienTable SET TenSinhVien = '"+tenUpdate+"' WHERE Id ='"+ id +"'");
                diaglog.dismiss();
                //action Getdata
                action();

            }
        });

        diaglog.show();
    }
    private void action(){
        Cursor dataSinhVien = sinhVienDao_118.GetData("SELECT * FROM SinhVienTable");
        arrayList_118.clear();
        while (dataSinhVien.moveToNext()){
            String ten = dataSinhVien.getString(1);
            //Hien thi thong bao
            //Toast.makeText(this,ten,Toast.LENGTH_SHORT).show();
            //Hien thi list
            int id = dataSinhVien.getInt(0);
            arrayList_118.add(new SinhVien(id, ten));
        }
        adapter_118.notifyDataSetChanged();
    }
}