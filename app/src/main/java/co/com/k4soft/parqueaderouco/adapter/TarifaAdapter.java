package co.com.k4soft.parqueaderouco.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.persistencia.room.DataBaseHelper;
import co.com.k4soft.parqueaderouco.view.MainActivity;
import co.com.k4soft.parqueaderouco.view.UpdateActivity;

public class TarifaAdapter extends BaseAdapter  {

    private final LayoutInflater inflater;
    private List<Tarifa> listaTarifasOut;
    private  List<Tarifa> listaTarifasIn;
    Context context;

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
            convertView = inflater.inflate(R.layout.tarifa_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            context = convertView.getContext();
        }

        holder.idTarifa.setText(listaTarifasOut.get(position).getIdTarifa()+"");
        holder.txtNombreTarifa.setText(listaTarifasOut.get(position).getNombre());
        holder.txtValorTarifa.setText("Precio: "+listaTarifasOut.get(position).getPrecio());
        //eventos
        holder.setOnclickListeners();

        return convertView;
    }

    class ViewHolder implements View.OnClickListener {


        @BindView(R.id.idTarifa)
        TextView idTarifa;
        @BindView(R.id.txtNombreTarifa)
        TextView txtNombreTarifa;
        @BindView(R.id.txtValorTarifa)
        TextView txtValorTarifa;
        @BindView(R.id.btnEdit)
        ImageView btnEdit;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setOnclickListeners() {
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnEdit:
                    enviarDatos();
                    break;
                case R.id.btnDelete:
                    deleteTarifa();
                    break;
            }
        }

        private void enviarDatos() {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("tarifa", idTarifa.getText());
            context.startActivity(intent);
        }

        private void deleteTarifa() {
            new DeleteTarifa().execute(Integer.parseInt(idTarifa.getText().toString()));
        }

        private class DeleteTarifa extends AsyncTask<Integer,Void,Void> {

            @Override
            protected Void doInBackground(Integer... integers) {
                DataBaseHelper.getSimpleDB(context)
                        .getTarifaDAO().deleteByIdTarifa(integers[0]);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText
                        (context, R.string.delete, Toast.LENGTH_SHORT)
                        .show();
                super.onPostExecute(aVoid);
            }
        }
    }
}
