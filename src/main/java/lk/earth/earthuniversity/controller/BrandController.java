package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.BrandDao;
import lk.earth.earthuniversity.entity.Brand;
import lk.earth.earthuniversity.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandDao branddao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Brand> get(@RequestParam HashMap<String,String > params) {

        List<Brand> brands = this.branddao.findAll();

        if(params.isEmpty()) return brands;

        String categoryid = params.get("categoryid");

        if(categoryid!=null) brands=this.branddao.findBrandByCategory(Integer.parseInt(categoryid));
        return brands;

    }

}