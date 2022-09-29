package com.ulp.plantilla.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ulp.plantilla.R;
import com.ulp.plantilla.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Inmueble> lista;

    public InmuebleAdapter(Context context, LayoutInflater layoutInflater, List<Inmueble> lista) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inmueble_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);
        //int img = Integer.parseInt(inmueble.getImagen());
        //holder.ivInmuebleR.setImageResource(img);

        Glide.with(context)
                .load(inmueble.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleR);

        holder.tvInmuebleDireccion.setText(inmueble.getDireccion());
        holder.tvInmueblePrecio.setText("$ " + inmueble.getPrecio());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController(view).navigate(R.id.inmuebleDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivInmuebleR;
        private TextView tvInmuebleDireccion, tvInmueblePrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivInmuebleR = itemView.findViewById(R.id.ivInmuebleR);
            tvInmuebleDireccion = itemView.findViewById(R.id.tvInmuebleDireccion);
            tvInmueblePrecio = itemView.findViewById(R.id.tvInmueblePrecio);
        }
    }
}
