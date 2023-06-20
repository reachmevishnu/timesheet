package com.mphasis.timesheetlogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
    joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")})
    private List<Role> roles=new ArrayList<>();
   /* public void setRoles(Set<Role> roles)
    {
        this.roles=roles;
    }
    public Set<Role> getRoles()
    {
        return roles;
    }
    public void addRole(Role role){
        this.roles.add(role);
    }
*/
}
