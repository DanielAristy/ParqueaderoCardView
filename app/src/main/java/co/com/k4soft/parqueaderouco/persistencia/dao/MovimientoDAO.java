package co.com.k4soft.parqueaderouco.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Query;

import co.com.k4soft.parqueaderouco.entidades.Movimiento;

/**Permiten acceder a las tablas
 * de manera directa, en la cual
 * se establece una relacion que
 * tenemos en las bases de datos
 * */
@Dao
public interface MovimientoDAO {
    @Query("SELECT * FROM MOVIMIENTO Where placa=:placa AND finalizaMovimiento = 0")
    public Movimiento findByPlaca(String placa);
}
