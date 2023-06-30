package ariel.fuentes.presupuestoslircayhub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ariel.fuentes.presupuestoslircayhub.Entidades.Gastos;

public class Adaptador_Gastos extends RecyclerView.Adapter<Adaptador_Gastos.MyViewHolder> {

    private final Context context;
    private final ArrayList<Gastos> listaGastos;

    public Adaptador_Gastos(Context context, ArrayList<Gastos> listaGastos) {
        this.context = context;
        this.listaGastos = listaGastos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_gastos, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Gastos gasto = listaGastos.get(position);
        holder.nombreGasto.setText(gasto.getNombre());
        holder.monto.setText(String.valueOf(gasto.getMonto()));
        holder.fechaGasto.setText(gasto.getFecha());
        holder.categoria.setText(gasto.getCategoria());

        // Asignar imagen según la categoría
        switch (gasto.getCategoria()) {
            case "Arriendo o Hipoteca":
                holder.icongastos.setImageResource(R.drawable.cat_arriendo);
                break;
            case "Alimentación":
                holder.icongastos.setImageResource(R.drawable.cat_alimentacion);
                break;
            case "Transporte":
                holder.icongastos.setImageResource(R.drawable.cat_transporte);
                break;
            case "Servicios básicos":
                holder.icongastos.setImageResource(R.drawable.cat_servicios);
                break;
            case "Educación":
                holder.icongastos.setImageResource(R.drawable.cat_educacion);
                break;
            case "Deudas":
                holder.icongastos.setImageResource(R.drawable.cat_deudas);
                break;
            case "Ahorros e Inversiones":
                holder.icongastos.setImageResource(R.drawable.cat_ahorros);
                break;
            case "Otros":
                holder.icongastos.setImageResource(R.drawable.cat_otros);
                break;
            default:
                holder.icongastos.setImageResource(R.drawable.cat_otros);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescripcionGastos.class);
                intent.putExtra("ID", gasto.getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaGastos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icongastos;
        TextView nombreGasto, monto, fechaGasto, categoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreGasto = itemView.findViewById(R.id.Nombre_gastos);
            monto = itemView.findViewById(R.id.Num_Gastos);
            fechaGasto = itemView.findViewById(R.id.Num_Fecha);
            categoria = itemView.findViewById(R.id.Nom_Categoria);
            icongastos = itemView.findViewById(R.id.icongastos);
        }
    }
}
