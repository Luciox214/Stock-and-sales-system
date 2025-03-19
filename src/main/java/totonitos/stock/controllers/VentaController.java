package totonitos.stock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totonitos.stock.entities.Venta;
import totonitos.stock.services.VentaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class VentaController {
    @Autowired
    private VentaServiceImpl ventaService;

    @PostMapping("/ventas")
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta) {
        log.info("Request: {}", venta);
        ResponseEntity<?> response = ventaService.crearVenta(venta);
        log.info("Response: {}", response);
        return response;
    }

    @GetMapping("/ventas")
    public List<Venta> obtenerVentas() {
        return ventaService.obtenerVentas();
    }

    @GetMapping("/ventas/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        if (venta == null) {
            return ResponseEntity.status(404).body("Venta no encontrada con ID: " + id);
        }
        return ResponseEntity.ok(venta);
    }

    @PutMapping("/ventas/{id}")
    public ResponseEntity<?> actualizarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        ResponseEntity<?> response = ventaService.actualizarVenta(id, venta);
        return response;
    }

    @DeleteMapping("/ventas/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {
        ResponseEntity<?> response = ventaService.eliminarVenta(id);
        return response;
    }
}