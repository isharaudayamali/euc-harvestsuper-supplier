package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.EmpstatusDao;
import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.entity.Empstatus;
import lk.earth.earthuniversity.entity.Item;
import lk.earth.earthuniversity.entity.Supplier;
import lk.earth.earthuniversity.entity.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDao supplierDao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Supplier> get() {

        List<Supplier> suppliers = this.supplierDao.findAllNameId();

        suppliers = suppliers.stream().map(
                supplier -> { Supplier su = new Supplier();
                    su.setId(supplier.getId());
                    su.setName(supplier.getName());
                    su.setCode(supplier.getCode());
                    return su; }
        ).collect(Collectors.toList());

        return suppliers;

    }

    @GetMapping(produces = "application/json")
    public List<Supplier> get (@RequestParam HashMap<String,String> params){

        List<Supplier> suppliers = this.supplierDao.findAll();

        if(params.isEmpty()) return suppliers;

        String name = params.get("name");
        String code = params.get("code");
        String supplierstatusid = params.get("supplierstatusid");

        Stream<Supplier> suStream = suppliers.stream();

        if (name!=null) suStream = suStream.filter(su->su.getName().equalsIgnoreCase(name));
        if (code!=null) suStream = suStream.filter(su->su.getCode().equals(code));
//        if (supplierstatusid!=null) suStream = suStream.filter(i->i.getSupplierstatus().getId()==Integer.parseInt(supplierstatusid));

        return suStream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Supplier supplier){

        HashMap<String,String> responce = new HashMap<>();

        String errors="";

        if(supplierDao.findByCode(supplier.getCode())!=null)
            errors = errors+"<br> Existing Code";

        for(Supply sp:supplier.getSupplies()) sp.setSupplier(supplier);

        if(errors=="")
            supplierDao.save(supplier);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(supplier.getId()));
        responce.put("url","/items/"+supplier.getId());
        responce.put("errors",errors);

        return responce;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Supplier supplier){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Supplier sup1 = supplierDao.findByCode(supplier.getCode());
        Supplier sup2 = supplierDao.findByName(supplier.getName());

        if(sup1!=null && supplier.getId()!=sup1.getId())
            errors = errors+"<br> Existing Code";

        if(sup2!=null && supplier.getId()!=sup2.getId())
            errors = errors+"<br> Existing Name";

        for (Supply sp:supplier.getSupplies()) sp.setSupplier(supplier);

        // If no validation errors, proceed to save
        if (errors.equals("")) {
            // Save the supplier first
            supplierDao.save(supplier);


        } else {
            errors = "Server Validation Errors : <br> " + errors;
        }

        responce.put("id",String.valueOf(supplier.getId()));
        responce.put("url","/suppliers/"+supplier.getId());
        responce.put("errors",errors);

        return responce;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Supplier sup = supplierDao.findByMyId(id);

        if(sup==null)
            errors = errors+"<br> Item Does Not Existed";

        if(errors.isEmpty()) supplierDao.delete(sup);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/items/"+id);
        responce.put("errors",errors);

        return responce;
    }
}


