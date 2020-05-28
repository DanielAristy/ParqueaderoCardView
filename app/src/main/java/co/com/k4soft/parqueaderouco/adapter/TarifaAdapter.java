package co.com.k4soft.parqueaderouco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;

public class TarifaAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Tarifa> listaTarifasOut;
    private  List<Tarifa> listaTarifasIn;

    public  TarifaAdapter (Context context, List<Tarifa> listaTarifas){
        listaTarifasOut = listaTarifas;
        listaTarifasIn = listaTarifas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaTarifasOut.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTarifasOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.planeta_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.idTarifa.setText("Id: "+listaTarifasOut.get(position).getIdTarifa());
        holder.txtNombreTarifa.setText(listaTarifasOut.get(position).getNombre());
        holder.txtValorTarifa.setText("Precio: "+listaTarifasOut.get(position).getPrecio());

        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.idTarifa)
        TextView idTarifa;
        @BindView(R.id.txtNombreTarifa)
        TextView txtNombreTarifa;
        @BindView(R.id.txtValorTarifa)
        TextView txtValorTarifa;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
