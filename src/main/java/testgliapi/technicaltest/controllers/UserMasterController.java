package testgliapi.technicaltest.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import testgliapi.technicaltest.UserMasterModel;
import testgliapi.technicaltest.dtos.UserMasterDTO;
import testgliapi.technicaltest.repositories.UserMasterRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserMasterController {

    @Autowired
    private UserMasterRepository userMasterRepository;

    ModelMapper modelMapper = new ModelMapper();


    private UserMasterModel convertUserToEntity(UserMasterDTO userMasterDTO){
        return modelMapper.map(userMasterDTO, UserMasterModel.class);
    }

    private UserMasterDTO convertUserToDTO (UserMasterModel userMasterModel){
        return modelMapper.map(userMasterModel, UserMasterDTO.class);
    }

    //    API Create User
    @PostMapping("/usermaster/create")
    public Map<String, Object> createUserMaster(@RequestBody UserMasterDTO userMasterDTO){
        Map<String, Object> mapResult = new HashMap<>();

        UserMasterModel userMasterModel = convertUserToEntity(userMasterDTO);

        userMasterModel.setUserName(userMasterDTO.getUserName());
        userMasterModel.setUserJenisKelamin(userMasterDTO.getUserJenisKelamin());
        userMasterModel.setUserAlamat(userMasterDTO.getUserAlamat());
        userMasterModel.setUserEmail(userMasterDTO.getUserEmail());
        userMasterModel.setUserRoleId(userMasterDTO.getUserRoleId());


        mapResult.put("message", "Create Success");
        mapResult.put("data", userMasterRepository.save(userMasterModel));

        return mapResult;
    }

    //    API READ User
    @GetMapping("/usermaster/get/all")
    public Map<String, Object> getAllUserMaster(){
        Map<String, Object> mapResult = new HashMap<>();
        List<testgliapi.technicaltest.dtos.UserMasterDTO> listUserMasterDto = new ArrayList<>();

        for(UserMasterModel userMasterModel : userMasterRepository.findAll()){
            UserMasterDTO userMasterDto = convertUserToDTO(userMasterModel);
            listUserMasterDto.add(userMasterDto);
        }

        String message;
        if(listUserMasterDto.isEmpty()){
            message = "Data is empty";
        }else{
            message = "Show all data";
        }

        mapResult.put("message", message);
        mapResult.put("data", listUserMasterDto);
        mapResult.put("total", listUserMasterDto.size());

        return mapResult;
    }

    //    API UPDATE User
    @PutMapping("/usermaster/update")
    public Map<String, Object> updateUserMaster (@RequestParam(value="idUser") long idUser, @RequestBody UserMasterDTO userMasterDto){
        Map<String, Object> mapResult = new HashMap<>();

        UserMasterModel userMasterModel = userMasterRepository.findById(idUser).orElse(null);

        if (userMasterModel == null){
            mapResult.put("message", "User tidak ditemukan, update gagal!");
        }else{
            userMasterModel.setUserName(userMasterDto.getUserName());
            userMasterModel.setUserJenisKelamin(userMasterDto.getUserJenisKelamin());
            userMasterModel.setUserAlamat(userMasterDto.getUserAlamat());
            userMasterModel.setUserEmail(userMasterDto.getUserEmail());
            userMasterModel.setUserRoleId(userMasterDto.getUserRoleId());

            mapResult.put("message", "Update Success");
            mapResult.put("data", userMasterRepository.save(userMasterModel));
        }

        return mapResult;
    }

    //    API DELETE User
    @DeleteMapping("/usermaster/delete/{idUser}")
    public Map<String, Object> deleteUserMaster (@PathVariable(value="idUser") long idUser){
        Map<String, Object> mapResult = new HashMap<>();

        UserMasterModel userMasterModel = userMasterRepository.findById(idUser).orElse(null);

        if(userMasterModel == null){
            mapResult.put("message", "Id tidak ditemukan, delete gagal");
        }else{
            userMasterRepository.delete(userMasterModel);
            mapResult.put("message", "Delete Succecss");
        }

        return mapResult;
    }
}
