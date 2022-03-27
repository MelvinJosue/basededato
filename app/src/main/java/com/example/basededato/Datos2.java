package com.example.basededato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class Datos2 extends AppCompatActivity {
    Button enviar,limpiar;
    EditText asunto,nombre,correo;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String HttpUrl = "https://bellicose-factor.000webhostapp.com/phptarea.php";
    String identidad,pedido,fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos2);
        enviar=findViewById(R.id.btnenviar);
        limpiar=findViewById(R.id.btnlimpiar);
        asunto=findViewById(R.id.txtasunto);
        nombre=findViewById(R.id.txtnombre);
        correo=findViewById(R.id.txtcorreo);

        requestQueue = Volley.newRequestQueue(Datos2.this);
        progressDialog = new ProgressDialog(Datos2.this);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Espera, Se esta insertando los datos en la base de datos");
                progressDialog.show();
                valoresedittext();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                progressDialog.dismiss();

                                //Toast.makeText(Datos2.this, ServerResponse, Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                progressDialog.dismiss();
                               // Toast.makeText(Datos2.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nombre",identidad);
                        params.put("pedido",pedido);
                        params.put("fecha",fecha);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Datos2.this);
                requestQueue.add(stringRequest);

            }
        });


        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        asunto.getText().clear();
                        nombre.getText().clear();
                        correo.getText().clear();
            }
        });

    }
    public void valoresedittext(){
        identidad = nombre.getText().toString().trim();
        pedido = correo.getText().toString().trim();
        fecha = asunto.getText().toString().trim();
    }




}
