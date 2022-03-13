package testgliapi.technicaltest;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="tb_master_user", schema="public")
public class UserMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_id_user_seq")
    @SequenceGenerator(name="generator_id_user_seq", sequenceName = "id_user_seq", schema = "public", allocationSize = 1)
    private long idUser;

    @Column(name ="nama_user")
    private String userName;

    @Column(name="jenis_kelamin")
    private String userJenisKelamin;

    @Column(name="alamat")
    private String userAlamat;

    @Column(name="email")
    private String userEmail;

    @Column(name="role_id")
    private String userRoleId;
}
