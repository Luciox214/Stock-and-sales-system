package totonitos.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totonitos.stock.entities.Producto;
import totonitos.stock.services.ProductoServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductoController {
    @Autowired
    private ProductoServiceImpl productoService;

    @PostMapping("/productos")
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto){
        return productoService.crearProducto(producto);
    }

    @GetMapping("/productos")
    public List<Producto> obtenerTodosLosProductos(){
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoService.obtenerProductoPorId(id);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }
}