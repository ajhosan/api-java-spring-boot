package testgliapi.technicaltest.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testgliapi.technicaltest.UserRoleModel;
import testgliapi.technicaltest.dtos.UserMasterDTO;
import testgliapi.technicaltest.dtos.UserRoleDTO;
import testgliapi.technicaltest.repositories.UserRoleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/userrole")
public class UserRoleController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    ModelMapper modelMapper = new ModelMapper();

    private UserRoleModel convertRoleToEntity(UserRoleDTO userRoleDTO){
        return modelMapper.map(userRoleDTO, UserRoleModel.class);
    }

    private UserRoleDTO convertRoleToDTO(UserRoleModel userRoleModel){
        return modelMapper.map(userRoleModel, UserRoleDTO.class);
    }

    @PostMapping("/create")
    public Map<String, Object> createUserRole(@RequestBody UserRoleDTO userRoleDTO){
        Map<String, Object> mapResult = new HashMap<>();

        UserRoleModel userRoleModel = convertRoleToEntity(userRoleDTO);

        if(userRoleDTO.getNamaRole() != "namaRole"){
            mapResult.put("message", "Gagal");
        }else{
            userRoleModel.setNamaRole(userRoleDTO.getNamaRole());

            mapResult.put("message", "Create Role Success");
            mapResult.put("data", userRoleRepository.save(userRoleModel));
        }

        return mapResult;
    }

    @GetMapping("/get/all")
    public Map<String, Object> getAllRole(){
        Map<String, Object> mapResult = new HashMap<>();
        List<UserRoleDTO> listUserRoleDto = new ArrayList<>();

        for(UserRoleModel userRoleModel : userRoleRepository.findAll()){
            UserRoleDTO userRoleDTO = convertRoleToDTO(userRoleModel);
            listUserRoleDto.add(userRoleDTO);
        }

        String message;
        if(listUserRoleDto.isEmpty()){
            message = "Data is empty";
        }else{
            message = "Show all data";
        }

        mapResult.put("message", message);
        mapResult.put("data", listUserRoleDto);
        mapResult.put("total", listUserRoleDto.size());

        return mapResult;
    }
}
