package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.EmpstatusDao;
import lk.earth.earthuniversity.dao.SuppliertypeDao;
import lk.earth.earthuniversity.entity.Empstatus;
import lk.earth.earthuniversity.entity.Suppliertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/suppliertypes")
public class SuppliertypeController {

    @Autowired
    private SuppliertypeDao suppliertypedao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Suppliertype> get() {

        List<Suppliertype> suppliertypes = this.suppliertypedao.findAll();

        suppliertypes = suppliertypes.stream().map(
                suppliertype -> { Suppliertype s = new Suppliertype();
                    s.setId(suppliertype.getId());
                    s.setName(suppliertype.getName());
                    return s; }
        ).collect(Collectors.toList());

        return suppliertypes;

    }

}


