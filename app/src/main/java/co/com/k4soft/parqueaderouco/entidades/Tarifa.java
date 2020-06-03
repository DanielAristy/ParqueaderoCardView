package co.com.k4soft.parqueaderouco.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.k4soft.parqueaderouco.persistencia.Tabla;
import lombok.Data;
import lombok.NoArgsConstructor;

/**Desde que me actualizo Android no me deja
 * no me generan los getters and setters*/
//@Data
@Entity(tableName = Tabla.TARIFA)
@NoArgsConstructor
public class Tarifa {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idTarifa")
    private Integer idTarifa;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "precio")
    private double precio;

    public Tarifa(@NonNull Integer idTarifa, String nombre, double precio) {
        this.idTarifa = idTarifa;
        this.nombre = nombre;
        this.precio = precio;
    }

    @NonNull
    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(@NonNull Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
