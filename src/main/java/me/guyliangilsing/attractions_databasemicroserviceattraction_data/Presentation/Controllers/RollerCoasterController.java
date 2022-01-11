package me.guyliangilsing.attractions_databasemicroserviceattraction_data.Presentation.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.RollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Models.SimpleRollerCoaster;
import me.guyliangilsing.attractions_databasemicroserviceattraction_data.Logic.Services.RollerCoasterService;

@RestController
@RequestMapping(path = "/rollercoaster")
public class RollerCoasterController
{
    private final RollerCoasterService rollerCoasterService;

    @Autowired
    public RollerCoasterController(RollerCoasterService rollerCoasterSerivce)
    {
        this.rollerCoasterService = rollerCoasterSerivce;
    }

    @GetMapping
    public List<SimpleRollerCoaster> getAll()
    {
        return this.rollerCoasterService.getAll();
    }

    @GetMapping(path = "/search")
    public List<SimpleRollerCoaster> search(
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String park
    )
    {
        return this.rollerCoasterService.search(name, park);
    }

    @GetMapping(path = "/{rollercoasterId}")
    public RollerCoaster get(@PathVariable Long rollercoasterId) throws NotFoundException
    {
        return this.rollerCoasterService.getByID(rollercoasterId);
    }

    @PostMapping
    public RollerCoaster create(@RequestBody RollerCoaster rollerCoaster)
    {
        rollerCoaster.setId(null);
        
        return this.rollerCoasterService.create(rollerCoaster);
    }

    @PutMapping(path = "/{rollercoasterId}")
    public RollerCoaster update(@PathVariable Long rollercoasterId, @RequestBody RollerCoaster rollerCoaster) throws NotFoundException
    {
        rollerCoaster.setId(rollercoasterId);
        
        return this.rollerCoasterService.update(rollerCoaster);
    }

    @DeleteMapping(path = "/{rollercoasterId}")
    public void delete(@PathVariable Long rollercoasterId) throws NotFoundException
    {
        this.rollerCoasterService.delete(rollercoasterId);
    }
}
