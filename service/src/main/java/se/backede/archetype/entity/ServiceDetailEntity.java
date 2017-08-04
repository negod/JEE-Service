/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.entity;

import com.negod.generics.persistence.entity.GenericEntity;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import se.backede.archetype.entity.constants.EntityConstants;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Table(name = EntityConstants.SERVICE_DETAIL)
@Entity
@XmlRootElement(name = EntityConstants.SERVICE_DETAIL)
@XmlAccessorType(XmlAccessType.NONE)
@Getter
@Setter
@Indexed
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = EntityConstants.SERVICE_DETAIL)
@AnalyzerDef(name = "service_detail_customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        })
public class ServiceDetailEntity extends GenericEntity {

    @Analyzer(definition = "service_detail_customanalyzer")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "name", insertable = true, unique = true)
    @XmlElement
    private String name;

    @IndexedEmbedded(depth = 1)
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = false)
    @PrimaryKeyJoinColumn
    @ContainedIn
    private ServiceEntity service;

    @PrePersist
    @Override
    protected void onCreate() {
        if (service != null) {
            super.setId(service.getId());
        }
    }

}
