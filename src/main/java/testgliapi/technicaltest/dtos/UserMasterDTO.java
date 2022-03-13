package testgliapi.technicaltest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserMasterDTO {

    private long idUser;

    private String userName;

    private String userJenisKelamin;

    private String userAlamat;

    private String userEmail;

    private String userRoleId;
}
