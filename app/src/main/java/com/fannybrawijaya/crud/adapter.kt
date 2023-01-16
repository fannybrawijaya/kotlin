package com.fannybrawijaya.crud

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.fannybrawijaya.crud.databinding.CostumTampilBinding
import org.json.JSONObject

class adapter(private val context: Context, private val results: ArrayList<model>) : RecyclerView.Adapter<adapter.MyHolder>() {
    private var Items = ArrayList<model>()

    init {
        this.Items = results

    }

    inner class MyHolder(val binding: CostumTampilBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return  MyHolder(
                CostumTampilBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val result = Items[position]
        with(holder){
            binding.tvNama.text = result.nama
            binding.tvAlamat.text = result.alamat
            if (result.jenis_kelamin == "L"){
                binding.tvJk.text == "Laki-Laki"
            }else{
                binding.tvJk.text == "Perempuan"
            }
            binding.root.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Pilih Pengaturan")
                val pilihan = arrayOf("Edit", "Delete", "Cancel")
                builder.setItems(pilihan) { dialog, which ->
                    when (which) {
                        0 -> {
                            val a = Intent(context, edit::class.java)
                            a.putExtra("id", result.id_crud)
                            context.startActivity(a)
                        }

                        1 -> {
                            hapus(result.id_crud)
                        }

                        2 -> {
                            dialog.dismiss()

                        }
                    }
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }

    fun hapus (id : String){
        AndroidNetworking.post("http://192.168.43.63/api/hapus.php")
                .addBodyParameter("id", id)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        if (response.getInt("success") ==1){
                            Toast.makeText(context,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                            (context as Activity).finish()
                        }else{
                            Toast.makeText(context,response.getString("pesan"), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(error: ANError) {
                        Toast.makeText(context,error.toString(), Toast.LENGTH_SHORT).show()

                    }
                })

    }

}