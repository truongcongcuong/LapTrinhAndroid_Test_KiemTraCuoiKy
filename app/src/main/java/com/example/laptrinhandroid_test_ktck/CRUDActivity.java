package com.example.laptrinhandroid_test_ktck;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CRUDActivity extends AppCompatActivity implements SendData{
    Button btn_crud_add, btn_crud_update, btn_crud_delete, btn_crud_search, btn_crud_logout;
    EditText edt_crud_id, edt_crud_name, edt_crud_job;
    RecyclerView rcv_crud_products;
    ProductAdapter adapter;
    Product currentProduct;
    FirebaseAuth auth;
    String url = "https://60ad9ae180a61f00173313b8.mockapi.io/Product_Employee_DD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r_u_d);

        getSupportActionBar().hide();
        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class
        , "product-database")
                .allowMainThreadQueries().build();
        ProductDAO dao = database.productDAO();
        auth = FirebaseAuth.getInstance();

        rcv_crud_products = findViewById(R.id.rcv_crud_products);
        btn_crud_add = findViewById(R.id.btn_crud_add);
        btn_crud_update = findViewById(R.id.btn_crud_update);
        btn_crud_delete = findViewById(R.id.btn_crud_delete);
        btn_crud_search = findViewById(R.id.btn_crud_search);
        btn_crud_logout = findViewById(R.id.btn_crud_logout);
        edt_crud_id = findViewById(R.id.edt_crud_id);
        edt_crud_name = findViewById(R.id.edt_crud_name);
        edt_crud_job = findViewById(R.id.edt_crud_job);

        setData(dao);
        rcv_crud_products.setLayoutManager(new GridLayoutManager(this,1));

//        edt_crud_id.setHint("id - khong nhap");
//        edt_crud_job.setHint("Job");
//        edt_crud_name.setHint("Full Name");
        setInputEdt();
        edt_crud_id.setEnabled(false);

        btn_crud_add.setOnClickListener(v->{
            dao.insertAll(new Product(edt_crud_name.getText().toString(), edt_crud_job.getText().toString()));
            setData(dao);
        });

        btn_crud_update.setOnClickListener(v->{
            if(currentProduct!=null){
                currentProduct.setFullName(edt_crud_name.getText().toString());
                currentProduct.setJob(edt_crud_job.getText().toString());
                dao.update(currentProduct);
                currentProduct = null;
                setInputEdt();
                setData(dao);
            }
            else
                Toast.makeText(CRUDActivity.this,"Choice Product to update...",Toast.LENGTH_SHORT).show();
        });

        btn_crud_delete.setOnClickListener(v->{
            if(currentProduct!=null){
                dao.delete(currentProduct);
                setData(dao);
                setInputEdt();
                currentProduct = null;
            }
            else
                Toast.makeText(CRUDActivity.this,"Choice Product to delete...",Toast.LENGTH_SHORT).show();
        });

        btn_crud_search.setOnClickListener(v->{
            String name =edt_crud_name.getText().toString();
            if(TextUtils.isEmpty(name))
                setData(dao);
            else
                setDataByName(dao,name);
        });

        btn_crud_logout.setOnClickListener(v->{
            auth.signOut();
            startActivity(new Intent(CRUDActivity.this, MainActivity.class));
            finish();
        });

    }

    private void setDataByName(ProductDAO dao, String name) {
        adapter = new ProductAdapter(this, dao.findByName(name));
        rcv_crud_products.setAdapter(adapter);
    }

    private void setInputEdt() {
        edt_crud_id.setHint("id - khong nhap");
        edt_crud_job.setHint("Job");
        edt_crud_name.setHint("Full Name");
        edt_crud_id.setText("");
        edt_crud_job.setText("");
        edt_crud_name.setText("");
        edt_crud_name.requestFocus();
    }

    private void setData(ProductDAO dao) {
        adapter = new ProductAdapter(this, dao.getAll());
        rcv_crud_products.setAdapter(adapter);
    }

    @Override
    public void SendingData(Serializable serializable) {
        currentProduct = (Product) serializable;
        edt_crud_id.setText("id : " + currentProduct.getId());
        edt_crud_name.setText(currentProduct.getFullName());
        edt_crud_job.setText(currentProduct.getJob());
    }

    private void addProductToMockAPI(String url, Product product){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> Toast.makeText(CRUDActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(CRUDActivity.this, "Thêm that bai", Toast.LENGTH_SHORT).show()
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(product.getId()));
                map.put("id",String.valueOf(product.getId()));
                map.put("id",String.valueOf(product.getId()));

                return map;
            }
        };
    }
}