package main.controlador;

import main.modelos.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.servicio.ProveedorServicio;

import java.util.List;

@RestController
public class ProveedorControlador {

    @Autowired
    private ProveedorServicio servicio;


    //LISTAR PROVEEDORES
    @CrossOrigin(allowedHeaders = {"Authorization", "Origin"})
    @GetMapping("/proveedores")
    public List<Proveedor> listarProveedores()
    {
        return servicio.listarProveedores();
    }


    //BUSCAR PROVEEDOR POR ID
    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> obtenerProveedor(@PathVariable Integer id)
    {
        try {
            Proveedor proveedor = servicio.obtenerProveedorPorId(id);
            return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<Proveedor>(HttpStatus.NOT_FOUND);
        }
    }

    //ALTA PROVEEDOR
    @PostMapping("/proveedores")
    public void registrarProveedor(@RequestBody Proveedor proveedor)
    {
        servicio.guardarProveedor(proveedor);
    }

    //ACTUALIZAR PROVEEDOR
    @PutMapping("proveedor/{id}")
    public ResponseEntity<?> actualizarProveedor(@RequestBody Proveedor proveedor, @PathVariable Integer id)
    {
        try {
            Proveedor proveedorExistente = servicio.obtenerProveedorPorId(id);

            proveedorExistente.setNombre(proveedor.getNombre());
            proveedorExistente.setTelefono(proveedor.getTelefono());
            proveedorExistente.setDireccion(proveedor.getDireccion());
            proveedorExistente.setLatitud(proveedor.getLatitud());
            proveedorExistente.setLongitud(proveedor.getLongitud());

            servicio.guardarProveedor(proveedorExistente);
            return new ResponseEntity<Proveedor>(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<Proveedor>(HttpStatus.NOT_FOUND);
        }
    }

    //ELIMINAR PROVEEDOR
    @DeleteMapping("/proveedores/{id}")
    public void eliminarProveedor(@PathVariable Integer id)
    {
        servicio.eliminarProveedor(id);
    }



}
