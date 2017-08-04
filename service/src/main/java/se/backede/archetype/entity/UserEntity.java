/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.backede.archetype.entity;

import com.negod.generics.persistence.entity.GenericEntity;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
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
@Table(name = EntityConstants.USER)
@Entity
@XmlRootElement(name = EntityConstants.USER)
@XmlAccessorType(XmlAccessType.NONE)
@Getter
@Setter
@Indexed
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = EntityConstants.USER)
@AnalyzerDef(name = "user_customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        })
public class UserEntity extends GenericEntity {

    @Analyzer(definition = "user_customanalyzer")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "name", insertable = true, unique = true)
    @XmlElement
    private String name;

    @ContainedIn
    @IndexedEmbedded(depth = 1)
    @ManyToMany(mappedBy = "users", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user.services")
    private Set<ServiceEntity> services;

}
