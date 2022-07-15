/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.Vista;

/**
 *
 * @author carlos
 */
public class Controlador implements ActionListener {
    PersonaDAO dao = new PersonaDAO();
    Persona persona = new Persona();
    Vista vista = new Vista();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador(Vista vista){
        this.vista = vista;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnListar){
            limpiarTabla();
            listar(vista.table);
        }else if(e.getSource()==vista.btnGuardar)
        {
            agregar();
            limpiarTabla();
            listar(vista.table);
        }else if(e.getSource()==vista.btnEditar)
        {
            int fila = vista.table.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                int id=Integer.parseInt((String)vista.table.getValueAt(fila, 0).toString());
                String nom = (String)vista.table.getValueAt(fila, 1);
                String correo = (String)vista.table.getValueAt(fila, 2);
                String tel = (String)vista.table.getValueAt(fila, 3);
                vista.txtId.setText(""+id);
                vista.txtNombres.setText(nom);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(tel);
            }
        }else if(e.getSource()==vista.btnActualizar)
        {
            actualizar();
            limpiarTabla();
            listar(vista.table);
        }else if(e.getSource()==vista.btnEliminar)
        {
            int fila = vista.table.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                int id = Integer.parseInt((String)vista.table.getValueAt(fila, 0).toString());
                dao.eliminar(id);
                limpiarTabla();
                listar(vista.table);
            }
        }
    }
    
    public void limpiarTabla(){
        for(int i=0; i<vista.table.getRowCount();i++){
            modelo.removeRow(i);
            i=i-1;
        }
    }
    
    public void listar(JTable table){
        
        modelo = (DefaultTableModel)table.getModel();
        ArrayList<Persona>lista = dao.listar();
        Object[]object=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getId();
            System.out.print(lista.get(i).getNombre());
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getEmail();
            object[3]=lista.get(i).getTelefono();
            modelo.addRow(object);
        }
        vista.table.setModel(modelo);
    }
    
    public void agregar(){
        String nom = vista.txtNombres.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTelefono.getText();
        
        persona.setNombre(nom);
        persona.setEmail(correo);
        persona.setTelefono(tel);
        
        int r = dao.agregar(persona);
        if (r == 1){
            JOptionPane.showMessageDialog(vista, "Usuario agregado");
        }else{
            JOptionPane.showMessageDialog(vista, "Ocurrio un error");
        }
    }
    
    public void actualizar(){
        int id = Integer.parseInt(vista.txtId.getText());
        String nom = vista.txtNombres.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTelefono.getText();
        persona.setId(id);
        persona.setNombre(nom);
        persona.setEmail(correo);
        persona.setTelefono(tel);
        int r = dao.actualizar(persona);
        if (r == 1){
            JOptionPane.showMessageDialog(vista, "Usuario actualizado");
        }else{
            JOptionPane.showMessageDialog(vista, "Ocurrio un error");
        }
    }
}
