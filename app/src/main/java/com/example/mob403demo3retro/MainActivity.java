package com.example.mob403demo3retro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mob403demo3retro.getdata.InterReqGetData;
import com.example.mob403demo3retro.getdata.Products;
import com.example.mob403demo3retro.getdata.ResponseServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button, btnGetData;
    TextView textView,tvGetData;
    EditText txtName,txtPrice,txtDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnInsert);
        btnGetData = findViewById(R.id.btnGetData);
        textView = findViewById(R.id.tvKQ);
        tvGetData =findViewById(R.id.KQ);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDes = findViewById(R.id.txtDes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAllData();
            }
        });
    }
    public void insertData()
    {
        //1.khai bao doi tuong chua du lieu
        Prd prd = new Prd();
        //2.nhap du lieu vao doi tuong
        prd.setName(txtName.getText().toString());
        prd.setPrice(txtPrice.getText().toString());
        prd.setDescription(txtDes.getText().toString());
        //3. su dung thu vien retrofit
        Retrofit retrofit
                =new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //4. goi interface
        InterInsertPrd interInsertPrd = retrofit.create(InterInsertPrd.class);
        //5. thuc thi ham trong interface
        Call<ServerResPrd> call =
                interInsertPrd.insertPrd(prd.getName(),prd.getPrice(),prd.getDescription());
        //6. thuc thi phia server
        call.enqueue(new Callback<ServerResPrd>() {
            //tra ve ket qua
            @Override
            public void onResponse(Call<ServerResPrd> call, Response<ServerResPrd> response) {
                ServerResPrd serverResPrd = response.body();//lay ve ket qua
                textView.setText(serverResPrd.getMessage());
            }
            //neu loi
            @Override
            public void onFailure(Call<ServerResPrd> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
    String strkq="";
    List<Products> data;
    public void loadAllData()
    {
        //1.Tao doi tuong retrofit
        Retrofit retrofit
                = new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2. Lay request
        InterReqGetData interReqGetData
                =retrofit.create(InterReqGetData.class);
        Call<ResponseServer> call = interReqGetData.GetJSON();
        //3. thuc thi request
        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                ResponseServer responseServer = response.body();//tra ve ket qua
                //chuyen ket qua thanh chuoi
                data = new ArrayList<>(Arrays.asList(responseServer.getProducts()));
                for(Products prd:data)
                {
                    strkq +="Name: "+ prd.getName()+"; Price: "+prd.getPrice()+"\n\n";
                }
                tvGetData.setText(strkq);

            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                tvGetData.setText(t.getMessage());//neu loi
            }
        });
    }
}
