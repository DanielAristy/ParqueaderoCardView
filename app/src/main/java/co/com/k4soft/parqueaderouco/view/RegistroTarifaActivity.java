package co.com.k4soft.parqueaderouco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.persistencia.room.DataBaseHelper;
import co.com.k4soft.parqueaderouco.utilities.ActionBarUtil;

public class RegistroTarifaActivity extends AppCompatActivity {

    /**Para usar butterKnife atributos siempre deben ser publicos
     * inyeccion de dependencias con butterKnife */

    @BindView(R.id.txtNombreTarifa)
    public EditText txtNombreTarifa;
    @BindView(R.id.txtValorTarifa)
    public EditText txtValorTarifa;
    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tarifa);
        /**Intancias de las dependencias que creamos*/
        ButterKnife.bind(this);
        initComponents();

    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.configurar_tarifa));
    }

    /**Evento onClick para hacer el registro de las tarifas*/
    public void registrar(View view) {
        String nombreTarifa = txtNombreTarifa.getText().toString();
        Double valorTarifa = toDouble(txtValorTarifa.getText().toString());

        if (validarInformacion(nombreTarifa,valorTarifa)){
            Tarifa tarifa = getTarifa(nombreTarifa,valorTarifa);
            new InsercionTarifa().execute(tarifa);
            finish();
        }
    }

    private Tarifa getTarifa(String nombreTarifa, Double valorTarifa) {
        Tarifa tarifa = new Tarifa();
        tarifa.setNombre(nombreTarifa);
        tarifa.setPrecio(valorTarifa);

        return tarifa;
    }

    private boolean validarInformacion(String nombreTarifa, Double valorTarifa) {

        boolean esValido = true;
        if ("".equals((nombreTarifa))){
            txtNombreTarifa.setError(getString(R.string.requerido));
            esValido = false;
        }

        if (valorTarifa == 0){
            txtValorTarifa.setError(getString(R.string.requerido));
            esValido = false;
        }

        return esValido;
    }

    /**Conversion de un string a un Double
     * En este caso donde no ingrese un valor
     * por defecto nos pondra 0 de los contrario
     * los parsea a Double  */
    private Double toDouble(String valor) {
        return "".equals(valor)? 0: Double.parseDouble(valor);
    }

    /**Iner class para cargar los datos a la base de datos
     * para ejecutarlo en un segundo hilo
     */
    private class InsercionTarifa extends AsyncTask<Tarifa,Void,Void>{

        @Override
        protected Void doInBackground(Tarifa... tarifas) {
            DataBaseHelper.getSimpleDB(getApplicationContext())
                    .getTarifaDAO()
                    .insert(tarifas[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText
                    (getApplicationContext(),
                            getString(R.string.succesfully), Toast.LENGTH_SHORT)
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
