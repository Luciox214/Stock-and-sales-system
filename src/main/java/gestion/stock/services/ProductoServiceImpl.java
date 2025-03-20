package gestion.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import gestion.stock.entities.Producto;
import gestion.stock.repositories.ProductoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepo productoRepository;

    @Override
    public ResponseEntity crearProducto(Producto producto) {
        productoRepository.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity actualizarProducto(Long id, Producto producto) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isEmpty()) {
            return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        }
        Producto productoExistente = productoOpt.get();
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setPrecio(producto.getPrecio());
        productoRepository.save(productoExistente);
        return new ResponseEntity<>(productoExistente, HttpStatus.OK);
    }

    @Override
    public ResponseEntity eliminarProducto(Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isEmpty()) {
            return new ResponseEntity<>("Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
        }
        productoRepository.deleteById(id);
        return new ResponseEntity<>("Producto eliminado con éxito", HttpStatus.OK);
    }
}