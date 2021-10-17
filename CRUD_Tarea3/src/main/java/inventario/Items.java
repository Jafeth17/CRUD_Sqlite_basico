/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;
import java.util.ArrayList;
/**
 *
 * @author I
 */
public class Items {
    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _producto
     */
    public String getProducto() {
        return _Producto;
    }

    /**
     * @param _Producto the _Producto to set
     */
    public void setProducto(String _Producto) {
        this._Producto = _Producto;
    }

    /**
     * @return the _proveedor
     */
    public String getProveedor() {
        return _proveedor;
    }

    /**
     * @param _proveedor the _proveedor to set
     */
    public void setProveedor(String _proveedor) {
        this._proveedor = _proveedor;
    }

    /**
     * @return the _precio
     */
    public int getPrecio() {
        return _precio;
    }

    /**
     * @param _precio the _precio to set
     */
    public void setPrecio(int _precio) {
        this._precio = _precio;
    }

    /**
     * @return the _cantidad
     */
    public int getCantidad() {
        return _cantidad;
    }

    /**
     * @param _cantidad the _cantidad to set
     */
    public void setCantidad(int _cantidad) {
        this._cantidad = _cantidad;
    }
    private int _id;
    private String _Producto;
    private String _proveedor;
    private int _precio;
    private int _cantidad;
    
    public Items(){
        this._id = 0;
        this._Producto = "";
        this._proveedor = "";
        this._cantidad = 0;
        this._precio =  0;
    }
    
    public Items(int id, String producto, String proveedor, int cantidad, int precio) {
        this._id = id;
        this._Producto = producto;
        this._proveedor = proveedor;
        this._cantidad = cantidad;
        this._precio =  precio;
    }
    
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(this._id));
        campos.add(this._Producto);
        campos.add(this._proveedor);
        campos.add(String.valueOf(this._cantidad));
        campos.add(String.valueOf(this._precio));
        
        return campos;
        
    }
    // setters y getters 
}
