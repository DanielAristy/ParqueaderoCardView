package co.com.k4soft.parqueaderouco.persistencia.room;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import co.com.k4soft.parqueaderouco.entidades.Movimiento;
import co.com.k4soft.parqueaderouco.entidades.Tarifa;
import co.com.k4soft.parqueaderouco.persistencia.dao.MovimientoDAO;
import co.com.k4soft.parqueaderouco.persistencia.dao.TarifaDAO;

/**
 * Listado de entities
 * Gestion de la conexiones de las base de datos
 */

@Database(entities = {
        Tarifa.class,
        Movimiento.class}, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)

public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "parqueadero";
    private static DataBaseHelper instace;

    /**Ejecutar la aplicacion fuera del hilo principal*/
    public static DataBaseHelper getSimpleDB(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return instace;
    }

    /**Hilo principal
     * Queries allowMainThreadQueries()*/
    public static DataBaseHelper getDBMainThread(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instace;
    }

    /**
     * Listado de DAO
     */

    public abstract TarifaDAO getTarifaDAO();

    public abstract MovimientoDAO getMovimientoDAO();


}
