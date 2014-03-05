package com.tynja.stubit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * @author Tommy Tynj&auml;
 */
@Entity
public class JpaEntityWithInterfaceTypeProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private List relations;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List getRelations() {
        return relations;
    }

    public void setRelations(final List relations) {
        this.relations = relations;
    }
}
