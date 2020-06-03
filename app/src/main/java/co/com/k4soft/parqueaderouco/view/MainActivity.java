package co.com.k4soft.parqueaderouco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.parqueaderouco.R;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.persistencia.room.DataBaseHelper;
import co.com.k4soft.parqueaderouco.utilities.ActionBarUtil;
import co.com.k4soft.parqueaderouco.view.movimiento.MovimientoActivity;

public class MainActivity extends AppCompatActivity{

    private ActionBarUtil actionBarUtil;
    public static DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    /**Titulo del activity
     * y funcion flecha*/
    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.menu_principal));
    }


    public void goToTarifaActivity(View view) {
       Intent intent = new Intent(this,TarifaActivity.class);
       startActivity(intent);
    }

    public void goToIngresoSalida(View view) {
        Intent intent = new Intent(this, MovimientoActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**Realizar el enlace para ir a los movimientos
     * de salida de los vehiculos*/


}
