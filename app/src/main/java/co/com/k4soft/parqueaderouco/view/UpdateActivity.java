package co.com.k4soft.parqueaderouco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.persistencia.room.DataBaseHelper;
import co.com.k4soft.parqueaderouco.utilities.ActionBarUtil;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.nombre)
    public EditText nombre;
    @BindView(R.id.precio)
    public EditText precio;
    public static DataBaseHelper db;

    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);
        initComponents();
        mostrarDatos();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.configurar_tarifa));
    }

    private void mostrarDatos() {
        String idTar = "";
        Bundle extras = getIntent().getExtras();
        idTar = extras.getString("tarifa");
        id.setText(idTar);
    }

    public void tarifaUpdate(View view) {

        int idTarifa = Integer.parseInt(id.getText().toString());
        String nombreTarifa = nombre.getText().toString();
        Double valor = toDouble(precio.getText().toString());

        if (validarInformacion(nombreTarifa, valor)){
            Tarifa tarifa = getTarifa(idTarifa, nombreTarifa, valor);
            new UpdateAsyncTask().execute(tarifa);
            finish();
        }

    }

    private Double toDouble(String valor) {
        return "".equals(valor)? 0: Double.parseDouble(valor);
    }

    private Tarifa getTarifa(int id, String nombreTarifa, Double valorTarifa) {
        Tarifa tarifa = new Tarifa();
        tarifa.setIdTarifa(id);
        tarifa.setNombre(nombreTarifa);
        tarifa.setPrecio(valorTarifa);

        return tarifa;
    }

    private boolean validarInformacion(String nombreTarifa, Double valorTarifa) {

        boolean esValido = true;
        if ("".equals((nombreTarifa))){
            nombre.setError(getString(R.string.requerido));
            esValido = false;
        }

        if (valorTarifa == 0){
            precio.setError(getString(R.string.requerido));
            esValido = false;
        }

        return esValido;
    }

    /**Actualizar tarifas https://www.youtube.com/watch?v=_SRxGSt82Yo
     * Eliminar   youtube.com/watch?v=_KwsIViPq7Q*/
    private class UpdateAsyncTask extends AsyncTask<Tarifa,Void,Void> {

        @Override
        protected Void doInBackground(Tarifa... tarifas) {
            DataBaseHelper.getSimpleDB(getApplicationContext())
                    .getTarifaDAO()
                    .update(tarifas[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText
                    (getApplicationContext(),
                            getString(R.string.update), Toast.LENGTH_SHORT)
                    .show();
            super.onPostExecute(aVoid);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}