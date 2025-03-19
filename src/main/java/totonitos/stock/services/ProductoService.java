package totonitos.stock.services;

import org.springframework.http.ResponseEntity;
import totonitos.stock.entities.Producto;

import java.util.List;

public interface ProductoService {
    ResponseEntity crearProducto(Producto producto);
    List<Producto> obtenerTodosLosProductos();
    Producto obtenerProductoPorId(Long id);
    ResponseEntity actualizarProducto(Long id, Producto producto);
    ResponseEntity eliminarProducto(Long id);
}