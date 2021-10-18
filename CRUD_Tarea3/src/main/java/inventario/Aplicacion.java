/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import utilities.Layout;
import java.util.Scanner;
import java.util.ArrayList;
import BD.InventarioDB;
/**
 *
 * @author I
 */
public class Aplicacion {
    private Scanner _EntradaTeclado;
    private ArrayList _MiInventario;
    private int _MiInventarioIdCounter;
    private InventarioDB _InvModel;
    public Aplicacion(Scanner EntradaTeclado) {
        this._EntradaTeclado = EntradaTeclado;
        this._MiInventario = new ArrayList<Items>();  
        this._MiInventarioIdCounter = 0;
        this._InvModel = new InventarioDB();
        this._InvModel.tableInitialize();
        this._MiInventario = this._InvModel.getItems(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarRegistros();
                break;
            case "N":
                this.ingresarNuevoRegistro();
                break;
            case "A":
                this.actualizarRegistro();
                break;
            case "E":   
                this.eliminarRegistro();
                break;
            case "S":
                break;
            default:
                System.out.println("Opción no reconocida!!!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Registro de Canción");
        Items nuevoItem =  new Items();
        nuevoItem.setProducto(Layout.obtenerValorParaCampo("Nombre del Producto", "Producto X", this._EntradaTeclado));
        nuevoItem.setProveedor(Layout.obtenerValorParaCampo("Nombre del Proveedor", "Proveedor X", this._EntradaTeclado));
        nuevoItem.setCantidad (Integer.parseInt(Layout.obtenerValorParaCampo("Cantidad", "0", this._EntradaTeclado)));
        nuevoItem.setPrecio (Integer.parseInt(Layout.obtenerValorParaCampo("precio", "0", this._EntradaTeclado)));
     
       
        this._InvModel.insertProdItem(nuevoItem);
        this._MiInventario = this._InvModel.getItems(true);
        
        Layout.printSeparator();
        System.out.println(this._MiInventario.size());
    }
    
    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Producto");
        columnas.add("Proveedor");
        columnas.add("Cantidad");
        columnas.add("Precio");
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(7);
        anchos.add(20);
        anchos.add(20);
        anchos.add(14);
        anchos.add(14);
        
        
        // |1234567890|123456789012345|
        try {
            //Imprimir encabezado
            Layout.imprimirEnColumna(columnas, anchos, "|");
            Layout.printSeparator();
            for(int i = 0; i< this._MiInventario.size(); i++){
                columnas = ((Items) this._MiInventario.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "|");
            }
            
        } catch(Exception ex) {
            System.err.println("Error al imprimir " + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));
        Items selectProducto = null;

        selectProducto = this._InvModel.getProdItemById(selectedId);
        if (selectProducto == null ) {
            System.out.println("Registro solicitado no existe!!!");
        } else {
            selectProducto.setProducto(Layout.obtenerValorParaCampo("Nombre del Producto", selectProducto.getProducto(), this._EntradaTeclado));
            selectProducto.setProveedor(Layout.obtenerValorParaCampo("Nombre del Proveedor", selectProducto.getProveedor(), this._EntradaTeclado));
            selectProducto.setCantidad(Integer.parseInt(Layout.obtenerValorParaCampo("Cantidad", Integer.toString(selectProducto.getCantidad()), this._EntradaTeclado)));
            selectProducto.setCantidad(Integer.parseInt(Layout.obtenerValorParaCampo("Precio", Integer.toString(selectProducto.getCantidad()), this._EntradaTeclado)));
            
            this._InvModel.updateProdItem(selectProducto);
            this._MiInventario = this._InvModel.getItems(true);
            Layout.printSeparator();
            System.out.println("Registro Actualizado Satisfactoriamente!!!");
        }
        
    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        int selectedId = Integer.valueOf(Layout.obtenerValorParaCampo("Ingrese Codigo Registro", "0", this._EntradaTeclado));

        Items selectedMusic = this._InvModel.getProdItemById(selectedId);
        if (selectedMusic != null){
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorParaCampo("¿Desea Eliminar este Registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")){
                //this._MiInventario.remove(encontradoEnIndice);
                this._InvModel.deleteProdItem(selectedMusic);
                 this._MiInventario = this._InvModel.getItems(true);
                Layout.printSeparator();
                System.out.println("Registro Eliminado Satisfactoriamente!!!");
            }
        } else {
            System.out.println("Registro solicitado no existe!!!");
        }
    
    }
}
