package com.ulp.plantilla.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.plantilla.R;
import com.ulp.plantilla.modelo.Contrato;
import com.ulp.plantilla.modelo.Inmueble;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    //private List<Inmueble> lista;
    private List<Contrato> lista;

    /*
    public ContratoAdapter(Context context, LayoutInflater layoutInflater, List<Inmueble> lista) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }*/

    public ContratoAdapter(Context context, LayoutInflater layoutInflater, List<Contrato> lista) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inmueble_item_v2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contrato contrato = lista.get(position);
        Inmueble inmueble = contrato.getPropiedad();

        Glide.with(context)
                .load("http://192.168.0.17:5000/" + inmueble.getFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleR_2);

        holder.tvInmuebleDireccion_2.setText(inmueble.getDireccion());
        holder.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contrato);
                Navigation.findNavController(view).navigate(R.id.contratoDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivInmuebleR_2;
        private TextView tvInmuebleDireccion_2;
        private Button btVer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivInmuebleR_2 = itemView.findViewById(R.id.ivInmuebleR_2);
            tvInmuebleDireccion_2 = itemView.findViewById(R.id.tvInmuebleDireccion_2);
            btVer = itemView.findViewById(R.id.btVer);
        }
    }
}
