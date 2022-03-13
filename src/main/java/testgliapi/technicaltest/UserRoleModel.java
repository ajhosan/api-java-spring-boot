package testgliapi.technicaltest;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_master_user_role", schema = "public")
public class UserRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_id_role_seq")
    @SequenceGenerator(name = "generator_id_role_seq", sequenceName = "id_role_seq", schema = "public", allocationSize = 1)
    private long idRole;

    @Column(name = "nama_role")
    private String namaRole;

}
