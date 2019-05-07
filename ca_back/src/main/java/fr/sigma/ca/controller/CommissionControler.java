package fr.sigma.ca.controller;

import fr.sigma.ca.dto.CommissionDTO;
import fr.sigma.ca.service.CommissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/commissions")
public class CommissionControler {

    @Autowired
    private CommissionService commissionService;

    @GetMapping()
    public ResponseEntity<List<CommissionDTO>> getCommissions(@RequestParam(value = "nomCourt", required=false) String nomCourt,
                                                              @RequestParam(value = "date", required=false) @DateTimeFormat(pattern="MM/yyyy") Date date){
        if (StringUtils.isEmpty(nomCourt) && StringUtils.isEmpty(date)){
            return ResponseEntity.ok(commissionService.findAll());
        }else if (StringUtils.isEmpty(date)){
            return ResponseEntity.ok(commissionService.find(nomCourt));
        }
        else if (StringUtils.isEmpty(nomCourt)){
            return ResponseEntity.ok(commissionService.find(date));
        } else{
            return ResponseEntity.ok(commissionService.find(nomCourt, date));
        }
    }


    @GetMapping(value = "/count")
    public ResponseEntity<Long> countCommissions(){
        return ResponseEntity.ok(commissionService.countAll());
    }

    @GetMapping(value = "/adresses")
    public ResponseEntity<List<Object>> getCommissionsAdresses(){
        return ResponseEntity.ok(commissionService.findCommissionWithAdresse());
    }
}
