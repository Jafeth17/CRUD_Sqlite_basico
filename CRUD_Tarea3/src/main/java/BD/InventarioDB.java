/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import inventario.Items;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author I
 */
public class InventarioDB {
    private ArrayList _invItems;
    
    public InventarioDB(){

        this._invItems = new ArrayList<Items>();
    }
    
   
    public ArrayList<Items> getItems(){
        return this.getinvItems(false);
    }
    
    public void tableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Inventario"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " PRODUCTO TEXT NOT NULL,"
                        + " PROVEEDOR TEXT NOT NULL,"
                        + " CANTIDAD INT NOT NULL,"
                        + " PRECIO INT NOT NULL"
                        + ")";
       
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch( Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Items> getItems(boolean forceLoad){
        try {
           if (forceLoad) {
                Statement comando =  Conexion.getConexion().createStatement();
                ResultSet misRegistro = comando.executeQuery("SELECT * from INVENTARIO;");
                this._invItems.clear();
                while (misRegistro.next()) {
                    Items registro = new Items();
                    registro.setId(misRegistro.getInt("ID"));
                    registro.setProducto(misRegistro.getString("PRODUCTO"));
                    registro.setProveedor(misRegistro.getString("PROVEEDOR"));
                    registro.setCantidad(misRegistro.getInt("CANTIDAD"));
                    registro.setPrecio(misRegistro.getInt("PRECIO"));
                    this._invItems.add(registro);
                }
                comando.close();
           }
           return this._invItems;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return this._invItems;
        }   
    }
    
    public Items getProdItemById(int id){
        try {
            String SQLGetByID = "SELECT * FROM INVENTARIO WHERE ID = ?;";
            PreparedStatement comando =  Conexion.getConexion().prepareStatement(SQLGetByID);
            comando.setInt(1, id);
            ResultSet misRegistro = comando.executeQuery();
            Items registro = new Items();
            while (misRegistro.next()) {
                registro.setId(misRegistro.getInt("ID"));
                registro.setProducto(misRegistro.getString("PRODUCTO"));
                registro.setProveedor(misRegistro.getString("PROVEEDOR"));
                registro.setCantidad(misRegistro.getInt("CANTIDAD"));
                registro.setCantidad(misRegistro.getInt("PRECIO"));
                break;
            }
            comando.close();

            return registro;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }   
    }
    
    public int updateProdItem(Items ItemToUpdate) {
        try {
            String SQLUpdate = "UPDATE INVENTARIO set PRODUCTO=?, PROVEEDOR=?, CANTIDAD=?, PRECIO=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, ItemToUpdate.getProducto());
            comando.setString(2, ItemToUpdate.getProveedor());
            comando.setInt(3, ItemToUpdate.getCantidad());
            comando.setInt(4, ItemToUpdate.getPrecio());
            comando.setInt(5, ItemToUpdate.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     public int insertProdItem(Items ItemToInsert) {
        try {
            String SQLInsert = "INSERT INTO INVENTARIO (PRODUCTO, PROVEEDOR, CANTIDAD, PRECIO) values (?, ?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, ItemToInsert.getProducto());
            comando.setString(2, ItemToInsert.getProveedor());
            comando.setInt(3, ItemToInsert.getCantidad());
            comando.setInt(4, ItemToInsert.getPrecio());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
     
    public int deleteProdItem(Items ItemToDelete) {
        try {
            String SQLDelete = "DELETE FROM INVENTARIO WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, ItemToDelete.getId());
            
            int registrAfectado = comando.executeUpdate();
            comando.close();
            return registrAfectado;
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    private ArrayList<Items> getinvItems(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
