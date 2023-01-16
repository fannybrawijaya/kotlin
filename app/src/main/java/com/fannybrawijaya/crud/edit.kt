package com.fannybrawijaya.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.fannybrawijaya.crud.databinding.ActivityEditBinding
import org.json.JSONObject
import java.sql.RowId

class edit : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    var jk= "L"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cari_data(intent.getStringExtra("id").toString())
        binding.btEdit.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val alamat = binding.etAlamat.text.toString()
            if (nama.isEmpty()){
                binding.etNama.error = "Nama Harus Di Isi"
                binding.etNama.requestFocus()

            }else if (alamat.isEmpty()){
                binding.etAlamat.error = "Alamat Harus Di Isi"
                binding.etAlamat.requestFocus()
            }else{
                ///editdata
                AndroidNetworking.post("http://192.168.43.63/api/edit.php")
                    .addBodyParameter("id",intent.getStringExtra("id"))
                    .addBodyParameter("nama", nama)
                    .addBodyParameter("jenis_kelamin", jk)
                    .addBodyParameter("alamat", alamat)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            if (response.getInt("success") ==1){
                                Toast.makeText(this@edit,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                                finish()
                            }else{
                                Toast.makeText(this@edit,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onError(error: ANError) {
                            Toast.makeText(this@edit,error.toString(), Toast.LENGTH_SHORT).show()

                        }
                    })

            }

        }

        binding.rgKelamin.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == binding.rbL.id) {
                jk = "L"
            }else{
                jk = "P"
            }
        }
    }
    fun cari_data(id: String){
        AndroidNetworking.get("http://192.168.43.63/api/cari.php")
            .addQueryParameter("id_crud",id)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    if (response.getInt("success") == 1){
                        val jsonObject = response.optJSONObject("data")
                        binding.etNama.setText(jsonObject.getString("nama"))
                        binding.etAlamat.setText(jsonObject.getString("alamat"))
                        if (jsonObject.getString("jenis_kelamin") == "L"){
                            binding.rbL.isChecked = true
                            jk = "L"
                        }else{
                            binding.rbP.isChecked = true
                            jk = "P"
                        }
                    }else{
                        Toast.makeText(this@edit,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(error: ANError) {
                    Toast.makeText(this@edit,error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
    }
}