package com.scriptlte.gopandas.security.dao.relation;

import com.scriptlte.gopandas.security.pojo.relation.OrgRel_X_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface OrgRel_X_RoleRepository extends JpaRepository<OrgRel_X_Role, String> {

    @Query(value="select roleId from OrgRel_X_Role xr where xr.relObjectId In (:relObjIds) ")
    List<String> queryRoleIdsByRelObjectIds(@Param("relObjIds")List<String> relObjIds);
}