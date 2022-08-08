package com.example.ApiLaptops.controllers;

import com.example.ApiLaptops.entities.Laptop;
import com.example.ApiLaptops.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    // Atributos
    private LaptopRepository laptopRepository;

    // Contructores
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    // Métodos
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    // Buscar todos los ordenadores (lista de ordenadores)

    /**
     * http://localhost:8082/api/laptops
     *
     * @return
     */
    @GetMapping("/api/laptops")
    @ApiOperation("REST request for getting all the laptops in the DB.")
    public List<Laptop> findAll() {
        log.info("REST request for getting all the laptops in the DB.");

        // Recuperar y devolver los ordenadores de la BBDD
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    @ApiOperation("REST request for getting a laptop by ID.")
    public ResponseEntity<Laptop> findOneById (@ApiParam("Clave primaria del ordenador") @PathVariable Long id){
        log.info("REST request for getting a laptop by ID.");

        //Este método devuelve un libro por id de la BBDD y proporciona mensajes de éxito o error NOT FOUND.
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        return laptopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        /* Esta expresión de una línea es lo mismo que hacer:
        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }
        else{
            return ResponseEntity.notFound().build();
        } */
    }

    // Almacenar un nuevo ordenador en la BBDD
    @PostMapping("/api/laptops")
    @ApiOperation("REST Request for introducing a laptop in the DB.")
    public ResponseEntity<Laptop> create(@ApiParam("Datos del ordenador a crear")
                                             @RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        log.info("REST Request for introducing a laptop in the DB.");

        System.out.println(headers.get("User-Agent"));

        if(laptop.getId() != null){
            log.warn("Trying to create a laptop with an existing ID.");
            System.out.println("Trying to create a laptop with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        // Guardar el ordenador recibido por parámetro en la BBDD
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    @ApiOperation("REST request for updating a laptop in the DB.")
    public ResponseEntity<Laptop> update(@ApiParam("Datos para modificar") @RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        log.info("REST request for updating a laptop in the DB.");

        if(laptop.getId() == null){
            log.warn("Trying to update a non existent laptop.");
            return ResponseEntity.badRequest().build();
        }

        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a non existent laptop.");
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("api/laptops/{id}")
    @ApiIgnore
    @ApiOperation("REST request for deleting a laptop by ID.")
    public ResponseEntity<Laptop> delete(@ApiParam("Clave primaria del ordenador.") @PathVariable Long id){
        log.info("REST request for deleting a laptop by ID.");

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a non existent laptop.");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("api/laptops")
    @ApiIgnore
    @ApiOperation("REST request for deleting all the laptops in the DB.")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST request for deleting all the laptops in the DB.");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}