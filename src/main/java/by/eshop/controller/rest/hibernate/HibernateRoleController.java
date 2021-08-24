package by.eshop.controller.rest.hibernate;

import by.eshop.controller.requests.SaveRoleRequest;
import by.eshop.domain.hibernate.HibernateRole;
import by.eshop.repository.hibernate.HibernateRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hibernate/role")
@RequiredArgsConstructor
public class HibernateRoleController {

    private final HibernateRoleRepository roleRepository;

    @GetMapping
    public List<HibernateRole> findAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public HibernateRole findOne(@PathVariable Long id){
        return roleRepository.findOne(id);
    }

    @PostMapping
    public HibernateRole save(@RequestBody SaveRoleRequest request){
        HibernateRole creatRole = new HibernateRole();
        creatRole.setRoleName(request.getRoleName());
        return roleRepository.save(creatRole);
    }
}
